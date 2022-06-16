package com.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Collections;

import static com.kafka.utils.Constants.CONSUMER_TOPIC;

@Slf4j
public class KafkaSubscriber {
    public static final KafkaConsumer<String, String> consumer = SingleInstance.CONSUMER;

    public void startReceivingMessages() {

        //handing manual interruption
        handleManualInterruption();

        //subscribing to topic
        consumer.subscribe(Collections.singleton(CONSUMER_TOPIC));
        try {
            while (true) {
                log.info("Polling message..");
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord record : consumerRecords) {
                    log.info("received message [key: {}, value: {}]", record.key(), record.value());
                }
            }
        } catch (WakeupException wakeupException) {
            log.info("wakeup exit called because of manual interruption!");
        } catch (Exception exception) {
            log.error("exception occurred while receiving message: {}", exception.getMessage());
        } finally {
            log.info("closing consumer client connection...");
            consumer.close();
            log.info("consumer client connection closed.");
        }
    }

    private void handleManualInterruption() {
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Detected manual interruption shutdown call..");
            KafkaSubscriber.consumer.wakeup();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    //return consumer client
    private static class SingleInstance {
        private static final KafkaConsumer<String, String> CONSUMER = new KafkaConsumer(KafkaConsumerConfig.getConsumerProperties());
    }
}
