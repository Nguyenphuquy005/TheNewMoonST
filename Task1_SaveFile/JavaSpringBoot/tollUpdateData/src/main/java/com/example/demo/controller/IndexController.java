package com.example.demo.controller;

import com.example.demo.model.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String getIndex(Model model){
        model.addAttribute("url" , new Record()) ;
        return "index" ;
    }
   @RequestMapping(value = "/run")
    public ResponseEntity getContent(Record record){
       System.out.println(record.getUrl());
       String output = getUrlContents(record.getUrl());
       return new ResponseEntity(output, HttpStatus.OK);
   }
   @RequestMapping(value = "/run123")
    public ResponseEntity getContent123(){
       String output = getUrlContents("https://www.ashtabula.com.au/collections/hawaiian-shirts-nrl-soo/products/nrl-raiders-hawaiian-shirt-adult-1.json");
       return new ResponseEntity(output, HttpStatus.OK);
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
