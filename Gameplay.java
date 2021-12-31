import java.util.Scanner;
import java.util.Random;

class Puzzle {
	
	private static int[][] PuzzleMaker(int[][] basePuzzle, int rows) { 
	    Random randGen = new Random();
	    int AVal;
	    int BVal;
	    int columnAIndex;
	    int columnBIndex;
		
		int[][] basePuzzleArray = basePuzzle;
		int[][] easyPuzzleArray = basePuzzleArray;
		
		// loop for switching 1 number per row
		for (int r = 0; r <= rows; ++r) { 			
			int shuffleNum = randGen.nextInt(4); 			// random number 0-3 (for row A)
			int shuffleNum2 = randGen.nextInt(4); 			// (for row B)

			columnAIndex = shuffleNum; 						// assign random to variable used for column index
			columnBIndex = shuffleNum2;  	
			
			AVal = easyPuzzleArray[r][columnAIndex]; 		// get the value of a random element in row A
			BVal = easyPuzzleArray[r+1][columnBIndex];		// get the value of a random element in row B
			
			// switch an element in row A with an element in row B 
			easyPuzzleArray[r+1][columnBIndex] = AVal; // 
			easyPuzzleArray[r][columnAIndex] = BVal;

		}
		
		return easyPuzzleArray;
	}
	
