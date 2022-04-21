package com.booky.libraryapp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Customer {
    
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last Name is mandatory")
    private String lastname;

    @NotNull(message = "DNI is mandatory")
    private Long document;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotNull(message = "Date up is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @DateTimeFormat
    private Date deletedAt;
    
}
