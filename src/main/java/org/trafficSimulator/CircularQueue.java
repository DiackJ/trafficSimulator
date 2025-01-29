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

    public void display(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return;
        }

        String open = "open";
        String closed = "closed";

        try {
            for (int i = 0; i < queue.length; i++) {
                System.out.println("\r" +queue[(front + i) % capacity].getName() + " is " + (queue[i].getOpen() ? open : closed) + " for " + queue[i].getInterval() + "s ");
                //System.out.print(queue[(front + i) % capacity].getInterval() + " ");
            }
        }catch(NullPointerException e){
            e.getStackTrace();
        }
    }

    public Road peek(){
        if(isEmpty()){
            System.out.println("queue is empty");
            return null;
        }

        return queue[front];
    }

    public Road getCurrent(){
        for(int i = 0; i < queue.length; i++){
            return queue[(front + 1) % capacity];
        }
        return null;
    }

    public Road getRoad(){
        for(int i = 0; i < queue.length; i++){
            return queue[i];
        }
        return null;
    }

    public void rotate(){
        if(!isEmpty()){
            Road road = dequeue();
            enqueue(road);
        }
    }

    public void resetQueue(int interval){
        try {
            for (int i = 0; i < queue.length; i++) {
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

    public void runTraffic(int interval){
        try {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run(){
                    StringBuilder string = new StringBuilder();
                    for(int i = 0; i < queue.length; i++){
                        int index = (front + i) % capacity;
                        queue[index].decrementInterval();
                        string.append(queue[index].getName())
                                .append(" is ")
                                .append(queue[index].getOpen() ? "open" : "closed")
                                .append(" for ")
                                .append(queue[index].getInterval())
                                .append("s ");
                    }
                    if(queue[front].intIsZero()){
                        rotate(); //move front to back
                        resetQueue(interval); //reset the intervals
                    }
                    System.out.print("\r"+string);
                }
            },0, 1000);

            //runTraffic(interval);
        }catch(NullPointerException e){
            e.fillInStackTrace();
        }
    }

    /*public void runTraffic() {
        try {
            while (true) { // Infinite loop to continuously process the queue
                StringBuilder systemString = new StringBuilder();
                Road roadf = null;

                // Step 1: Decrement intervals for all elements
                for (int i = 0; i < queue.length; i++) {
                    int road = (front + i) % capacity;
                    roadf = queue[road];
                    queue[road].decrementInterval();

                    // Build the system string for display
                    systemString.append(queue[road].getName())
                            .append(" is ")
                            .append(queue[road].getOpen() ? "open" : "closed")
                            .append(" for ")
                            .append(queue[road].getInterval())
                            .append("s ");

                    if (queue[front].intIsZero()) {
                        // Rotate and reset
                        rotate();
                        resetQueue(roadf.getInterval()); // Reset all intervals to the interval of the rotated element
                    }
                    System.out.print("\r" + systemString);
                }

                // Step 2: Print all intervals at once
                //System.out.print("\r" + systemString);

                // Step 3: Check if the front element reached 0
                /*if (queue[front].intIsZero()) {
                    // Rotate and reset
                    rotate();
                    resetQueue(roadf.getInterval()); // Reset all intervals to the interval of the rotated element
                }

                // Pause for 1 second before next iteration
                Thread.sleep(1000);
            }
        } catch (NullPointerException | InterruptedException e) {
            e.fillInStackTrace();
        }*/
    }


