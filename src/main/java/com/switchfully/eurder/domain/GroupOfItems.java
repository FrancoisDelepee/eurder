package com.switchfully.eurder.domain;

import com.switchfully.eurder.api.dtos.AddItemsDto;
import com.switchfully.eurder.api.dtos.CreateGroupOfItemsDto;
import com.switchfully.eurder.api.dtos.GroupOfItemsToUpdateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOfItems {
    private final String id;
    private  String name;
    private String description;
    private final List<Item> items;
    private int amount;
    private int price;

    public GroupOfItems(String itemName, String description, int price) {
        this.id = UUID.randomUUID().toString();
        this.items = new ArrayList<>();
        this.name = itemName;
        this.description = description;
        this.price = price;
    }

    public GroupOfItems(CreateGroupOfItemsDto dto) {
        this(dto.getName(), dto.getDescription(), dto.getPrice());
    }

    public GroupOfItems(AddItemsDto dto) {
        this(dto.getName(), dto.getDescription(), dto.getPrice());
    }

    public GroupOfItems(GroupOfItemsToUpdateDto dto) {
        this(dto.getName(), dto.getDescription(), dto.getPrice());
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return this.items.size();
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(List<Item> items){
        this.items.addAll(items);
    }

    public boolean removeItem(String itemId){
       return this.items.remove(itemId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
