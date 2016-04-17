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
public class TrainTrack {
    private TrainStation fromStation;
    private TrainStation toStation;
    
    private Integer length;
    private Integer trainPositionOnTrack = 0;
    
    private Train currentTrainOnTrack = null;
    
    TrainTrack(Integer trackLength, TrainStation trackFromStation, TrainStation trackToStation)
    {  
        length = trackLength;
        fromStation = trackFromStation;
        toStation = trackToStation;
        
        System.out.println("Create TrainTrack from " + fromStation.getName() + " to " +  toStation.getName() + " with length " + length);
    }
    
    public Boolean addTrainToTrack(Train train)
    {
        //Is there a train already on this track?
        if(currentTrainOnTrack == null) {
            currentTrainOnTrack = train;
            trainPositionOnTrack = 0;
            
            System.out.println(currentTrainOnTrack.getName() + " enters track between " + fromStation.getName() + " and " + toStation.getName());
            return true;
        } else {
            System.out.println(train.getName() + " cannot enter track between " + fromStation.getName() + " and " + toStation.getName() + " because " + currentTrainOnTrack.getName() + " is on the track.");
            return false;
        }
    }
    
    public Boolean removeTrainFromTrack()
    {
        if(currentTrainOnTrack != null) {
            System.out.println(currentTrainOnTrack.getName() + " leaves track between " + fromStation.getName() + " and " + toStation.getName());
            currentTrainOnTrack = null; 
            trainPositionOnTrack = 0;
            return true;
        }
        
        return false;
    }
    
    public Integer distanceUntilEnd()
    {
        return length - trainPositionOnTrack;
    }
    
    public void drive(Integer distance) 
    {
        trainPositionOnTrack+=distance;
        System.out.println(currentTrainOnTrack.getName() + " distance covered: " + trainPositionOnTrack + "km");
    }
    
    public Boolean reachedStation()
    {
        if(trainPositionOnTrack == length) {
            return true;
        }
        
        return false;
    }
    
    public TrainStation getToStation()
    {
        return toStation;
    }
}
