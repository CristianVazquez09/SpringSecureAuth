package com.wolfpack.controller;

import com.wolfpack.model.Book;
import com.wolfpack.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@PreAuthorize("@authorizeLogic.hasAccess('book')")
public class BookController {

    private final BookServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() throws Exception{

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById (@PathVariable("id") Integer id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save (@RequestBody Book book) throws Exception{

        Book obj = service.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdBook()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> save (@PathVariable("id") Integer id, @RequestBody Book book) throws Exception{

        book.setIdBook(id);

        return ResponseEntity.ok(service.update(id,book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}
