package com.wolfpack.service.impl;

import com.wolfpack.model.ResetMail;
import com.wolfpack.repo.IResetMailRepo;
import com.wolfpack.service.IResetMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetMailImpl  implements IResetMailService {

    private final IResetMailRepo repo;
    @Override
    public ResetMail findByRandom(String random) throws Exception{
        return repo.findByRandom(random);
    }

    @Override
    public void save(ResetMail random) {
        repo.save(random);

    }

    @Override
    public void delete(ResetMail random) {
        repo.delete(random);
    }
}
