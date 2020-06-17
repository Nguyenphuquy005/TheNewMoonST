package stackjava.com.mongodb.controller;



import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
public class Controller {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://root:toor@cluster0-xfu3n.mongodb.net/nmt");
        MongoDatabase database = mongoClient.getDatabase("nmt");

    @GetMapping(path = "test")
    public FindPublisher Test(){
//        BsonDocument document = BsonDocument.parse("{status:'55.71'}") ;
        return database.getCollection("orders").find();
    }
}
