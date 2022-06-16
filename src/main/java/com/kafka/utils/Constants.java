package com.kafka.utils;

public final class Constants {
    private Constants() {
    }

    public static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
    public static final String CONSUMER_GROUP_ID = "test-grp-id-1";
    public static final String CONSUMER_AUTO_RESET_CONFIG = "earliest";
    public static final String CONSUMER_TOPIC = "test_topic";
    public static final String PRODUCER_TOPIC = "test_topic";
}
