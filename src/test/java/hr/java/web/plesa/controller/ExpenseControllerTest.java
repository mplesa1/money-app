package hr.java.web.plesa.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerTest {
    @Autowired
    private MockMvc mockMvc;

/*    @Test
    public void testExpenseSearchWithExistingExpenseName () throws Exception {
        this.mockMvc
                .perform(get("/expense")
                        .with(user("test")
                                .password("test")
                                .roles("USER", "ADMIN")))
                .with(csrf())
                .param("name", "Hamburger")
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("expenses"))
                .andExpect(model().attribute("expenses", hasSize(1)))
                .andExpect(view().name("expenseSearch"));
    }
*/
}