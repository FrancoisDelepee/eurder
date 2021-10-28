package com.switchfully.eurder.services.mappers;

import com.switchfully.eurder.api.dtos.AddItemsDto;
import com.switchfully.eurder.api.dtos.CreateGroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsToUpdateDto;
import com.switchfully.eurder.domain.GroupOfItems;
import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
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
}
