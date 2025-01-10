package org.trafficSimulator;

import java.util.Timer;
import java.util.TimerTask;

public class TimingThread  implements Runnable{
    private long seconds = 0;

    @Override
    public void run(){
        try {
          seconds++;
          Thread.sleep(1000);
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void displayTimer(){
        run();
        System.out.println("\r" + seconds);
    }

}
