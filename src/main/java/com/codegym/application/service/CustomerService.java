package com.codegym.application.service;

import com.codegym.application.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);
    Customer findById(int id);
    void save(Customer customer);
    void deleteById(int id);
    List<Customer> searchByName(String name);
}
