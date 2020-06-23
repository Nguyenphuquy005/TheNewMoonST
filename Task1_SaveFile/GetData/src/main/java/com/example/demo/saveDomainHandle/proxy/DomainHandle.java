package com.example.demo.saveDomainHandle.proxy;
import com.example.demo.saveDomainHandle.saveLinhkDomain.ProxyAuthenticator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.*;
import java.util.*;

public class DomainHandle {
    private Queue<String> proxyQueue = new LinkedList<>();
    private String currentProxy;
    private List<String> domainList = new ArrayList<>();
    public static String getUrlContents(String theUrl) {
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
            System.out.println("test12345");

        }
        return content.toString();
    }
    public String getUrlContents(String theUrl, String proxyLink) {
        Proxy proxy = Proxy.NO_PROXY;
        if (!proxyLink.equals("") && !proxyLink.equals(("0.0.0.0"))) {
            List<String> proxyProfile = Arrays.asList(proxyLink.split(" "));
            if (!proxyProfile.get(0).equals(System.getProperty("http.proxyHost"))) {
                System.setProperty("http.proxyHost", proxyProfile.get(0));
                System.setProperty("http.proxyPort", proxyProfile.get(1));
                System.out.println("Change to proxy: " + proxyProfile.get(0));
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
            urlConnection.setConnectTimeout(2000);
            urlConnection.setReadTimeout(2000);
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            String temp = currentProxy;
            currentProxy = proxyQueue.poll();
            proxyQueue.add(temp);
            System.out.println(getUrlContents("http://ip-api.com/json/", currentProxy));
        }
        return content.toString();
    }
    public void getProxyFromPath(String path){
        try {
            File myObj = new File(path);
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
    public void getDomainFromPath(String path){
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String domain = myReader.nextLine();
                domainList.add(domain);
            }
            myReader.close();
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
    public void getItemFormUrl(String url){
        System.out.println("Stat with domain: "+ url);
        System.out.println("check valid proxy" +getUrlContents("http://ip-api.com/json/", currentProxy));
        int count=0;
        while (true){
            try {
                Thread.sleep(500);
                System.out.println("Try times: " + ++count);
                String input = getUrlContents(url+"/products.json?limit=1",currentProxy);
                System.out.println(getProductFromJSON(input, "products"));
            }catch (Exception ex){
                String temp = currentProxy;
                currentProxy = proxyQueue.poll();
                proxyQueue.add(temp);
                System.out.println(getUrlContents("http://ip-api.com/json/", currentProxy));
                count=0;
            }
        }
    }
    public String getProductFromJSON(String jsonString, String key){
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        return "Product: "+jsonArray.length()+"pc\n";
    }
    public static void main(String[] args) {
        DomainHandle domainHandle = new DomainHandle();
        domainHandle.getProxyFromPath("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
       // domainHandle.getDomainFromPath("D:\\TheNewMoonST\\file\\domain_file\\proxy");
        domainHandle.getData();
    }
    public void getData(){
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\get.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("1:  " + data);

                Boolean check = true;
                long p = 1;
                while (check) {
                    String output = getUrlContents(data+"/products.json?limit=500&page="+p , currentProxy);
                    System.out.println(output);
                    System.out.println(p);
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
                                System.out.println(data);
                                JSONObject objectResult = new JSONObject(array.get(i).toString());
                                if(objectResult.get("images").toString().equals("[]")){
                                    System.out.println("next Record");
                                }else {
                                    System.out.println(objectResult.getString("handle"));
//                                    SaveFile(data+"/products/"+objectResult.get("handle").toString());
                                }

                            }
                            p++ ;
                        }
                    }
                };

            };
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            e.fillInStackTrace();
        } catch (IOException e) {
            System.out.println( "Io Exception");
            e.printStackTrace();
        }
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
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\domain_file\\canvas_shopify1Handle.txt", true));
        writer.write(data + "\n");
        writer.close();
}
}

