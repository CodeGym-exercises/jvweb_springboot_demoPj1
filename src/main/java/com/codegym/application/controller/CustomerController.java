package com.codegym.application.controller;

import com.codegym.application.model.Customer;
import com.codegym.application.service.CustomerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
@RequestMapping(value = {"/","customers"})
public class CustomerController{
    @Autowired
    CustomerService customerService;

    @GetMapping
    ModelAndView showHomePage(@PageableDefault(size=3) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/search")
    ModelAndView search(@RequestParam("search") String string,@PageableDefault(size=3) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Customer> customers = customerService.findAll(pageable);
        modelAndView.addObject("customers",customers);
        modelAndView.addObject("results",customerService.searchByName(string));
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView showCreatePage(@ModelAttribute("customer") Customer customer){
        return  new ModelAndView("create");
    }

    @PostMapping("/create")
    ModelAndView CreateNewCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("create");
        }
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("message","Success!!");
        customerService.save(customer);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    ModelAndView showViewDetailsPage(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("customer",customerService.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    ModelAndView showDeletePage(@PathVariable int id){
        return new ModelAndView("/delete").addObject("customer",customerService.findById(id));
    }

    @PostMapping("/delete")
    ModelAndView deleteCustomer(@RequestParam(name = "id") String id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer",customerService.findById(Integer.parseInt(id)));
        customerService.deleteById(Integer.parseInt(id));
        modelAndView.addObject("message","delete success");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    ModelAndView showEditPage(@PathVariable int id){
        return new ModelAndView("edit")
                .addObject("customer",customerService.findById(id));
    }

    @PostMapping("/edit")
    ModelAndView editCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("edit");
        }
        ModelAndView modelAndView = new ModelAndView("edit");
        customerService.save(customer);
        modelAndView.addObject("message","Success!!");
        return modelAndView;
    }
}
