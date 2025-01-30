package org.trafficSimulator;

import java.util.Timer;
import java.util.TimerTask;

public class CircularQueue {
    private int front;
    private int rear;
    private int capacity;
    private int size;
    private Road[] queue;

    public CircularQueue(int capacity){
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = capacity;
        this.queue = new Road[capacity];
    }

    public boolean isFull(){
        return size == capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(Road road){
        if(isFull()){
            System.out.println("queue is full");
            return;
        }

        rear = (rear +1) % capacity;
        queue[rear] = road;
        size++;
    }

    public Road dequeue(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return null;
        }
        Road road = queue[front];
        front = (front + 1) % capacity;
        size--;

        return road;
    }

    public Road peek(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return null;
        }

        return queue[front];
    }

    public void rotate(){
        if(!isEmpty()){
            Road road = dequeue();
            enqueue(road);
        }
    }

    public void resetQueue(int interval){
        try {
            for (int i = 0; i < size; i++) {
                int index = (front + i) % capacity;
                if (queue[index].equals(queue[front])) {
                    queue[index].setInterval(interval);
                } else {
                    queue[index].setInterval(interval * i);
                }
            }
        }catch(NullPointerException e){
            e.fillInStackTrace();
        }
    }

    public int getSize(){
        return size;
    }

    public Timer runTraffic(int interval){
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    StringBuilder string = new StringBuilder();
                    String RED = "\u001B[31m";
                    String GREEN = "\u001B[32m";
                        for (int i = 0; i < size; i++) { //loop through queue
                            int index = (front + i) % capacity;//calculate logical position of each element
                            Road road = queue[index];
                            //allows user to declare queue with larger capacity than elements initially added
                            //and helps intervals properly reset after deletion
                            if(road == null) continue;

                            if(road == queue[front]){
                                road.setState("open");
                                string.append(GREEN); //set string color to green

                            }else{
                                road.setState("closed");
                                string.append(RED); //set string color to red
                            }

                            road.decrementInterval(); //decrease interval of each element by 1
                            string.append(road.getName()) //build string
                                    .append(" is ")
                                    .append(road.getState())
                                    .append(" for ")
                                    .append(road.getInterval())
                                    .append("s ")
                                    .append("\u001B[0m");//reset color back to white after exit

                        }
                        if (queue[front] != null && queue[front].intIsZero()){//once the front (open) element has interval of 0
                            rotate(); //move front to back
                            resetQueue(interval); //reset the intervals
                        }

                        System.out.print("\r" + string);


                }//run()

            },0, 1000); //every second

        return timer;
    }
}


