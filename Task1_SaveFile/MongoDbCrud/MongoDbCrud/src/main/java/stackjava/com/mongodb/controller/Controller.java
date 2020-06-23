package stackjava.com.mongodb.controller;



import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.BsonDocument;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
public class Controller {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false");
        MongoDatabase database = mongoClient.getDatabase("demo");

    @GetMapping(path = "test")
    public FindPublisher Test(
    ){
        BsonDocument document = BsonDocument.parse("{status:'55.71'}") ;
        return database.getCollection("customer").find().limit(3);
    }

    @GetMapping(path = "test/{skip}")
    public FindPublisher TestAll(
            @PathVariable(name = "skip")  int page
    ){
        BsonDocument document = BsonDocument.parse("{status:'55.71'}") ;
        return database.getCollection("customer").find().limit(3).skip(page*3 - 3);
    }
}
