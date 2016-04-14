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
        if(currentTrainOnTrack == null) {
            currentTrainOnTrack = train;
            
            System.out.println(currentTrainOnTrack.getName() + " enters track between " + fromStation.getName() + " and " + toStation.getName());
            return true;
        }
            
        return false;
    }
    
    public Boolean removeTrainFromTrack()
    {
        if(currentTrainOnTrack != null) {
            System.out.println(currentTrainOnTrack.getName() + " leaves track between " + fromStation.getName() + " and " + toStation.getName());
            currentTrainOnTrack = null; 
            return true;
        }
        
        return false;
    }
}
