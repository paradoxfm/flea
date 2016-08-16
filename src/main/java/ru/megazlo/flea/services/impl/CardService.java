package ru.megazlo.flea.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.megazlo.flea.components.UserAccessor;
import ru.megazlo.flea.entity.Card;
import ru.megazlo.flea.services.ICardService;

import java.util.List;

/**
 * @author paradoxfm - 21.01.2016
 */
@Service
public class CardService implements ICardService {

    /*@Autowired
    private CardRepository cardRepository;*/

    @Autowired
    private UserAccessor userAccessor;

    @Override
    public List<Card> getMyCards() {
        return null;// cardRepository.findMyCards(userAccessor.getUser().getUsername());
    }

    @Override
    public List<Card> getCardsContacts() {
        return null;
    }

    @Override
    public List<Card> getCardsContactsNew() {
        return null;
    }

    @Override
    public Card getCardById(Long id) {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public Card saveUpdate(Card card) {
        throw new UnsupportedOperationException("Метод не реализован");
    }
}
