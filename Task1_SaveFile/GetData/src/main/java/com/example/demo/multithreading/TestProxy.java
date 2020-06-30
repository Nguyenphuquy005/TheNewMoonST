package com.example.demo.multithreading;

import com.example.demo.saveDomainHandle.saveLinhkDomain.ProxyAuthenticator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class TestProxy {
    public static void main(String[] args) throws InterruptedException, IOException {
//        UrlHandle urlHandle = new UrlHandle();
//        int count = 0;
//        while (true) {
//            System.out.println("Try times: " + ++count);
//            Thread.sleep(250);
//            List<String> result = urlHandle.getJsonFromURL("https://www.thecanvascompany.com.co/products.json?limit=1");
//            System.out.println(result.size());
//            System.out.println();
//        }
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        System.setProperty("jdk.http.auth.proxying.disabledSchemes", "");
        URL url = new URL("https://www.thecanvascompany.com.co/products.json?limit=1");
        String result = getStringTest(Proxy.NO_PROXY);
        System.out.println("Test result:" + result);
        List<String> proxyProfile1 = Arrays.asList("107.173.135.146 4444 7215cbb11c BlFJbvA6".split(" "));
        List<String> proxyProfile2 = Arrays.asList("104.227.210.77 4444 7215cbb11c BlFJbvA6".split(" "));
        List<String> proxyProfile3 = Arrays.asList("107.175.68.105 4444 7215cbb11c BlFJbvA6".split(" "));
        List<String> proxyProfile4 = Arrays.asList("23.229.27.225 4444 7215cbb11c BlFJbvA6".split(" "));
        String username = proxyProfile1.get(2);
        String password = proxyProfile1.get(3);

        Authenticator.setDefault(new ProxyAuthenticator(username, password));
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProfile1.get(0), Integer.parseInt(proxyProfile1.get(1))));
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProfile2.get(0), Integer.parseInt(proxyProfile2.get(1))));
        Proxy proxy3 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProfile3.get(0), Integer.parseInt(proxyProfile3.get(1))));
        Proxy proxy4 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProfile4.get(0), Integer.parseInt(proxyProfile4.get(1))));
        Proxy proxy5 = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("104.144.155.110", 4444));

        test(proxy1);
        System.out.println("Proxy1 " + getCode(url, proxy1));
        System.out.println("Proxy2 " + getCode(url, proxy2));
        System.out.println("Proxy3 " + getCode(url, proxy3));
        System.out.println("Proxy4 " + getCode(url, proxy4));
        System.out.println("Proxy5 " + getCode(url, proxy5));

    }

    private static String getStringTest(Proxy proxy) throws IOException {
        URL tempUrl = new URL("https://www.0degreescelsius.com/collections.json");
        HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection(proxy);
//        String user = "7215cbb11c";
//        String password = "BlFJbvA6";
//        String uname_pwd = user + ":" + password;
//        String authString = "Basic " + new sun.misc.BASE64Encoder().encode(uname_pwd.getBytes());
//        connection.setRequestProperty("Proxy-Authenticate", "Basic realm=\"Access to the internal site\"");
//        connection.setRequestProperty("Proxy-Authorization", authString);
        connection.connect();
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        bufferedReader.close();
        return content.toString();
    }

    private static int getCode(URL url, Proxy proxy) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
//        System.out.println(proxy.toString());
//        String user = "7215cbb11c";
//        String password = "BlFJbvA6";
//        String uname_pwd = user + ":" + password;
//        String authString = "Basic " + new sun.misc.BASE64Encoder().encode(uname_pwd.getBytes());
//        connection.setRequestProperty("Proxy-Authenticate", "Basic");
//        connection.setRequestProperty("Proxy-Authorization", authString);
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }
    private static void test(Proxy proxy) throws IOException {
        URL tempUrl = new URL("https://api.ipify.org?format=json");
        String uname_pwd = "7215cbb11c" + ":" + "BlFJbvA6";
        String authString = "Basic " + new String(Base64.getEncoder().encode(uname_pwd.getBytes()));
        HttpURLConnection connection1;
        connection1 = (HttpURLConnection) tempUrl.openConnection(proxy);
//        connection1.setRequestProperty("Proxy-Authenticate", "Basic");
        connection1.setRequestProperty("Proxy-Authorization", authString);
        connection1.connect();
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        bufferedReader.close();
        String result = content.toString();
        System.out.println("Test result:" + result);
    }
}
