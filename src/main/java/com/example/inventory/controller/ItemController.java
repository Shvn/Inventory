package com.example.inventory.controller;

import com.example.inventory.kafka.ItemProducer;
import com.example.inventory.service.ItemService;
import com.example.inventory.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemProducer itemProducer;

    @GetMapping
    public List<ItemEntity> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemEntity createItem(@RequestBody ItemEntity item) {
        ItemEntity savedItem =  itemService.saveItem(item);
        itemProducer.sendItemToKafka(savedItem);
        return savedItem;
    }

    @GetMapping("/{id}")
    public Optional<ItemEntity> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public ItemEntity updateItem(@PathVariable Long id, @RequestBody ItemEntity item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
