package com.switchfully.eurder.api.dtos;

import com.switchfully.eurder.domain.Item;

public class ItemDto {
    private final String Id;
    private final String groupeId;
    private final String name;
    private String description;
    private int price;

    public ItemDto(Item item, String description, int price) {
        this.Id = item.getId();
        this.groupeId = item.getId();
        this.name = item.getName();
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return Id;
    }

    public String getGroupeId() {
        return groupeId;
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
}
