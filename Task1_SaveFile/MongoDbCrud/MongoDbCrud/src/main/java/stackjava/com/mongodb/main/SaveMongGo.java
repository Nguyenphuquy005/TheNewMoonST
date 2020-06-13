package stackjava.com.mongodb.main;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;


public class SaveMongGo {

    public static void main(String[] args) {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("demo");
        MongoCollection<Document> collection = database.getCollection("Products");



//        String output = getUrlContents("https://0011am.co/products.json?limit=1");
//        System.out.println(output);
//        String[] myStringArray = new String[]{output};
//       output = output.toString().substring(13,output.toString().length()-3);
//        System.out.println(output);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Product artist = mapper.readValue(output, Product.class);
//            System.out.println(artist.getBody_html());
//            ArrayList listTag = new ArrayList();
//            listTag.add("asd");
//            listTag.add("132");
//            Document document = new Document("_id",new Object());
//            document.append("id",artist.getId())
//                     .append("title",artist.getTitle())
//                        .append("handle",artist.getHandle())
//                        .append("body_html",artist.getBody_html())
//                        .append("published_at",artist.getPublished_at())
//                        .append("created_at",artist.getCreated_at())
//                        .append("updated_at",artist.getUpdated_at())
//                        .append("vendor",artist.getVendor())
//                        .append("product_type",artist.getProduct_type())
//                        .append("tags",listTag);
//            database.getCollection("Products").insertOne(document);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        document.append("id",ar)
    }




//    private static String getUrlContents(String theUrl) {
//        StringBuilder content = new StringBuilder();
//        try
//        {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null)
//            {
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
    }

