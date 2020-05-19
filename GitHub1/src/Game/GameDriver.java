package Game;

import java.util.Scanner;
import java.util.Random;

//There is basically 2 players and a Gameboard. The Gameboard is made up of levels and positions and players will rolls dies and attempt to
//reach the end of the Gameboard FirstPlayer.They will gather energy and use energy along the way to roll the 2 die and whoever reaches the end FirstPlayer wins.This code uses classes, arrays and many other
//things to achieve its goal.

public class GameDriver {

	public static void main(String[] args) {

		System.out.println(
				"************************************************************\n"
						+ "\tWELCOME to a ladder Board-Game!\n"
						+ "************************************************************\n");
		//Declaring scanner , random generator and objects for later usage
		Scanner KeyboardInput = new Scanner(System.in);
		Random gen = new Random();
		Player player[] = new Player[2];
		Dice dice = new Dice();
		Board Gameboard = null;
		//Giving instructions to the player
		System.out.print("The default game Gameboard has 3 levels and each level has a 4x4 Gameboard.\n"
				+ "You can use this Gameboard size or change the size\n\t" + "0 to use the Gameboard size\n\t"
				+ "-1 to enter your own size\n" + "What do you want to do? ");

		// Giving a choice to the player(s) between a normal sized board or a size they choose

		for (boolean Stop = false; Stop == false;) { //Setup a for loop in case the inputed values are not allowed, this way we will be able to repeat the question until the player inputs a valid response

			int input = KeyboardInput.nextInt();//Declare an integer that will store the player's input
			if (input == 0) {//0 means the default board, which calls for the default constructor
				Gameboard = new Board();
				break;//Loop will stop if the default board is chosen
			}
			else if (input == -1) {//-1 means a specific board chosen by the player
				System.out.print("How many levels would you like? (minimum size 3, max 10) ");//Giving instructions to the player
				int level = KeyboardInput.nextInt();//Declare integer for the custom level
				while (level > 10 || level < 3) { //Checks if the level is valid, if its not then it will continously ask for a valid input until it gets one
					System.out.println("Sorry but " + level + " is not a legal choice");
					level = KeyboardInput.nextInt();
				}
				System.out.print("\nWhat size do you want the nxn boards on each level to be?\n"//Same logic as the level
						+ "Minimum size is 3x3, max is 10x10\n" + "Enter the value of n: ");

				int size = KeyboardInput.nextInt();
				while (size <3 || size >10) {
					System.out.println("Sorry but " + size + " is not a legal choice");
					size = KeyboardInput.nextInt();
				}
				if (size >2 && size<11) {
					Gameboard = new Board(level, size); break;}//Sets up the board with the parameter-ed constructor
			}
		}


		//Next line of code uses the toString method of the board classes to print the board
		System.out.println("Your 3D Gameboard has been set up and looks like this:\n");
		Gameboard.toString();


		//Next line of codes will ask for the player's names and stores them into the player array object
		System.out.print("\nWhat is player 0's name (one word only): ");
		player[0] = new Player(KeyboardInput.next());
		System.out.print("What is player 1's name (one word only): ");
		player[1] = new Player(KeyboardInput.next());


		//Next line of codes will randomly generate which player will play FirstPlayer
		int InitialPlayer = gen.nextInt(2);
		String FirstPlayer = "";//Declare a new String to store in the name of the first player
		if (InitialPlayer == 0) { FirstPlayer = player[0].getName(); }
		else if (InitialPlayer == 1) { FirstPlayer = player[1].getName();}


		// Next section is the game and how it works
		System.out.println("\nThe game has started and " + FirstPlayer + " is playing first!\n===================================\n");
		for (boolean WinAchieved = false; WinAchieved == false;) {

			//The next lines of code will control the player's movement and energy gains etc.
			for (int k = 0; k < 2; k++) {
				System.out.println("It's " + player[InitialPlayer].getName() + "'s turn");

				// The next line of code will make the player try to roll doubles in case they have less or equal to 0 energy
				//Checks if they have <= 0 energy
				if (player[InitialPlayer].getEnergy() <= 0) { 
					int MultiplicationFactor = Dice.DoubleCheck(dice);//Declare a multiplicator integer that uses the newly made doublecheck method, it stores the amount of doubles rolled
					player[InitialPlayer].setEnergy(2 * MultiplicationFactor);//The amount of doubles rolled times 2 = the amount of energy gained
					if (MultiplicationFactor == 0) {//If no doubles were rolled then print out that they failed
						System.out.println( player[InitialPlayer].getName() + " has no energy and failed his rolls. Their turn is skipped\n");
						InitialPlayer = Player.Switch(InitialPlayer);
						continue;}
					else {//If they rolled doubles or not , it will print out how many were rolled and how much energy is gained
						System.out.println(player[InitialPlayer].getName() + " has no energy and rolls " + MultiplicationFactor
								+ " double(s). Your energy went up by " + (2 * MultiplicationFactor));}
				}
				Player PlayerCopy = new Player(player[InitialPlayer]);//Seting up a boject that copies the player's current variable values for later usage
				// Here we turn the roll dice method into an integer for easier usage
				int roll = dice.rollDice();
				System.out.println(player[InitialPlayer].getName() + " " + dice.toString());
				//isDouble Method
				if (dice.isDouble()) {//Uses the isDouble method to check if the roll is a double and gives energy according to the outcome
					System.out.println("You rolled a double " + dice.getDie1() + ". Your energy went up by 2 units.");
					player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() + 2);
				}

				// Change the placement of the player depending on the roll(uses the assignment's instruction logic)
				int x = player[InitialPlayer].getX() + (player[InitialPlayer].getY() + roll) / Gameboard.getSize(); 
				int y = (player[InitialPlayer].getY() + roll) % Gameboard.getSize();
				if (x >= Gameboard.getSize()) {
					x = x % Gameboard.getSize();
					player[InitialPlayer].setLevel(player[InitialPlayer].getLevel() + 1);
					if (player[InitialPlayer].getLevel() >= Gameboard.getLevel()) {
						System.out.println("Out of bound. Lose 2 energy.");
						player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() - 2);
						player[InitialPlayer].setLevel(player[InitialPlayer].getLevel() - 1);
						x = player[InitialPlayer].getX();
						y = player[InitialPlayer].getY();
					}
				}
				player[InitialPlayer].setX(x);//Changing the player's position values here
				player[InitialPlayer].setY(y);

				if ((y == Gameboard.getSize() - 2) &&  (x == Gameboard.getSize() - 1)//Here we make it so the player will go backwards spaces if the is at the last position since if there is only 1 space left , 2 dies cannot get a value of 1
						&& (player[InitialPlayer].getLevel() == Gameboard.getLevel() - 1)) {
					player[InitialPlayer].setX(x);
					player[InitialPlayer].setY(Gameboard.getSize() - 3);

					System.out.println("You landed on the second to last space. Move Backward");
				}

				//////////////////////////////////////////////////////////////////////////////////////////////////////////				
				////////////////////////////////////The Challenge or Forfeit System///////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////////////				
				if (player[InitialPlayer].equals(player[0]) && player[InitialPlayer].equals(player[1])) {//Setting up the condition

					System.out.println("Landed on other player's location\n" + "What do you want to do?\n"//Giving out the information that it has happened
							+ "\t0-Challenge\n" + "\t1-Forfeit");

					for (;;) {
						int PlayerChoice = KeyboardInput.nextInt();//Declare an integer that will be either 0 or 1
						// Next line of code will
						if (PlayerChoice == 1) {
							if (player[InitialPlayer].getLevel() == 0) {//If the player's level is 0 and they forfeit then their position will be set to 0
								player[InitialPlayer].setX(0);
								player[InitialPlayer].setY(0);} 
							else {
								player[InitialPlayer].setLevel(player[InitialPlayer].getLevel() - 1);}//If the player's level is not 0, you lose 2 energer and lose 1 level
							player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() - 2);
							System.out.println("You lost 2 energy and moved 1 level down");
							break;

						} else if (PlayerChoice == 0) {//In case they want to challenge then another outcome will be decided
							Player OriginalPlace = new Player(PlayerCopy);//An object just to keep the original position in case they change place or not
							int ChallengeRoll = gen.nextInt();//A special roll just for a challenge
							if (ChallengeRoll < 6) {//If the roll is less than 6 , the player A loses
								player[InitialPlayer].moveTo(PlayerCopy);//The copy of the player will not change position
								System.out.println("You lost the challenge and half your energy");
								player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() / 2);//They lose half their energy

							} else {
								System.out.println("Nice! You won the challenge");//If they won
								if (InitialPlayer == 0) { //Since we switch between players with the integer 0 and 1(in the array there is only 1 and 0) thanks to the special method added
									player[InitialPlayer].moveTo(player[1]);//Players will switch places
									player[1].moveTo(OriginalPlace);//The player B will go to the original one
									player[1].setEnergy(player[1].getEnergy() / 2);//The player B loses half his energy
									player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() + player[1].getEnergy());//The player A gains the player B's lost enegery
								}

								else if (InitialPlayer == 1) {//Same as last code , just if the player that challenges is actually player A
									player[InitialPlayer].moveTo(player[0]);
									player[0].moveTo(OriginalPlace);
									player[0].setEnergy(player[0].getEnergy() / 2);
									player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy() + player[0].getEnergy());
								}
							}
								break;		
							} 
								else {
								System.out.println("Sorry but " + PlayerChoice + " is not a valid choice."); //If the entered value is not 0 or 1
								}	
					}

				}

				// The next line of code will adjust the player's level based on what energy cell they land on
				player[InitialPlayer].setEnergy(player[InitialPlayer].getEnergy()
						+ Gameboard.getEnergyAdj(player[InitialPlayer].getLevel(), player[InitialPlayer].getX(), player[InitialPlayer].getY()));

				System.out.println("Your energy value is adjusted by "
						+ Gameboard.getEnergyAdj(player[InitialPlayer].getLevel(), player[InitialPlayer].getX(), player[InitialPlayer].getY())
						+ " for landing at (" + player[InitialPlayer].getX() + "," + player[InitialPlayer].getY() + ")\n");

				// If player is on last case. Player wins and loop stops
				if (player[InitialPlayer].won(Gameboard)) {
					k = 2;
					WinAchieved = true;
					break;
				}
				//Use the method that we added to switch the player's turns
				InitialPlayer = Player.Switch(InitialPlayer);
			}

			// Energy resets to 0 if negative
			if (player[0].getEnergy() < 0) {
				player[0].setEnergy(0);
			}
			if (player[1].getEnergy() < 0) {
				player[1].setEnergy(0);
			}


			//Message to print out at the end of every round, uses the toString method to give information about the player's position
			System.out.println("At the end of this round, the current state of the players is :");
			System.out.println("\t" + player[0].toString() + "\n\t" + player[1].toString());

			// If player wins, game ends
			if (player[InitialPlayer].won(Gameboard) ) {
				System.out.println("\nThe winner is " + player[InitialPlayer].getName() + "! Congratulations!! \n" + "The Game is over. Thanks for playing!");
				break;
			}
	        //The next line of code will make the game continue if it has not ended
			System.out.print("Enter any key to continue the game....");
			String nextTurn = KeyboardInput.next();
			System.out.println("_________________________________________________________________________\n");
			if (nextTurn.length() > 0) { //Here , to achieve the "press any button" condition , if the length is higher than 0 that means that some button was pressed
				continue;
			}
		}

		KeyboardInput.close();
	}
}


