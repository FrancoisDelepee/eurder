package com.switchfully.eurder.domain;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
import com.switchfully.eurder.api.dtos.UpdateCustomerDto;

import java.util.UUID;

public class Customer {

    private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Boolean isAdmin = false;

    public Customer(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String id, String firstName, String lastName, String email, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName, String email, String address, String phoneNumber, boolean isAdmin) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public Customer(CreateCustomerDto customerDto){
        this(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getAddress(), customerDto.getPhoneNumber());
    }

    public Customer(CreateCustomerDto customerDto, Boolean isAdmin){
        this(customerDto);
        this.isAdmin = isAdmin;
    }

    public Customer(UpdateCustomerDto updateCustomerDto){
        this(updateCustomerDto.getId(), updateCustomerDto.getFirstName(), updateCustomerDto.getLastName(), updateCustomerDto.getEmail(), updateCustomerDto.getAddress(), updateCustomerDto.getPhoneNumber());
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

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }
}
