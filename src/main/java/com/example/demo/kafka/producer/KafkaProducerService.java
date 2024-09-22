package com.example.demo.kafka.producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Constructor injection
    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, Object>> sendMessage(String topic, Object message) {
        // Send message asynchronously
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message).toCompletableFuture();
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Message sent successfully to topic: " + topic + " at offset: " + result.getRecordMetadata().offset());
            } else {
                System.err.println("Failed to send message to topic: " + topic + " due to error: " + ex.getMessage());
            }
        });
        
        return future;
    }
}
