package com.wolfpack.service;

import com.wolfpack.model.User;

public interface ILoginService  {

    User checkUsername(String username);
    void changePassword(String password, String username);
}
