package SharkSimulation;
import java.util.*;

public class Main{
  
  public static void main(String args[])
  {

    // You need to manually adjust the number of sharks
    // by changing the numberofSharks variable.
    // Input is taken in by the scanner class, 
    // and you can add as much as you want, the scanner
    // will accept data until you either enter illegal
    // characters that cause it to break or enter the word
    // exit when prompted to add or exit.  
    
    // create a scanner object
    // and instnatiate variables
    Scanner scanIn = new Scanner(System.in);
    LinkedList heroList = new LinkedList();
    float feedingTime = 8.0f; // This was specified in the assignment but I think it should have been smaller...
    float globalTime = 0.0f;
    int numberofSharks = 1;  // !! number of sharks variable !!
    String input = null;
    
    // while the scanner input isn't "exit"
    // stay in the scanner
    while(input != "exit")
    {
      
      String name = null;
      int rankNumber;
      boolean rank = false;
      float time = 0.0f;
      // Get the hero name from the user
      System.out.println("HERO NAME: ");
      name = scanIn.next();
      
    
      //     !!   EXTRA CREDIT  !!
      // if my name is given as input
      if(name.compareTo("TimothyLaRock") == 0)
      {
        // RUN AWAY!!
        System.out.println("The villian is turning on his henchman, RUN!!");
      }
      
      
      // Othwerise do normal henchmen stuff
      else{
        
        // Get the integer value for the rank
        // and give rank a boolean value
        System.out.println("HERO RANK (0 for normal, 1 for nemesis): ");
        rankNumber = scanIn.nextInt();
        if(rankNumber == 1) rank = true;
        if(rankNumber == 0) rank = false;
        
        
        // Get the time from the user
        System.out.println("HERO TIMELINESS: ");
        time = scanIn.nextFloat();
        
        
        
        //Create the new hero
        Hero newHero = new Hero(name, rank, time); 
        // add the hero
        heroList.add(newHero);
        
        
        // increment global time by the amount of 
        // time it took the hero to get to the trap
        globalTime = globalTime + newHero.getTime();
        // set the capture time of the hero
        newHero.setCaptureTime(globalTime);
        
        
        // if the global time is greater than or equal to the 
        // feeding time, pop a hero from the list
        if(globalTime >= (feedingTime/numberofSharks))
        {
          // print the feeding time and global time
          // !!  Originally used for testing but I think it is a  !!
          // !!  relevant addition to the simulation so I left it in  !!
          System.out.print("Feeding Time: " + (feedingTime/numberofSharks));
          System.out.println("  Global Time: " + globalTime);
          
          
          // pop the hero.
          heroList.pop();
          // increment feeding time so it will work properly with global time
          feedingTime+=feedingTime;
          
        }
        
      }
      
      
      
      
      // Menu options
      System.out.println("ADD OR EXIT?");
      input = scanIn.next();
      if((input.compareTo("ADD") == 0) || (input.compareTo("add") == 0) || (input.compareTo("Add") == 0))
      {
        input = null;
      }
      else if((input.compareTo("EXIT") == 0) || (input.compareTo("exit") == 0) || (input.compareTo("Exit") == 0))
      {
        input = "exit";
      }
      
    }
    
    // print the remainder of the list
    // in case the simulation ends while
    // there are still heroes in cages
    heroList.printList();
  }
}

