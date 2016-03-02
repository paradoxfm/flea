package com.platifon.mycards.services;

import com.platifon.mycards.model.RestorePasswordRequest;

/**
 * @author paradoxfm - 01.02.2016
 */
public interface IEmailService {
    void sendRestoreLink(RestorePasswordRequest usr);
}
