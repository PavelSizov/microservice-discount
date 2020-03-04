package com.microservices.mentoring.discount.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/")
    public String healthcheck() {
        return "OK";
    }
}
