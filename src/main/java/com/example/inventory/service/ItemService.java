package com.example.inventory.service;

import com.example.inventory.repository.BrandRepository;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ItemRepository;
import com.example.inventory.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    // Create or Update Item
    public ItemEntity saveItem(ItemEntity item) {
        // Check if the category and brand are valid
        if (item.getCategory() == null || item.getBrand() == null) {
            throw new RuntimeException("Category and Brand cannot be null");
        }

        // Save the item
        return itemRepository.save(item);
    }

    // Update Item (by ID)
    public ItemEntity updateItem(Long id, ItemEntity item) {
        // Check if the item exists
        Optional<ItemEntity> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()) {
            ItemEntity itemToUpdate = existingItem.get();
            itemToUpdate.setName(item.getName());  // Update the name
            itemToUpdate.setCategory(item.getCategory());  // Update the category
            itemToUpdate.setBrand(item.getBrand());  // Update the brand
            return itemRepository.save(itemToUpdate);  // Save updated item
        } else {
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }

    // Get All Items
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    // Get Item by ID
    public Optional<ItemEntity> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    // Delete Item by ID
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}


