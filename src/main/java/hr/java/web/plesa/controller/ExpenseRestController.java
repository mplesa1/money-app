package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/expense", produces="application/json")
@CrossOrigin
public class ExpenseRestController {
    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;

    public ExpenseRestController(ExpenseRepository expenseRepository, WalletRepository walletRepository) {
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

        @GetMapping
        public Iterable<Expense> findAll() {

            var expenses = expenseRepository.findAll();

            return expenses;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Expense> findOne(@PathVariable Long id) {
            var expense = expenseRepository.findOne(id);

            if (expense == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(expense, HttpStatus.OK);
            }
        }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(path = "/{walletId}", consumes="application/json")
        public Expense save(@RequestBody Expense expense, @PathVariable Long walletId) {
            var newExpense = expenseRepository.save(expense, walletId);
            return newExpense;
        }

        @PutMapping("/{id}/{walletId}")
        public Expense update(@RequestBody Expense expense, @PathVariable Long id, @PathVariable Long walletId) {
            var expenseCheck = expenseRepository.findOne(id);
            if (expenseCheck == null){
                return null;
            }else {
                expenseRepository.update(expense, walletId);
                return expense;
            }
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            var expense = expenseRepository.findOne(id);
            if (expense != null){
                expenseRepository.delete(expense);
            }
        }
}
