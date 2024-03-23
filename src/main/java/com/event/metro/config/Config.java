package com.event.metro.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Config extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Override
    protected @NonNull String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    public @Bean
    @NonNull MongoClient mongoClient() {
        return MongoClients.create(mongoDbUri);
    }

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
