package com.wolfpack.repo;

import com.wolfpack.model.ConfirmationMail;

public interface IConfirmationMailRepo extends IGenericRepo<ConfirmationMail, Integer>{

    ConfirmationMail findByRandom(String random) throws Exception;
}
