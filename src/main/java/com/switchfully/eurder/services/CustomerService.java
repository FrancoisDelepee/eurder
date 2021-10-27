package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.repositories.CustomerRepository;
import com.switchfully.eurder.services.mappers.CustomerMapper;
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
        if(isAdmin(adminId)){
            return customerRepository.getAllCustomers()
                    .stream()
                    .map(customer -> customerMapper.toDto(customer))
                    .collect(Collectors.toList());
        }
            throw new IllegalArgumentException("This user has not admin privilege");

    }

    public CustomerDto getCustomerById(String id){
        if(idExists(id)){
            return customerMapper.toDto(customerRepository.getCustomerById(id));
        }
        throw new IllegalArgumentException("This id does not exist");
    }


    private boolean idExists(String id){
        return customerRepository.getAllCustomers()
                .stream()
                .anyMatch(customer -> customer.getId().equals(id));
    }

    private boolean isAdmin(String id){
        if(!idExists(id)){
            throw new IllegalArgumentException("This admin id does not exist");
        }
        return customerRepository.getAllCustomers()
                .stream()
                .filter(customer -> customer.isAdmin())
                .anyMatch(customer -> customer.getId().equals(id));
    }

}
