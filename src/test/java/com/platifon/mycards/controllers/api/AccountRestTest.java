package com.platifon.mycards.controllers.api;

import com.platifon.mycards.controllers.AbstractControllerTest;
import com.platifon.mycards.controllers.rest.AccountRest;
import com.platifon.mycards.services.ICardService;
import com.platifon.mycards.services.IUserService;
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
