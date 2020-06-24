package com.example.demo.getDataNoJson;

import com.example.demo.model.Product;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GetInfomation {
    public static void main(String[] args) throws IOException {
                getHandle("https://thecustomee.com/collections/all/tumbler");
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\json\\handle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                 getInfo(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
   public static  void getInfo(String url) throws IOException {
       System.out.println("_________________________");
       Document docElement = Jsoup.connect(url).get();
       System.out.println(docElement.selectFirst(".product_name").text());
       System.out.println(docElement.selectFirst(".current_price ").text());
       Element imagelist   = docElement.selectFirst(".product_gallery_nav");
       Elements images = imagelist.select("img") ;
       ArrayList<String> lists = new ArrayList<String>();

       for (int i=0;i<images.size();i++){
           images.get(i).attr("src") ;
           lists.add( images.get(i).attr("src")) ;
       }

       Product product = new Product(docElement.selectFirst(".product_name").text(),docElement.selectFirst(".current_price ").text(),
               lists)   ;


   }
    public static void getHandle(String data) throws IOException {
        System.out.println("Get infomation on many products");
        Document docAll = Jsoup.connect(data).get();
        Elements handle = docAll.select(".product-info__caption");
        for (int i = 0; i < handle.size(); i++) {
            System.out.println("https://thecustomee.com" + handle.get(i).attr("href"));
            SaveFile("https://thecustomee.com" + handle.get(i).attr("href"),"D:\\TheNewMoonST\\file\\json\\handle.txt");
        }
    }
        public static void SaveFile(String data , String file) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(data + "\n");
            writer.close();
        }
    public static void SaveFileJson(String data ,String file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(data + "\n");
        writer.close();
    }

}
