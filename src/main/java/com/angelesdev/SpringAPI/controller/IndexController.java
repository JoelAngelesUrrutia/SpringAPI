package com.angelesdev.SpringAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class IndexController {

    @GetMapping("/Spring-API")
    public String mainPage() {
        return "¡Welcome to Spring Boot API!";
    }
}