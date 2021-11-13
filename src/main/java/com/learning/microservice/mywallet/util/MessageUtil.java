package com.learning.microservice.mywallet.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessageUtil {
    @Autowired
    Environment environment;
    //@Bean
    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}
