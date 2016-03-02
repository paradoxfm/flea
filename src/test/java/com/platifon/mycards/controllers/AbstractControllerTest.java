package com.platifon.mycards.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author paradoxfm - 17.02.2016
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestContext.class})
public abstract  class AbstractControllerTest<T> {

    //protected T controller;
    protected MockMvc mockMvc;
    //final Class<?> genericType;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public AbstractControllerTest() {
        //this.genericType = GenericTypeResolver.resolveTypeArgument(getClass(), AbstractControllerTest.class);
    }

    @Before
    @SuppressWarnings("unchecked")
    public void setUpController() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        //controller = (T) webApplicationContext.getBean(Class.forName(genericType.getName()));
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //MockitoAnnotations.initMocks(this);
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
