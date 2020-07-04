//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.demo.updateJson;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.util.ArrayUtils;

@Controller
public class UpdatePrice {
    public UpdatePrice() {
    }

    @RequestMapping(
            value = {"/"},
            method = {RequestMethod.GET},produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity getURL(@RequestParam(name = "product") String product ) throws JSONException {
        String output = getUrlContents(product);
        JSONObject obj = new JSONObject(output);
        return new ResponseEntity(obj.toString(), HttpStatus.OK);
    }
    @RequestMapping(
            value = {"/products"},
            method = {RequestMethod.GET},produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity findAllProduct(@RequestParam(name = "product") String product ) throws JSONException {
        String output = getUrlContents(product);
        JSONObject obj = new JSONObject(output);
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        for(int i = 0; i < lenghts; ++i) {
            JSONObject itemArr = (JSONObject)obj.getJSONObject("product").getJSONArray("variants").get(i);

            if (itemArr.getString("title").equals("Buy 1")) {
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("price","4500000");
                itemArr.put("compare_at_price", (Collection< ? >) null);
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            } else if (itemArr.getString("title").equals("Buy 2")){
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("compare_at_price", (Collection< ? >) null);
                itemArr.put("option1", (Collection< ? >) null);
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            }else if (itemArr.getString("title").equals("Buy 3")){
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("compare_at_price", (Collection< ? >) null);
                itemArr.put("option1", (Collection< ? >) null);
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);

            }else if (itemArr.getString("title").equals("Buy 5")){
                System.out.println("old Price: " + itemArr.getString("price"));
                itemArr.put("compare_at_price", (Collection< ? >) null);
                itemArr.put("option1", (Collection< ? >) null);
                System.out.println("new Price: " + itemArr.getString("price"));
                System.out.println(i + "_" + itemArr);
            }
            else {
                System.out.println("3");
            }

            JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(0) ;
            JSONArray values = value.getJSONArray("values") ;
            for (int j = 1;j <= values.length();j++ ){
                values.remove(j);
                System.out.println(values);
            }
        }
        JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(0);
        JSONArray values = value.getJSONArray("values");

        return new ResponseEntity(obj.toString(), HttpStatus.OK);

    }




    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return content.toString();
    }
}
