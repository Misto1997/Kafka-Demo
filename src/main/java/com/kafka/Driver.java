package com.kafka;

import com.kafka.consumer.KafkaSubscriber;
import com.kafka.producer.KafkaPublisher;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;

import static com.kafka.utils.Constants.PRODUCER_TOPIC;

@Slf4j
public class Driver {
    public static void main(String[] args) {

        //publishing messages
        KafkaPublisher kafkaPublisher = new KafkaPublisher();
        for (int i = 1; i <= 5; i++) {
            String key = "key=" + i;
            String value = "value=" + i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(PRODUCER_TOPIC, key, value);
            kafkaPublisher.sendMessage(producerRecord);
        }
        //closing producer
        kafkaPublisher.closeProducerClientConnection();


        //Starting consumer
        KafkaSubscriber kafkaSubscriber = new KafkaSubscriber();
        kafkaSubscriber.startReceivingMessages();


    }
}
