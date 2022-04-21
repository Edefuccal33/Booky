package com.booky.libraryapp.repository;

import java.util.List;
import com.booky.libraryapp.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, String>{
    
    public List<Publisher> findByDeletedAtIsNull();
}
