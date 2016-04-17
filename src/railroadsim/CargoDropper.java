/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railroadsim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Sander
 */
public class CargoDropper implements Runnable {
   private Thread t;
   private List<TrainStation> trainStations = new ArrayList<TrainStation>();
   
   CargoDropper(List<TrainStation> trainStationsList)
   {
       trainStations = trainStationsList;
   }
   
   public void dropCargo()
   {
       Random randomGenerator = new Random();
       
       Integer index = randomGenerator.nextInt(trainStations.size());
       TrainStation destinationTrainStation = trainStations.get(index);
       
       Cargo cargo = new Cargo(destinationTrainStation);
       
       TrainStation startingTrainStation = null;
       
       while(startingTrainStation == null || startingTrainStation == destinationTrainStation) {
           index = randomGenerator.nextInt(trainStations.size());
           startingTrainStation = trainStations.get(index);
       }
       
       startingTrainStation.addCargo(cargo);
       
       System.out.println("Dropping cargo (size " + cargo.getSize() + ") at " + startingTrainStation.getName() + " with destination " + destinationTrainStation.getName());
       
       try {
           Thread.sleep(Config.droppingCargoRate * 1000);
       } catch (InterruptedException e) {
           System.out.println("Thread CargoDropper interrupted.");
       }
   }
   
   public void run() 
   {   
       while(true) {
           dropCargo();
       }
   }
   
   public void start ()
   {
      System.out.println("Starting cargo dropper");
      if (t == null) {
         t = new Thread (this, "CargoDropper");
         t.start ();
      }
   }
}
