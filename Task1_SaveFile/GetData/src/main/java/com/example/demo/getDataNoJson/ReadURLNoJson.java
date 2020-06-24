package com.example.demo.getDataNoJson;

import java.io.IOException;

import com.example.demo.model.Product;
import com.google.gson.Gson;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadURLNoJson {
    public static void main(String[] args) throws IOException {

        System.out.println("Get infomation on many products");
        Document docAll = Jsoup.connect("https://thecustomee.com/collections/all/tumbler").get();
        Elements handle = docAll.select(".product-info__caption")  ;
        for(int i=0;i<handle.size();i++){
            System.out.println(    "https://thecustomee.com" + handle.get(i).attr("href"));
//            SaveFile("https://thecustomee.com" + handle.get(i).attr("href"));
        }
        System.out.println("_________________________");
        Document docElement = Jsoup.connect("https://thecustomee.com/collections/all/products/cat-tumbler-7").get();
        System.out.println(docElement.selectFirst(".product_name").text());
        System.out.println(docElement.selectFirst(".current_price ").text());
        Element imagelist   = docElement.selectFirst(".product_gallery_nav");
        Elements images = imagelist.select("img") ;
        ArrayList<String> lists = new ArrayList<String>();

        for (int i=0;i<images.size();i++){
            images.get(i).attr("src") ;
            lists.add( images.get(i).attr("src")) ;
            System.out.println(lists);
        }

        Product product = new Product(docElement.selectFirst(".product_name").text(),docElement.selectFirst(".current_price ").text(),
                lists)   ;

        Gson gson = new Gson();
        String json = gson.toJson(product) ;
        System.out.println(json);
        SaveFileJson(json);

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
