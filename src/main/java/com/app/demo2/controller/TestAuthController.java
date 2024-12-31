package com.app.demo2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecured() {
        return "Hello Secured";
    }

    @GetMapping("/hello-secured2")
    public String helloSecured2() {
        return "Hello Secured2";
    }

}
