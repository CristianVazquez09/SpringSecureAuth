package com.wolfpack.controller;

import com.wolfpack.model.ConfirmationMail;
import com.wolfpack.service.IConfirmationMailService;
import com.wolfpack.service.ISIgnUpService;
import com.wolfpack.util.EmailUtil;
import com.wolfpack.util.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final ISIgnUpService service;
    private final IConfirmationMailService confirmationMailService;
    private final EmailUtil emailUtil;

    @PostMapping(value = "/sendMail/signup")
    public ResponseEntity<Integer> sendMailSignUp(@RequestBody String username) throws Exception{
        int rpa=0;

        if(username !=null && !username.isEmpty()){

            ConfirmationMail confirmationEmail = new ConfirmationMail();
            confirmationEmail.setRandom(UUID.randomUUID().toString());
            confirmationEmail.setUsername(username);
            confirmationEmail.setExpiration(10);
            confirmationMailService.save(confirmationEmail);

            Mail mail = new Mail();
            mail.setFrom("C-WOLF.software@gmail.com");
            mail.setTo(username);
            mail.setSubject("Confirmar correo electrónico");

            Map<String, Object> model = new HashMap<>();
            String url = "http://localhost:8081/sigup/check/" + confirmationEmail.getRandom();
            model.put("user", confirmationEmail.getUsername());
            model.put("resetUrl", url);
            mail.setModel(model);

            emailUtil.sendMailConfirmation(mail);

            rpa=1;

        }

        return new ResponseEntity<>(rpa, HttpStatus.OK);
    }


    @GetMapping(value = "/check/{random}")
    public ResponseEntity<Object> checkReset(@PathVariable("random") String random, @RequestBody String password) throws Exception{
        ConfirmationMail rm = confirmationMailService.findByRandom(random);

        if(rm !=null && rm.getId() > 0){
            if (!rm.isExpired()){
               service.saveSigUp(rm.getUsername(), password);
                confirmationMailService.delete(rm);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
