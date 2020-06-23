package com.example.demo.saveDomainHandle.saveLinhkDomain;

import java.io.*;
import java.util.Scanner;

public class CloneFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\canvas_ImgHandle.txt");
            Scanner myReader = new Scanner(myObj);
            long i =2 ;
            long  k  = 1 ;
            while (myReader.hasNextLine()) {

                if (k == 5001){
                    i++ ;
                    k = 1 ;
                }
                String data = myReader.nextLine();
                    System.out.println(data);
                    SaveFile(data,i);
                    k ++ ;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean CheckChart(String link) {
        return link.toLowerCase().contains("canvas");

    }
    public static void SaveFile(String data , long i) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\domain_file\\Canvas\\handle\\canvas_shopify"+i+"Handle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
