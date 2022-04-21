package com.booky.libraryapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.booky.libraryapp.entity.Publisher;
import com.booky.libraryapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublisherService {
    
    @Autowired
    private PublisherRepository publisherRepo;

    @Transactional(readOnly = true)
    public List<Publisher> findAll(){
        return publisherRepo.findByDeletedAtIsNull();
    }

    @Transactional(readOnly = true)
    public Publisher findById(String id) {
        Optional<Publisher> publisher = publisherRepo.findById(id);
        if (publisher.isPresent()) {
            return publisher.get();
        }
        return null;
    }

    @Transactional
    public void save(Publisher publisher) {
        publisher.setUpdatedAt(new Date());
        publisherRepo.save(publisher);
    }
    
    @Transactional
    public void delete(Publisher publisher) {
        publisher.setDeletedAt(new Date());
        publisherRepo.save(publisher);
    }
}
