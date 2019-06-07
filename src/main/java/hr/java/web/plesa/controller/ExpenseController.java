package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Wallet;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/expense")
@SessionAttributes({"types"})
public class ExpenseController {

    private ExpenseRepository expenseRepository;
    private WalletRepository walletRepository;

    public ExpenseController(ExpenseRepository expenseRepository, WalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    @GetMapping("/newExpense")
    public String showForm(Model model) {

        String username = getUserName();
        Wallet wallet;
        wallet = walletRepository.findByUsername(username);

       if (wallet == null) {
           wallet = new Wallet();
           wallet.setType(Wallet.WalletType.CASH);
           wallet.setUsername(username);
           walletRepository.save(wallet);
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
        var username = getUserName();
        var wallet = walletRepository.findByUsername(username);
        var walletID = wallet.getId();
        expense.setWallet(wallet);
        // dodavanje expense u bazu
        expenseRepository.save(expense);

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

        var wallet = walletRepository.findByUsername(getUserName());

        expenseRepository.deleteAllByWalletId(wallet.getId());

        log.info("Resetiran wallet.");
        return "redirect:/expense/newExpense";
    }

    @GetMapping
    public String expenseSearch(@RequestParam(value = "query", required = false) String name,
                                  Model model) {
        var wallet = walletRepository.findByUsername(getUserName());

        var expenses = expenseRepository.findAllByWalletIdInAndNameLike(wallet.getId(), name);
        if (expenses.size() == 0) {
            expenses = expenseRepository.findAllByWalletId(wallet.getId());
        }

        var total = expenses.stream().map(e -> e.getAmount()).reduce((e1, e2) -> e1.add(e2)).get();

        model.addAttribute("expenses", expenses);
        model.addAttribute("total", total);
        return "expenses";

    }
}
