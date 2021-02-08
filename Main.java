////////////////////////////////////////////////////////////////////////////////
/// @file Main Holds base chess gameloop and reuesed methods
////////////////////////////////////////////////////////////////////////////////

// Imports
import java.util.Scanner;

////////////////////////////////////////////////////////////////////////////////
/// @brief Class for gameloop
////////////////////////////////////////////////////////////////////////////////
public class Main {

	////////////////////////////////////////////////////////////////////////////
	/// @brief Main class for game loop
	public static void main(String[] args) {
		// Intro
		intro();

		// Instantiate data
		Board board = new Board();
		Scanner input = new Scanner(System.in);
		boolean inputValid = false;
		boolean moveValid = false;
		boolean canMove = false;
		int x = 0;
		int y = 0;
		int newX = 0;
		int newY = 0;

		// Game loop
		while(true) {
			// Player 1 (White)

			clearBoard();

			// Check for winners!!!
			board.isWhiteKingAlive();
			board.isBlackKingAlive();
			System.out.println("White players turn...");
			System.out.println();
			board.drawBoard();
			
			moveValid = false;
			inputValid = false;
			
			while(moveValid == false){
				while(inputValid == false){
					// Take input
					System.out.println();
					System.out.print("X cord of piece:");
					x = input.nextInt();
					x -= 1;
					System.out.print("Y cord of piece:");
					y = input.nextInt();
					y -= 1;
					System.out.println();
					// Is piece selected valid
					inputValid = board.isWhiteThere(x, y);
					if(inputValid == false){
						System.out.println("Selected spot is not a valid piece");
					}
				}
				inputValid = false;
				System.out.println("Piece Selected");
				
				// Check if can move there
				while (inputValid == false){
					System.out.println();
					System.out.print("X cord to move:");
					newX = input.nextInt();
					newX -= 1;
					System.out.print("Y cord to move:");
					newY = input.nextInt();
					newY -= 1;

					if(((newX >= 0) && (newX <= 7)) && ((newY >= 0) && (newY <= 7))){
						inputValid = true;
					}
				}
				
				inputValid = false;

				canMove = board.whiteCanMove(x, y, newX, newY);

				if(canMove == true){
					// Pawn
					if(board.whitePieceType(x, y) == 0){
							moveValid = true;
							System.out.println("Moving pawn");
							System.out.println();
							board.whiteMove(x, y, newX, newY);
					}else{
						moveValid = true;
						board.whiteMove(x, y, newX, newY);
					}
				}else{
					moveValid = false;
					System.out.println();
					System.out.println("Cannot move there");
				}
			}
			

			System.out.println("Player 2's turn");
			System.out.println();
			sleep(1500);

			// Player 2 (black)

			clearBoard();

			// Check for winners!!!
			board.isWhiteKingAlive();
			board.isBlackKingAlive();

			System.out.println("Black players turn...");
			System.out.println();
			board.drawBoard();

			moveValid = false;
			inputValid = false;
			canMove = false;

			while(moveValid == false){
				while(inputValid == false){
					// Take input
					System.out.println();
					System.out.print("X cord of piece:");
					x = input.nextInt();
					x -= 1;
					System.out.print("Y cord of piece:");
					y = input.nextInt();
					y -= 1;
					System.out.println();
					// Is piece selected valid
					inputValid = board.isBlackThere(x, y);
					if(inputValid == false){
						System.out.println("Selected spot is not a valid piece");
					}
				}
				inputValid = false;
				System.out.println("Piece Selected");
				
				// Check if can move there
				while (inputValid == false){
					System.out.println();
					System.out.print("X cord to move:");
					newX = input.nextInt();
					newX -= 1;
					System.out.print("Y cord to move:");
					newY = input.nextInt();
					newY -= 1;

					if(((newX >= 0) && (newX <= 7)) && ((newY >= 0) && (newY <= 7))){
						inputValid = true;
					}
				}
				
				inputValid = false;

				canMove = board.blackCanMove(x, y, newX, newY);

				if(canMove == true) {
					// Pawn
					if(board.blackPieceType(x, y) == 0) {
							moveValid = true;
							System.out.println("Moving pawn");
							System.out.println();
							board.blackMove(x, y, newX, newY);
					} else {
						moveValid = true;
						board.blackMove(x, y, newX, newY);
					}
				} else {
					moveValid = false;
					System.out.println();
					System.out.println("Cannot move there");
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Clears board of lines
	public static void clearBoard() {
		for(int i = 0; i < 100; i++) {
			System.out.println();
		}
	}
	////////////////////////////////////////////////////////////////////////////
	/// @brief Prints a seperator in the output
	public static void seperator() {
		System.out.println("----------------------------------------------------------------------");
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Sleeps for time given
	/// @param time How long to sleep in milliseconds
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch(Exception e) {}
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Intro to game
	public static void intro() {
		// Print logo
		System.out.println("       _                     _____ _                   ");
		System.out.println("      | |                   / ____| |                  ");
		System.out.println("      | | __ ___   ____ _  | |    | |__   ___  ___ ___ ");
		System.out.println("  _   | |/ _` \\ \\ / / _` | | |    | '_ \\ / _ \\/ __/ __|");
		System.out.println(" | |__| | (_| |\\ V / (_| | | |____| | | |  __/\\__ \\__ \\");
		System.out.println("  \\____/ \\__,_| \\_/ \\__,_|  \\_____|_| |_|\\___||___/___/");
		System.out.println();
		sleep(1500);
		seperator();

		// Intro
		System.out.println("Hello and welcome to two player java chess!");
		System.out.println();
		System.out.println("All code is produced by: Maxwell Simpson");
		System.out.println();
		sleep(1500);
		System.out.println("This is a hardcore two player chess game");
		System.out.println();
		System.out.println("If you are put in check you will not be warned and may lose your king, forfeiting the game.");
		System.out.println();
		System.out.println("To play type in the location of a piece you own and then type in where you would like to move it to.");
		System.out.println();
		System.out.println("Player 1's pieces are white and Player 2's are black");
		System.out.println();
		sleep(3000);
		seperator();
		System.out.println();

	}

}
