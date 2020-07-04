package com.example.demo.saveDomainHandle.saveLinhkDomain;

import java.io.*;
import java.util.Scanner;

public class FilterLink {
    public static void main(String[] args) throws IOException {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\json\\22.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine()  ;
                checkLink(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\json\\passtport\\passportHandle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
    public  static  void  checkLink(String url) throws IOException {
        Boolean check = false;
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\json\\passtport\\passportHandle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (url.equals(data) == true){
                    System.out.println("break");
                    check = false ;
                    break;
                }else {
                    check = true ;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (check == true){
            SaveFile(url);
        }
    }
    }
