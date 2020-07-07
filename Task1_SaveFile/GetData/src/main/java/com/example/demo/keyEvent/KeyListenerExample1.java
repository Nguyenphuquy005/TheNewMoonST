package com.example.demo.keyEvent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KeyListenerExample1  {
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(17);
                    robot.delay(5000);
                    robot.keyPress(KeyEvent.VK_L);

//                    robot.keyPress(KeyEvent.VK_KP_UP);
//                    robot.keyRelease(KeyEvent.VK_KP_UP);
//                    robot.keyPress(KeyEvent.VK_ENTER);
//                    robot.keyRelease(KeyEvent.VK_ENTER);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 3, 3, TimeUnit.SECONDS);
    }
}
