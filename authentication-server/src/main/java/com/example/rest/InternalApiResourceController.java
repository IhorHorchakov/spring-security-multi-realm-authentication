package com.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/internal-api")
public class InternalApiResourceController {

    @GetMapping("/resource")
    public String resource(Principal principal) {
        return "Dear " + principal.getName() + ", you have been authorized and got 'INTERNAL RESOURCE'";
    }
}
