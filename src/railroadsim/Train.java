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
       state = "TrainStationIdle";
       
       System.out.println("Creating train: \"" +  name + "\" with speed " + speed);
   }
   
   public void run() 
   {
       try {
           while(!state.equals("Destroyed")) {
               performAction();
               Thread.sleep(1000); // Sleep 1 sec
           }
       } catch (InterruptedException e) {
           System.out.println("Thread " +  name + " interrupted.");
       }
       System.out.println(name + " destroyed.");
   }
   
   private void performAction()
   {
       System.out.println(name + " " + state);
       switch(state) {
           case "TrainStationIdle":
               TrainStationIdle();
       }
       
       state = "Destroyed";
   }
   
   private void TrainStationIdle()
   {       
       System.out.println(name + " Do stuff!!");
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
