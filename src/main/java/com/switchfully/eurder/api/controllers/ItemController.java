package com.switchfully.eurder.api.controllers;

import com.switchfully.eurder.api.dtos.AddItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsToUpdateDto;
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
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupOfItemsDto> getAllGroupsOfItems() {
        logger.info("Asking for items");
        return itemService.getAllGroupOfItems();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupOfItemsDto getGroupOfItemsById(@PathVariable String id){
        System.out.println(itemService.getGroupOfItemsById(id));
        return itemService.getGroupOfItemsById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupOfItemsDto addItems(@RequestBody AddItemsDto addItemsDto) {
        return itemService.addItems(addItemsDto);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/{groupOfItemsId}")
    @ResponseStatus(HttpStatus.OK)
    public GroupOfItemsDto updateItem(@PathVariable String groupOfItemsId, @RequestHeader String requesterId, @RequestBody GroupOfItemsToUpdateDto groupOfItemToUpdateDto ){
        return itemService.updateItem(groupOfItemsId, requesterId, groupOfItemToUpdateDto);
    }


}
