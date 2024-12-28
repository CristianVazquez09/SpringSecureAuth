package com.wolfpack.controller;

import com.wolfpack.model.ResetMail;
import com.wolfpack.model.User;
import com.wolfpack.service.ILoginService;
import com.wolfpack.service.IResetMailService;
import com.wolfpack.util.EmailUtil;
import com.wolfpack.util.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final ILoginService loginService;
    private final IResetMailService resetMailService;
    private final EmailUtil emailUtil;

    @PostMapping(value = "/sendMail")
    public ResponseEntity<Integer> sendMail(@RequestBody String username) throws Exception{
        int rpa=0;

        User us = loginService.checkUsername(username);

        if(us != null && us.getIdUser() > 0){
            ResetMail resetMail = new ResetMail();
            resetMail.setRandom(UUID.randomUUID().toString());
            resetMail.setUser(us);
            resetMail.setExpiration(10);
            resetMailService.save(resetMail);

            Mail mail = new Mail();
            mail.setFrom("C-WOLF.software@gmail.com");
            mail.setTo(us.getUsername());
            mail.setSubject("RESTABLECER TU CONTRASEÑA");

            Map<String, Object> model = new HashMap<>();
            String url = "http://localhost:8081/forgot/" + resetMail.getRandom();
            model.put("user", resetMail.getUser().getUsername());
            model.put("resetUrl", url);
            mail.setModel(model);

            emailUtil.sendMailReset(mail);

            rpa=1;

        }

        return new ResponseEntity<>(rpa, HttpStatus.OK);
    }

    @GetMapping(value = "reset/check/{random}")
    public ResponseEntity<Integer> checkReset(@PathVariable("random") String random) throws Exception{
        int rpa=0;

        if(random != null && !random.isEmpty()) {
            ResetMail rm = resetMailService.findByRandom(random);
            if(rm !=null && rm.getId() > 0){
                rpa=1;
            }

        }
        return ResponseEntity.ok(rpa);
    }


    @GetMapping(value = "/reset/{random}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> resetPassword(@PathVariable("random") String random, @RequestBody String password) throws Exception{

        ResetMail rm = resetMailService.findByRandom(random);

        if(rm !=null && rm.getId() > 0){
            if (!rm.isExpired()){
                loginService.changePassword(password, rm.getUser().getUsername());
                resetMailService.delete(rm);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
