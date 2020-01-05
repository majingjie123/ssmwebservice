package com.xjst.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements MessageListener<String, String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> msg) {
        System.out.println(msg.value());
        System.out.println(msg.offset());
        System.out.println(msg.checksum());
    }
}
