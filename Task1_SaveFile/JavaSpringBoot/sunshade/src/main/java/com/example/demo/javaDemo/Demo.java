package com.example.demo.javaDemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
    public static void main(String[] args) throws JSONException {
    String output = getUrlContents("https://www.powderaddicts.com/collections/leggings/products/black-and-white-leggings.json");
    JSONObject obj = new JSONObject(output);
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("title","abcxyz");
    System.out.println(jsonObj.toString());
    obj.getJSONObject("product").getJSONArray("variants").put(jsonObj) ;
    int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
    for (int i=0;i<lenghts;i++){
        System.out.println(obj.getJSONObject("product").getJSONArray("variants").get(i));
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
