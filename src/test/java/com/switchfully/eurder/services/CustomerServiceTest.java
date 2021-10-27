package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.CreateCustomerDto;
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
    private CreateCustomerDto customerToCreate;

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

        customerToCreate = new CreateCustomerDto("Ben", "uur", "ben@urr.be", "bxl", "0123456789");
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

    @Nested
    @DisplayName("test of the creating customers methods")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class testCreatCustomers {

        @Test
        void whenCreatingACustomer_returnACustomerDtoEquivalent(){
            // given


            // when
            CustomerDto createdCustomer = customerService.createCustomer(customerToCreate);

            //then
            assertEquals(createdCustomer.getFirstName(), customerToCreate.getFirstName());
            assertEquals(createdCustomer.getLastName(), customerToCreate.getLastName());
            assertEquals(createdCustomer.getEmail(), customerToCreate.getEmail());
            assertEquals(createdCustomer.getAddress(), customerToCreate.getAddress());
            assertEquals(createdCustomer.getPhoneNumber(), customerToCreate.getPhoneNumber());
        }

        @Test
        void whenCreatingACustomerWithoutFirstName_throwException(){
            // given
            CreateCustomerDto badCustomerToCreate = new CreateCustomerDto(null, "uur", "ben@urr.be", "bxl", "0123456789");

            //then
            assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(badCustomerToCreate));



        }
    }


}