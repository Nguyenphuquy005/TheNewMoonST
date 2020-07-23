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
import java.util.Collection;
import java.util.Scanner;

@Controller
public class Test {


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity findOptionProduct() throws JSONException {
        String output = getUrlContents("https://www.powderaddicts.com/collections/leggings/products/black-and-white-leggings.json");
        JSONObject obj = new JSONObject(output);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id",1234567891);
        jsonObj.put("title","Gloss Poster / 50x75");
        jsonObj.put("price","74.95");
        jsonObj.put("sku","");
        jsonObj.put("position",4);
        jsonObj.put("inventory_policy","continue");
        jsonObj.put("compare_at_price","");
        jsonObj.put("fulfillment_service","scalable-press");
        jsonObj.put("inventory_management",null);
        jsonObj.put("option1","Canvas");
        jsonObj.put("option2","50 x 75cm (19.7 x 29.5 inches)");
        jsonObj.put("option3",null);
        jsonObj.put("created_at","2018-02-09T09:23:29-08:00");
        jsonObj.put("updated_at","2019-11-12T17:54:17-08:00");
        jsonObj.put("taxable",true) ;
        jsonObj.put("barcode",null);
        jsonObj.put("grams",227) ;
        jsonObj.put("image_id",1324567891) ;
        jsonObj.put("weight",0.5);
        jsonObj.put("weight_unit","lb");
        jsonObj.put("old_inventory_quality",-3);
        jsonObj.put("requires_shipping",true);
        obj.getJSONObject("product").getJSONArray("variants").put(jsonObj) ;

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
