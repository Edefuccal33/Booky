package com.booky.libraryapp.controller;

import javax.validation.Valid;
import com.booky.libraryapp.entity.Publisher;
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
@RequestMapping("/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @GetMapping()
    public String showAutorList(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "/publisher/publisher";
    }

    @GetMapping("/new")
    public String showSingUpForm(Publisher publisher) {
        return "/publisher/form-publisher";
    }

    @PostMapping("/publisher-create")
    public String createPublisher(@Valid Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/publisher/form-publisher";

        publisherService.save(publisher);
        model.addAttribute("publishers", publisherService.findAll());
        return "redirect:/publisher";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") String id, Model model) {
        Publisher publisher = publisherService.findById(id);
        if (publisher == null) {
            model.addAttribute("publishers", publisherService.findAll());
            return "redirect:/publisher";
        }

        model.addAttribute("publisher", publisher);
        return "/publisher/update-publisher";
    }

    @PostMapping("/publisher-update/{id}")
    public String updatePublisher(@PathVariable(name = "id") String id, @Valid Publisher publisher, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            publisher.setId(id);
            return "/publisher/update-publisher";
        }
        publisherService.save(publisher);
        model.addAttribute("publishers", publisherService.findAll());
        return "redirect:/publisher";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable(name = "id") String id, Model model){
        Publisher publisher = publisherService.findById(id);
        if (publisher != null )
            publisherService.delete(publisher);
        model.addAttribute("publishers", publisherService.findAll());
        return "redirect:/publisher";
    }
}
