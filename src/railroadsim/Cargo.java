/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railroadsim;

import java.util.Random;

/**
 *
 * @author Sander
 */
public class Cargo {
    private Integer size;
    
    Cargo()
    {
        Random randomGenerator = new Random();
        size = randomGenerator.nextInt((Config.droppedCargoSizeMax - Config.droppedCargoSizeMin) +1)+Config.droppedCargoSizeMin;
    }
    
    public Integer getSize() 
    {
        return size;
    }
    
    public void setSize(Integer cargoSize)
    {
        size = cargoSize;
    }
}
