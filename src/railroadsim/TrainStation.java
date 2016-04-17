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
public class TrainStation {
    private String name;
    
    private TrainTrack trackToNextStation;
    
    private List<Train> trainsInStation = new ArrayList<Train>();
    private List<Cargo> cargoInStation = new ArrayList<Cargo>();
    
        
    TrainStation(String stationName) 
    {
        name = stationName;
        
        System.out.println("Creating trainstation: \"" +  name + "\"");
    }
    
    public void setNextStation(TrainStation nextTrainStation)
    {
        System.out.println("Add " + nextTrainStation.getName() + " as destination for " +  name);
        
        Random randomGenerator = new Random();
        Integer randomLength = randomGenerator.nextInt((Config.trackDistanceMax - Config.trackDistanceMin) +1)+Config.trackDistanceMin;
        trackToNextStation = new TrainTrack(randomLength, this, nextTrainStation);
    }
    
    public String getName()
    {
       return name;
    }
    
    public Boolean addTrain(Train train)
    {
        System.out.println("Add " + train.getName() + " to " +  name);
        
        trainsInStation.add(train);
        
        return true;
    }
    
    public Boolean hasCargo()
    {
        return false;
    }
    
    public TrainTrack leaveStation(Train train)
    {
        //Can I enter the track to the next station?
        if(trackToNextStation.addTrainToTrack(train)) {
            System.out.println(train.getName() + " left " +  name);
            trainsInStation.remove(train);
            return trackToNextStation;
        }
        
        return null;
    }
    
    public void addCargo(Cargo cargo)
    {
        cargoInStation.add(cargo);
    }
}
