package com.example.demo.saveDomainHandle;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\sunshadeLink.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Boolean check = true;
                long p = 1;
                while (check) {
                    String output = getUrlContents(data + "/products.json?limit=500&page=" + p);
                    System.out.println(output);
                    if (output.equals("{\"products\":[]}")){
                        check = false ;
                    };
                    if (isJSONValid(output)) {
                        JSONObject object = new JSONObject(output) ;
                        JSONArray array = object.getJSONArray("products");
                        System.out.println(array.length());
                        if (array.length() == 0){
                            System.out.println("next Page");
                            check = false ;
                        } else {
                            for (int i=0 ; i< array.length() ; i++){
                                System.out.println(i);
                                System.out.println(array.length());
                                System.out.println("2:  "  + data);
                                JSONObject objectResult = new JSONObject(array.get(i).toString());
                                if(CheckChart(objectResult.getString("handle").toLowerCase())){
                                    System.out.println(objectResult.getString("handle"));
                                SaveFile(data+"/products/"+objectResult.get("handle").toString());
                                }else {
                                    System.out.println("next Record");
                                }
                            }
                            p++ ;
                        }
                    }
                };

            };
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean CheckChart(String link) {
        return link.toLowerCase().contains("sunshade");

    }
    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    private static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\domain_file\\sunshadeHandle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
