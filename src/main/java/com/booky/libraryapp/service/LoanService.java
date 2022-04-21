package com.booky.libraryapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.booky.libraryapp.entity.Loan;
import com.booky.libraryapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {
    
    @Autowired
    private LoanRepository loanRepo;

    @Transactional(readOnly = true)
    public List<Loan> findAll(){
        return loanRepo.findByDeletedAtIsNull();
    }

    @Transactional(readOnly = true)
    public List<Loan> findOpenLoans(){
        return loanRepo.findOpenLoans();
    }
    
    @Transactional
    public void save(Loan loan){
        loanRepo.save(loan);
    }

    @Transactional(readOnly = true)
    public Loan findById(String id){
        Optional<Loan> loan = loanRepo.findById(id);
        if (loan.isPresent())
            return loan.get();
        return null;
    }

    @Transactional
    public void cancelLoan(Loan loan){
        loan.setReturnedAt(new Date());
        loanRepo.save(loan);
    }

    @Transactional
    public void delete(Loan loan){
        loan.setDeletedAt(new Date());
        loanRepo.save(loan);
    }

}
