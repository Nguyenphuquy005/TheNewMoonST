package com.example.demo.getDataNoJson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CaoHandle {
    public static void main(String[] args) throws IOException {
        for (int i =1 ; i<=16;i++){
            System.out.println(i);
            Document docAll = Jsoup.connect("https://minkyway.com/page/12/?s=sunshade&post_type=product").get();
//        Elements handle = docAll.select(".grid-view-item__link grid-view-item__image-container")  ;
            Element handleDIV = docAll.selectFirst("#shopify-section-search-template") ;
            Elements handle = handleDIV.select("a") ;
            System.out.println(handle.size());
            for(int t =0;t<handle.size();t++){
                if(CheckChart(handle.get(t).attr("href"))){
                    System.out.println( "https://jtamigo.com"+ handle.get(t).attr("href"));
                SaveFile(  "https://jtamigo.com" +  handle.get(t).attr("href"));
                }

            }
        }

    }
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\sunshade04Handle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
    public static boolean CheckChart(String link) {
        return link.toLowerCase().contains("products");
    }
}
