/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railroadsim;

/**
 *
 * @author Sander
 */
public class Train implements Runnable {
   private Thread t;
   
   private String name;
   private Integer speed;
   
   private String state;
   
   private Integer count = 0;
   
   private TrainStation currentTrainStation = null;
   private TrainTrack currentTrainTrack = null;
   
   
   Train(String trainName, Integer trainSpeed, TrainStation startingTrainStation)
   {
       name = trainName;
       speed = trainSpeed;
       
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
           case "LeavingStation":
               leaveStation();
               break;
           case "LoadingCargo":
               loadCargo();
               break;
            case "OnTrack":
               driveOnTrack();
               break;   
       }
       
       //state = "Destroyed";
   }
   
   private void IdleAtTrainStation()
   {
       if(isFull()) { //Is the train full?
           //Yes -> Leave station
           state = "LeavingStation";
       } else if(currentTrainStation.hasCargo()) { //No -> Is there cargo at this station?
           //Yes -> Load cargo
           state = "LoadingCargo";
       } else {
           //No -> Try to leave station once
           leaveStation();
       }
   }
   
   public void loadCargo()
   {
       System.out.println(name + " Loading cargo...");
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
   
   private Boolean isFull()
   {
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
