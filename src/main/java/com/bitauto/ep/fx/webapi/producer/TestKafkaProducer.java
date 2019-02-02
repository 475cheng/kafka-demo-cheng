package com.bitauto.ep.fx.webapi.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/alive")
public class TestKafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @RequestMapping("/producer")
    public String kafkaProducer() {
        kafkaTemplate.send("test", "kafkaTemplate-producer");
        return "OK";
    }

    /**
     * 发送消息时，可以指定发送到哪个partition ,key是什么
     * @return
     */
    @RequestMapping("/producer/partition")
    public String kafkaProducerPartition() {
        kafkaTemplate.send("mytopic", 1, "syc", "kafkaTemplate-producer");
        return "OK";
    }
}
