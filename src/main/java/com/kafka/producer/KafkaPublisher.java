package com.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class KafkaPublisher {

    private static final KafkaProducer<String, String> producer = SingleInstance.PRODUCER;

    //sends message to kafka topic asynchronously
    public void sendMessage(ProducerRecord producerRecord) {
        log.info("sending message...");
        producer.send(producerRecord, (recordMetadata, e) -> {
            if (e == null) {
                log.info("Record [key: {}, value: {}] send successfully to Topic: {}, Partition: {} and Offset: {}",
                        producerRecord.key(), producerRecord.value(), recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
            } else {
                log.error("Error occurred while sending data: {}", e.getMessage());
            }
        });
        producer.flush();
    }

    public void closeProducerClientConnection() {
        log.info("closing producer client connection....");
        producer.close();
        log.info("producer client connection closed.");
    }

    //return producer client
    private static class SingleInstance {
        private static final KafkaProducer<String, String> PRODUCER = new KafkaProducer(KafkaProducerConfig.getProducerProperties());
    }

}
