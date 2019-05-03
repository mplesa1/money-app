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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/wallet")
public class WalletController {

    private IExpenseRepository expenseRepository;
    private IWalletRepository walletRepository;

    public WalletController(IExpenseRepository expenseRepository, IWalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    @GetMapping("/all")
    public String showForm(Model model) {

        var wallets = walletRepository.findAll();


        model.addAttribute("wallets", wallets);
        return "wallets";
    }


    @PutMapping("/{walletId}")
    public String update(Wallet wallet, @PathVariable(value="walletId") Long walletId){
        var checkWallet
    }
}
