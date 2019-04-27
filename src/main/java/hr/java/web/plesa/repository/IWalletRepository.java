package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Wallet;

public interface IWalletRepository {

    Iterable<Wallet> findAll();

    Wallet findOne(String username);

    Wallet save(Wallet wallet, String username);

}
