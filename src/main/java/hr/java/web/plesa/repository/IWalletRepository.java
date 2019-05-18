package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Wallet;

import java.util.List;

public interface IWalletRepository {

    List<Wallet> findAll();

    Wallet findOne(String username);

    Wallet save(Wallet wallet);

    Wallet findById(Long id);
}
