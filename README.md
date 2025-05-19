# Traffic Simulator

## Table of Contents:

- [Project description](#description)

- [Why I built it](#why-i-built-it)

- [How to run the project locally](#how-to-run)

- [Tech stack and features](#stack-and-features)

- [What I learned](#what-i-learned)

## Description:

This is a simple console-based project that emulates the basic flow of a traffic system by rotating roads as open or closed.

## Why I Built It:

This project was part of a coding challenge provided by a platform called HyperSkill. I found this project interesting and 
thought it would be beneficial to improve my logical problem-solving skills.

## How To Run:

**1. Clone the repository**
>git clone https//github.com/DiackJ/trafficSimulator.git
> 
> cd TrafficSimulator

**2. Compile and run**

- navigate to 
>\TrafficSimulator\src\main\java\org\trafficSimulator
- compile all the files: 
  > javac CircularQueue.java Main.java Road.java TrafficModel.java
- navigate back to the root directory  
  >\TrafficSimulator
- run the program 
  > java -cp src/main/java org.trafficSimulator.Main
  
## Stack and Features

**Tech Stack**
- Java

**Features**
- Set intended number of roads and interval for road changes
- Add and delete roads 
- View system stats like current number of roads, interval and the working traffic system itself
- The program also used a circular queue and timer to handle the roads and interval rotation

## What I Learned:
- Improved my understanding of intermediate data structures such as circular queues when finding the best method to handle 
  roads.
- Improved my logical problem-solving skills by having to figure out how to rotate and reset the queue. Especially regarding
  making the first and second elements have the same interval.
- Learned how to use a timer to handle interval count down.