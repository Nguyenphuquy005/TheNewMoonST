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
        for (int i =1 ; i<=251;i++){
            System.out.println(i);
            Document docAll = Jsoup.connect("https://checkthis.store/shop/page/"+ i).get();
//        Elements handle = docAll.select(".grid-view-item__link grid-view-item__image-container")  ;
//            Element handleDIV = docAll.selectFirst("#shopify-section-search-template") ;
            Elements handle = docAll.select("a") ;
            System.out.println(handle.size());

            for(int t =0;t<handle.size();t++){
                if(CheckChart(handle.get(t).attr("href"))
                        && CheckChartPieces1s(handle.get(t).attr("href").toLowerCase())){
                    System.out.println( handle.get(t).attr("href"));
                    System.out.println( handle.get(t).text());
                     SaveFile( handle.get(t).attr("href"));
                }

            }
        }

    }
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\sunshade1PiecesHandle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
    public static boolean CheckChart(String link) {
        return link.toLowerCase().contains("product");
    }
    public static boolean CheckChartPieces(String link) {
        if(link.toLowerCase().contains("2 pieces")){
            return false ;
        }else {
            return true ;
        }
    }
    public static boolean CheckChartPieces1s(String link) {
        if(link.toLowerCase().contains("2-pieces")){
            return false ;
        }else {
            return true ;
        }
    }
}
