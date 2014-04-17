package SharkSimulation;

public class Hero {
  
  // -- DATA -- \\
  private String name;
  private boolean rank;
  private float time;
  private Hero prevHero;
  private Hero nextHero;
  private float captureTime;
  
  
  
  
  // -- CONSTRUCTORS -- \\
  public Hero()
  {
    name = null;
    rank = false;
    time = 0.0f;
  }
  
  public Hero(String name, boolean rank, float time)
  {
    this.setName(name);
    this.setRank(rank);
    this.setTime(time);
  }
  
  
  // -- METHODS -- \\
  public void setCaptureTime(float captureTime)
  {
    this.captureTime = captureTime;
  }
  
  public float getCaptureTime()
  {
    return this.captureTime;
  }
  
  
  public void setPrevHero(Hero prevHero)
  {
    this.prevHero = prevHero;
  }
  
  public Hero getPrevHero()
  { return prevHero;}
  
  
  public void setNextHero(Hero nextHero)
  {
    this.nextHero = nextHero;
  }
  
  public Hero getNextHero()
  { return nextHero; }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setRank(boolean rank)
  {
    this.rank = rank;
  }
  
  public boolean getRank()
  { return rank; }
  
  public void setTime(float time)
  {
    this.time = time;
  }
  
  public float getTime()
  {return time;}
  
}