package com.wolfpack.service.impl;

import com.wolfpack.model.ConfirmationMail;
import com.wolfpack.repo.IConfirmationMailRepo;
import com.wolfpack.service.IConfirmationMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationMailImpl implements IConfirmationMailService {

    private final IConfirmationMailRepo repo;

    @Override
    public ConfirmationMail findByRandom(String random) throws Exception{
        return repo.findByRandom(random);
    }

    @Override
    public void save(ConfirmationMail random) {
        repo.save(random);

    }

    @Override
    public void delete(ConfirmationMail random) {
        repo.delete(random);
    }
}
