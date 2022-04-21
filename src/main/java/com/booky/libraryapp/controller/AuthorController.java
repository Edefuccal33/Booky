package com.booky.libraryapp.controller;

import javax.validation.Valid;
import com.booky.libraryapp.entity.Author;
import com.booky.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public String showAuthorList(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "/author/author";
    }

    @GetMapping("/new")
    public String showSingUpForm(Author author) {
        return "/author/form-author";
    }

    @PostMapping("/author-create")
    public String createAutor(@Valid Author author, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/author/form-author";

        authorService.save(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") String id, Model model) {
        Author author = authorService.findById(id);
        if (author == null) {
            model.addAttribute("authors", authorService.findAll());
            return "redirect:/author";
        }

        model.addAttribute("author", author);
        return "/author/update-author";
    }

    @PostMapping("/author-update/{id}")
    public String updateAuthor(@PathVariable(name = "id") String id, @Valid Author author, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            author.setId(id);
            return "/author/update-author";
        }
        authorService.save(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable(name = "id") String id, Model model){
        Author author = authorService.findById(id);
        if (author != null )
            authorService.delete(author);
        model.addAttribute("authors", authorService.findAll());
        return "redirect:/author";
    }
}
