package com.example.demo.multithreading;

public class HelloMain {
    public  static  void  main(String[] args) throws  InterruptedException{
         int idx = 1 ;
         for(int i=0 ; i<2;i++){
             System.out.println(" 1 . Main thread running" + idx++);
             Thread.sleep(2101);
         }

            HelloThread helloThread = new HelloThread();
            helloThread.start();

            for(int i=0;i<3;i++){
                System.out.println(" 2 .Main Thread running" + idx++);
                Thread.sleep(2101);
        }

    }
        public static void HelloDemo(){
            System.out.println("12348978945");
        }
    public static class HelloThread extends Thread {
        @Override
        public void run() {
            int index = 1;
            for (int i=0; i<10;i++) {
                System.out.println("3. Hello Thread running" + index++);
                 try {
                     Thread.sleep(1030);
                 }catch (InterruptedException e){

                 }
            }
            System.out.println("==>>Stoped");
        }
    }
    }
