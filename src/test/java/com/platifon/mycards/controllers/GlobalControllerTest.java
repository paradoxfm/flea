package com.platifon.mycards.controllers;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Все что долже делать этот контроллер это возвращать вьюхи
 *
 * @author paradoxfm - 17.02.2016
 */
public class GlobalControllerTest extends AbstractControllerTest<GlobalController> {

    @Test
    public void indexTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void initTest() throws Exception {
        mockMvc.perform(get("/intro"))
                .andExpect(status().isOk())
                .andExpect(view().name("intro"));
    }
}
