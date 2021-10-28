package com.switchfully.eurder.services.mappers;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.api.dtos.UpdateCustomerDto;
import com.switchfully.eurder.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer customer){
        return new CustomerDto(customer);
    }

    public Customer toDomain(CreateCustomerDto customerCreateDto){
        return new Customer(customerCreateDto);
    }


    public Customer fromUpdateToDomain(UpdateCustomerDto customerToUpdate) {
        return new Customer(customerToUpdate);
    }
}
