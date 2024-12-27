package com.wolfpack.service.impl;

import com.wolfpack.model.User;
import com.wolfpack.repo.ILoginRepo;
import com.wolfpack.service.ILoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements ILoginService {

    private final PasswordEncoder bcrypt;
    private final ILoginRepo repo;

    @Override
    public User checkUsername(String username) {
        return repo.checkUsername(username);
    }

    @Override
    public void changePassword(String password, String username) {

        repo.changePassword(bcrypt.encode(password), username);

    }



}
