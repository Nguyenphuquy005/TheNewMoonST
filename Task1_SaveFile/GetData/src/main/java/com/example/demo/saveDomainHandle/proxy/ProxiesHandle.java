package com.example.demo.saveDomainHandle.proxy;

import com.example.demo.saveDomainHandle.saveLinhkDomain.ProxyAuthenticator;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.List;

public class ProxiesHandle {
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
            e.printStackTrace();
        }
        return content.toString();
    }

    static String getUrlContents(String theUrl, String proxyLink) {
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
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String getProductFromJSON(String jsonString, String key) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        return "Product: " + jsonArray.length() + "pc\n";
    }
}
