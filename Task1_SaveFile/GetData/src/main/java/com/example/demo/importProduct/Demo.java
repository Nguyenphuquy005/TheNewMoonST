package com.example.demo.importProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {
    private static String currentProxy;
    public static void main(String[] args) {
        StandardProxy domainHandle = new StandardProxy();
        domainHandle.getProxyFromPath("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\Domain\\123.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                StandardProxy standardProxy = new StandardProxy() ;
                StandardProxy.getUrlContents(data,currentProxy) ;
                myReader.close();
            }
        } catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
