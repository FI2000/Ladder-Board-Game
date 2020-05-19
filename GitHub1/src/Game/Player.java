package Game;

public class Player{
	
	//Instance variable declarations
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;
   //Overload constructors declarations
    public Player() {
	   this.name = "";
	   this.energy = 10;
	   this.level = 0;
	   this.x = 0;
	   this.energy = 0;
   }
    public Player(String name) {
    	this.name = name;
    	this.energy = 10;
    	this.x = 0;
    	this.energy = 0;
    	this.level = 0;
    }
    public Player(int x, int y, int level) {
    	this.x = x;
    	this.y =y;
    	this.level = level;
    }
    public Player(Player Player) {
    	x = Player.x;
    	y = Player.y;
    	level = Player.level;
    	energy = Player.energy;
    }
    //Declaration of Setters and Getters for each variable
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
    //Declaration of methods
	public void moveTo(Player p) {//Moves the players position to the pointed player object
    	this.level =p.level;
    	this.x = p.x;
    	this.y = p.y;
    }
    
    public boolean won(Board b) {
    	return (this.level == (b.getLevel() -1) && this.y==(b.getSize() -1) && this.x==(b.getSize()-1));//If the position and level are at their final value on the board , it gives back a true value
    } 
    public boolean equals(Player p) {
    	return (this.x == p.x && this.y==p.y && this.level == p.level);//Checks if 2 players are on the same place
    }
    public String toString() {
    	String info=(name + " is on level " + level + " at location (" + x + "," + y + ") and has " + energy + " units of energy.");//String that gives general info about the players
		return info;
    }
    //Added method to switch the value of an integer used in the main method(mainly used to switch between the two players)
    public static int Switch(int start) {
    	if (start == 0) {return 1;}//If the value of the parameter integer is 0 then it gives 1 , else it stays 0
    	else {return 0;}
      }
}
