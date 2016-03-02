package ru.megazlo.flea.controllers.api;

import ru.megazlo.flea.controllers.AbstractControllerTest;
import ru.megazlo.flea.controllers.rest.AccountRest;
import ru.megazlo.flea.services.ICardService;
import ru.megazlo.flea.services.IUserService;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author paradoxfm - 17.02.2016
 */
public class AccountRestTest extends AbstractControllerTest<AccountRest> {

    @Autowired
    private ICardService cardService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    @After
    public void after() {
        Mockito.reset(cardService);
        Mockito.reset(userService);
        Mockito.reset(userDetailsService);
    }

    @Test
    public void registrationTest() {

    }
}
