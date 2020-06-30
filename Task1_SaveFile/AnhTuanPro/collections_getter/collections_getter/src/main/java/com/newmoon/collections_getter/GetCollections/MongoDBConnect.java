package com.newmoon.collections_getter.GetCollections;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnect {
    private MongoCollection<Document> collection ;

    MongoDBConnect(String dbName, String collectionName, boolean createIDIndex) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase(dbName);
        collection = database.getCollection(collectionName);
        if(createIDIndex){
            IndexOptions indexOptions = new IndexOptions().unique(true);
            collection.createIndex(Indexes.ascending("id"), indexOptions);
        }
    }

    public Document save(Document document) {
        return document;
    }

    public Document update(Document document, double id) {
        return document;
    }

    public Document deleteById(Document document, double id) {
        return document;
    }

    public Document getById(double id) {
        return null;
    }

    public Document getByCustomeQuery(String query) {

        return null;
    }

    public void update(Document document) {
        Bson filter = eq("id", document.get("id"));
        Document document1 = new Document("$set", document);
        UpdateOptions optionAfter = new UpdateOptions().upsert(true);
        collection.updateOne(filter, document1, optionAfter);
    }

}
