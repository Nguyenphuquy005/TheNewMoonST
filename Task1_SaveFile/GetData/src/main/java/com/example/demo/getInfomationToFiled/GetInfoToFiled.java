package com.example.demo.getInfomationToFiled;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetInfoToFiled {
    public static void main(String[] args) {
         String output = getUrlContents("https://thecustomee.com/collections/all/products/50-scientists-tumbler.json");
        System.out.println(output);
        JSONObject obj = new JSONObject(output) ;
        JSONArray jsonArray = new JSONArray("product") ;
        System.out.println(jsonArray);
    }


    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
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
