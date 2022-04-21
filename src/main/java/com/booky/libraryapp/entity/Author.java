package com.booky.libraryapp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
public class Author {

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last Name is mandatory")
    private String lastname;

    @NotNull(message = "Date up is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date createdAt; 

    @LastModifiedDate
    private Date updatedAt;

    @DateTimeFormat
    private Date deletedAt;
}
