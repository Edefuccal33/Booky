package com.booky.libraryapp.controller;

import javax.validation.Valid;
import com.booky.libraryapp.entity.Loan;
import com.booky.libraryapp.service.BookService;
import com.booky.libraryapp.service.CustomerService;
import com.booky.libraryapp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan")
public class LoanController {
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanService loanService;

    @GetMapping()
    public String showLoanList(Model model) {
        model.addAttribute("loans", loanService.findOpenLoans());
        return "/loan/loan";
    }

    @GetMapping("/new")
    public String showSingUpForm(Loan loan, Model model) {
        model.addAttribute("books", bookService.findAvailables());
        model.addAttribute("customers", customerService.findAll());
        return "/loan/form-loan";
    }

    @PostMapping("/loan-create")
    public String createLoan(@Valid Loan loan, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/loan/form-loan";

        loanService.save(loan);
        bookService.borrowCopy(loan.getBook());
        model.addAttribute("loans", loanService.findOpenLoans());
        return "redirect:/loan";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") String id, Model model) {
        Loan loan = loanService.findById(id);
        if (loan == null) {
            model.addAttribute("loans", loanService.findOpenLoans());
            return "redirect:/loan";
        }

        model.addAttribute("loan", loan);
        model.addAttribute("books", bookService.findAvailables());
        model.addAttribute("customers", customerService.findAll());
        return "/loan/update-loan";
    }

    @PostMapping("/loan-update/{id}")
    public String updateLoan(@PathVariable(name = "id") String id, @Valid Loan loan, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            loan.setId(id);
            return "/loan/update-loan";
        }
        loanService.save(loan);
        model.addAttribute("loans", loanService.findOpenLoans());
        return "redirect:/loan";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoan(@PathVariable(name = "id") String id, Model model){
        Loan loan = loanService.findById(id);
        if (loan != null )
            bookService.returnCopy(loan.getBook());
            loanService.delete(loan);
        model.addAttribute("loans", loanService.findAll());
        return "redirect:/loan";
    }
    
    @GetMapping("/return/{id}")
    public String cancelLoan(@PathVariable(name = "id") String id, Model model){
        Loan loan = loanService.findById(id);
        if (loan != null){
            bookService.returnCopy(loan.getBook());
            loanService.cancelLoan(loan);
        }
        model.addAttribute("loans", loanService.findAll());
        return "redirect:/loan";
    }

}
