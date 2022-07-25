package com.api.book.controllers;

import com.api.book.entities.Book;
import com.api.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id){
        return bookService.deleteBookById(id);
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id){
        return bookService.updateBook(book,id);
    }
}
