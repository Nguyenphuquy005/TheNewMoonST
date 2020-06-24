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
        Document docElement = Jsoup.connect("hhttps://www.houzz.com/products/wolf-girl-framed-canvas-giclee-by-oren-hayman-17x17-prvw-vr~79251825").get();
        System.out.println(docElement.selectFirst(".view-product-title").text());
        Element imagelist   = docElement.selectFirst(".product_gallery_nav");
        Elements images = imagelist.select("img") ;
        ArrayList<String> lists = new ArrayList<String>();

        for (int i=0;i<images.size();i++){
            images.get(i).attr("src") ;
            lists.add( images.get(i).attr("src")) ;
            System.out.println(lists);
        }
        org.bson.Document document = new org.bson.Document();
        document.append("name", docElement.selectFirst(".product_name").text());
        document.append("body_html",docElement.body());

         System.out.println(document.toJson());
//        SaveFileJson(document.toJson());

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
