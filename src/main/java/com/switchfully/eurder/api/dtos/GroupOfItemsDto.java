package com.switchfully.eurder.api.dtos;

import com.switchfully.eurder.domain.GroupOfItems;

public class GroupOfItemsDto {
    private final String id;
    private final String name;
    private final String description;
    private final int price;
    private final int amount;

    public GroupOfItemsDto(String id, String name, String description, int price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public GroupOfItemsDto(GroupOfItems groupOfItems) {
        this(groupOfItems.getId(), groupOfItems.getName(), groupOfItems.getDescription(), groupOfItems.getPrice(), groupOfItems.getAmount());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
