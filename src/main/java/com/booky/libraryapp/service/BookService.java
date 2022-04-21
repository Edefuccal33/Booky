package com.booky.libraryapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.booky.libraryapp.entity.Book;
import com.booky.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepo;

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepo.findByDeletedAtIsNull();
    }

    @Transactional(readOnly = true)
    public List<Book> findAvailables(){
        return bookRepo.findByHasRemainingCopies();
    }

    @Transactional
    public void save(Book book) {
        book.setBorrowedCopies(book.getBorrowedCopies());
        book.setRemainingCopies(book.getCopies() - book.getBorrowedCopies());
        bookRepo.save(book);
    }

    @Transactional
    public void borrowCopy(Book book){
        book.setBorrowedCopies(book.getBorrowedCopies()+1);
        book.setRemainingCopies(book.getCopies() - book.getBorrowedCopies());
        bookRepo.save(book);
    }

    @Transactional
    public void returnCopy(Book book){
        book.setBorrowedCopies(book.getBorrowedCopies()-1);
        book.setRemainingCopies(book.getCopies() - book.getBorrowedCopies());
        bookRepo.save(book);
    }

    @Transactional
    public void register(Book book){
        book.setBorrowedCopies(0);
        book.setRemainingCopies(book.getCopies());
        bookRepo.save(book);
    }

    @Transactional(readOnly = true)
    public Book findById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if(book.isPresent())
            return book.get();
        return null;
    }

    @Transactional
    public void delete(Book book) {
        book.setDeletedAt(new Date());
        bookRepo.save(book);
    }
}
