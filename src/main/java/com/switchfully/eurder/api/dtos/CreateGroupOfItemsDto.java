package com.switchfully.eurder.api.dtos;

public class CreateGroupOfItemsDto {

    private final String name;
    private final String description;
    private final int price;


    public CreateGroupOfItemsDto(String name, String description, int price) {

        this.name = name;
        this.description = description;
        this.price = price;

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
