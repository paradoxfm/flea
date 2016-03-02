package ru.megazlo.flea.services;

import ru.megazlo.flea.entity.Card;

import java.util.List;

/**
 * @author paradoxfm - 21.01.2016
 */
public interface ICardService {

    List<Card> getMyCards();

    List<Card> getCardsContacts();

    List<Card> getCardsContactsNew();

    Card getCardById(Long id);

    Card saveUpdate(Card card);
}
