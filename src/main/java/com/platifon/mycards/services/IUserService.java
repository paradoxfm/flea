package com.platifon.mycards.services;

import com.platifon.mycards.entity.User;
import com.platifon.mycards.model.CreateUserRequest;
import com.platifon.mycards.model.RestorePasswordRequest;

/**
 * @author paradoxfm - 21.01.2016
 */
public interface IUserService {
    User registerUser(CreateUserRequest req);

    User getUserByEmail(RestorePasswordRequest req);
}
