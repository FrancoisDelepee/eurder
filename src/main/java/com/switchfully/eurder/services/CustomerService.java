package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.repositories.CustomerRepository;
import com.switchfully.eurder.services.mappers.CustomerMapper;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDto> getAllCustomers(String adminId) {
        return customerRepository.getAllCustomers()
                .stream()
                .map(customer -> customerMapper.toDto(customer))
                .collect(Collectors.toList());
    }

}
