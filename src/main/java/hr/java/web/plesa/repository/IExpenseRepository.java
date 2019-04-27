package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Expense;

public interface IExpenseRepository {

    Iterable<Expense> findAll();

    Iterable<Expense> findAllByWalletId(Long id);

    Expense findOne(Long id);

    Expense save(Expense expense, Long walletID);

    void removeExpensesFromWallet(Long walletID);
}
