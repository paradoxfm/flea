package com.platifon.mycards.components;

import org.springframework.security.core.userdetails.User;

/**
 * @author iv - 10.02.2016
 */
public interface UserAccessor {
    User getUser();
}
