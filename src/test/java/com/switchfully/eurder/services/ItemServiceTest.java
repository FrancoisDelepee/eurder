package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.AddItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsToUpdateDto;
import com.switchfully.eurder.api.dtos.UpdateCustomerDto;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.exceptions.AuthorisationException;
import com.switchfully.eurder.repositories.CustomerRepository;
import com.switchfully.eurder.repositories.ItemRepository;
import com.switchfully.eurder.services.mappers.CustomerMapper;
import com.switchfully.eurder.services.mappers.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;
    private ItemService itemService;
    private AddItemsDto itemToAdd_1 = new AddItemsDto("Phone", "Nice phone", 150, 0);
    private AddItemsDto itemToAdd_2 =  new AddItemsDto("Table", "Nice table", 500, 5);
    private Customer admin;
    private Customer customer;

    @BeforeEach
    void setup(){
        itemRepository = new ItemRepository();
        itemMapper = new ItemMapper(itemRepository);
        customerRepository = new CustomerRepository();
        customerMapper = new CustomerMapper();
        customerService = new CustomerService(customerRepository, customerMapper);
        itemService = new ItemService(itemRepository, itemMapper, customerService);
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
    @DisplayName("Test items methods")
    class testGetItems{

        @Test
        void whenAddingAnItemThatDoesNotExist_CreateANewItemGroupContainingThatItem(){
            // given
            GroupOfItemsDto addedItem = itemService.addItems(itemToAdd_1);


            // when
            List<GroupOfItemsDto> items = itemService.getAllGroupOfItems();


            // then
            boolean isItThere = items.stream()
                    .map(item -> item.getName())
                    .collect(Collectors.toList())
                    .contains(addedItem.getName());

            assertTrue(isItThere);

        }

        @Test
        void whenAddingTwoDifferentItems_shouldCreat2GroupsOfItems(){
            //Given
            GroupOfItemsDto addedItem_1 = itemService.addItems(itemToAdd_1);
            GroupOfItemsDto addedItem_2 = itemService.addItems(itemToAdd_2);

            // when
            List<GroupOfItemsDto> items = itemService.getAllGroupOfItems();

            boolean isItem1InStock = items.stream()
                    .map(item -> item.getName())
                    .collect(Collectors.toList())
                    .contains(addedItem_1.getName());

            boolean isItem2InStock = items.stream()
                    .map(item -> item.getName())
                    .collect(Collectors.toList())
                    .contains(addedItem_2.getName());

            //Then

            assertTrue(isItem1InStock && isItem2InStock);
        }

        @Test
        void whenAdding5ItemsInAGroup_thenFind5ItemsInThatGroup(){
            // Given
            GroupOfItemsDto addedItem_2 = itemService.addItems(itemToAdd_2); //amount = 5


            // When
            GroupOfItemsDto groupOfItemsDto = itemService.getGroupOfItemsById(addedItem_2.getId());

            // Then

            assertEquals(5, groupOfItemsDto.getAmount());

        }

        @Test
        void whenAdding2Times5ItemsInAGroup_thenFind10ItemsInThatGroup(){

            // Given
            itemService.addItems(itemToAdd_2);
            GroupOfItemsDto addedItem_2 = itemService.addItems(itemToAdd_2); //amount = 5


            // When
            GroupOfItemsDto groupOfItemsDto = itemService.getGroupOfItemsById(addedItem_2.getId());

            // Then

            assertEquals(10, groupOfItemsDto.getAmount());

        }

        @Test
        void whenAdding2Times5ItemsInAGroup_thenStyleOnlyOneGroupExist(){

            // Given
            int fistNumberOfGroup = itemService.getAllGroupOfItems().size();
            itemService.addItems(itemToAdd_2);
            int numberOfGroupAfterFirstAdd = itemService.getAllGroupOfItems().size();
            GroupOfItemsDto addedItem_2 = itemService.addItems(itemToAdd_2); //amount = 5
            int numberOfGroupAfterAddToExistingGroup = itemService.getAllGroupOfItems().size();

            // Then

            assertTrue(fistNumberOfGroup == 0 && numberOfGroupAfterFirstAdd == 1 && numberOfGroupAfterAddToExistingGroup == 1);
        }

        @Test
        void ifItemHasNoNameThrowException(){
            // Given
            AddItemsDto itemToAdd = new AddItemsDto("", "Nice phone", 150, 0);


            // Then

            assertThrows(IllegalArgumentException.class, () -> itemService.addItems(itemToAdd));
        }

    }

    @Test
    void whenUpdatingAnItemGroup_theItemGroupIsUpdated(){
        // Given
        AddItemsDto itemToAdd = new AddItemsDto("phone", "Coll phone", 150, 0);
        GroupOfItemsDto newGroup = itemService.addItems(itemToAdd);
        GroupOfItemsToUpdateDto dataToUpdate = new GroupOfItemsToUpdateDto("Cool Phone", "Nice phone", 12);
        // When
        itemService.updateItem(newGroup.getId(), admin.getId(), dataToUpdate);
        GroupOfItemsDto updatedGroup = itemService.getGroupOfItemsById(newGroup.getId());
        // Then
        assertTrue(updatedGroup.getName().equals(dataToUpdate.getName())
                && updatedGroup.getDescription().equals(dataToUpdate.getDescription())
                && updatedGroup.getPrice() == dataToUpdate.getPrice() );
    }

    @Test
    void whenUpdatingAnItemGroupIfNotAdminThrowException(){
        // Given
        AddItemsDto itemToAdd = new AddItemsDto("phone", "Coll phone", 150, 0);
        GroupOfItemsDto newGroup = itemService.addItems(itemToAdd);
        GroupOfItemsToUpdateDto dataToUpdate = new GroupOfItemsToUpdateDto("Cool Phone", "Nice phone", 12);

        // Then
        assertThrows(AuthorisationException.class, () -> itemService.updateItem(newGroup.getId(), customer.getId(), dataToUpdate));
    }

    @Test
    void whenUpdatingAnItemGroupNoNewGroupIsCreated(){
        // Given
        AddItemsDto itemToAdd = new AddItemsDto("phone", "Coll phone", 150, 0);
        GroupOfItemsDto newGroup = itemService.addItems(itemToAdd);
        GroupOfItemsToUpdateDto dataToUpdate = new GroupOfItemsToUpdateDto("Cool Phone", "Nice phone", 12);
        int numberOfGroupsBeforeUpdate = itemService.getAllGroupOfItems().size();

        // When
        itemService.updateItem(newGroup.getId(), admin.getId(), dataToUpdate);
        int numberOfGroupsAfterUpdate = itemService.getAllGroupOfItems().size();
        // Then
        assertEquals(numberOfGroupsBeforeUpdate, numberOfGroupsAfterUpdate);
    }

}