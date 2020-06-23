package com.example.demo.saveDomainHandle.proxy;

import com.example.demo.saveDomainHandle.saveLinhkDomain.ProxyAuthenticator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.*;


public class urlHandle {
    private Map<String,Integer> proxyList = new HashMap<>();
    private Set<String> proxyFailedList = new HashSet<>();
    private String currentProxy;
    private Iterator<String> it;
    public urlHandle(String proxyListPath){
        currentProxy= "0.0.0.0";
        try {
            File myObj = new File(proxyListPath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String proxy = myReader.nextLine();
                proxyList.put(proxy,0);
            }
            myReader.close();
            Set<String> entrySet = proxyList.keySet();
            it = entrySet.iterator();
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
    public void getConntentFromURL(String domain){
        System.out.println("Current proxy: "+System.getProperty("http.proxyHost"));
        try {
            URL url= new URL(domain +"/products.json?limit=1");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection(getProxy(currentProxy));
            connection.setRequestMethod("GET");
            connection.connect();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int code = connection.getResponseCode();
            switch (code){
                case 404: break; // domain fail
                case 503:
                    proxyList.put(currentProxy,(proxyList.get(currentProxy)+1));
                case 407:
                case 430:
                    if(it.hasNext()){
                        currentProxy = it.next();
                        while (proxyList.get(currentProxy)==5){
                            if(it.hasNext()){
                                currentProxy = it.next();
                            } else {
                                Set<String> entrySet = proxyList.keySet();
                                it = entrySet.iterator();
                                currentProxy = "0.0.0.0";
                            }
                        }
                    } else {
                        currentProxy = "0.0.0.0";
                        Set<String> entrySet = proxyList.keySet();
                        it = entrySet.iterator();
                    }
                    break;
                // swtich proxy
                case 200:
                    System.out.println(convertContentToJSON(connection,"products"));
                    connection.disconnect();
            }
        }catch (NoSuchElementException ex){
            System.out.println(currentProxy+" profife errors");
            proxyFailedList.add(currentProxy);
            proxyList.remove(currentProxy);
            if (it.hasNext()){
                currentProxy = it.next();
            } else {
                Set<String> entrySet = proxyList.keySet();
                it = entrySet.iterator();
                currentProxy = "0.0.0.0";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void changeProxy() {

    }


    private Proxy getProxy(String proxyLink){
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        Proxy proxy = Proxy.NO_PROXY;
        if (!proxyLink.equals("")&&!proxyLink.equals(("0.0.0.0"))) {
            List<String> proxyProfile = Arrays.asList(proxyLink.split(" "));
            if(!proxyProfile.get(0).equals(System.getProperty("http.proxyHost"))){
                Authenticator.setDefault(new ProxyAuthenticator(proxyProfile.get(2), proxyProfile.get(3)));
                System.setProperty("http.proxyHost", proxyProfile.get(0));
                System.setProperty("http.proxyPort", proxyProfile.get(1));
                System.out.println("Change to proxy: "+ proxyProfile.get(0));
            }
            SocketAddress addr = new
                    InetSocketAddress(proxyProfile.get(0), Integer.parseInt(proxyProfile.get(1)));
            proxy = new Proxy(Proxy.Type.HTTP, addr);
        }
        return proxy;
    }
    public static String convertContentToJSON(HttpURLConnection connection, String key) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        bufferedReader.close();
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        return "Product: "+jsonArray.length()+"pc\n";
    }

    public static void main(String[] args) throws InterruptedException {
        urlHandle urlHandle = new urlHandle("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
        int count=0;
        while (true){
            Thread.sleep(250);
            System.out.println("Try times: " + ++count);
            urlHandle.getConntentFromURL("https://0011am.co");
        }
    }
}
