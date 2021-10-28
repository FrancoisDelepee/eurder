package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.api.dtos.UpdateCustomerDto;
import com.switchfully.eurder.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomer(@RequestHeader String adminId){
        logger.info("User " + adminId + " requested for all members");
        return customerService.getAllCustomers(adminId);
    }

    @GetMapping(produces = "application/json", path = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomer(@PathVariable String customerId, @RequestHeader String requesterId){
        logger.info("User " + requesterId + " requested info about " + customerId);
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    //@RequiresRole(ADMIN)
    //@RequiresPermission(CREATE_CUSTOMER)
    public CustomerDto createCustomer(@RequestBody CreateCustomerDto createCustomerDto, @RequestHeader (required = false) String requesterId){
        String requestedIdToLog = requesterId == null ? "anonymous":requesterId;
        CustomerDto createdCustomer = customerService.createCustomer(createCustomerDto);
        logger.info("User " + requestedIdToLog + " created member " + createdCustomer.getId());
        return createdCustomer;
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable String customerId, @RequestBody UpdateCustomerDto customerToUpdate, @RequestHeader String requesterId){
        return customerService.updateCustomer(customerId, customerToUpdate, requesterId);
    }
}
