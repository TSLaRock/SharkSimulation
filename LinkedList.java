package SharkSimulation;

public class LinkedList
{
  // -- DATA -- \\
  private Hero head;
  private Hero tail;
  private Hero currentHero;
  private int size = 0;
  private boolean rankSwitch = false; // used to switch between adding to queue or stack
  private float globalTime = 0.0f; // only needed in the class for printing purposes, sharks
  // are handled in main
  private float nemesisCageTime = 0.0f;
  
  
  // -- CONSTRUCTOR -- \\
  public LinkedList()
  {
    this.head = null;
    this.tail = null;
    this.currentHero = null;
    size = 0;
  }
  
  public LinkedList(Hero newHero)
  {
    size = 0;
    push(newHero);
  }
  
  // -- METHODS -- \\
  
  // Decides whether to treat the list as a stack or a queue
  // and adds heroes accordingly
  public void add(Hero newHero)
  {
    // if rankSwitch has not been flipped
    if(rankSwitch == false)
    {
      // if the hero is not a nemesis, add
      // to the queue as usual
      if(newHero.getRank() == false)
      {
        this.enqueue(newHero);
      }
      // if the hero is a nemesis,
      else if(newHero.getRank() == true)
      {
        // add him to the list as before,
        // activate rankSwitch
        this.enqueue(newHero);
        rankSwitch = true;
      }
    }
    // if rankSwitch has been flipped
    else
    {
      // treat the list as a stack, pushing new
      // heroes onto it so that the nemesis hero
      // has to watch them get eaten
      this.push(newHero);
      
    }
    
    // increment global time for internal use
    globalTime += newHero.getTime();
    
    
    // if there is a nemesis in the cages,
    // increment how long he or she has been in there
    if(rankSwitch == true && (newHero.getRank() == false))
    { 
      nemesisCageTime += newHero.getTime();
      
      // if the nemesisCageTime is more than 1 hour
      // the monlogue is too long and everyone should
      // be terminated
      if(nemesisCageTime >= 1)
      {
        System.out.println("DRAT! I've squabbled too long! Henchman, empty the cages!"
                             + " I need to add more sharks.");
        this.popAll();
      }
      
    }
  }
  
  
  public void push(Hero newHero)
  {
    // If the list is empty, create the head
    if( size == 0)
    {
      head = newHero;
      currentHero = newHero;
      currentHero.setPrevHero(null);
      currentHero.setNextHero(null);
      tail = null;
    }
    // Special case if size is 1
    else if(size == 1)
    {
      head.setPrevHero(newHero);
      newHero.setNextHero(head);
      head = newHero;
    }
    // otherwise, add the hero to the top of the stack
    else
    {
      head.setPrevHero(newHero);
      newHero.setNextHero(head);
      head = newHero;
    }
    
    // reset current, increment size
    currentHero = head;
    size ++;
  }
  
  public void pop()
  {
    // if the hero is being popped immediately upon arrival,
    // feed him to the sharks immediately
    if((globalTime - head.getCaptureTime()) == 0.0)
    {
      System.out.println(head.getName() + " was immmediately fed to the sharks!");
      head.getNextHero().setPrevHero(null);
      head = head.getNextHero();
    }
    else
    {
      System.out.println(head.getName() + " was thrown to the sharks after " + (globalTime - head.getCaptureTime()));
      
      // if the size is larger than 1, change the references to head
      if(size > 1)
      {
        head.getNextHero().setPrevHero(null);
        head = head.getNextHero();
      }
      // special case if the size = 1
      // set the head to null, otherwise
      // popAll() will enter an infinite loop
      else if(size == 1) 
      {
        head = null;
      }
    }
    // reset current
    // decrement size
    currentHero = head;
    size--;
  }
  
  public boolean isEmpty()
  { return(head == null); }
  
  public void popAll()
  {
    // while there is data in the list
    // pop()
    while(this.isEmpty() == false)
    {
    this.pop();
    }
    
    // this was originally done by simply setting
    // the head = null, but for printing purposes it was
    // actually more convienient to use the pop() method
  }
  
  public void enqueue(Hero newHero)
  {
    // if the list is empty, make the newHero the head
    if(size == 0)
    {
      head = newHero;
      newHero.setNextHero(null);
      newHero.setPrevHero(null);
    }
    // special case for if size == 1 because
    // the head must be referenced directly
    else if(size == 1)
    {
      head.setNextHero(newHero);
      tail = newHero;
      newHero.setPrevHero(head);
      newHero.setNextHero(null);
    }
    // otherwise, add to the tail
    else
    {
      tail.setNextHero(newHero);
      newHero.setPrevHero(tail);
      tail = newHero;
    }
    // reset current, increment size
    currentHero = head;
    size++;
  }
  
  
  public void dequeue()
  {
    // if the hero arrives when the sharks are hungry, 
    // immediately feed them to the sharks
    if((globalTime - head.getCaptureTime()) == 0.0)
    {
      System.out.println(head.getName() + " was immmediately fed to the sharks!");
    }
    // otherwise, feed first hero in the queue to the sharks
    else
    {
      System.out.println(head.getName() + " was thrown to the sharks after " + (globalTime - head.getCaptureTime()));
      if(size > 1)
      {
        head = head.getNextHero();
      }
    }
    
    // decrement size
    size--;
    
  }
  
  // dequue all
  public void dequeueAll()
  {
    while( head.getNextHero() != null)
    {
      dequeue();
    }
    dequeue();
  }
  
  
  // print the list
  public void printList()
  {
    // if the list is empty, tell the user
    if(this.isEmpty() == true)
    {System.out.println("EmptyList");}
    // otherwise, travers and print the list
    else
    {
      for(int i = 0; i < size; i++)
      {
        System.out.println(currentHero.getName());
        if(currentHero.getNextHero() != null){
          currentHero = currentHero.getNextHero();
        }
      }
    }
  }
  
}
