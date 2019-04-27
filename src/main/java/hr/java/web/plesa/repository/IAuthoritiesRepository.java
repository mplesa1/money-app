package hr.java.web.plesa.repository;

import hr.java.web.plesa.domain.Authorities;

public interface IAuthoritiesRepository {

    Iterable<Authorities> findAllWithoutCurrentUser(String username);

    Authorities save(String username);

    void removeAdminAuthorities(String username, String authority);
}
