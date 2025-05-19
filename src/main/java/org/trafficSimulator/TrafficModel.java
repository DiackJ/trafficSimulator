package org.trafficSimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class TrafficModel {
    private int roadNumber;
    private int closeOpenInterval;
    private CircularQueue roadQueue;
    private final List<Scanner> scannerList;

    public TrafficModel(){
        this.scannerList = new ArrayList<>();
    }

//    public void setRoadNumber(int roadNumber){
//        this.roadNumber = roadNumber;
//    }

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
        //setRoadNumber(roadInt);

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
                \n2.Delete road\
                \n3.View system\
                \n0.Quit""");
        String input = scn.nextLine();
        int inputInt = Integer.parseInt(input);

        switch(inputInt){
            case 1: //add road to queue and set interval according to position
                Road newRoad = addRoad(roadQueue);
                setRoadInterval(roadQueue, newRoad);
                menu();
                break;
            case 2: //delete the first road in the queue
                deleteRoad(roadQueue);
                break;
            case 3: //view system stats and traffic flow
                openSystem(roadQueue);
                break;
            case 0: //quit system and close all input scanners
                System.out.println("Bye!");
                for(Scanner scns : scannerList){
                    scns.close();
                }
                break;
        }

        scannerList.add(scn);
    }

//take user input for road name and add to queue
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

//set intervals according to position in queue
    public void setRoadInterval(CircularQueue queue, Road road){
        for(int i = 0; i < queue.getSize(); i++){
            if(road.equals(queue.peek())){ //front road
                road.setInterval(getInterval());
                road.setState("open");
            }else{ //every other road
                road.setInterval(getInterval() * i);
                road.setState("closed");
            }
        }
    }

//remove front road (as per queue rules) and reset intervals of remaining roads and return to menu
    public void deleteRoad(CircularQueue queue){
        queue.dequeue();

        queue.resetQueue(getInterval());
        System.out.println("Road deleted successfully");

        menu();
    }

//prints road and interval info, initiates timer to see traffic flow, when user is ready...cancels timer and goes back to menu
    public void openSystem(CircularQueue queue){
        System.out.println("Roads: " + queue.getSize()+"\nInterval: " + getInterval());

        Timer timer = queue.runTraffic(getInterval());

        System.out.println("press enter to return to menu");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        if(input != null){
            timer.cancel();
            menu();
        }
    }
}
