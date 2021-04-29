package com.adt.adtwebtemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    public String homePage() {
        return "admin/index";
    }
}
