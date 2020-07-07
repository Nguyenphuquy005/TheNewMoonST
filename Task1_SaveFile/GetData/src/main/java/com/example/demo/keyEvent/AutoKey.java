package com.example.demo.keyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AutoKey {
    public  static  void main(String[] args){
        try {
            Robot robot = new Robot() ;
             robot.keyPress(KeyEvent.VK_WINDOWS);
             robot.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
