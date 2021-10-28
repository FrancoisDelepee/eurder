package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dtos.*;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.repositories.ItemRepository;
import com.switchfully.eurder.services.mappers.ItemMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    ItemRepository itemRepository;
    ItemMapper itemMapper;
    CustomerService customerService;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, CustomerService customerService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.customerService = customerService;
    }

    public List<ItemDto> getAllOnSaleItems() {
        return null;
    }


    public List<GroupOfItemsDto> getAllGroupOfItems() {
        return itemRepository.getAllGroupOfItems()
                .stream()
                .map(groupOfItems -> itemMapper.groupOfItemsDto(groupOfItems))
                .collect(Collectors.toList());
    }

    public GroupOfItemsDto getGroupOfItemsById(String id) {
        return itemMapper.groupOfItemsDto(itemRepository.getGroupOfItemsById(id));

    }


    public GroupOfItemsDto addItems(AddItemsDto addItemsDto) {
        List<Item> itemsToAdd = new ArrayList<>();
        for (int i = 0; i < addItemsDto.getAmount(); i++) {
            itemsToAdd.add(itemMapper.addItemDtoToDomain(addItemsDto));
        }

        return itemMapper.groupOfItemsDto(itemRepository.addItem(itemsToAdd, addItemsDto));
    }

    public GroupOfItemsDto updateItem(String groupOfItemId, String adminId, GroupOfItemsToUpdateDto groupOfItemToUpdateDto) {
        customerService.isAdmin(adminId);
        return itemMapper.groupOfItemsDto(itemRepository.updateGroupOfItems(groupOfItemId, itemMapper.groupOfItemsToUpdateToGroupItemDomain(groupOfItemToUpdateDto)));
    }
}
