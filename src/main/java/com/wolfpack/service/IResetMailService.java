package com.wolfpack.service;

import com.wolfpack.model.ResetMail;

public interface IResetMailService {

    ResetMail findByRandom(String random) throws Exception;

    void save(ResetMail random);

    void delete(ResetMail random);
}
