package com.newmoon.collections_getter.GetCollections;

import org.bson.Document;

class Uploader extends Thread {
    private final SyncContainer<Document> productContainer;
    private boolean firstRun=true;

    Uploader(SyncContainer<Document> productContainer, String name) {
        super(name);
        this.productContainer = productContainer;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            int count = productContainer.getSize();
            System.out.println(getName() + " found " + count);
            if (count!=0){
                MongoDBConnect mongoDBConnect;
                if (firstRun) {
                    mongoDBConnect = new MongoDBConnect("test","testCollections",true);
                    firstRun=false;
                } else {
                    mongoDBConnect = new MongoDBConnect("test","testCollections",false);
                }
                for (int i = 0; i < count; i++) {
                    Document doc = productContainer.get();
                    mongoDBConnect.update(doc);
                }
                System.out.println( getName()+ "pushed " +count);
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


