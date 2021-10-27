package com.switchfully.eurder.api.dtos;

import com.switchfully.eurder.domain.Customer;

public class CustomerDto {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;

    public CustomerDto(String id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDto(Customer customer) {
        this(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber());
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
