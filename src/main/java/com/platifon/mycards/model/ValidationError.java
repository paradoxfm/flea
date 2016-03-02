package com.platifon.mycards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paradoxfm - 17.02.2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ValidationError {
    private List<FieldError> errors = new ArrayList<>();

    public void addFieldError(String path, String message) {
        errors.add(new FieldError(path, message));
    }
}
