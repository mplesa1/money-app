package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Wallet findByUsername(String username);
}
