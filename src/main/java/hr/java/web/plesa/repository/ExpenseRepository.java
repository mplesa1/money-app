package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findAllByWalletId(Long walletId);

    void deleteAllByWalletId(Long walletId);

    List<Expense> findAllByWalletIdInAndNameLike(Long walletId, String name);

    List<Expense> findAll();
}
