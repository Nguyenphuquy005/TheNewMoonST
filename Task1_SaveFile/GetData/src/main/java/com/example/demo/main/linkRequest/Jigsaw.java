package com.example.demo.main.linkRequest;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Jigsaw {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\TheNewMoonST\\file\\shopify.txt"));
            String contentLine = br.readLine();
            while (contentLine != null) {
                if (CheckChart(contentLine)) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(contentLine);
                    SaveFile(contentLine);
                }
                contentLine = br.readLine();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean CheckChart(String link) {
        for (int i = 0; i < link.length(); i++) {
            for (int j = i + 1; j < link.length(); j++) {
                if (
//                        link.substring(i, j).equals("tumbler")
//                                ||
//                                link.substring(i, j).equals("outfits")
//                                ||
//                        link.substring(i, j).equals("t-shirt")
//                                ||
//                                link.substring(i, j).equals("dog") ||
//                        link.substring(i, j).equals("canvas")
//                                ||
//                        link.substring(i, j).equals("quilts")
//                                ||
//                        link.substring(i, j).equals("horoscopes")
//                                ||
//                        link.substring(i, j).equals("puzzle")
//                                ||
//                        link.substring(i, j).equals("native")
//                                ||
//                                link.substring(i, j).equals("pet")
//                                ||
//                        link.substring(i, j).equals("sport")
//                                ||
//                        link.substring(i, j).equals("hoodies")
//                                ||
//                                link.substring(i, j).equals("summer")
//                                ||
                                link.substring(i, j).equals("jigsaws")
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\link_request\\jigsaws.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
