package ru.megazlo.flea.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для вывода основных страниц без ajax
 *
 * @author paradoxfm - 29.01.2016
 */
@Slf4j
@Controller
public class GlobalController {

    @RequestMapping(path = {"/", "/cards/*"})
    public Object index() {
        return "index";
    }

    @RequestMapping("/intro")
    public Object intro() {
        return "intro";
    }
}