	public static void PuzzlePrinter(int[][] puzzle) {
		int[][] puzzleArray = puzzle;

		for (int i = 0; i < 4; i++)
		{
		    for (int j = 0; j < 4; j++)
		    {
		        System.out.print(puzzleArray[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
	
	
	public static int[][] PuzzleGenerator() {
		int[][] returnArray = new int[4][4];
		int[][] basePuzzleArray = {
				{0, 1, 2, 3}, // row 0
				{4, 5, 6, 7}, //  row 1
				{8, 9, 10, 11}, // row 2
				{12, 13, 14, 15} // row 3
		};
		
		Scanner scnr = new Scanner(System.in);
		String userChoice;
		
		System.out.println("Please select a difficulty level: \n"
				+ "__________________________\n"
				+ "+++ Press 1 for EASY   +++\n"
				+ "+++ Press 2 for MEDIUM +++\n"
				+ "+++ Press 3 for HARD   +++\n"
				+ "__________________________");
		
		userChoice = scnr.next();
		
		switch (userChoice) {
		case ("1"):
			int[][] easyPuzzleArray = PuzzleMaker(basePuzzleArray, 0);
			PuzzlePrinter(easyPuzzleArray);
			returnArray = easyPuzzleArray;
			break;
		case ("2"):
			int[][] mediumPuzzleArray = PuzzleMaker(basePuzzleArray, 1);
			PuzzlePrinter(mediumPuzzleArray);
			returnArray = mediumPuzzleArray;
			break;
		case ("3"):
			int[][] hardPuzzleArray = PuzzleMaker(basePuzzleArray, 2);
			PuzzlePrinter(hardPuzzleArray);
			returnArray = hardPuzzleArray;
			break;
		}	
		return returnArray;
		
	}

	
	public static int[][] ValidationPuzzleGenerator() {
		Scanner scnr = new Scanner(System.in);
		int[][] puzzleArray = new int[4][4];
		
		System.out.println("Enter 16 numbers, 4 per line:"
				+ "\n((array will appear once you have entered 16 numbers))");

		for(int i = 0; i < 4; ++i) {
			String userInput = scnr.nextLine();
			Scanner scnr2 = new Scanner(userInput);
			while (scnr2.hasNextInt()) {
				for(int j = 0; j < 4; ++j) {
						puzzleArray[i][j] = scnr2.nextInt();
				}
			}
		}
		PuzzlePrinter(puzzleArray);
		return puzzleArray;
	}
	
}


public class Gameplay {
	static boolean gameBeingPlayed = true;;
	static int turns;
	
	private static int[][] BlockSlider (int[][] puzzle, boolean up, boolean left, boolean down, boolean right) {
		int[][] puzzleArray = puzzle;
		int row0 = 0;
		int column0 = 0;
		int tempVal;
		
		// search for 0 in the array and get its index
		for (int i = 0; i < 4; i++)
		{
		    for (int j = 0; j < 4; j++)
		    {
		        if (puzzleArray[i][j] == 0) {
		        	row0 = i;
		         	column0 = j;
		        }
		    }
		} 
		tempVal = puzzleArray[row0][column0];
		
		if (up) {
			
			// switch 0 with element above it
			if (row0 == 0) { // if 0 is on the top row 
				System.out.println("Invalid move");
				turns--;
			}
			
			else { // if 0 is not on the top row 
				puzzleArray[row0][column0] = puzzleArray[row0 - 1][column0];
				puzzleArray[row0 - 1][column0] = tempVal;
			}
		}
		
		if (left) {
			
			// switch 0 with element to the left of it
			if ((row0 != 0) && (column0 == 0)) { // if 0 is on left edge of row
				System.out.println("Invalid move");
				turns--;
			}
			else if ((row0 == 0) && (column0 == 0)) {  // if 0 is top left
				System.out.println("Invalid move");
				turns--;
			}
			else { // neither left edge nor top left
				puzzleArray[row0][column0] = puzzleArray[row0][column0 - 1]; 
				puzzleArray[row0][column0 - 1] = tempVal;
			}
		}
		
		if (down) {
			
			// switch 0 with element under it
			if (row0 == 3) { // if 0 is on the bottom row 
				System.out.println("Invalid move");
				turns--;
			}
			
			else { // if 0 is not on the bottom row 
				puzzleArray[row0][column0] = puzzleArray[row0 + 1][column0];
				puzzleArray[row0 + 1][column0] = tempVal;
			}
		}
		
		if (right) {
			
			// switch 0 with element to the right of it
			if ((row0 != 3) && (column0 == 3)) { // if 0 is on right edge of row
				System.out.println("Invalid move");
				turns--;
			}
			else if ((row0 == 3) && (column0 == 3)) { // if 0 is bottom right
				System.out.println("Invalid move");
				turns--;
			}
			else { // neither right edge nor bottom right
				puzzleArray[row0][column0] = puzzleArray[row0][column0 + 1]; 
				puzzleArray[row0][column0 + 1] = tempVal;
			}
	
		}
		
		return puzzleArray;
	}
	
	
	public static void SlidingPuzzle(int[][] puzzle2) {
		int[][] gameArray = puzzle2;
		boolean up = false;
		boolean left = false;
		boolean down = false;
		boolean right = false;
		Scanner scnr = new Scanner(System.in);
		String userChoice;
		 
		 System.out.println("***ENTER A DIRECTION***"
					+ "\nW for UP"
					+ "\nA for LEFT"
					+ "\nS for DOWN"
					+ "\nD for RIGHT");
		
		while (gameBeingPlayed) {
			userChoice = scnr.next();
			if (userChoice.equals("W")) {
				gameArray = BlockSlider(gameArray, true, false, false, false);
				turns++;
				GameWon(gameArray);
			}
			else if (userChoice.equals("A")) {
				gameArray = BlockSlider(gameArray, false, true, false, false);
				turns++;
				GameWon(gameArray);
				
				
			}
			else if (userChoice.equals("S")) {
				gameArray = BlockSlider(gameArray, false, false, true, false);
				turns++;
				GameWon(gameArray);
			}
			else if (userChoice.equals("D")) {
				gameArray = BlockSlider(gameArray, false, false, false, true);
				turns++;
				GameWon(gameArray);
			}
			
			System.out.println("");
			if (gameBeingPlayed)
				Puzzle.PuzzlePrinter(gameArray);
		}
	}
	
	
	private static void GameWon(int[][] puzzle3) {
		int winCounter = 0;
		int[][] gamePuzzle = puzzle3;
		
		// check if 0 is on the top left & the array is in ascending order
		if (gamePuzzle[0][0] == 0) {
			
			// test for all normal elements
			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 3; ++j) {
					if (gamePuzzle[i][j] < gamePuzzle[i][j + 1]) {
						if (gamePuzzle[i][j] == gamePuzzle[3][2]) { 
							winCounter += 1;	
						}
					}
					else 
						break;
				}
			}
			
			// test for all right edge elements
			for (int i2 = 0; i2 < 3; ++i2) {
				if (gamePuzzle[i2][3] < gamePuzzle[i2 + 1][0]) {
					if (gamePuzzle[i2][3] == gamePuzzle[2][3]) {
						winCounter += 1;
					}
				}
				else 
					break;
			}
		}
			
		// check if 0 is on the bottom right
		else if (gamePuzzle[3][3] == 0) {
						
			// test for all right edge elements
			for (int i4 = 0; i4 < 3; ++i4) {
				if (gamePuzzle[i4][3] < gamePuzzle[i4 + 1][0]) {
					if (gamePuzzle[i4][3] == gamePuzzle[2][3]) { 
						winCounter += 1;
					}
					
				}
				else
					break;
			}
			
			// test for all normal elements
			for (int i3 = 0; i3 < 4; ++i3) {
				for (int j2 = 0; j2 < 3; ++j2) {
					if ((gamePuzzle[i3][j2] < gamePuzzle[i3][j2 + 1]) || (gamePuzzle[i3][j2] == gamePuzzle[3][2])) {
						if (gamePuzzle[i3][j2] == gamePuzzle[3][2]) { 
							winCounter += 1;	
						}
					}
					else 
						break;
							}
				}
			
			}
		
		
		// if all normal elemets and edge elements are ascending
		if (winCounter == 2) {
			System.out.println("You won the game in " + turns + " turns!");
			gameBeingPlayed = false;
		}
		else 
			return;
	}
	
	
	public static void InteractiveGameStart() {
		int[][] gamePuzzle = Puzzle.PuzzleGenerator();
		SlidingPuzzle(gamePuzzle);
		
	}
	
	public static void ValidationGameStart() {
		int[][] gamePuzzle = Puzzle.ValidationPuzzleGenerator();
		SlidingPuzzle(gamePuzzle);
		
	}
	
}
		
	

