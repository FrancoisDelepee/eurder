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

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> getAllOnSaleItems() {
        return null;
    }

    public GroupOfItemsDto createNewGroupOfItem(CreateGroupOfItemsDto createGroupOfItemsDto) {
        return itemMapper.groupOfItemsDto(itemRepository.createNewGroupOfItem(itemMapper.groupOfItemToDomain(createGroupOfItemsDto)));
    }

    public List<GroupOfItemsDto> getAllGroupOfItems() {
        return itemRepository.getAllGroupOfItems()
                .stream()
                .map(groupOfItems -> itemMapper.groupOfItemsDto(groupOfItems))
                .collect(Collectors.toList());
    }

    public GroupOfItemsDto addItems(AddItemsDto addItemsDto) {
        List<Item> itemsToAdd = new ArrayList<>();
        for (int i = 0; i < addItemsDto.getAmount(); i++) {
            itemsToAdd.add(itemMapper.addItemDtoToDomain(addItemsDto));
        }

        return itemMapper.groupOfItemsDto(itemRepository.addItem(itemsToAdd, itemMapper.addItemDtoToDomain(addItemsDto)));

    }
}
