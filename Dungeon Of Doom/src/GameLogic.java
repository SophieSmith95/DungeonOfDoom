/**
*The GameLogic class contains all the commands required to play the game Dungeon of Doom
*and can be used by any kind of player e.g. human or computer.
*
*@author Sophie Smith
*@version 1.0
*
*/

import java.io.File;
import java.util.Random;

class GameLogic{
	private int rows;
	private int columns;
	private int numberOfGold;
	private Map map;
	private char[][] map1;
	private int numberOfKeys;
	private int numberOfSwords;
	private boolean moved;	
	
	public GameLogic(){
		map = new Map();
		map1 = map.importMap(new File("Map.txt"));
		numberOfGold = 0;
		numberOfKeys = 0;
		numberOfSwords = 0;
	}
	
	/*This method receives the input from the command line and carries out a response
  	  depending on the input.*/
	public String processCommand(String userInput1){
	String[] userInput = userInput1.trim().split(" ");
		/*If the userInput is any variation of 'move' prompt the user to input
		  their chosen direction and call the moveCommand() method.*/
		if(userInput[0].equals("Move") || userInput[0].equals("move") || 
		  userInput[0].equals("MOVE")){					
			map.setMap(moveCommand(userInput[1], 'P'));
			String move = "";
			if(moved== true){
				move = "Success!";
			}else{
				move = "Fail! Cannot walk into walls";
			}
			return move;
		/*If the userInput is any variation of 'hello' print to the screen the amount
		  of gold required to win and how much gold the player currently has.*/
	}else if(userInput[0].equals("Hello") || userInput[0].equals("hello") || 
			userInput[0].equals("HELLO")){
			hello();
			return "You currently have " + getNumberOfGold() + " pieces of gold";
		/*If the userInput is any variation of 'pickup' call the pickUp() 
		  method.*/
		}else if(userInput[0].equals("PickUp") || userInput[0].equals("Pickup") || 
			     userInput[0].equals("pickup") || userInput[0].equals("PICKUP")){
			return pickUp();
		//If the userInput is any variation of 'quit' call the quit() method.
		}else if(userInput[0].equals("Quit") || userInput[0].equals("quit") ||
				 userInput[0].equals("QUIT")){
			return quit();
		}else if(userInput[0].equals("GodsEye")){
			GodsGUI godsGUI = new GodsGUI();
			return "Success";
		//Anything else typed in by the user is invalid input.
		}else{
			return "Invalid Input";
		}
	}	
	
	//This method returns the value of the integer rows.
	public int getRows(){
		return rows;
	}
	
	//This method returns the value of the integer columns.	
	public int getColumns(){
		return columns;
	}
	
	//This method returns the number of swords the player has.
	public int getNumberOfSwords(){
		return numberOfSwords;
	}
	
	//This method returns map in the form of a String.
	public String getMapString(){
		return map.getMapString();
	}
	
	//This method creates and returns a random integer.
	public int randomNumber(int max){
		Random random = new Random();					//Create a new random object.
		int randomInteger = random.nextInt(max -1) + 0; //This allows a random number 
		return randomInteger;							//between 0 and 4 to be generated
	}
	
	/*This method places a player token on the map in a random position as long as it 
 	  is not a wall. */
	public String placePlayer(char player){
		char wall = '#';
		rows = randomNumber(map.numberOfLines(new File("Map.txt")));	//Set the number of rows.	
		columns = randomNumber(map.charactersPerLine(new File("Map.txt"))); //Set the number of columns.
		if (map1[rows][columns] == wall){
			placePlayer(player);
			return "Cannot place player in wall";
		}else{
			//If the position specified is not a wall then place the player.
			map1[rows][columns] = player; 
			map.setMap(map1);
			String mapString = map.getMapString();
			return mapString;
		}
	}

	//This method moves the players token in the specified direction across the map.
	public char[][] moveCommand(String userInput, char player){
		//If the userInput is any variation of 'north', move the player upwards.
		if(userInput.equals("NORTH") || userInput.equals("North") || userInput.equals("north") ||
			userInput.equals("N") || userInput.equals("n")){
		   	map1 = movePlayer(rows-1, columns, 'P');
		//If the userInput is any variation of 'south', move the player downwards.
		}else if(userInput.equals("SOUTH") || userInput.equals("South") || 
				 userInput.equals("south") || userInput.equals("S") || userInput.equals("s")){
			 map1 = movePlayer(rows+1, columns, 'P');
		//If the userInput is any variation of 'east', move the player to the right.
		}else if(userInput.equals("EAST") || userInput.equals("East") ||
				 userInput.equals("east") || userInput.equals("E") || userInput.equals("e")){
			 map1 = movePlayer(rows, columns+1, 'P');
		//If the userInput is any variation of 'west', move the player left.
		}else if(userInput.equals("WEST") || userInput.equals("West") ||
				 userInput.equals("west") || userInput.equals("W") || userInput.equals("w")){
			  map1 = movePlayer(rows, columns-1, 'P');
		}else{
			System.err.println("Invalid input");
		}
		return map1;	
	}	

