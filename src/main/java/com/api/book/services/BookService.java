package com.api.book.services;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books= (List<Book>) bookRepository.findAll();
        if(books.size()<=0)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(books));
    }

    public ResponseEntity<Book> getBookById(int id){
        Optional<Book>optional=bookRepository.findById(id);
        Book book= optional.isPresent() ? optional.get() : null;
        return book==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.of(Optional.of(book));
    }

    public ResponseEntity<Book> addBook(Book book){
        try {
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity<Book> deleteBookById(int id){
        try {

            Optional<Book> optional = bookRepository.findById(id);
            Book book = optional.isPresent() ? optional.get() : null;
            bookRepository.delete(book);
            return ResponseEntity.of(Optional.of(book));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<Book> updateBook(Book book,int id) {
        try {
            Book book1 = bookRepository.findById(id).isPresent() ? bookRepository.findById(id).get() : null;
            if (book1 == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else {
                book1.setId(book.getId());
                book1.setAuthor(book.getAuthor());
                book1.setTitle(book.getTitle());
                bookRepository.save(book1);
                return ResponseEntity.of(Optional.of(book1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
