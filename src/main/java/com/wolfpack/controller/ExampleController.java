package com.wolfpack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("@authorizeLogic.hasAccess('example')")
public class ExampleController {

    @GetMapping("/example")
    public String syHello() {
        return "Hola";
    }
}
