package com.codegym.application.repository;

import com.codegym.application.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer> {
    List<Customer> findAllByNameContains(String name);
}
