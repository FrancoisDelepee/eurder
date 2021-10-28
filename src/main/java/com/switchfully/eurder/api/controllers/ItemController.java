package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.AddItemsDto;
import com.switchfully.eurder.api.dtos.CreateGroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsDto;
import com.switchfully.eurder.api.dtos.ItemDto;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupOfItemsDto> getAllGroupsOfItems() {
        System.out.println("GoI = " + itemService.getAllGroupOfItems());
        return itemService.getAllGroupOfItems();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupOfItemsDto addItems(@RequestBody AddItemsDto addItemsDto) {
        return itemService.addItems(addItemsDto);
    }

}
