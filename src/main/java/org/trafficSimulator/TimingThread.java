package org.trafficSimulator;

public class TimingThread  implements Runnable{
    private long seconds = 0;

    @Override
    public void run(){
        try {
            while(true) {
                seconds++;
                System.out.println("\r" + seconds);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
