package com.newmoon.collections_getter.GetCollections;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.Proxy;

@Component
public class MainThread implements CommandLineRunner {

    public static void main(String[] args) {
        SyncContainer<String> domainContainer = new SyncContainer<>();
        SyncContainer<Proxy> proxyContainer = new SyncContainer<>();
        SyncContainer<Document> documentContainer = new SyncContainer<>();
        DomainProvider domainProvider = new DomainProvider(domainContainer,"Domain provider ");
        ProxyProvider proxyProvider = new ProxyProvider(proxyContainer,"Proxy provider");
        proxyProvider.start();
        domainProvider.start();

        try {
            domainProvider.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int num=proxyContainer.getSize();
        for (int i=1;i<=num;i++){
            Collector collector= new Collector("Collector "+i,proxyContainer.get(),documentContainer,domainContainer);
            collector.start();
        }

        Uploader uploader = new Uploader(documentContainer,"Uploader");
        uploader.setPriority(6);
        uploader.start();
    }
    @Override
    public void run(String... args) throws Exception {
        main(args);
    }
}
