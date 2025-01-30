package org.trafficSimulator;

public class Road {
    private String name;
    private String state;
    private int interval;

    public Road(){}

    public Road(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getState(){
        return this.state;
    }
    public void setState(String state){
        this.state = state;
    }

    public int getInterval(){
        return this.interval;
    }
    public void setInterval(int interval){
        this.interval = interval;
    }

    public void decrementInterval(){
        try {
            if (interval > 0) {
                interval--;
            }
        }catch(NullPointerException e){
            e.fillInStackTrace();
        }
    }

    public boolean intIsZero(){
        return interval == 0;
    }
}
