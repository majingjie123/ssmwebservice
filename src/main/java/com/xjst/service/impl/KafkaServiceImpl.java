package com.xjst.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements MessageListener<Integer, String> {
    @Override
    public void onMessage(ConsumerRecord<Integer, String> msg) {
        System.out.println(msg.value());
    }
}
