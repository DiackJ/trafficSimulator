package org.trafficSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class TrafficModel {
    private int roadNumber;
    private int closeOpenInterval;
    private CircularQueue roadQueue;
    private List<Scanner> scannerList;

    public TrafficModel(){
        this.scannerList = new ArrayList<>();
    }

    public int getRoadNumber(){
        return this.roadNumber;
    }
    public void setRoadNumber(int roadNumber){
        this.roadNumber = roadNumber;
    }

    public int getInterval(){
        return this.closeOpenInterval;
    }
    public void setInterval(int interval){
        this.closeOpenInterval = interval;
    }

    public void programStart(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome to the traffic simulator!\nPlease input number of roads:");
        String input = scn.nextLine();
        int roadInt = Integer.parseInt(input);
        setRoadNumber(roadInt);

        System.out.println("Please input the interval for road changes:");
        input = scn.nextLine();
        int intervalInt = Integer.parseInt(input);
        setInterval(intervalInt);
        scannerList.add(scn);

        roadQueue = new CircularQueue(roadInt);

        menu();

    }

    public void menu(){
        Scanner scn = new Scanner(System.in);
        System.out.println("""
                Please select an option
                Menu:
                1.Add road\
                2.Delete road\
                3.View system\
                0.Quit""");
        String input = scn.nextLine();
        int inputInt = Integer.parseInt(input);

        switch(inputInt){
            case 1:
                Road newRoad = addRoad(roadQueue);
                setRoadInterval(roadQueue, newRoad);
                menu();
                break;
            case 2:
                deleteRoad(roadQueue);
                break;
            case 3:
                openSystem(roadQueue);
                break;
            case 0:
                System.out.println("Bye!");
                for(Scanner scns : scannerList){
                    scns.close();
                }
                break;
        }

        scannerList.add(scn);
    }

    public Road addRoad(CircularQueue queue){
        Scanner scn = new Scanner(System.in);
        System.out.println("Please input the road to add:");
        String roadName = scn.nextLine();
        Road road = new Road();
        road.setName(roadName);
        queue.enqueue(road);

        System.out.println("Road added successfully");

        scannerList.add(scn);
        return road;
    }

    public void setRoadInterval(CircularQueue queue, Road road){
        for(int i = 0; i < queue.getSize(); i++){
            if(road.equals(queue.peek())){
                road.setInterval(getInterval());
                road.setState("open");
            }else{
                road.setInterval(getInterval() * i);
                road.setState("closed");
            }
        }
    }

    public void deleteRoad(CircularQueue queue){//, Road road){
        queue.dequeue();

        queue.resetQueue(getInterval());
        System.out.println("Road deleted successfully");

        menu();
    }


    public void openSystem(CircularQueue queue){
        System.out.println("Roads: " + queue.getSize()+"\nInterval: " + getInterval());

        Timer timer = queue.runTraffic(getInterval());

        System.out.println("press any key to return to menu");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        if(input != null){
            timer.cancel();
            menu();
        }
    }

}
