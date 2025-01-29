package com.angelesdev.SpringAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String redirectToApi() {
        // Redirige a la URL deseada
        return "redirect:/Spring-API";
    }
}
