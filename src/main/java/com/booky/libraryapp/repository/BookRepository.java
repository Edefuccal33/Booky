package com.booky.libraryapp.repository;

import java.util.List;
import com.booky.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    
    public List<Book> findByDeletedAtIsNull();

    @Query(value = "SELECT b FROM Book b WHERE b.remainingCopies > 0")
    public List<Book> findByHasRemainingCopies();
    
}
