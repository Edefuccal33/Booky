package com.booky.libraryapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.booky.libraryapp.entity.Customer;
import com.booky.libraryapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepo;

    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        return customerRepo.findByDeletedAtIsNull();
    }

    @Transactional
    public void save(Customer customer){
        customerRepo.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findById(String id){
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent())
            return customer.get();
        return null;
    }

    @Transactional
    public void delete(Customer customer){
        customer.setDeletedAt(new Date());
        customerRepo.save(customer);
    }
}
