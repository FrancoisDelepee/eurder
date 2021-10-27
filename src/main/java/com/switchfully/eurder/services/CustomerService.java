package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.exceptions.AuthorisationException;
import com.switchfully.eurder.exceptions.UnexpectedException;
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
        if(!isValidInput(adminId)){
            throw new IllegalArgumentException("adminId can not be blank or empty");
        }

        if(idExists(adminId) && isAdmin(adminId)){
            return customerRepository.getAllCustomers()
                    .stream()
                    .map(customer -> customerMapper.toDto(customer))
                    .collect(Collectors.toList());
        }

        throw new UnexpectedException("Something wrong is happening!");
    }

    public CustomerDto getCustomerById(String id){
        if(!isValidInput(id)){
            throw new IllegalArgumentException("An id must be provided");
        }
        if(idExists(id)){
            return customerMapper.toDto(customerRepository.getCustomerById(id));
        }
        throw new UnexpectedException("Something wrong is happening!");
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        if(isCreateCustomerDtoValid(createCustomerDto)){
            return customerMapper.toDto(customerRepository.createCustomer(customerMapper.toDomain(createCustomerDto)));
        }
        throw new UnexpectedException("Something unexpected happened");
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
        return input != null && !input.isEmpty() && !input.isBlank();
    }

    private boolean isCreateCustomerDtoValid(CreateCustomerDto createCustomerDto){
        if(!isValidInput(createCustomerDto.getFirstName())){
            throw new IllegalArgumentException("The First name must be provided");
        }
        if(!isValidInput(createCustomerDto.getLastName())){
            throw new IllegalArgumentException("The last name must be provided");
        }

        if(!isValidInput(createCustomerDto.getEmail())){
            throw new IllegalArgumentException("The email must be provided");
        }

        return true;
    }


}
