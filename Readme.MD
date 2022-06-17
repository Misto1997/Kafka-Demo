# Introduction
This repository contains `Kafka Producer and Consumer` implementation using plain `Maven` project with `Java`.

## Note
I have not used `Spring Boot` here intentionally to reduce complexity and to make it easy to understand for beginners.

## Run guide
1. Install Kafka binary from here: `https://kafka.apache.org/downloads` and untar/unzip it.
2. Open terminal and goto kafka directory. e.g /Downloads/kafka_2.12-3.2.0
3. Start `Zookeeper` with this command: ./bin/zookeeper-server-start.sh  /config/zookeeper.properties
4. Start `Kafka` with this command: ./bin/kafka-server-start.sh  /config/server.properties
5. Create topic with this command: ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic test_topic --create
6. Clone this project, import needed packages via `mvn compile`.
7. Run the `Driver` class.
8. Consumer will keep polling messages, you have to manually `Shut Down` program to exit.
9. Change configs from `Constants` class if needed.
10. Tweak it as you want :)


## Reference

1. https://kafka.apache.org/quickstart
