package com.example.demo.getDataNoJson;

import java.io.IOException;

import com.example.demo.model.Product;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


public class ReadURLNoJson {
    public static void main(String[] args) throws IOException {

        System.out.println("Get infomation on many products");
        Document docAll = Jsoup.connect("https://www.wayfair.com/decor-pillows/sb0/canvas-art-c261307.html").get();
        System.out.println(docAll.body());
//        Elements handle= docAll.body().selectFirst(".BrowseProductGrid").select("a.ProductName");
//        System.out.println(handle);
        Elements handle = docAll.select(".ProductCard")  ;
//        System.out.println(handle);
        System.out.println(handle.size());
        for(int i=0;i<handle.size();i++){
            System.out.println(    handle.get(i).attr("href"));
//            SaveFile("https://thecustomee.com" + handle.get(i).attr("href"));
        }
        System.out.println("_________________________");
        Document docElement = Jsoup.connect("https://thecustomee.com/collections/all/products/cat-tumbler-7").get();
        System.out.println(docElement.selectFirst(".product_name").text());
        System.out.println(docElement.selectFirst(".current_price").text());
        Element imagelist   = docElement.selectFirst(".product_gallery_nav");
        Elements images = imagelist.select("img") ;
        ArrayList<String> lists = new ArrayList<String>();

        for (int i=0;i<images.size();i++){
            images.get(i).attr("src") ;
            lists.add( images.get(i).attr("src")) ;
            System.out.println(lists);
        }

//        Product product = new Product(docElement.selectFirst(".product_name").text(),docElement.selectFirst(".current_price ").text(),
//                lists)   ;
//
//        Gson gson = new Gson();
//        String json = gson.toJson(product) ;
//        System.out.println(json);
//        SaveFileJson(json);

        String jsonString = new JSONObject()
                .append("name", docElement.selectFirst(".product_name").text())
                .append("price", docElement.selectFirst(".current_price ").text())
                .append("images", lists)
                .toString();
        System.out.println(jsonString);
        SaveFileJson(jsonString);
        org.bson.Document document = new org.bson.Document();
        document.append("Name", docElement.selectFirst(".product_name").text());
        document.append("Price",docElement.selectFirst(".current_price ").text());
        document.append("Images", lists);
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
