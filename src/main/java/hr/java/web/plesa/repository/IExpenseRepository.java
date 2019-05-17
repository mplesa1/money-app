package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Expense;

import java.util.List;

public interface IExpenseRepository {

    List<Expense> findAll();

    List<Expense> findAllByWalletId(Long id);

    Expense findOne(Long id);

    Expense save(Expense expense, Long walletID);

    void removeExpensesFromWallet(Long walletID);

    void update(Expense expense);
}
