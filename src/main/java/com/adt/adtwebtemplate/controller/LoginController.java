package com.adt.adtwebtemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thanglv on 4/28/2021
 * @project adt-web-template
 */

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "views_public/login";
    }
}
