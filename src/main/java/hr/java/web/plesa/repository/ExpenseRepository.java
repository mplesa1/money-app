package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ExpenseRepository implements IExpenseRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert expenseInserter;

    public ExpenseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.expenseInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("expense")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Iterable<Expense> findAll() {
        return jdbc.query("SELECT * FROM expense", this::mapRowToExpense);
    }

    @Override
    public Iterable<Expense> findAllByWalletId(Long id) {
        return jdbc.query("SELECT * FROM expense WHERE wallet_id = " + id, this::mapRowToExpense);
    }

    @Override
    public Expense findOne(Long id) {
//        return jdbc.query("SELECT * FROM expense WHERE id = ?", this::mapRowToExpense);
        return null;
    }

    @Override
    public Expense save(Expense expense, Long walletID) {

        expense.setCreateDate( LocalDateTime.now() );
        expense.setId(saveExpense(expense, walletID));
        return expense;
    }

    @Override
    public void removeExpensesFromWallet(Long walletID) {
        jdbc.update("DELETE FROM expense WHERE wallet_id = ?", walletID);
    }

    private long saveExpense(Expense expense, Long walletID) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", expense.getName());
        values.put("expenseType", expense.getExpenseType());
        values.put("amount", expense.getAmount());
        values.put("createDate", expense.getCreateDate());
        values.put("wallet_id", walletID);

        return expenseInserter.executeAndReturnKey(values).longValue();

    }

    private Expense mapRowToExpense (ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setAmount(rs.getBigDecimal("amount"));
        expense.setExpenseType(Expense.ExpenseType.valueOf(rs.getString("expenseType")));
        expense.setName(rs.getString("name"));
        return expense;
    }
}
