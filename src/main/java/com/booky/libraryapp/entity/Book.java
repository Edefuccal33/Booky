package com.booky.libraryapp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

    @NotNull(message = "ISBN is mandatory")
    private Long isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Year is mandatory")
    private Integer year;

    @NotNull(message = "Copies number is mandatory")
    private Integer copies;

    private Integer borrowedCopies;

    private Integer remainingCopies;

    @NotNull(message = "Date up is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @DateTimeFormat
    private Date deletedAt;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;

}
