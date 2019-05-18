package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Users;

import java.util.List;

public interface IUserRepository {
    Users save(Users user);

    void delete(Users user);

    Users findOne(Long id);

    boolean isExist(String username);
}
