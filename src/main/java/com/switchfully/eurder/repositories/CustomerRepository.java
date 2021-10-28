package com.switchfully.eurder.repositories;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class CustomerRepository {

    private final HashMap<String, Customer>  customerDataBase;

    public CustomerRepository() {
        this.customerDataBase = new HashMap<>();
        Customer customer1 = new Customer("Bob", "Marley", "bob@marley.be", "Rue blabla", "069696969");
        Customer customer2 = new Customer("Maria", "Marley", "maria@marley.be", "Rue blabla", "068686868", true);
        System.out.println("id of customer1" + customer1.getId());
        System.out.println("id of customer2 admin " + customer2.getId());
        customerDataBase.put(customer1.getId(), customer1);
        customerDataBase.put(customer2.getId(), customer2);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customerDataBase.values();
    }

    public Customer getCustomerById(String id){
        return customerDataBase.get(id);
    }

    public Customer createCustomer(Customer customer) {
        this.customerDataBase.put(customer.getId(), customer);
        return customerDataBase.get(customer.getId());
    }

    public Customer update(String customerId, Customer customerToUpdate) {
        customerDataBase.put(customerId, customerToUpdate);
        return customerDataBase.get(customerId);
    }
}
