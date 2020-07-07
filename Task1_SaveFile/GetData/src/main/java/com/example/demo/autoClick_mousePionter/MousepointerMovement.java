package com.example.demo.autoClick_mousePionter;

import com.example.demo.autoClick.AutoClicker;

import java.awt.*;
import java.awt.event.InputEvent;

public class MousepointerMovement {
    public static void main(String[] args) throws AWTException, InterruptedException {
        int x=100 , y=400;
        boolean bool =true ;
        Robot robot = new Robot();
        while(true){
            robot.mouseMove(x,y);
            AutoClicker autoClicker = new AutoClicker();
            if(bool){
                x+=5 ;y+=5;
            }else {
                x+=5; y-=5;
            }
            if (y==420){
                bool = false ;
            }else if (y==380){
                  bool = true ;
            }
            Thread.sleep(2);
        }
    }
}
