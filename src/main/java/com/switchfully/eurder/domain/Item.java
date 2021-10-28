package com.switchfully.eurder.domain;

import com.switchfully.eurder.api.dtos.AddItemsDto;

import java.util.UUID;

public class Item {
    private final String id;
    private final String name;

    public Item(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Item(AddItemsDto dto) {
        this(dto.getName());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
