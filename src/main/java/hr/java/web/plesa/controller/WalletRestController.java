package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Wallet;
import hr.java.web.plesa.dto.ExpenseDto;
import hr.java.web.plesa.dto.WalletDto;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/wallet", produces="application/json")
@CrossOrigin
public class WalletRestController {
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    public WalletRestController(WalletRepository walletRepository, ModelMapper modelMapper) {

        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
    }

        @GetMapping
        public Iterable<WalletDto> findAll() {

            var wallets = walletRepository.findAll();
            // Define the target type
            java.lang.reflect.Type targetListType = new TypeToken<List<ExpenseDto>>() {}.getType();
            List<WalletDto> walletDtos = modelMapper.map(wallets, targetListType);
            return walletDtos;
        }

        @GetMapping("/{id}")
        public ResponseEntity<WalletDto> findOne(@PathVariable Long id) {
            var wallet = walletRepository.findById(id);

            if (!wallet.isPresent()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(modelMapper.map(wallet.get(), WalletDto.class), HttpStatus.OK);
            }
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(consumes="application/json")
        public WalletDto save(@RequestBody Wallet wallet) {
            walletRepository.save(wallet);

            return modelMapper.map(wallet, WalletDto.class);
        }

        @PutMapping("/{id}")
        public WalletDto update(@RequestBody Wallet wallet, @PathVariable Long id) {
            var walletOptional = walletRepository.findById(id);
            var walletCheck = walletOptional.get();
            if (walletCheck == null){
                return null;
            }else {
                walletCheck.setType(wallet.getType());
                walletCheck.setType(wallet.getType());
                walletRepository.save(walletCheck);
            }

                return modelMapper.map(walletCheck, WalletDto.class);
            }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            var wallet = walletRepository.findById(id);
            if (wallet.get() != null){
                walletRepository.delete(wallet.get());
            }
        }
}
