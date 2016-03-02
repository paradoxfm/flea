package com.platifon.mycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * @author paradoxfm - 24.02.2016
 */
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestorePasswordRequest {
    @NotNull @Email
    private String email;
}
