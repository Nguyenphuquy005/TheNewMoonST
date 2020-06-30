package com.example.demo.getDataNoJson;

import com.example.demo.model.Product;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class G13Filed {
    public static void main(String[] args) throws IOException {
        String url = "https://thecustomee.com/collections/all/products/cat-tumbler-7" ;
        String[]  handle = url.split("/") ;
        Document docElement = Jsoup.connect(url).get();
        System.out.println(handle[handle.length-1]);                                //handle
        System.out.println(docElement.selectFirst(".product_name").text());   // title
        Element variants = docElement.selectFirst(".single-option-selector") ;
             Element v =  docElement.after(".single-option-selector");
        System.out.println(v);
//        Elements listVariants = variants.getElementsByTag("option") ;
//        for(int i=0 ; i<listVariants.size();i++){
//            System.out.println(listVariants.get(i).attr("value"));
//        }


    replacePrice("https://thecustomee.com/collections/all/products/50-scientists-tumbler.json");

    }
    public static void replacePrice(String url) {

    }
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\link.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
    public static void SaveFileJson(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\JsonObject.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
