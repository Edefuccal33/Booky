package com.booky.libraryapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.booky.libraryapp.entity.Author;
import com.booky.libraryapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepo;
    
    @Transactional(readOnly = true)
    public List<Author> findAll(){
        return authorRepo.findByDeletedAtIsNull();
    }

    @Transactional(readOnly = true)
    public Author findById(String id){
        Optional<Author> author = authorRepo.findById(id);
        if(author.isPresent() ){
            return author.get();
        }
        return null;
    }

    @Transactional
    public Author save(Author author){
        author.setUpdatedAt(new Date());
        authorRepo.save(author);
        return author;
    }

    @Transactional
    public void delete(Author author){
        author.setDeletedAt(new Date());
        authorRepo.save(author);
    }
}
