/**
*The Map class reads in a file and puts the contents of the file into an array. 
*This class also stores all information and data linked with the game's maps.
*
*@author Sophie Smith
*@version 1.0
*
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Map{

	private char[][] map;
	private char[][] originalMap;
	
	//This method returns the map char[][] array.	
	public char[][] getMap(){
		return map;
	}
	
	public void setMap(char[][] newMap){
		map = newMap;
	}
	
	public char[][] getOriginalMap(){
		return originalMap;
	}
	
	/*This method imports a map from a file and puts it into an array. It will then return
	  the map char[][] array.*/	
	public char[][] importMap(File f){
		int numberOfRows = numberOfLines(f); 			//An integer to store the number of rows.
		int numberOfColumns = charactersPerLine(f); 	//An integer to store the number of columns.
		map = new char[numberOfRows][numberOfColumns]; 
		originalMap = new char[numberOfRows][numberOfColumns];
		int counter = 0;
		try{
			Scanner scanner = new Scanner(f); 		//Scan in a map from a file.
			String tempLine = scanner.nextLine();	//Set each line of the file to the string 'tempLine'.
			tempLine = scanner.nextLine();
			while(scanner.hasNextLine()){ 			
				tempLine = scanner.nextLine();
				for(int rows=0; rows<=tempLine.length()-1; rows++){ //Loop through each line.
					map[counter][rows]= tempLine.charAt(rows);		//Store each character into the array.
					originalMap[counter][rows] = tempLine.charAt(rows);
				}
				counter++;
			}
			scanner.close();
			
		}catch(FileNotFoundException e){
			System.err.print("Out of bounds");
		}
		return map;
	}

	//This method reads and prints the first two lines of a file.	
	public String printGameName(File f){
		try{
			Scanner scanner = new Scanner(f);
			String gameName = scanner.nextLine();
			String goldToWin = scanner.nextLine();
			scanner.close();
			return gameName + "\n " + goldToWin;
		}catch(FileNotFoundException e){
			return "Out of bounds";
		}
	}
	
	public String getMapString(){
		String mapString = "";
		for(int row=0; row<map.length; row++){ 		 //Loop through each row and each
			for(int col=0; col<map[0].length; col++){//column and place each character
				mapString = mapString + map[row][col];
			}
		mapString = mapString + "\n";	
		}
		return mapString;
	}
	
	//This method works out the number of lines in the file.
	public int numberOfLines(File f){
		int counter = 0;
		try{
			Scanner scanner = new Scanner(f);
			while(scanner.hasNextLine()){		//While there is a next line to read from
				counter++;						//the file add one to the counter.
				scanner.nextLine();
			}
			scanner.close();
		}catch(FileNotFoundException e){
			System.err.print("Out of bounds");
		}
		return (counter-2);	
	}

	//This method works out the number of columns in the file.	
	public int charactersPerLine(File f){
		int columns=0;
		try{
			Scanner scanner = new Scanner(f);
			//Read in a line one at a time until the third line is reached.
			String Line = scanner.nextLine();	
			Line = scanner.nextLine();			
			Line = scanner.nextLine();	
			//The length of the third line will give the number of columns.		
			columns = Line.length();
			scanner.close();					
		}catch(FileNotFoundException e){
				System.err.print("Out of bounds");
		}
		return columns;
	}

	//This method reads the file and specifies the amount of gold required to win the game.	
	public int goldToWin(File f){
		int numberOfGold = 0;
		try{
			Scanner scanner = new Scanner(f);
			String number = scanner.nextLine();
			number = scanner.nextLine();
			String[] line = number.split(" ");			//Split the string in between the spaces.
			numberOfGold = Integer.parseInt(line[1]);	//Make the second element of the array an integer.
			scanner.close();
		}catch(FileNotFoundException e){
			System.err.print("Out of bounds");
		}
		return numberOfGold;
	}	
}