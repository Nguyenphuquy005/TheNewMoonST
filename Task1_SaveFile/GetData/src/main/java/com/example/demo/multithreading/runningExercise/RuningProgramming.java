package com.example.demo.multithreading.runningExercise;

import java.util.Scanner ;
public class RuningProgramming {
    public  static  void  main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        NewYear newYear = new NewYear();
        newYear.start();
        Thread.sleep(1000);
        newYear.join(10000);
        for(int i=1;i< 10;i++){
            System.out.println("sout" + i);
            Thread.sleep(2100);
        }
    }
    public  static  class NewYear extends Thread{
        @Override
        public void run(){
            for (int y = 10 ; y< 120 ; y += 10){
                System.out.println( y + " year laters , you are have :"  );
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
