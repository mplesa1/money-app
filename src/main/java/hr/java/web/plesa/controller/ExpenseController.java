package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/expense")
@SessionAttributes({"types", "wallet"})
public class ExpenseController {

    @GetMapping("/roles")
    public String showRoles(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        model.addAttribute("roles", roles);
        return "userRoles";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("types", Expense.TypeOfExpense.values());
        return "newExpense";
    }

    @PostMapping("/new")
    public String processForm(@Validated Expense expense, Errors errors, Model model, HttpSession session) {

        if (errors.hasErrors()) {
            log.info("Model has errore.");
            return "newExpense";
        }

        var wallet = (Wallet)session.getAttribute("wallet");
        wallet.addExpense(expense);

        model.addAttribute("expenses", wallet.getExpenses());
        model.addAttribute("total", wallet.getTotal());
        return "expenses";
    }

    @GetMapping("/resetWallet")
    public String resetWallet(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        log.info("Wallet reset.");
        return "redirect:/expense/new";
    }

    // when a request comes in, the first thing Spring will do is to notice @SessionAttributes
    // and then attempt to find the value  in javax.servlet.http.HttpSession.
    // If it doesn't find the value, then the method with @ModelAttribute having the same name
    // will be invoked
    @ModelAttribute("wallet")
    public Wallet setWallet(Model model){
        log.info("New wallet.");
        return new Wallet();
    }
}
