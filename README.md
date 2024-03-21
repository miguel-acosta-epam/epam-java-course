# EPAM Learning
### Kafka branch

I used custom kafka implementation, on wsl2, some useful commands:

---

Disable ipv6 wsl2:

    sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1  
    sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1

Start the server without zookeper, using kraft:

    # generate a Kafka UUID
    kafka-storage.sh random-uuid
    
    # This returns a UUID, for example 76BLQI7sT_ql1mBfKsOk9Q
    kafka-storage.sh format -t <uuid> -c ~/kafka_2.13-3.0.0/config/kraft/server.properties

Creating the topics for the exercise:

    kafka-topics.sh --bootstrap-server localhost:9092 --topic orders --create --partitions 3 --replication-factor 1
    kafka-topics.sh --bootstrap-server localhost:9092 --topic notifications --create --partitions 3 --replication-factor 1

to list the topics:

    kafka-topics.sh --bootstrap-server localhost:9092 --list


And that's it, you only need the kafka server running and the topics created.