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
public class RailroadSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Railroad Simulator started");
        RailRoad railroad = new RailRoad(Config.numberOfStations, Config.numberOfTrains);
    }
}

/*
TODO


REFACTOR
Te hoge koppeling. Train moet minder van Trainstation weten en andersom
Trains omzetten naar state pattern
*/