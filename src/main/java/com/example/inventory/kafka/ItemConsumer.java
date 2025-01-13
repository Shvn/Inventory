package com.example.inventory.kafka;

import com.example.inventory.entity.ItemEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ItemConsumer {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ItemConsumer.class);

    @KafkaListener(topics = "item-topic", groupId = "inventory-group")
    public void consumeItem(String message) {
        try {
            ItemEntity item = new ObjectMapper().readValue(message, ItemEntity.class);
            mongoTemplate.save(item);
            logger.info("Item saved to MongoDB: {}", item);
        } catch (JsonProcessingException e) {
            logger.error("An error occurred while consuming the message: {}", e.getMessage(), e);
        }
    }
}
