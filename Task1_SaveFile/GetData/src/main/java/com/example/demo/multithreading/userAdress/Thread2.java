package com.example.demo.multithreading.userAdress;

public class Thread2 extends  Thread {
    ShareData shareData ;

    public Thread2(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        for (int i=0 ; i< shareData.adressList.length ; i++){
           synchronized (shareData){
               shareData.notifyAll();
               System.out.println(shareData.adressList[i]);
               if (i != shareData.adressList.length-1){
                   try {
                       shareData.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }

           }
        }
        System.out.println("T2 Stop");
    }
}
