package Game;


public class Board {
	//Declaration of the instance variables
	private int[][][] board ;
	private static int MIN_LEVEL = 3;
	private static int MIN_SIZE = 3;
	private int level;
	private int size;
	//Declaration of the overloaded constructor
	public Board() {
		this.level = 3;
		this.size= 4 ;
        createBoard(3,4);
	}
	
	public Board(int l, int x) {
		this.level = l;
		this.size = x;
		createBoard(l,x);
	}
	
//Private method to create the board (where energy is and where it is not)
	private void createBoard(int level, int size) {
		this.board=new int[level][size][size];
		
		for(int l=0;l<level;l++) {//Three for loops that loop through every space in the board and give it energy values
			
			for(int x=0;x<size;x++) {
					
				for(int y=0;y<size;y++) {
					
					if(x+y+l==0) {
						this.board[l][x][y]=0;
					}
					
					else if((x+y+l) % 3 == 0) {
						this.board[l][x][y]=-3;
					}
		
					else if((x+y+l) % 5 == 0) {
						this.board[l][x][y]=-2;
					}
		
					else if((x+y+l) % 7 == 0) {
						this.board[l][x][y]=2;
					}
		
					else {
						this.board[l][x][y]=0;
					}
				}
			}
		}
	}
//Declaration of Setters and Getters
	public int getLevel() {
		return level;
	}

	public int getSize() {
		return size;
	}
//Declaration of method that gives the value of an energy cell at a certain place in the board	
	public int getEnergyAdj(int l, int x, int y) {
		return this.board[l][x][y];
	}
//A public method that returns a "drawing" of a board where each energy cell is shown in the main method
	public String toString() { 			     
	     
	     String BoardInfo = "";
	     String fullBoard ="";
	     for(int l = 0; l< getLevel(); l++) {//a For loop to repeat creation of lines and creation of energy cells 
	    	 
	         System.out.println("Level " + l + "\n --------");
	         
	         for (int x = 0; x< getSize();x++) { 
	        	 
	        	 for (int y = 0;y< getSize();y++) {
	                BoardInfo = String.valueOf(board[l][x][y]);

	                System.out.print("\t" + BoardInfo);
	                if (y == getSize() - 1) {System.out.println();}
	                }
	            }
	        }
	     return fullBoard;
	}
   
}