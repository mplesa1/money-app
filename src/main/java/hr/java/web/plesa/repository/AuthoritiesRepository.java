package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Authorities;
import hr.java.web.plesa.domain.Expense;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class AuthoritiesRepository implements IAuthoritiesRepository{

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert jdbcInsert;

    public AuthoritiesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.jdbcInsert = new SimpleJdbcInsert(jdbc)
                .withTableName("authorities");
    }
    @Override
    public Iterable<Authorities> findAllWithoutCurrentUser(String username) {
        var authorites = jdbc.query("SELECT * FROM authorities WHERE username != ?",  new BeanPropertyRowMapper<>(Authorities.class), username);
        //jdbc.queryForObject("SELECT * FROM authorities WHERE username != ?", new BeanPropertyRowMapper<>(Authorities.class), username);
        return authorites;
    }

    @Override
    public Authorities save(String username) {
        Authorities authorities = new Authorities();
        authorities.setAuthority(Authorities.Authority.ROLE_ADMIN);
        authorities.setUsername(username);
        saveAuthorities(authorities);
        return authorities;
    }

    @Override
    public void removeAdminAuthorities(String username, String authority) {
       // Authorities.Authority authority1 = Authorities.Authority.valueOf(authority);
        jdbc.update("DELETE FROM authorities WHERE username = ? AND authority = ?", username, authority);
    }

    private void saveAuthorities(Authorities authorities) {
        Map<String, Object> values = new HashMap<>();
        values.put("username", authorities.getUsername());
        values.put("authority", authorities.getAuthority());

        jdbcInsert.execute(values);

    }
}
