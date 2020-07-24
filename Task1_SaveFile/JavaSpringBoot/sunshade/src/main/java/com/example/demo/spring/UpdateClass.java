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
            value = {"/products"},
            method = {RequestMethod.GET},produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity findAllProductQ(@RequestParam(name = "product") String product ) throws JSONException {
        String output = getUrlContents(product);
        JSONObject obj = new JSONObject(output);
        obj.getJSONObject("product").put("body_html", "<p><strong>SHIPPING AND DELIVERY</strong><br><span>Processing time: 3-5 Business days (Depend on products styles)</span><br><span>Delivery time</span><br><span>- <strong>Express Shipping (with insurance)</strong><strong>:</strong> within 15 - 20 working days</span><br><span>- <strong>Free Shipping (Standard Delivery)</strong>: within 2 - 6 weeks</span></p><p><strong>MATERIAL &amp; DESCRIPTION</strong></p><ul><li><strong><span>Mug holds 11oz / 325ml of your favorite hot or cold beverage.</span></strong></li><li><span>Design professionally printed on both sides of the mug so that everyone can see the awesome graphic whether you are right or left-handed.</span></li><li><span>Printed on only the highest quality mugs. The print will never fade no matter how many times it is washed.</span></li><li>100% Dishwasher and Microwave safe</li></ul><ul></ul><ul></ul>") ;
        JSONObject value = (JSONObject) obj.getJSONObject("product").getJSONArray("options").get(0);
        JSONArray values = value.getJSONArray("values");
        int i = 1;
        while (i <= values.length()) {
            values.remove(0);
        }

        values.put("11oz");
        int lenghts = obj.getJSONObject("product").getJSONArray("variants").length();
        for(int j = 0; j < lenghts; j++) {
            JSONObject itemArr = (JSONObject)obj.getJSONObject("product").getJSONArray("variants").get(j);

            if (itemArr.getString("title").contains("10oz")) {
                itemArr.put("compare_at_price", (Collection< ? >) null);
                itemArr.put("price", "19.95");
                itemArr.put("option1", "11oz");
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
