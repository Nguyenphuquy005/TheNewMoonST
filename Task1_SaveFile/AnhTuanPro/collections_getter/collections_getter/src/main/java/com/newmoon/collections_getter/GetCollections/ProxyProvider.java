package com.newmoon.collections_getter.GetCollections;


import java.io.File;
import java.io.IOException;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


class ProxyProvider extends Thread {
    private SyncContainer<Proxy> container;

    ProxyProvider(SyncContainer<Proxy> container, String name) {
        super(name);
        this.container = container;
    }

    @Override
    public void run() {
try {
                File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
                Scanner myReader = new Scanner(myObj);
                container.put(Proxy.NO_PROXY);
                String username = null,password = null;
                while (myReader.hasNextLine()) {
                    String temp = myReader.nextLine();
                    List<String> proxyProfile1 = Arrays.asList(temp.split(" "));
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProfile1.get(0), Integer.parseInt(proxyProfile1.get(1))));
                    username= proxyProfile1.get(2);
                    password= proxyProfile1.get(3);
                    container.put(proxy);
                }
            System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
            Authenticator.setDefault(new ProxyAuthenticator(username, password));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

