package com.example.demo.saveDomainHandle.saveLinhkDomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

public class DemoProxy {
    public   Queue<String> proxyQueue = new LinkedList<>();
    public     String currentProxy ;
    public  void getProxyFromPath(){
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String proxy = myReader.nextLine();
                proxyQueue.add(proxy);
            }
            myReader.close();
            currentProxy= "";
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public  String getUrlContents(String theUrl, String proxyLink) {
        Proxy proxy = Proxy.NO_PROXY;
        if (!proxyLink.equals("")) {
            List<String> proxyProfile = Arrays.asList(proxyLink.split(" "));
            if(!proxyProfile.get(0).equals(System.getProperty("http.proxyHost"))){
                System.setProperty("http.proxyHost", proxyProfile.get(0));
                System.setProperty("http.proxyPort", proxyProfile.get(1));
                System.out.println("Change to proxy: "+ proxyProfile.get(0));
                Authenticator.setDefault(new ProxyAuthenticator(proxyProfile.get(2), proxyProfile.get(3)));
            }
            SocketAddress addr = new
                    InetSocketAddress(proxyProfile.get(0), Integer.parseInt(proxyProfile.get(1)));
            proxy = new Proxy(Proxy.Type.HTTP, addr);
        }
        StringBuilder content = new StringBuilder();
        try {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection(proxy);
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    public void getItemFormUrl(String url) {
        System.out.println("Stat with domain: "+ url);
        System.out.println("check valid proxy" + getUrlContents("http://ip-api.com/json/", currentProxy));
        int count=0;
        int page=0;
        while (true){
            try {
                Thread.sleep(250);
                System.out.println("Try times: " + ++count);
                String input = getUrlContents(url+"/products.json?limit=1",currentProxy);
                System.out.println(input.length());
//                System.out.println(getProductFromJSON(input, "products"));
            }catch (Exception ex){
//                String temp = currentProxy;
                currentProxy = proxyQueue.poll();
//                proxyQueue.add(temp);
                System.out.println(getUrlContents("http://ip-api.com/json/", currentProxy));
                count=0;
            }
        }
    }
    public static void main(String[] args) {
        DemoProxy demoProxy = new DemoProxy();
       demoProxy.getProxyFromPath();
        demoProxy.getItemFormUrl("https://www.thecanvascompany.com.co");
    }
}
