package com.platifon.mycards.controllers.rest;

import com.platifon.mycards.entity.Card;
import com.platifon.mycards.services.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author iv - 10.02.2016
 */
@RestController
@RequestMapping("/api/cards")
public class CardsRest {

    @Autowired
    private ICardService cardService;

    @RequestMapping("/my")
    public Object getMyCards() {
        return cardService.getMyCards();
    }

    @RequestMapping("/{id}")
    public Object getMyCards(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @RequestMapping("/contacts")
    public Object getCardsContacts() {
        return cardService.getCardsContacts();
    }

    @RequestMapping("/contacts/new")
    public Object getCardsContactsNew() {
        return cardService.getCardsContactsNew();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Card saveCard(@Valid @RequestBody Card card) {
        return cardService.saveUpdate(card);
    }
}
