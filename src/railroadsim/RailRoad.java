/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railroadsim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jdk.nashorn.internal.runtime.Undefined;

/**
 *
 * @author Sander
 */
public class RailRoad {
    private List<TrainStation> trainStations = new ArrayList<TrainStation>();
    private List<Train> trains = new ArrayList<Train>();
    
    RailRoad(Integer numberOfStations, Integer numberOfTrains)
    {
        System.out.println("Creating RailRoad");
        
        createStations(numberOfStations);    
        createCargoDropper();
        createTrains(numberOfTrains);
        
        start();
    }
    
    private void start()
    {
        for (Train train : trains) {
            train.start();
        }
    }
    
    private void createTrains(Integer numberOfTrains) 
    {
        System.out.println("Creating " + numberOfTrains + " trains");
        
        Random randomGenerator = new Random();
        for(int i=1;i<=numberOfTrains;i++) {
            Integer randomSpeed = randomGenerator.nextInt((Config.trainSpeedMax - Config.trainSpeedMin) +1)+Config.trainSpeedMin;
            
            Integer index = randomGenerator.nextInt(trainStations.size());
            TrainStation trainStation = trainStations.get(index);
            
            Train train = new Train("Train " + i, randomSpeed, trainStation);
            trains.add(train);
        }
    }
    
    private void createCargoDropper()
    {
        CargoDropper cargoDropper = new CargoDropper(trainStations);
        cargoDropper.start();
    }
    
    private void createStations(Integer numberOfStations) 
    {
        System.out.println("Creating " + numberOfStations + " stations");
        
        for(int i=1;i<=numberOfStations;i++) {
            TrainStation trainStation = new TrainStation("Station " + i);
            trainStations.add(trainStation);
        }
        
        TrainStation firstStation = null;
        TrainStation previousStation = null;
        for (TrainStation trainStation : trainStations) {
            if(firstStation == null) {
                firstStation = trainStation;
            }
            
            if(previousStation != null) {
                previousStation.setNextStation(trainStation);
            }
            
            if(trainStations.indexOf(trainStation) == (trainStations.size() -1)) {
                trainStation.setNextStation(firstStation);
            }
            
            previousStation = trainStation;
        }
    }
}
