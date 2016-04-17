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
public class Config {
    public static Integer numberOfStations = 8; // 8
    public static Integer numberOfTrains = 4; // 4
    
    public static Integer trainSpeedMin = 10; //10
    public static Integer trainSpeedMax = 50; //50
    
    public static Integer trackDistanceMin = 4; //4
    public static Integer trackDistanceMax = 10; //10
    
    public static Integer trainCapacityMin = 5;
    public static Integer trainCapacityMax = 30;
    
    public static Integer droppedCargoSizeMin = 1;
    public static Integer droppedCargoSizeMax = 3;
    
    /**
     * Each * second(s)
     */
    public static Integer droppingCargoRate = 10; //10
    
    /**
     * 1 cargo each * second(s)
     */
    public static Integer loadingCargoTime = 4; //4

    /**
     * 1 cargo each * second(s)
     */
    public static Integer droppingCargoTime = 4; //4
}
