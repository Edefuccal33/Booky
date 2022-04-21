package com.booky.libraryapp.repository;

import java.util.List;
import com.booky.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    public List<Author> findByDeletedAtIsNull();

}
