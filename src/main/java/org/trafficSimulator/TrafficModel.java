package org.trafficSimulator;

import java.util.Scanner;

public class TrafficModel {
    private int roadNumber;
    private int closeOpenInterval;
    private CircularQueue roadQueue;

    public TrafficModel(){
        //this.roadQueue = new CircularQueue(roadNumber);
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

    public void menu(){
        Scanner scn = new Scanner(System.in);

        System.out.println("select an option");
        String menuOpt = "Menu\n"+
                "1. Add road\n"+
                "2. Delete road\n"+
                "3. View system\n"+
                "0. Quit";
        System.out.println(menuOpt);

        String userInput = scn.nextLine();
        int userOpt = Integer.parseInt(userInput);

        switch(userOpt){
            case 0:
                System.out.println("Bye!");
                break;
            case 1:
                addRoad();
                break;
            case 2:
                roadQueue.dequeue();
                System.out.println("Road deleted successfully!");
                menu();
                break;
            case 3:
                //viewSystem();
                roadQueue.displayQueue();
                menu();
                break;
        }
    }

    public void enterRoadInterval(){
        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome to the traffic simulator!");

        System.out.println("Please input road number: ");
        String userInput = scn.nextLine();
        int input = Integer.parseInt(userInput);
        setRoadNumber(input);

        System.out.println("Please input road change interval: ");
        userInput = scn.nextLine();
        input = Integer.parseInt(userInput);
        setInterval(input);

        roadQueue = new CircularQueue(roadNumber);
        System.out.println(roadNumber);

        menu();
    }

    public void addRoad(){
        Scanner scn = new Scanner(System.in);
        System.out.println("please enter a road name: ");
        String input = scn.nextLine();
        roadQueue.enqueue(input);
        System.out.println("Road added successfully!");

        menu();
    }

}
