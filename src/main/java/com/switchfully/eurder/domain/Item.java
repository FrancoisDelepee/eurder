package com.switchfully.eurder.domain;

import com.switchfully.eurder.api.dtos.AddItemsDto;

import java.util.UUID;

public class Item {
    private final String id;
    private final String name;
    private String description;
    private int price;

    public Item(String name, String description, int price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(AddItemsDto dto) {
        this(dto.getName(), dto.getDescription(), dto.getPrice());
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
