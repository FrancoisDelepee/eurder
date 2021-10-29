package com.switchfully.eurder.services.mappers;

import com.switchfully.eurder.api.dtos.*;
import com.switchfully.eurder.domain.GroupOfItems;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.repositories.ItemRepository;
import org.springframework.stereotype.Component;



@Component
public class ItemMapper {

    ItemRepository itemRepository;

    public ItemMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public GroupOfItems groupOfItemToDomain(CreateGroupOfItemsDto createGroupOfItemsDto) {
        return new GroupOfItems(createGroupOfItemsDto);
    }

    public GroupOfItemsDto groupOfItemsDto(GroupOfItems groupOfItem) {
        return new GroupOfItemsDto(groupOfItem);
    }

    public Item addItemDtoToDomain(AddItemsDto dto){
        return new Item(dto);
    }

    public GroupOfItems addItemDtoToGroupItemDomain(AddItemsDto dto){
       return new GroupOfItems(dto);
    }

    public GroupOfItems groupOfItemsToUpdateToGroupItemDomain(GroupOfItemsToUpdateDto dto){
        return new GroupOfItems(dto);
    }

    public ItemDto itemToDto(Item item) {
        GroupOfItems group  = itemRepository.getGroupOfItemsById(item.getGroupId());
        return new ItemDto(item, group.getDescription(), group.getPrice());
    }
}
