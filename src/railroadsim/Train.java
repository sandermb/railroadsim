/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railroadsim;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sander
 */
public class Train implements Runnable {
   private Thread t;
   
   private String name;
   private Integer speed;
   private Integer capacity;
   
   private String state;
   
   private TrainStation currentTrainStation = null;
   private TrainTrack currentTrainTrack = null;
   
   private List<Cargo> cargoInTrain = new ArrayList<Cargo>();
   
   
   Train(String trainName, Integer trainSpeed, Integer trainCapacity, TrainStation startingTrainStation)
   {
       name = trainName;
       speed = trainSpeed;
       capacity = trainCapacity;
       
       currentTrainStation = startingTrainStation;
       currentTrainStation.addTrain(this);
       state = "IdleAtTrainStation";
       
       System.out.println("Creating train: \"" +  name + "\" with speed " + speed);
   }
   
   public void run() 
   {
       while(!state.equals("Destroyed")) {
           performAction();
       }
       
       System.out.println(name + " Destroyed.");
   }
   
   public void takeTime(Integer miliseconds)
   {
       try {
           Thread.sleep(miliseconds); // Sleep 1 sec
       } catch (InterruptedException e) {
           System.out.println("Thread " +  name + " interrupted.");
       }
   }
   
   private void performAction()
   {
       System.out.println(name + " " + state);
       switch(state) {
           case "IdleAtTrainStation":
               IdleAtTrainStation();
               break;
           case "LoadingCargo":
               loadCargo();
               break;
           case "UnloadingCargo":
               unloadCargo();
               break;
           case "OnTrack":
               driveOnTrack();
               break;   
       }
       
       //state = "Destroyed";
   }
   
   private void IdleAtTrainStation()
   {
       if(hasCargoForCurrentStation()) { //Is there cargo in the train for this station?
           //Yes -> Unload that cargo
           state = "UnloadingCargo";
       } else if(ableToLoadCargo()) { //Is the train able to load more cargo from this station?
           //Yes -> Leave station
           state = "LoadingCargo";
       } else {
           //No -> Try to leave station once
           leaveStation();
       }
   }
   
   public void unloadCargo()
   {
       System.out.println(name + " Unloading cargo at " + currentTrainStation.getName());
       
       for (Cargo cargo : cargoInTrain) {
           if(cargo.getDestination() == currentTrainStation) {
               for(Integer i=1;i<=cargo.getSize();i++) {
                   takeTime(Config.droppingCargoTime * 1000);
                   System.out.println(name + " Unloading cargo: " + (10000 / ((cargo.getSize()*100) / i)) + "%");
               }
               
               cargoInTrain.remove(cargo);
               break;
           }
       }
       
       state = "IdleAtTrainStation";
   }
   
   public void loadCargo()
   {
       System.out.println(name + " Loading cargo at " + currentTrainStation.getName());
       
       Cargo cargo = currentTrainStation.getCargoSmallerThan(getCapacityAvailable());
       
       if(cargo != null) {
           for(Integer i=1;i<=cargo.getSize();i++) {
               takeTime(Config.loadingCargoTime * 1000);
               System.out.println(name + " Loading cargo: " + (10000 / ((cargo.getSize()*100) / i)) + "%");
           }
           
           cargoInTrain.add(cargo);
           state = "IdleAtTrainStation";
       }
   }
   
   public void leaveStation()
   {
       System.out.println(name + " Leaving station (" + currentTrainStation.getName() + ")...");
       
       //Can I leave the station?
       TrainTrack traintrack = currentTrainStation.leaveStation(this);
       if(traintrack != null) {
           currentTrainTrack = traintrack;
           currentTrainStation = null;
           state = "OnTrack";
       } else {
           System.out.println(name + " waits until tracks is clear");
           takeTime(1000);
       }
   }
   
   public void driveOnTrack()
   {
       System.out.println(name + " Driving on track...");
       
       Integer distanceUntilNextStation = currentTrainTrack.distanceUntilEnd();
       for(Integer i=0;i<distanceUntilNextStation;i++) {
           takeTime(60000 / speed);
           currentTrainTrack.drive(1);
       }
       
       if(currentTrainTrack.reachedStation()) {
           currentTrainTrack.removeTrainFromTrack();
           
           currentTrainStation = currentTrainTrack.getToStation();
           currentTrainTrack = null;
           
           state = "IdleAtTrainStation";
           
           System.out.println(name + " reached " + currentTrainStation.getName());
       }
   }
   
   private Boolean ableToLoadCargo()
   {
       return currentTrainStation.hasCargoSmallerThan(getCapacityAvailable());
   }
   
   private Integer getCapacityAvailable()
   {
       Integer capacityAvailable = capacity;
       
       for (Cargo cargo : cargoInTrain) {
           capacityAvailable -= cargo.getSize();
       }
       
       return capacityAvailable;
   }
   
   private Boolean hasCargoForCurrentStation()
   {
       for (Cargo cargo : cargoInTrain) {
           if(cargo.getDestination() == currentTrainStation) {
               return true;
           }
       }
       
       return false;
   }
   
   public void start ()
   {
      System.out.println("Starting " +  name );
      if (t == null) {
         t = new Thread (this, name);
         t.start ();
      }
   }
   
   public String getName()
   {
       return name;
   }
   
   
}
