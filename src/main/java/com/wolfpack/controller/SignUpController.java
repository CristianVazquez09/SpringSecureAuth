package com.wolfpack.controller;

import com.wolfpack.util.SignupRequest;
import com.wolfpack.service.impl.SignUpServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpServiceImpl service;

    @PostMapping("/signup")
    public ResponseEntity<Integer> signup (@Valid @RequestBody SignupRequest signup) throws RuntimeException{

        int rpa = 0;

        boolean obj = service.saveSigUp(signup.getUsername(), signup.getPassword());

        if(obj){
            rpa =1;
        }

        return ResponseEntity.ok(rpa);
    }
}
