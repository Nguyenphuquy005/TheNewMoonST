package com.example.demo.spring;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdatePrice {

    @RequestMapping(value = "/updatePrice", method = RequestMethod.GET)
    public ResponseEntity testProduct() throws JSONException {
        String output = updatePrice("https://thecustomee.com/collections/all/products/50-scientists-tumbler", 25.25, 25.32);
        return new ResponseEntity<>(updatePrice("https://thecustomee.com/collections/all/products/50-scientists-tumbler", 25.25, 25.32), HttpStatus.OK);
    }

    public static String updatePrice(String data, Double price1, Double price2) throws JSONException {
        String output = getUrlContents(data + ".json");
        JSONObject obj = new JSONObject(output);
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        for (int i = 0; i < lenghts; i++) {
            JSONObject itemArr = (JSONObject) obj.getJSONObject("product").getJSONArray("variants").get(i);

            if (itemArr.getString("title").equals("20oz")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", price1.toString());
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else if (itemArr.getString("title").equals("30oZ")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", price2.toString());
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else {
                System.out.println("3");
            }
//            price += sum ;
        }
        System.out.println(obj);
        return obj.toString();
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
