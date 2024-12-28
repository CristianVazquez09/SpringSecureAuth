package com.wolfpack.service;

import com.wolfpack.model.ConfirmationMail;

public interface IConfirmationMailService {

    ConfirmationMail findByRandom(String random) throws Exception;

    void save(ConfirmationMail random);

    void delete(ConfirmationMail random);
}
