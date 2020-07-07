package com.example.demo.autoClick;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

public class AutoClickMain {
    public  static void  main(String[] args) throws InterruptedException, AWTException {
        Scanner scanner = new Scanner(System.in) ;
        System.out.println("__Auto Click");
        System.out.println("Enter the number of desired clicks");
        int clicks = scanner.nextInt();
        System.out.println("Enter delay between clicks in millisconds: ");
        int delay = scanner.nextInt();
        System.out.println();
        System.out.println("Program will start in 3 seconds");
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        AutoClicker clicker = new AutoClicker();
        clicker.setDelay(delay);
        for(int i =0;i<clicks;i++){
            clicker.clickMouse(InputEvent.BUTTON1_DOWN_MASK);
        }
    }
}
