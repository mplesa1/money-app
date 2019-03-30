package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("types", Expense.TypeOfExpense.values());
        return "newExpense";
    }

    @PostMapping("/new")
    public String processForm(Expense expense, Model model) {
        model.addAttribute("expense", expense);
        return "expenses";
    }
}