	/*This method moves the player in the specified direction as long as it is not
	  a wall. */	
	private char[][] movePlayer(int newRow, int newCols, char player){
		char[][] originalMap = map.getOriginalMap();
		char wall = '#';
		if(map1[newRow][newCols] != wall){
			map1[newRow][newCols] = player;
			/*If the position specified by the row and column was a '.' or 'G'
			  before the player landed on it replace it with a '.'.*/
			if(originalMap[rows][columns] == '.'||originalMap[rows][columns] == 'G'){
				map1[rows][columns] = '.';
			}else if(originalMap[rows][columns] == 'K' &&numberOfKeys>0){
				map1[rows][columns] = '.';
			}else if(originalMap[rows][columns] == 'K' && numberOfKeys<=0){
				map1[rows][columns] = 'K';
			}else if(originalMap[rows][columns] == 'M' && numberOfSwords>0){
				map1[rows][columns] = '.';
			}else if(originalMap[rows][columns] == 'M' && numberOfSwords<=0){
				map1[rows][columns] = 'M';
			}else if(originalMap[rows][columns] == 'S' && numberOfSwords>0){
				map1[rows][columns] = '.';
			}else if(originalMap[rows][columns] == 'S' && numberOfSwords<=0){
				map1[rows][columns] = 'S';
			}else if(originalMap[rows][columns] == 'D' && numberOfKeys>0){
				map1[rows][columns] = '.';
			}else if(originalMap[rows][columns] == 'D' && numberOfKeys<=0){
				map1[rows][columns] = 'D';
			}else if(originalMap[rows][columns] == 'D'){
				map1[rows][columns] = 'D';
			}else{
				/*If the position specified by the row and column was not a '.' or 
				 'G' before the player landed on it replace it with an 'E'.*/
				map1[rows][columns] = 'E';
			}
			columns = newCols;
			rows = newRow;
			moved = true;
			System.out.println("Success!");
		}else{
			moved = false;
			System.err.println("Fail, cannot walk into a wall");
		}
		return map1;
	}		
	
	//This method is used to pickup an item on the map e.g. a piece of gold.	
	public String pickUp(){
		char[][] originalMap = map.getOriginalMap();
		char gold = 'G';
		char key = 'K';
		char sword = 'S';
		if(originalMap[rows][columns] == gold){
			map1[rows][columns] = '.';
			numberOfGold++;
			return "Success! Amount of gold in pocket: " + numberOfGold;
		}else if(originalMap[rows][columns] == key){
			map1[rows][columns] = '.';
			numberOfKeys++;
			return "Success! You have picked up a key";
		}else if(originalMap[rows][columns] == sword){
			map1[rows][columns] = '.';
			numberOfSwords++;
			return "Success! You have picked up a sword";
		}else{
			return "Fail there is no nothing to pick up";
		}
	}

	//This method is used to check if the player can slay a dragon.
	public String slayDragon(){
		char[][] originalMap = map.getOriginalMap();
		char dragon = 'M';
		//If the players is standing on a dragon and has at least one sword they can slay the dragon.
		if(originalMap[rows][columns] == dragon && numberOfSwords > 0){
			return "Success! You have slayed the dragon";
		}else{
		//If the player is standing on a dragon and doesnt have a sword move the player back one space.
			map1 = movePlayer(rows-1, columns, 'P');
			return "Fail! You need to find a sword to slay the dragon";
		}
	}
	
	//This method is used to check if the player can slay a dragon.
	public String openDoor(){
		char[][] originalMap = map.getOriginalMap();
		char door = 'D';
		//If the players is standing on a door and has at least one key they can unlock the door.
		if(originalMap[rows][columns] == door && numberOfKeys > 0){
			return "Success! You have opened the door";
		}else{
		//If the player is standing on a door and doesnt have a key move the player back one space.
			map1 = movePlayer(rows, columns+1, 'P');
			return "Fail! You need to find a key to open the door";
		}
	}
	
	//This method returns the value of the integer numberOfGold.
	public int getNumberOfGold(){
		return(numberOfGold);
	}

	//This method prints to the screen how much gold the player requires to win.	
	public void hello(){
		System.out.println("Gold " + map.goldToWin(new File("Map.txt")));
	}

	/*This method is used to exit the game if the player is on an exit space and has the
   	  required number of gold.*/	
	public String quit(){
		char[][] originalMap = map.getOriginalMap();
		char exit = 'E';
		if(originalMap[rows][columns] == exit){
			if(numberOfGold >= map.goldToWin(new File("Map.txt"))){ 
			return "You have won!";
			}else{
				return "You dont have enough coins to exit";
			}
		}else{
			return "You are not on an exit space";
		}
	}

	//This returns a string which is a 5x5 view of the map around the player.	
	public String look(){
		char[][] lookMap = map.getMap();
		int tempRow = rows;
		int up = (tempRow+3);		//The number of the row that is two above the player.
		int down = (tempRow-2);		//The number of the row that is two below the player.
		int tempCol = columns;
		int right = (tempCol+3);	//The number of the column that is two to the right of the player.
		int left = (tempCol-2);		//The number of the column that is two to the left of the player.
		String lookString = "";
		for(int row=down; row<up; row++){
			for(int cols=left; cols<right; cols++){
				if(row<0){
					lookString = lookString + "x";
				}
				else if(row>lookMap.length){
					lookString = lookString + "x";
				}
				else if(cols<0){
					lookString = lookString + "x";
				}
				else if(cols>lookMap[row].length){
					lookString = lookString + "x";
				}
				else{
					lookString = lookString + lookMap[row][cols];
				}
			}
		lookString = lookString + "\n";
		}
		return lookString;
	}
}	