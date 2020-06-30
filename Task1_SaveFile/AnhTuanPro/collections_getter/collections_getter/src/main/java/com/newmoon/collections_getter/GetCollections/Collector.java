package com.newmoon.collections_getter.GetCollections;

import org.bson.Document;
import org.bson.json.JsonParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Collector extends Thread {
    private Proxy proxy;
    private SyncContainer<Document> collectionContainer;
    private SyncContainer<String> domainContainer;

    public Collector(String name, Proxy proxy, SyncContainer<Document> collectionContainer, SyncContainer<String> domainContainer) {
        super(name);
        this.proxy = proxy;
        this.domainContainer = domainContainer;
        this.collectionContainer = collectionContainer;
    }

    private HttpURLConnection requestThroughProxy(String urlLink) throws IOException {
        URL url = new URL(urlLink);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);
        connection.setRequestMethod("GET");
        connection.connect();
        System.out.println(getName() + " get code through proxy " + connection.getResponseCode());
        return connection;
    }

    private List<Document> convertToList(HttpURLConnection connection, String domain) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }
        bufferedReader.close();
        connection.disconnect();
        String result = content.toString();
        try {
            List<Document> list = Document.parse(result).getList("collections", Document.class);
            for (Document doc :
                    list) {
                if (domain.equals("0fuxgvn.com")) {
                    doc.put("endList", true);
                }
                doc.put("domain", domain);
                doc.put("scanned_at", new Date());
            }
            if (!list.isEmpty()) {
                return list;
            }
        } catch (NullPointerException | JsonParseException ignored) {
        }
        return new ArrayList<>();
    }

    private void scanDomain(String domain) throws InterruptedException, IOException {
        int page = 1;
        String url = "https://" + domain + "/collections.json?limit=500&page=" + page;
        HttpURLConnection connection;
        try {
            connection = requestThroughProxy(url);
            System.out.println(getName() + " scan in page" + page);
            if (connection.getResponseCode() == 200) {
                List<Document> documents = convertToList(connection, domain);
                if (!documents.isEmpty()) {
                    List<Document> tempDoc = new ArrayList<>();
                    loopDomainPage(domain, page, documents, tempDoc);
                }
                System.out.println(getName() + " report collections get " + documents.size());
                collectionContainer.addAll(documents);// thread end point pushing result to container
                System.out.println(getName() + " end and sleep 2s");
            }
        } catch (SocketTimeoutException e) {
            System.out.println(getName() + " get no things at " + domain);
        }
    }

    private void loopDomainPage(String domain, int page, List<Document> documents, List<Document> tempDoc) throws InterruptedException, IOException {
        String url;
        HttpURLConnection connection;
        do {
            url = "https://" + domain + "/collections.json?limit=500&page=" + ++page;
            do {
                Thread.sleep(500);
                System.out.println(getName() + " scan in page " + page);
                connection = requestThroughProxy(url);
            } while (connection.getResponseCode() == 430);
            if (connection.getResponseCode() == 200) {
                tempDoc = convertToList(connection, domain);
                documents.addAll(tempDoc);
            } else {
                tempDoc.clear();
            }
        } while (!tempDoc.isEmpty());
    }

    @Override
    public void run() {
        String domain = "";
        try {
            Thread.sleep(2000);
            while (!domainContainer.isEmpty()) {
                domain = domainContainer.getAndPut();
                System.out.println(getName() + " start scan " + domain + " through " + proxy.toString());
                try {
                    scanDomain(domain);
                } catch (IOException e) {
                    System.out.println(getName() + " with " + proxy.toString() + " scanned no things at" + domain);
                }
            }
        } catch (InterruptedException ir) {
            Thread.currentThread().interrupt();
        }
    }
}
