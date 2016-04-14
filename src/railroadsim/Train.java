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
   
   private String state = "Idle";
   
   private Integer count = 0;
   
   Train(String trainName, Integer trainSpeed)
   {
       name = trainName;
       speed = trainSpeed;
       
       System.out.println("Creating train: \"" +  name + "\" with speed " + speed);
   }
   
   public void run() 
   {
       try {
           while(state != "Destroyed") {
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
       switch(state) {
           case "Idle":
               
       }
       count++;
        System.out.println(name + "Says: hi!");
        if(count == 5) {
            state = "Destroyed";
        }
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
