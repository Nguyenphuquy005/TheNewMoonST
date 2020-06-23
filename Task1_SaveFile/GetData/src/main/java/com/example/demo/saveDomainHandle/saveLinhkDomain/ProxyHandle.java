package com.example.demo.saveDomainHandle.saveLinhkDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.SSLException;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProxyHandle {
    public static void main(String[] args) {
        String[] c = {"192.3.214.15 4444 7215cbb11c BlFJbvA6","104.227.210.77 4444 7215cbb11c BlFJbvA6","107.172.138.53 4444 7215cbb11c BlFJbvA6",
                      "107.173.135.146 4444 7215cbb11c BlFJbvA6","107.175.68.105 4444 7215cbb11c BlFJbvA6","198.23.134.115 4444 7215cbb11c BlFJbvA6",
                       "23.229.27.225 4444 7215cbb11c BlFJbvA6","23.236.233.70 4444 7215cbb11c BlFJbvA6","45.57.230.150 4444 7215cbb11c BlFJbvA6",
                       "23.250.107.114 4444 7215cbb11c BlFJbvA6"};
        int rnd = new Random().nextInt(c.length);


        try {
            File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\get.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("1:  " + data);
//                1
                Boolean check = true;
                long p = 1;
                System.out.println(getUrlContents("http://ip-api.com/json/",getProxy("23.250.107.114 4444 7215cbb11c BlFJbvA6")));
                while (check) {

                    String output = getUrlContents(data+"/products.json?limit=2&page="+p,getProxy("23.250.107.114 4444 7215cbb11c BlFJbvA6"));
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
                                System.out.println(objectResult.getString("handle"));
//                                SaveFile(data+"/products/"+objectResult.get("handle").toString());
                            }
                            p++ ;
                        }
                    }
                };
//                1
            };
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isValidURL(String domain)
    {
        try
        {
            URL url = new URL("https://"+ domain +"/products.json?limit=1");
            url.toURI();
            return true;
        } catch (Exception exception)
        {
            return false;
        }
    }
    public static boolean checkValid( String domain) throws IOException {
        try {
            URL url = new URL("https://"+ domain +"/products.json?limit=1");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code==200){
                return true;
            }
        }
        catch (ConnectException | UnknownHostException| SSLException e){

        }
        return false;
    }


    public static boolean CheckChart(String link) {
        return link.toLowerCase().contains("canvas");

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

            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();

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
    private static String getUrlContents(String theUrl , Proxy proxy) {
        StringBuilder content = new StringBuilder();
        try {

            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection(proxy);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    public static Proxy getProxy(String link){
        List<String> proxyId = Arrays.asList(link.split(" "));
        Authenticator.setDefault(new ProxyAuthenticator(proxyId.get(2),  proxyId.get(3)));
        SocketAddress addr = new
                InetSocketAddress(proxyId.get(0), Integer.parseInt(proxyId.get(1)));

        return new Proxy(Proxy.Type.HTTP,addr);
    }

    public static void SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\domain_file\\canvas1-1-1Handle.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
