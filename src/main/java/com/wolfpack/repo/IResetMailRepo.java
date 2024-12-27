package com.wolfpack.repo;

import com.wolfpack.model.ResetMail;

public interface IResetMailRepo extends IGenericRepo<ResetMail, Integer>{

    ResetMail findByRandom(String random) throws Exception;
}
