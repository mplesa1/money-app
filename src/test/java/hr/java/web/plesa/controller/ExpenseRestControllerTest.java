package hr.java.web.plesa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Wallet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ExpenseRestControllerTest {

    private String password = new BCryptPasswordEncoder().encode("adminpass");
    private String user = "admin";

    @Autowired
    private MockMvc mockMvc;


    private SessionFactory sessionFactory;
    private Session session = null;


    @Before
    void setUp() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Expense.class)
                .addAnnotatedClass(Wallet.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
        configuration.setProperty("username", "sa");
        configuration.setProperty("password", "");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Test
    void findOne() throws Exception {
        this.mockMvc.perform(get("/api/expense/1").with(user(this.user).password(password).roles("ADMIN", "USER")))
                .andExpect(jsonPath("$.name", is("Kava")));
    }

    @Test
    void save() throws Exception {

        Expense expense = new Expense();
        expense.setName("test");
        expense.setExpenseType(Expense.ExpenseType.FOOD);
        expense.setAmount(new BigDecimal(2));

        this.mockMvc.perform(post("/api/expense/1").with(user(this.user).password(password).roles("ADMIN", "USER"))
                .content(asJsonString(expense))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {

        Expense expense = new Expense();
        expense.setName("test");
        expense.setExpenseType(Expense.ExpenseType.FOOD);
        expense.setAmount(new BigDecimal(2));
        expense.setId(1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/expense/1/1").with(user(this.user).password(password).roles("ADMIN", "USER"))
                .content(asJsonString(expense))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/expense/1").with(user(this.user).password(password).roles("ADMIN", "USER")))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}