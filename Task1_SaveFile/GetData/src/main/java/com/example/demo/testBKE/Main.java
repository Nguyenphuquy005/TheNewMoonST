package com.example.demo.testBKE;

import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
       String str = "000tumbsdler.com" ;
       if (CheckChart(str)){
           System.out.println("ok");
       }else {
           System.out.println("noOK");
       }
    }

    public static boolean CheckChart(String link)  {
        for (int i=0 ; i < link.length() ; i++){
            for (int j = i+1 ; j < link.length() ; j++ ){
                if (link.substring(i,j).equals("tumbler")){
                    return true ;
                }
            }
        }
        return false ;
    }
}
