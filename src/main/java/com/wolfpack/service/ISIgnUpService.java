package com.wolfpack.service;

import com.wolfpack.model.Role;
import com.wolfpack.model.User;
import com.wolfpack.util.SignupRequest;

import java.util.Set;

public interface ISIgnUpService {

    boolean saveSigUp(String username,String password) throws Exception;
}
