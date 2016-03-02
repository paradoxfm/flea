package ru.megazlo.flea.repositories;

import ru.megazlo.flea.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author paradoxfm - 21.01.2016
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("select c from Card c where c.owner.login = :login")
    List<Card> findMyCards(@Param("login") String login);
}
