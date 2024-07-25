/*
 * Copyright (c) 2024. Balamurugan Tamilmani. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are not permitted.
 */

package com.balatamilmani.kafkasslconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    /**
     * 
     * @return
     */
    @GetMapping("/greet")
    public String getMethodName() {
        return new String("Greetings from Kafka consumer using SSL Certificates !!");
    }

}
