package com.switchfully.eurder.api.dtos;

public class AddItemsDto {

    private final String name;
    private final String description;
    private final int price;
    private final int amount;

    public AddItemsDto(String name, String description, int price, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
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
