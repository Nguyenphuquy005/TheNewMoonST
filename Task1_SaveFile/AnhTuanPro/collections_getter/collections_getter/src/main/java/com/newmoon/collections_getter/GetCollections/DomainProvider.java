package com.newmoon.collections_getter.GetCollections;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


 class DomainProvider extends Thread {
    private SyncContainer<String> container;

    DomainProvider(SyncContainer<String> container, String name) {
        super(name);
        this.container = container;
    }

    @Override
    public void run() {
        try {
            while (container.getSize() == 0) {
                File myObj = new File("D:\\TheNewMoonST\\file\\domain_file\\proxy\\proxy.txt");
                Scanner myReader = new Scanner(myObj);
                System.out.println(this.getName() + " start");
                while (myReader.hasNextLine()) {
                    String temp = myReader.nextLine();
                    System.out.println("Put " + temp + " in queue");
                    container.put(temp);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

