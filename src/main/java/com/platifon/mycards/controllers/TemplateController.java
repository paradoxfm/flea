package com.platifon.mycards.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author paradoxfm - 25.01.2016
 */
@Controller
public class TemplateController {

    @RequestMapping("/t/{folder}/{tempName}")
    public String userTemplate(@PathVariable String folder, @PathVariable String tempName) {
        return "t/" + folder + "/" + tempName;
    }
}
