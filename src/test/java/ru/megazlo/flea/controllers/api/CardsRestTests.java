package ru.megazlo.flea.controllers.api;

import ru.megazlo.flea.controllers.AbstractControllerTest;
import ru.megazlo.flea.controllers.rest.CardsRest;
import ru.megazlo.flea.entity.Card;
import ru.megazlo.flea.entity.CardType;
import ru.megazlo.flea.services.ICardService;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author paradoxfm - 17.02.2016
 */
public class CardsRestTests extends AbstractControllerTest<CardsRest> {

    @Autowired
    public ICardService cardService;

    @After
    public void after() {
        Mockito.reset(cardService);
    }

    @Test
    public void getByIdTest() throws Exception {
        when(cardService.getCardById(1L)).thenReturn(createCards().get(1));
        mockMvc.perform(get("/api/cards/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("c1")))
                .andExpect(jsonPath("$.comment", is("comnt1")));
    }

    @Test
    public void findMyTest() throws Exception {
        when(cardService.getMyCards()).thenReturn(createCards());
        mockMvc.perform(get("/api/cards/my"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("c0")))
                .andExpect(jsonPath("$[0].comment", is("comnt0")))
                .andExpect(jsonPath("$[0].cardType", is("EAST")))
                .andExpect(jsonPath("$[1].name", is("c1")))
                .andExpect(jsonPath("$[1].comment", is("comnt1")));
    }

    @Test
    public void findContactsTest() throws Exception {
        when(cardService.getCardsContacts()).thenReturn(createCards());
        mockMvc.perform(get("/api/cards/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));//здесь смотрим по упрощеной схеме
    }

    @Test
    public void findContactsNewTest() throws Exception {
        when(cardService.getCardsContactsNew()).thenReturn(createCards());
        mockMvc.perform(get("/api/cards/contacts/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));//здесь смотрим по упрощеной схеме
    }

    @Test
    public void saveUpdateTest() throws Exception {
        Card card = createCards().get(0);
        when(cardService.saveUpdate(any(Card.class))).thenReturn(card);//на уровне mock сервиса происходит подмена

        mockMvc.perform(post("/api/cards/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(card))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andDo(print())
                .andExpect(jsonPath("$.name", is("c0")));
    }

    @Test
    public void saveUpdateValidationTest() throws Exception {
        Card card = createCards().get(0);
        card.setCardType(null);//обламываем валидацию
        when(cardService.saveUpdate(any(Card.class))).thenReturn(card);//на уровне mock сервиса происходит подмена

        mockMvc.perform(post("/api/cards/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(card))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].message", is("1may not be null")));
    }

    private List<Card> createCards() {
        List<Card> rez = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            Card c = new Card();
            c.setName("c" + i);
            c.setComment("comnt" + i);
            c.setCardType(CardType.EAST);
            rez.add(c);
        }
        return rez;
    }
}
