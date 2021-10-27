package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CustomerDto;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.exceptions.AuthorisationException;
import com.switchfully.eurder.repositories.CustomerRepository;
import com.switchfully.eurder.services.mappers.CustomerMapper;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;
    private Customer admin;
    private Customer customer;

    @BeforeEach
    void setup() {
        customerRepository = new CustomerRepository();
        customerMapper = new CustomerMapper();
        customerService = new CustomerService(customerRepository, customerMapper);
        admin = customerRepository.getAllCustomers()
                .stream()
                .filter(customer -> customer.isAdmin())
                .findFirst().get();
        customer = customerRepository.getAllCustomers()
                .stream()
                .filter(customer -> !customer.isAdmin())
                .findFirst().get();
    }

    @Nested
    @DisplayName("test of the get customers methods")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class testGetCustomers {

        @Test
        void getCustomerById_returnCustomerDtoWithTheSameId() {

            // when
            CustomerDto retrievedFromDB = customerService.getCustomerById(admin.getId());
            //
            assertEquals(admin.getId(), retrievedFromDB.getId());

        }

        @Test
        void getCustomerByIdWithAWrongId_thenThrowIllegalArgumentException() {
            // Given
            String wrongId = UUID.randomUUID().toString();

            // then
            assertThrows(IllegalArgumentException.class, () -> customerService.getCustomerById(wrongId));

        }

        @Test
        void getAllCustomersWithAdminId() {
            // given
            String goodId = admin.getId();

            // when
            List<CustomerDto> listOFCustomers = customerService.getAllCustomers(goodId);

            // then
            List<String> listOfNames = listOFCustomers.stream().map(customerDto -> customerDto.getFirstName()).collect(Collectors.toList());
            assertTrue(listOfNames.contains("Bob") && listOfNames.contains("Maria"));

        }

        @Test
        void getAllCustomersWithAWrongAdminId_throwIllegalArgumentException() {
            // given
            String customerId = customer.getId();

            // then
            assertThrows(AuthorisationException.class, () -> customerService.getAllCustomers(customerId));

        }

    }
}