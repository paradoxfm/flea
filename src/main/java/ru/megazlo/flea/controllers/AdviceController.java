package ru.megazlo.flea.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.megazlo.flea.model.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * @author paradoxfm - 17.02.2016
 */
@ControllerAdvice
public class AdviceController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws JsonProcessingException {
        ValidationError rez = new ValidationError();
        Locale currentLocale = LocaleContextHolder.getLocale();
        for (ObjectError err : ex.getBindingResult().getAllErrors()) {
            if (err.getClass() == FieldError.class) {
                FieldError fErr = (FieldError) err;
                String localErrorMessage = messageSource.getMessage(fErr, currentLocale);
                rez.addFieldError(fErr.getField(), localErrorMessage);
            } else {
                String localErrorMessage = messageSource.getMessage(err, currentLocale);
                rez.addFieldError(null, localErrorMessage);
            }
        }
        return new ResponseEntity<>(rez, HttpStatus.BAD_REQUEST);
    }
}
