package com.example.demo.autoClick_mousePionter;

import com.example.demo.autoClick.AutoClicker;

import java.awt.*;

public class Test {
    public static void main(String[] args) throws AWTException, InterruptedException {
        int x = 1800 , change = 200 , y=350;
        boolean bool =true ;
        int i = 1 ;
        Robot robot = new Robot();
        while (bool){
            robot.mouseMove(x,y);
            robot.mousePress(12);
            AutoClicker autoClicker = new AutoClicker();
             if(x == 1800){
                 x+=change;
             }else {
                 x-=change;
             }
            i ++ ;
            if(i == 11){
                bool = false ;
            }
            Thread.sleep(1000);
        }
        }
}
