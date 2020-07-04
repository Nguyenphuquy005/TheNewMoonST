package com.example.demo.spring;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class GetURL {

    @GetMapping("/my")
    public ResponseEntity getURL() {
        String body = getUrlContents("https://thecustomee.com/collections/all/products/50-scientists-tumbler.json");
        return new ResponseEntity(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/productsmy", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findPriceProduct() throws JSONException {
        String output = getUrlContents("https://thecustomee.com/collections/all/products/50-scientists-tumbler.json");
        JSONObject obj = new JSONObject(output);
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        for (int i = 0; i < lenghts; i++) {
            JSONObject itemArr = (JSONObject) obj.getJSONObject("product").getJSONArray("variants").get(i);

            if (itemArr.getString("title").equals("20oz")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", "35000");
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else if (itemArr.getString("title").equals("30oZ")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price", "2000");

                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else {
                System.out.println("3");
            }
//            price += sum ;
        }
        System.out.println(obj);


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
