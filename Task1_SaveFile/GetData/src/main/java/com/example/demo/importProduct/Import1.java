package com.example.demo.importProduct;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Import1 {

    public static void main(String[] args) {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\Domain\\123.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);

                myReader.close();
            }
            } catch(FileNotFoundException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

    }
    public static void getUrlContents(String data, String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }


    }
      public  static void loadURL(String url) {
          try {
              Desktop desktop = java.awt.Desktop.getDesktop();
              URI oURL = new URI(url);
              desktop.browse(oURL);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

    }






