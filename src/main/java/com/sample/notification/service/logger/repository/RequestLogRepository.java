package com.sample.notification.service.logger.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sample.notification.service.logger.RequestLog;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Repository
public class RequestLogRepository {

    private final MongoCollection<RequestLog> requestLogCollection;

    public RequestLogRepository(@Value("${spring.data.mongodb.uri}") String mongoUri,
                                @Value("${spring.data.mongodb.database}") String databaseName) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = MongoClients.create(mongoUri);
        MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
        this.requestLogCollection = database.getCollection("requestLogs", RequestLog.class);
    }

    public void save(RequestLog requestLog) {
        requestLogCollection.insertOne(requestLog);
    }

    public List<RequestLog> findAll() {
        return requestLogCollection.find().into(new ArrayList<>());
    }
}
