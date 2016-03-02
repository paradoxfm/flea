package com.platifon.mycards.components;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author iv - 10.02.2016
 */
@Component("activeUserAccessor")
public class UserAccessorImpl implements UserAccessor {
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().getClass() == User.class) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
}