package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Wallet;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    @Secured("admin")
    public String getWalletsByUsername(@RequestParam(value = "username", required = false) String username,
                                Model model) {

        var wallets = walletRepository.findAllByUsername(username);
        if (wallets.size() == 0){
            wallets = walletRepository.findAll();
        }
        for (var wallet: wallets
             ) {
            wallet.setTotal();
        }
        model.addAttribute("wallets", wallets);
        return "wallets";

    }

    @GetMapping("/range")
    @Secured("admin")
    public String getWalletsRange(@RequestParam(value = "min", required = false) String min,
                                  @RequestParam(value = "max", required = false) String max,
                                       Model model) {

        var wallets = walletRepository.findAll();
        BigDecimal minBd = new BigDecimal(min);
        BigDecimal maxBd = new BigDecimal(max);


        for (var wallet: wallets
        ) {
            wallet.setTotal();
        }

        List<Wallet> walletsFiltered = wallets.stream()
                .filter(wallet -> wallet.getTotal().compareTo(minBd) > 0 && wallet.getTotal().compareTo(maxBd) < 0)
                .collect(Collectors.toList());

        model.addAttribute("wallets", walletsFiltered);
        return "wallets";

    }


//    @PutMapping("/{walletId}")
//    public String update(Wallet wallet, @PathVariable(value="walletId") Long walletId){
//        var checkWallet
//    }
}
