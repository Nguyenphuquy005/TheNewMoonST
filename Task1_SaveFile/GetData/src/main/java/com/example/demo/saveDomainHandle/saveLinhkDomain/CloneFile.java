package com.example.demo.saveDomainHandle.saveLinhkDomain;

import java.io.*;
import java.util.Scanner;

public class CloneFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\json\\sunshadeHandle.txt");
            Scanner myReader = new Scanner(myObj);
            long i =1 ;
            long  k  = 1 ;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (k == 3001){
                    i++ ;
                    k = 1 ;
                }
                if(CheckChart(data.toLowerCase())){
                    System.out.println("next");
                }else {
                    System.out.println(data);
                    SaveFile(data,i);
                    k ++ ;
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
        return link.toLowerCase().contains("2-pieces");

    }
    public static void SaveFile(String data , long i) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\s"+i+"Handle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
