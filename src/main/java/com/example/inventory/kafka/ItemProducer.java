package com.example.inventory.kafka;

import com.example.inventory.entity.ItemEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ItemProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "item-topic";

    private static final Logger logger = LoggerFactory.getLogger(ItemProducer.class);

    public void sendItemToKafka(ItemEntity item) {
        try {
            String message = new ObjectMapper().writeValueAsString(item);
            kafkaTemplate.send(TOPIC, message);
            logger.info("Message sent to Kafka: {}", message);
        } catch (JsonProcessingException e) {
            logger.error("An error occurred while producing the message: {}", e.getMessage(), e);
        }
    }
}

