package hr.java.web.plesa.repository;


import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Users;
import hr.java.web.plesa.domain.Wallet;
import org.hibernate.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Primary
@Repository
@Transactional
public class UserRepository implements IUserRepository {

    //  you can also get Hibernate's session by calling getDelegate() method
    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Users save(Users user) {
        var session = (Session) em.getDelegate();

        session.save(user);

        return user;
    }

    @Override
    public void delete(Users user) {
        var session = (Session) em.getDelegate();
        session.delete(user);
    }

    @Override
    public Users findOne(Long id) {
        return ((Session)em.getDelegate()).find(Users.class, id);
    }

    @Override
    public boolean isExist(String username) {
        var lista = ((Session)em.getDelegate())
                .createQuery("FROM Users WHERE username = ?1")
                .setParameter(1, username)
                .list();

        if(lista.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}