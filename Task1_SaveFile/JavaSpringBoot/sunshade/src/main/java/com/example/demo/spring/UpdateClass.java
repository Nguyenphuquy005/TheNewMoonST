package com.example.demo.spring;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;

@Controller
public class UpdateClass {
    @RequestMapping(
            value = {"/productsQ"},
            method = {RequestMethod.GET},produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity findAllProductQ(@RequestParam(name = "productQ") String product ) throws JSONException{
        String output = getUrlContents(product);
        JSONObject obj = new JSONObject(output);
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        if (obj.getJSONObject("product").getJSONArray("options").length() == 1){
            JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(0) ;
            JSONArray values = value.getJSONArray("values") ;
            int i = 1;
            while (i <= values.length()){
                values.remove(0);
            }
            values.put("S");
            values.put("M");
            values.put("L");
            values.put("XL");
            values.put("2XL");
            values.put("3XL");
            values.put("4XL");
            values.put("5XL");
            for(int j = 0; j < lenghts; j++) {
                JSONObject itemArr = (JSONObject)obj.getJSONObject("product").getJSONArray("variants").get(j);
                if (itemArr.getInt("position") == 1) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","S");
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getInt("position") == 2) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","M");
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getInt("position") == 3) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","L");
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getInt("position") == 4) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","XL");
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getInt("position") == 5) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","2XL");
                    itemArr.put("price", "34.95");
                }
                else if (itemArr.getInt("position") == 6) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","3XL");
                    itemArr.put("price", "35.95");
                }
                else if (itemArr.getInt("position") == 7) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","4XL");
                    itemArr.put("price", "36.95");
                }
                else if (itemArr.getInt("position") == 8) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("option2","5XL");
                    itemArr.put("price", "37.95");
                }
                continue;

            }
        }else {
            JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(1) ;
            JSONArray values = value.getJSONArray("values") ;
            int i = 1;
            while (i <= values.length()){
                values.remove(0);
            }
            values.put("S");
            values.put("M");
            values.put("L");
            values.put("XL");
            values.put("2XL");
            values.put("3XL");
            values.put("4XL");
            values.put("5XL");
            for(int j = 0; j < lenghts; j++) {
                JSONObject itemArr = (JSONObject)obj.getJSONObject("product").getJSONArray("variants").get(j);
                if (itemArr.getString("option2").equals("S")) {
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getString("option2").equals("M")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getString("option2").equals("L")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getString("option2").equals("XL")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "33.95");
                }
                else if (itemArr.getString("option2").equals("2XL")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "34.95");
                }
                else if (itemArr.getString("option2").equals("3XL")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "35.95");
                }
                else if (itemArr.getString("option2").equals("4XL")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "36.95");
                }
                else if (itemArr.getString("option2").equals("5XL")){
                    itemArr.put("compare_at_price",(Collection< ? >) null);
                    itemArr.put("price", "37.95");
                }
                continue;

            }
        }




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
