package com.alexaframework.springalexa.db;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public AmazonDynamoDBClient amazonDynamoDBClient() {
        return new AmazonDynamoDBClient();
    }
}
