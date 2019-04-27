package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Wallet;
import hr.java.web.plesa.repository.IExpenseRepository;
import hr.java.web.plesa.repository.IWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/expense")
@SessionAttributes({"types"})
public class ExpenseController {

    private IExpenseRepository expenseRepository;
    private IWalletRepository walletRepository;

    public ExpenseController(IExpenseRepository expenseRepository, IWalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    @GetMapping("/newExpense")
    public String showForm(Model model) {

        String username = getUserName();
        Wallet wallet;
        try {
            wallet = walletRepository.findOne(username);
        } catch (Exception e) {

            wallet = new Wallet();
            wallet.setType(Wallet.WalletType.CASH);

            wallet = walletRepository.save(wallet);
        }


        model.addAttribute("expense", new Expense());
        model.addAttribute("types", Expense.ExpenseType.values());
        return "newExpense";
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/newExpense")
    public String processForm(@Validated Expense expense, Errors errors, Model model, HttpSession session) {

        if (errors.hasErrors()) {
            log.info("Model ima errore.");
            return "newExpense";
        }

        var wallet = walletRepository.findOne(getUserName());
        var walletID = wallet.getId();

        // dodavanje expense u bazu
        expenseRepository.save(expense, walletID);

        // dodavanje expensea u model
        //model.addAttribute("expense", expense);

        // dohvaćanje iz baze svih troškova i računanje totala
        var expenseList = new ArrayList<Expense>();
        expenseRepository.findAllByWalletId(walletID).iterator().forEachRemaining(
                e -> expenseList.add(e)
        );
        var expenses = expenseRepository.findAllByWalletId(walletID);
        var total = expenseList.stream().map(e -> e.getAmount()).reduce((e1, e2) -> e1.add(e2)).get();

        model.addAttribute("expenses", expenses);
        model.addAttribute("total", total);
        return "expenses";
    }

    @GetMapping("/resetWallet")
    public String resetWallet() {

        var walletID = walletRepository.findOne(getUserName()).getId();

        expenseRepository.removeExpensesFromWallet(walletID);

        log.info("Resetiran wallet.");
        return "redirect:/expense/newExpense";
    }
}
