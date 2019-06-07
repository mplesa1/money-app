package hr.java.web.plesa.controller;

import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/wallet")
public class WalletController {

    private ExpenseRepository expenseRepository;
    private WalletRepository walletRepository;

    public WalletController(ExpenseRepository expenseRepository, WalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    @GetMapping("/all")
    public String showForm(Model model) {

        var wallets = walletRepository.findAll();


        model.addAttribute("wallets", wallets);
        return "wallets";
    }


//    @PutMapping("/{walletId}")
//    public String update(Wallet wallet, @PathVariable(value="walletId") Long walletId){
//        var checkWallet
//    }
}
