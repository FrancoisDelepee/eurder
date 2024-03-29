package com.switchfully.eurder.api.dtos;

public class CreateCustomerDto {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;

    public CreateCustomerDto(String firstName, String lastName, String email, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
}
