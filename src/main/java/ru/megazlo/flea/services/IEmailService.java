package ru.megazlo.flea.services;

import ru.megazlo.flea.model.RestorePasswordRequest;

/**
 * @author paradoxfm - 01.02.2016
 */
public interface IEmailService {
    void sendRestoreLink(RestorePasswordRequest usr);
}
