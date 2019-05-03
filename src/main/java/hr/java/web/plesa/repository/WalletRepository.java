package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Wallet;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@Transactional
public class WalletRepository implements IWalletRepository {

    private final EntityManager em;

    public WalletRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Wallet> findAll() {
        return ((Session)em.getDelegate()).createQuery("FROM Wallet").list();
    }

    @Override
    public Wallet findOne(String username) {
        var lista = ((Session)em.getDelegate())
                .createQuery("FROM Wallet WHERE username = ?1")
                .setParameter(1, username)
                .list();

        return  (Wallet) lista.get(0);
    }

    @Override
    public Wallet save(Wallet wallet) {

        ((Session)em.getDelegate()).save(wallet);

        return  wallet;
    }
}
