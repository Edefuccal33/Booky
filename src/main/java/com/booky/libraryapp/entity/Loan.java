package com.booky.libraryapp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
public class Loan {
    
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    @NotNull(message = "Book is mandatory")
    @OneToOne
    private Book book;

    @NotNull(message = "Customer is mandatory")
    @OneToOne
    private Customer customer;

    @NotNull(message = "Date up is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat
    private Date returnedAt;

    @LastModifiedDate
    private Date updatedAt;

    @DateTimeFormat
    private Date deletedAt;

}
