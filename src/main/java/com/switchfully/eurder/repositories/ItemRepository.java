package com.switchfully.eurder.repositories;

import com.switchfully.eurder.api.dtos.GroupOfItemsDto;
import com.switchfully.eurder.domain.GroupOfItems;
import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ItemRepository {

    Map<String, GroupOfItems> stock;

    public ItemRepository() {
        this.stock = new HashMap<>();
    }

    public Collection<GroupOfItems> getAllGroupOfItems() {
      return stock.values();
    }


    public GroupOfItems createNewGroupOfItem(GroupOfItems groupOfItem) {
        stock.put(groupOfItem.getId(), groupOfItem);
        return stock.get(groupOfItem.getId());
    }

    public GroupOfItems addItem(List<Item> itemsToAdd, Item itemToAdd) {
        System.out.println(itemsToAdd);
        Supplier<GroupOfItems> potentiallyNeededGroupOfItems = () -> createNewGroupOfItem(new GroupOfItems(itemToAdd.getName(), itemToAdd.getDescription(), itemToAdd.getPrice()));
        GroupOfItems groupOfItems = stock.values().stream()
                .filter(group -> group.getName().equals(itemToAdd.getName()))
                .findFirst()
                .orElseGet(potentiallyNeededGroupOfItems);

        groupOfItems.addItem(itemsToAdd);
        stock.put(groupOfItems.getId(), groupOfItems);
        return stock.get(groupOfItems.getId());
    }
}
