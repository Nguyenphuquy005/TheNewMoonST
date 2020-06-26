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
        String url = "https://minkyway.com/product/limited-edition-3d-full-printing-tt051ass/" ;
        String[]  handle = url.split("/") ;
        Document docElement = Jsoup.connect(url).get();
        System.out.println(docElement.selectFirst(".variable-item-span").text());   // Product Type
        System.out.println(docElement.selectFirst(".product-title").text());        // title
        System.out.println(docElement.selectFirst(".variable-item-span").text());   // Variants
        System.out.println(handle[handle.length-1]);                                //handle
        Element listImg = docElement.selectFirst(".flickity-viewport");
        System.out.println(listImg);
//        Elements img = docElement.getElementsByTag("a") ;

//        System.out.println(img);
//        System.out.println(img);
//        for (int i =0 ; i< img.size() ; i++){
//            System.out.println(img.get(i).attr("href"));    // IMG
//        }



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
