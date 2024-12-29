package com.wolfpack.service.impl;
import com.wolfpack.model.Book;
import com.wolfpack.repo.IBookRepo;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl extends CRUDImpl<Book,Integer> implements IBookService {


    private final IBookRepo repo;


    @Override
    protected IGenericRepo<Book, Integer> getRepo() {
        return this.repo;
    }
}
