package com.wolfpack.service.impl;

import com.wolfpack.exception.UserAlreadyExistsException;
import com.wolfpack.repo.ISignUpRepo;
import com.wolfpack.service.ISIgnUpService;
import com.wolfpack.model.User;
import com.wolfpack.repo.IUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements ISIgnUpService {

    private final ISignUpRepo signUpRepo;
    private final PasswordEncoder bcrypt;
    private final IUserRepo userRepo;



    @Transactional
    @Override
    public boolean saveSigUp(String username,String password) throws UserAlreadyExistsException{

         User findUser =userRepo.findOneByUsername(username);

         if(findUser != null){
           throw new UserAlreadyExistsException("Usuario ya existe");

         }

       signUpRepo.insertUser(username, bcrypt.encode(password));
       signUpRepo.associateRoleWithUser(username);


       return true;
    }

}
