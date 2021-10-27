package com.switchfully.eurder.repositories;

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
        Customer customer2 = new Customer("Maria", "Marley", "maria@marley.be", "Rue blabla", "068686868");
        customerDataBase.put(customer1.getId(), customer1);
        customerDataBase.put(customer2.getId(), customer2);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customerDataBase.values();
    }

    public Customer getCustomerByID(String id){
        return customerDataBase.get(id);
    }
}
