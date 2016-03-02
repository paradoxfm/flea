package com.platifon.mycards.repositories;

import com.platifon.mycards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author paradoxfm - 21.01.2016
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    User findUserByEmail(String email);

    @Transactional(readOnly = true)
    User findUserByLogin(String login);

    @Transactional(readOnly = true)
    @Query("select u from User u join fetch u.roles where u.login = :login")
    User findUserByLoginWithRole(@Param("login") String login);
}
