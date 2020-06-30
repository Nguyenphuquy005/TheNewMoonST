package com.example.demo.myProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Task1 {
    public static void main(String[] args) throws IOException {
        String url = "https://www.bestiemoon.com/products/hulk-smash-auto-sun-shade " ;
        String[]  handle = url.split("/") ;
        Document docElement = Jsoup.connect(url).get();
        System.out.println(docElement.selectFirst(".variable-item-span").text());   // Product Type
        System.out.println(docElement.selectFirst(".product-title").text());        // title
        System.out.println(docElement.selectFirst(".variable-item-span").text());   // Variants
        System.out.println(handle[handle.length-1]);                                //handle
        Element listImg = docElement.selectFirst(".flickity-slider");
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
