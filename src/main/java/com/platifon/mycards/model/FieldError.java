package com.platifon.mycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * @author paradoxfm - 17.02.2016
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class FieldError {
    private String field;

    private String message;

    FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
