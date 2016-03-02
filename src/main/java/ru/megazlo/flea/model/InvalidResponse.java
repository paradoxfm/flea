package ru.megazlo.flea.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paradoxfm - 29.01.2016
 */
@Getter
@Setter
public class InvalidResponse {
    private List<ErrorField> errors = new ArrayList<>();
}
