/*
 * Copyright (c) 2024. Balamurugan Tamilmani. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted.
 */

package com.balatamilmani.kafkasslconsumer.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;

import com.balatamilmani.kafkasslconsumer.model.Student;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    SslBundles sslBundles;

    @Value("${consumer.key.deserializer.class}")
    private String keyDeserializerClass;

    @Value("${consumer.value.deserializer.class}")
    private String valueDeserializerClass;

    @Bean
    public ByteArrayJsonMessageConverter jsonConverter(){
        return new ByteArrayJsonMessageConverter();
    }
    
    /**
     * 
     * @param consumerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Student> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, Student> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, Student> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, Student>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return concurrentKafkaListenerContainerFactory;
    }

    /**
     * 
     * @param kafkaProperties
     * @param sslBundles
     * @return
     */
    @Bean("consumerFactory")
    public ConsumerFactory<String, Student> consumerFactory(KafkaProperties kafkaProperties, SslBundles sslBundles){
        ConsumerFactory<String, Student> consumerFactory = null;
        Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties(sslBundles);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializerClass);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializerClass);
        consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProperties);
        return consumerFactory;
    }
}
