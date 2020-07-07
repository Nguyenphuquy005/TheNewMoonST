package com.example.demo.spring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

@Controller
public class Test {
    @GetMapping("/test")
    public ResponseEntity getURL() {
        String body = getUrlContents("https://planetvegeta.me/collections/car-auto-sunshade/products/css-0474-car-auto-sun-shade.json");
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/test123", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findOptionProduct() throws JSONException {
        String output = getUrlContents("https://planetvegeta.me/collections/car-auto-sunshade/products/css-0474-car-auto-sun-shade.json");
        JSONObject obj = new JSONObject(output);
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        for (int i = 0; i < lenghts; i++) {
            JSONObject itemArr = (JSONObject) obj.getJSONObject("product").getJSONArray("variants").get(i);
            if (itemArr.getString("title").equals("Buy 1")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", "4564565");
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else if (itemArr.getString("title").equals("Buy 2")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", "4564565");
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            }
//            price += sum ;
        }
        int lenghtoption = obj.getJSONObject("product").getJSONArray("options").length();
        JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(0);
        JSONArray values = value.getJSONArray("values");
        values.remove(2);
        values.remove(4);
        values.remove(3);
        values.remove(5);
        System.out.println(values.get(0));
        return new ResponseEntity<>(obj.toString(), HttpStatus.OK);
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
