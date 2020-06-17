package com.example.demo.main;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReadSaveFile {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");


        MongoDatabase database = mongoClient.getDatabase("demo");
        MongoCollection<Document> collection = database.getCollection("Products");
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\shopify_link.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                int p = 1;
                Boolean check = true;
                String data = myReader.nextLine();
                while (check)
                {
                    String output = getUrlContents("https://"+data+"/products.json?limit=1&page="+p);

                    JSONObject obj = new JSONObject(output);
                    JSONArray products = obj.getJSONArray("products");
                    System.out.println(products.get(3));
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < products.length(); i++) {
                        Document doc = Document.parse(products.getJSONObject(i).toString());
                        arrayList.add(doc) ;
                    }
                    if (arrayList.isEmpty() == true){
                        check = false ;

                    }else {
                       collection.insertMany(arrayList);
                        p++ ;
                    }

                };

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void checkURL(String url) {
        boolean check = true;
        while (check) {

        }
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
