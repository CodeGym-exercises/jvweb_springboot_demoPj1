package com.codegym.application;

import com.codegym.application.service.CustomerService;
import com.codegym.application.service.impl.CustomerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CustomerService customerService(){
        return new CustomerServiceImpl();
    }
}
