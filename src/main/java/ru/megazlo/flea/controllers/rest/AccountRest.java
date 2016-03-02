package ru.megazlo.flea.controllers.rest;

import ru.megazlo.flea.entity.User;
import ru.megazlo.flea.model.CreateUserRequest;
import ru.megazlo.flea.model.RestorePasswordRequest;
import ru.megazlo.flea.services.IEmailService;
import ru.megazlo.flea.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author paradoxfm - 29.01.2016
 */
@Slf4j
@RestController
public class AccountRest {
    @Autowired
    private IUserService userService;
    @Autowired
    private IEmailService emailService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody @Validated CreateUserRequest req) {
        User usr = null;//userService.registerUser(req);
        if (usr == null) {
            usr = new User();
            usr.setEmail("paradoxfmamailru");
        }
        return usr;
    }

    @RequestMapping(value = "/user/restore", method = RequestMethod.POST)
    public ResponseEntity<Object> restorePassword(@RequestBody @Validated RestorePasswordRequest req) {
        emailService.sendRestoreLink(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
