package com.platifon.mycards.validate;

import com.platifon.mycards.model.CreateUserRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author paradoxfm - 29.01.2016
 */
public class RequestValidationTest {

    private static Validator validator;

    @BeforeClass
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCreateUserRequestValid() {
        CreateUserRequest req = new CreateUserRequest();
        req.setUsername("paradoxfm");
        req.setEmail("paradoxfm@mail.ru");
        req.setPassword("qwepoiqwepoi");
        req.setConfirmPassword("qwepoiqwepoi");

        Set<ConstraintViolation<CreateUserRequest>> contVal = validator.validate(req);
        assertEquals(0, contVal.size());
    }

    @Test
    public void testCreateUserRequest1() {
        CreateUserRequest req = new CreateUserRequest();
        Set<ConstraintViolation<CreateUserRequest>> contVal = validator.validate(req);

        List<ConstraintViolation<CreateUserRequest>> list = new ArrayList<>(contVal);
        assertEquals(3, contVal.size());
        for (ConstraintViolation<CreateUserRequest> itm : list) {
            assertTrue("may not be null".equals(itm.getMessage()));
        }
    }

    @Test
    public void testCreateUserRequest2() {
        CreateUserRequest req = new CreateUserRequest();
        req.setEmail("sdf");
        req.setUsername("sd");
        req.setPassword("pass");
        Set<ConstraintViolation<CreateUserRequest>> contVal = validator.validate(req);

        List<ConstraintViolation<CreateUserRequest>> list = new ArrayList<>(contVal);
        assertEquals(4, contVal.size());
        String[] strings = {"not a well-formed email address", "size must be between 6 and 14",
                "{com.platifon.mycards.model.ext.FieldMatch.message}", "size must be between 3 and 14"};
        List<String> messagesValid = Arrays.asList(strings);
        for (ConstraintViolation<CreateUserRequest> itm : list) {
            assertTrue("message not found " + itm.getMessage(), messagesValid.contains(itm.getMessage()));
        }
    }
}
