package Game;


// Written by: Farouk Ihdene 40129461
// For COMP 248 Section P  – Fall 2019
// For 2nd December 2019 
// --------------------------------------------------------

import java.util.Random;

//Dice class acting as a die for the game

public class Dice {

	// Instance variables for each die

	private int die1;
	private int die2;
	// Constructor
	public Dice() {
	
	}
	// Accessor for the two dies

	public int getDie1() {
		return die1;
	}

	public int getDie2() {
		return die2;
	}

	// Method for the total dice roll

	public int rollDice() {
		Random Generator = new Random();
		this.die1 = Generator.nextInt(6) + 1;
		this.die2 = Generator.nextInt(6) + 1;
		return this.die1 + this.die2;
	}

	// Method to check if we rolled a double

	public boolean isDouble() {
		return this.die1 == this.die2;
	}

	// toString method

	public String toString() {
		String info1 = Integer.toString(this.die1);
		String info2 = Integer.toString(this.die2);
        return "rolled Die1: " + info1 + " Die2: " + info2;
	}

	// Method added to check how many doubles were rolled

	public static int DoubleCheck(Dice a) {
		a.rollDice();//Rolls dice
		boolean y = a.isDouble(); //3 booleans will return each if a double was rolled, if it was then its true and the if statements below return the amount of doubles in integer value
		a.rollDice();
		boolean x = a.isDouble();
		a.rollDice();
		boolean z = a.isDouble();
		if (y & x & z) {return 3;}
		else if (x && y || x && z || y && z) {return 2;}
		else if (x || y || z) {return 1;}
		else {	return 0;}

	}

}
