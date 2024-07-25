/*
 * Copyright (c) 2024. Balamurugan Tamilmani. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted.
 */

package com.balatamilmani.kafkasslconsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.balatamilmani.kafkasslconsumer.model.Student;

@Configuration
public class KafkaTopicListener {
    
    private Logger LOGGER = LoggerFactory.getLogger(KafkaTopicListener.class);

    //group.instance.id property is used to make this consumer as Static member
    @KafkaListener(topics="${topic.name}", groupId = "${consumer.group.id}", properties ={"group.instance.id=${group.instance.id}"} )
    public void consume(
        @Header(KafkaHeaders.RECEIVED_KEY) String key,
        @Header(KafkaHeaders.OFFSET) long offset,
        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
        @Payload Student student
    ) {
        LOGGER.info("Consumer received an event............");
        LOGGER.info(String.format("key=%s, offset=%d, partition=%d",key, offset, partition));
        LOGGER.info(student.toString());
    }
}
