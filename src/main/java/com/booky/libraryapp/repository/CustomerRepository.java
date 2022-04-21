package com.booky.libraryapp.repository;

import java.util.List;
import com.booky.libraryapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    
    public List<Customer> findByDeletedAtIsNull();

}
