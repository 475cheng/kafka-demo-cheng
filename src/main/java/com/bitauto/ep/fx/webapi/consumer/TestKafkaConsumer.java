package com.bitauto.ep.fx.webapi.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class TestKafkaConsumer {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * topics：监听的主题
     * topicPartitions：每个主题的监听详情
     *
     * @message 监听到的消息
     * @ConsumerRecord 每条消息在kafka中的详情 如：ConsumerRecord(topic = topic1, partition = 0, offset = 41405, CreateTime = 1545285547046, serialized key size = -1, serialized value size = 5, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = 32131)
     */
    @KafkaListener(topics = {"topic1", "topic2"},
            topicPartitions = {@TopicPartition(topic = "topic1", partitions = {"0", "3"}),    //监听topic1的主题，监听0和3的分区
                    @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "4"))    //监听topic2接收分区0和分区1的消息，但是分区1的消费者初始位置为4
            })
    public void consumer(String message, ConsumerRecord consumerRecord) {
        logger.info("message = " + message);
        logger.info("consumerRecord:" + consumerRecord.toString());
    }

    @KafkaListener(topics = {"mytopic"}, groupId = "test")
    public void consumer1(String message, ConsumerRecord consumerRecord) {
        logger.info("test.message = " + message);
        logger.info("test.consumerRecord:" + consumerRecord.toString());
    }
}

