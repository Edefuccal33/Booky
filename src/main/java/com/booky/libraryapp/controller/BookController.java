package com.booky.libraryapp.controller;

import javax.validation.Valid;
import com.booky.libraryapp.entity.Author;
import com.booky.libraryapp.entity.Book;
import com.booky.libraryapp.entity.Publisher;
import com.booky.libraryapp.service.AuthorService;
import com.booky.libraryapp.service.BookService;
import com.booky.libraryapp.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;

    @GetMapping()
    public String showBookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "/book/book";
    }

    @GetMapping("/form-book")
    public String getFormBook(Book book, Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        return "/book/form-book";
    }

    @PostMapping("/new-book")
    public String addNewLibro(@Valid Book book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            System.out.println("new libro has errors: " + result.toString());
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return "/book/form-book";
        }

        Author author = authorService.findById(book.getAuthor().getId());
        Publisher publisher = publisherService.findById(book.getPublisher().getId());

        if (author == null || publisher == null) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return "/book/form-book";
        }
        bookService.register(book);
        return "redirect:/book";
    }

    @GetMapping("/edit-book/{id}")
    public String updateFormBook(@PathVariable(name = "id") String id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            model.addAttribute("books", bookService.findAll());
            return "/book/book";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        return "/book/update-book";
    }

    @PostMapping("/update-book/{id}")
    public String updateBook(@PathVariable(name = "id") String id, Book book, BindingResult result,
            Model model) {
        System.out.println(book.toString());
        if (result.hasErrors()) {
            System.out.println("new book has errors: " + result.toString());
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return "/book/form-book";
        }
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoook(@PathVariable(name = "id") String id, Model model){
        Book book = bookService.findById(id);
        if (book != null) 
            bookService.delete(book);
        return "redirect:/book";

    }
}
