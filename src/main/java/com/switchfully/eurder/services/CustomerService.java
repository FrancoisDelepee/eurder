package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.exceptions.AuthorisationException;
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

        if(isValidInput(adminId) && idExists(adminId) && isAdmin(adminId)){
            return customerRepository.getAllCustomers()
                    .stream()
                    .map(customer -> customerMapper.toDto(customer))
                    .collect(Collectors.toList());
        }

        throw new IllegalArgumentException("Something wrong is happening!");
    }

    public CustomerDto getCustomerById(String id){
        if(isValidInput(id) && idExists(id)){
            return customerMapper.toDto(customerRepository.getCustomerById(id));
        }
        throw new IllegalArgumentException("Something wrong is happening!");
    }


    private boolean idExists(String id){

        if (customerRepository.getAllCustomers()
                .stream()
                .anyMatch(customer -> customer.getId().equals(id))) {
            return true;
        }

        throw new IllegalArgumentException("This id does not exist");
    }

    private boolean isAdmin(String id){

        if (customerRepository.getAllCustomers()
                .stream()
                .filter(customer -> customer.isAdmin())
                .anyMatch(customer -> customer.getId().equals(id))){

            return true;
        }
        throw new AuthorisationException("This user has not admin privilege");
    }

    private boolean isValidInput(String input){
        if (input != null && !input.isEmpty() && !input.isBlank()){
            return true;
        }
        throw new IllegalArgumentException("Your input can not be blank empty or null");
    }

}
