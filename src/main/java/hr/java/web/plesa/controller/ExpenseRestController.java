package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.dto.ExpenseDto;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/expense", produces="application/json")
@CrossOrigin
public class ExpenseRestController {
    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    public ExpenseRestController(ExpenseRepository expenseRepository, WalletRepository walletRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
    }

        @GetMapping
        public List<ExpenseDto> findAll() {

            var expenses = expenseRepository.findAll();
            // Define the target type
            java.lang.reflect.Type targetListType = new TypeToken<List<ExpenseDto>>() {}.getType();
            List<ExpenseDto> expenseDtos = modelMapper.map(expenses, targetListType);
            return expenseDtos;
        }

        @GetMapping("/{id}")
        public ResponseEntity<ExpenseDto> findOne(@PathVariable Long id) {
            var expense = expenseRepository.findById(id);

            if (expense == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(modelMapper.map(expense, ExpenseDto.class), HttpStatus.OK);
            }
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(path = "/{walletId}", consumes="application/json")
        public ExpenseDto save(@RequestBody Expense expense, @PathVariable Long walletId) {
            var wallet = walletRepository.findById(walletId);
            expense.setWallet(wallet.get());
            var newExpense = expenseRepository.save(expense);

            return modelMapper.map(newExpense, ExpenseDto.class);
        }

        @PutMapping("/{id}/{walletId}")
        public ExpenseDto update(@RequestBody Expense expense, @PathVariable Long id, @PathVariable Long walletId) {
            var expenseOptional = expenseRepository.findById(id);
            var expenseCheck = expenseOptional.get();
            if (expenseCheck == null){
                return null;
            }else {
                var wallet = walletRepository.findById(walletId);
                expenseCheck.setWallet(wallet.get());
                expenseCheck.setName(expense.getName());
                expenseCheck.setAmount(expense.getAmount());
                expenseCheck.setExpenseType(expense.getExpenseType());
                expenseRepository.save(expenseCheck);
                return modelMapper.map(expenseCheck, ExpenseDto.class);
            }
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            var expense = expenseRepository.findById(id);
            if (expense.get() != null){
                expenseRepository.delete(expense.get());
            }
        }
}
