package ru.megazlo.flea.services;

import ru.megazlo.flea.entity.User;
import ru.megazlo.flea.model.CreateUserRequest;
import ru.megazlo.flea.model.RestorePasswordRequest;

/**
 * @author paradoxfm - 21.01.2016
 */
public interface IUserService {
    User registerUser(CreateUserRequest req);

    User getUserByEmail(RestorePasswordRequest req);
}
