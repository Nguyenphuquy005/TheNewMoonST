package com.example.demo.updatePrices;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UpdateArray {
    public  static void  main(String[] args){
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\json\\handle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                updatePrice(data , (double) 12.5 , (double) 20);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public  static  void updatePrice(String data , Double  price , Double sum){
        String output = getUrlContents(data+".json") ;
        JSONObject obj = new JSONObject(output) ;
        int lenghts= obj.getJSONObject("product").getJSONArray("variants").length();
        for(int i =0; i< lenghts ; i++){
            JSONObject itemArr = (JSONObject) obj.getJSONObject("product").getJSONArray("variants").get(i);
            System.out.println( "old Price: "  + itemArr.getString("price"));
            itemArr.put("price", price.toString()) ;
            System.out.println( "new Price: "  + itemArr.getString("price"));
            System.out.println( i+ "_" + itemArr);
            price += sum ;
        }
        System.out.println(obj);
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
