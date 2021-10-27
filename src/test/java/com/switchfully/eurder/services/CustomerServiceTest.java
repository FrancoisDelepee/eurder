package com.switchfully.eurder.services;

import com.switchfully.eurder.repositories.CustomerRepository;
import com.switchfully.eurder.services.mappers.CustomerMapper;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    CustomerRepository customerRepository = new CustomerRepository();
    CustomerMapper customerMapper = new CustomerMapper();
    CustomerService customerService = new CustomerService(customerRepository, customerMapper);


}