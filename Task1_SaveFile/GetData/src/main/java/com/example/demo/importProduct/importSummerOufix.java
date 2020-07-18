package com.example.demo.importProduct;
import com.example.demo.saveDomainHandle.saveLinhkDomain.ProxyAuthenticator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.*;

public class importSummerOufix {
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
//            urlConnection.setConnectTimeout(2000);
//            urlConnection.setReadTimeout(2000);
            urlConnection.setConnectTimeout(350);
            urlConnection.setReadTimeout(350);
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
        importSummerOufix domainHandle = new importSummerOufix();
        domainHandle.getProxyFromPath("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
       // domainHandle.getDomainFromPath("D:\\TheNewMoonST\\file\\domain_file\\proxy");
        domainHandle.getData();
    }
    public void getData(){
        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\LinkHandle\\132.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
               getUrlContents(data ,currentProxy);

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
            // e.g. in case JSONArray is valid as well...
            // edited, to include @Arthur's comment
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\08\\t-shirts.txt", true));
        writer.write(data + "\n");
        writer.close();

}
}

