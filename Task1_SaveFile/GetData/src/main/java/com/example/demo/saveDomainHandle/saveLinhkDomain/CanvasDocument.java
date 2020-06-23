package com.example.demo.saveDomainHandle.saveLinhkDomain;

import java.io.*;
import java.util.Scanner;

public class CanvasDocument {
    public static void main(String[] args) {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\zalooHandle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
               if(CheckChart(data)){
                    System.out.println(data);
                    SaveFile(data);
                }else {
                   System.out.println("nextRecord");
               }
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
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\domain_file\\zalooCanvasHandle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
