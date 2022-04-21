package com.booky.libraryapp.repository;

import java.util.List;
import com.booky.libraryapp.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    
    public List<Loan> findByDeletedAtIsNull();

    @Query(value = "SELECT l FROM Loan l WHERE l.returnedAt IS NULL AND l.deletedAt IS NULL")
    public List<Loan> findOpenLoans();

}
