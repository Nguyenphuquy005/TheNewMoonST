package com.example.demo.mouseProgram;

import com.example.demo.autoClick.AutoClicker;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Program {
    public static  void  main(String[] args) throws AWTException, InterruptedException {
        // Open Chrom
        Robot robot = new Robot() ;
        String abc = "KeyEvent.VK_U" ;
        robot.mouseMove(210,1100);
        AutoClicker autoClicker = new AutoClicker() ;
        autoClicker.clickMouse(InputEvent.BUTTON1_DOWN_MASK);
        // Write Domain
        Thread.sleep(250);
        robot.keyPress(17);
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_L);
        robot.keyRelease(KeyEvent.VK_L);
        robot.keyRelease(17);
        Thread.sleep(250);
        robot.keyPress(KeyEvent.VK_U);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_R);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyPress(KeyEvent.VK_R);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_SLASH);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyPress(KeyEvent.VK_4);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_Q);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(10000);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyPress(KeyEvent.VK_M);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyPress(KeyEvent.VK_N);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_CAPS_LOCK);
        robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
        robot.keyPress(KeyEvent.VK_H);
        robot.keyPress(KeyEvent.VK_CAPS_LOCK);
        robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyPress(KeyEvent.VK_P);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_SHIFT);
        Thread.sleep(250);
        robot.keyPress(KeyEvent.VK_2);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_1);
        robot.keyPress(KeyEvent.VK_2);
        robot.keyPress(KeyEvent.VK_3);
        robot.mouseMove(1064,532);
        autoClicker.clickMouse(InputEvent.BUTTON1_DOWN_MASK);

    }
}
