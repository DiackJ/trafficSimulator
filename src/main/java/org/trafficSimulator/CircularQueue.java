package org.trafficSimulator;

public class CircularQueue {
    private int front;
    private int rear;
    private int capacity;
    private int size;
    private String[] queue;

    public CircularQueue(int capacity){
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = capacity;
        this.queue = new String[capacity];
    }

    public boolean isFull(){
        return this.size == capacity;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void enqueue(String road){
        //make sure queue is not full before adding
        if(isFull()){
            System.out.println("Queue is full, cannot add anymore roads!");
        }
        //moves rear pointer to next position and ensures it wraps back around to the beginning
        rear = (rear + 1) % capacity;
        //add the road to the queue
        queue[rear] = road;
        //increase the size by 1
        size++;
    }

     public void dequeue(){
        if(isEmpty()){
            System.out.println("Queue is empty, cannot delete road!");
        }
        front = (front + 1) % capacity;
        size--;
     }

     public int getSize(){
        return this.size;
     }

     public String peek(){
        if(isEmpty()){
            System.out.println("Queue is empty, nothing to see here.");
        }
        return queue[front];
     }

     public void displayQueue(){
        if(isEmpty()){
            System.out.println("Queue is empty, nothing to see here.");
        }
        for(int i = 0; i < size; i++){
            System.out.print(queue[(front + i) % capacity] + ", ");
        }
     }
}
