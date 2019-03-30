package hr.java.web.plesa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Wallet {

    private List<Expense> expenses = new ArrayList<Expense>();

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public BigDecimal getTotal() {
        return expenses.stream()
                .map(expense -> expense.getAmount())
                .reduce((expense, expense2) -> expense.add(expense2))
                .get();
    }
}
