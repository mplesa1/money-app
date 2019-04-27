package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Wallet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class WalletRepository implements IWalletRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert jdbcInsert;

    public WalletRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.jdbcInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("wallet")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Iterable<Wallet> findAll() {

        var wallets = jdbc.query("SELECT * FROM wallet", new BeanPropertyRowMapper(Wallet.class));

        return wallets;
    }

    @Override
    public Wallet findOne(String username) {
        return jdbc.queryForObject("SELECT * FROM wallet WHERE username = ?", new BeanPropertyRowMapper<>(Wallet.class), username);
    }

    @Override
    public Wallet save(Wallet wallet, String username) {
        wallet.setCreateDate(LocalDateTime.now());
        wallet.setId(saveAndReturnID(wallet, username));

        return wallet;
    }

    public long saveAndReturnID(Wallet wallet, String username) {
        Map<String, Object> vals = new HashMap<>();

        vals.put("username", username);
        vals.put("createDate", wallet.getCreateDate());
        vals.put("type", wallet.getType().toString());

        return jdbcInsert.executeAndReturnKey(vals).longValue();
    }

    private Wallet mapRowToWallet(ResultSet rs) throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setType(Wallet.WalletType.valueOf(rs.getString("type")));
//        wallet.setCreateDate(LocalDateTime.parse(rs.getDate("createDate")));
        wallet.setId(rs.getLong("id"));
        return wallet;
    }

}
