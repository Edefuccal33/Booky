package com.booky.libraryapp.controller;

import javax.validation.Valid;
import com.booky.libraryapp.entity.Customer;
import com.booky.libraryapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String showCustomerList(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "/customer/customer";
    }

    @GetMapping("/new")
    public String showSingUpForm(Customer customer) {
        return "/customer/form-customer";
    }

    @PostMapping("/customer-create")
    public String createCustomer(@Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors())
            return "/customer/form-customer";

        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") String id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            model.addAttribute("customers", customerService.findAll());
            return "redirect:/customer";
        }
        model.addAttribute("customer", customer);
        return "/customer/update-customer";
    }

    @PostMapping("/customer-update/{id}")
    public String updateCustomer(@PathVariable(name = "id") String id, @Valid Customer customer, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "/customer/update-customer";
        }
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") String id, Model model){
        Customer customer = customerService.findById(id);
        if (customer != null )
            customerService.delete(customer);
        model.addAttribute("customers", customerService.findAll());
        return "redirect:/customer";
    }
}
