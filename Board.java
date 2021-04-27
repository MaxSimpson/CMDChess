
////////////////////////////////////////////////////////////////////////////
/// @file File for board class
////////////////////////////////////////////////////////////////////////////

// Imports
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
/// @brief Class for chess board
////////////////////////////////////////////////////////////////////////////
public class Board {
	// Public

	////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for making blank board
	public Board() {
		// Set to space for everything
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				board[i][j] = "  ";

		// Setup pieces on the board
		blackSetup();
		whiteSetup();
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks if white king is alive
	public void isWhiteKingAlive() {
		int kings = 0;
		for(int i = 0; i < whitePieces.size(); i++)
			if(whitePieces.get(i).pieceType() == 2)
				kings += 1;
		if(kings != 1) {
			System.out.println();
			System.out.println("BLACK WINS!!!");
			System.exit(0);
		}
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks if black king is alive
	public void isBlackKingAlive() {
		int kings = 0;
		for(int i = 0; i < blackPieces.size(); i++)
			if(blackPieces.get(i).pieceType() == 2)
				kings += 1;
		if(kings != 1) {
			System.out.println();
			System.out.println("WHITE WINS!!!");
			System.exit(0);
		}
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Moves white piece
	/// @param x Current x
	/// @param y Current y
	/// @param newX X to move to
	/// @param newY Y to move to
	public void whiteMove(int x, int y, int newX, int newY) {
		// Delete enemy piece
		if(isBlackThere(newX, newY))
			blackPieces.remove(blackIndex(newX, newY));
		// Move
		whitePieces.get(whiteIndex(x, y)).move(newX, newY);
	}

	public void blackMove(int x, int y, int newX, int newY) {
		// Delete enemy piece
		if(isWhiteThere(newX, newY))
			whitePieces.remove(whiteIndex(newX, newY));
		// Move
		blackPieces.get(blackIndex(x, y)).move(newX, newY);
	}

	// Piece in between two points?
	public boolean verticalChecker(int x, int y, int newX, int newY) {
		// Edits
		int baseDifference = x - newX; // - 2 = -1
		int difference  = baseDifference;  // = -1
		if(difference <= 0)
			difference *= -1; // = 1

		for(int i = 1; i <= difference; i++) { 
			if(baseDifference <= 0) { //-1 <= 0
				// Moving down
				if(isWhiteThere(x + i, y)) { // false
					return true;
				} else if(isBlackThere(x + i, y)) { // false
					return true;
				} else {
					return false;
				}
			} else {
				// Moving up
				if(isWhiteThere(x - i, y)) {
					return true;
				}else if(isBlackThere(x - i, y)) {
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks if there is a piece between two points
	/// @param x X of point 1
	/// @param y Y of point 1
	/// @param newX X of point 2
	/// @param newY Y of point 2
	public boolean horizontalChecker(int x, int y, int newX, int newY) {
		// 4,7
		// 4,4
		int baseDifference = y - newY;
		int difference  = baseDifference;
		if(difference <= 0)
			difference *= -1;
		for(int i = 1; i <= difference; i++) {
			if(baseDifference <= 0) {
				// Moving right
				if(isWhiteThere(x, y + i)) {
					return true;
				}else if(isBlackThere(x, y + i)) {
					return true;
				}
			} else {
				// Moving left
				if(isWhiteThere(x, y - i)) {
					return true;
				} else if(isBlackThere(x, y - i)) {
					return true;
				}
			}
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks if there is a piece diagonaly between two points
	/// @param x X of point 1
	/// @param y Y of point 1
	/// @param newX X of point 2
	/// @param newY Y of point 2
	public boolean diagonalChecker(int x, int y, int newX, int newY) {
		int xBaseDifference = x - newX;
		int xDifference = xBaseDifference;
		if(xDifference <= 0)
			xDifference *= -1;

		int yBaseDifference = y - newY;
		int yDifference = yBaseDifference;
		if(yDifference <= 0)
			yDifference *= -1;

		// Up right - X decreasing y increasing
		for(int i = 1; i <= xDifference; i++) {
			if(xBaseDifference >= 0 && yBaseDifference <= 0) {
				if((isBlackThere(x - i, y + i)) || (isWhiteThere(x - i, y + i))) {
					return true;
				}
			// Down right - X increasing y increasing
			} else if(xBaseDifference <= 0 && yBaseDifference <= 0) {
				if((isBlackThere(x + i, y + i)) || (isWhiteThere(x - i, y + i))) {
					return true;
				}
			// Up left - X decreasing y decreasing
			} else if(xBaseDifference >= 0 && yBaseDifference >= 0) {
				if((isBlackThere(x + i, y - i)) || (isWhiteThere(x - i, y + i))) {
					return true;
				}
			// Down left - X increasing y decreasing
			} else if(xBaseDifference <= 0 && yBaseDifference >= 0) {
				if((isBlackThere(x + i, y - i)) || (isWhiteThere(x + i, y - i))) {
					return true;
				}
			}
		}
		// -2 -2 __ down right __ X increasing y increasing
		// -2  2 __ down left  __ X increasing y decreasing
		return false;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks for obstructing pieces to movement for white
	/// @param x X of point 1
	/// @param y Y of point 1
	/// @param newX X of point 2
	/// @param newY Y of point 2
	public boolean whiteCanMove(int x, int y, int newX, int newY) {
		if(whitePieceType(x, y) == 0) {
			// Pawn
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				if(newY != y)
					if(isBlackThere(newX, newY)) {
						return true;
					} else {
						return false;
					}
				else if(isWhiteThere(newX, newY) || isBlackThere(newX, newY)) {
					return false;
				} else {
					return true; 
				}
			} else {
				return false;
			}
		} else if (whitePieceType(x, y) == 1) {	
			// Rook
			int xDifference = x - newX;
			int yDifference = y - newY;
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				if(yDifference != 0) {
					// Horizontal
					// Do horizontal check
					return !horizontalChecker(x, y, newX, newY);
				} else {
					// Vertical
					// Do vertical check
					return !verticalChecker(x, y, newX, newY);
				}
			} else {
				return false;
			}
		} else if(whitePieceType(x, y) == 2) {
			// King
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				return !isWhiteThere(newX, newY);
			} else {
				return false;
			}
		} else if(whitePieceType(x, y) == 3) {
			// Queen
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				// Check if diagonal
				int xBaseDifference = x - newX;
				int yBaseDifference = y - newY;
				int xDifference = xBaseDifference;
				int yDifference = yBaseDifference;
				if(xDifference <= 0)
					xDifference *= -1;
				if(yDifference <= 0)
					yDifference *= -1;

				if(xDifference == yDifference) {
					// Diagonal
					if(!diagonalChecker(x, y, newX, newY)) {
						return true;
					} else {
						return false;
					}
				} else if(xDifference == 0 && yDifference != 0) {
					//Horizontal
					return !horizontalChecker(x, y, newX, newY);
				} else if(xDifference != 0 && yDifference == 0) {
					// Vertical
					return !verticalChecker(x, y, newX, newY);
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if(whitePieceType(x, y) == 4) {
			// Bishop
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				return !diagonalChecker(x, y, newX, newY);
			} else {
				return false;
			}
		} else if(whitePieceType(x, y) == 5) {
			// Knight
			if(whitePieces.get(whiteIndex(x, y)).canMove(newX, newY)) {
				return !isWhiteThere(newX, newY);
			} else {
				return false;
			} 
		} else {
			System.out.println("Unsupported piece");
			System.exit(0);
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Checks for obstructing pieces to movement for black pieces
	/// @param x X of point 1
	/// @param y Y of point 1
	/// @param newX X of point 2
	/// @param newY Y of point 2
	public boolean blackCanMove(int x, int y, int newX, int newY) {
		if(blackPieceType(x, y) == 0) {
			// Pawn
			if(blackPieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				if(newY != y)
					if(isWhiteThere(newX, newY)) {
						return true;
					} else {
						return false;
					}
				else if(isBlackThere(newX, newY) || isWhiteThere(newX, newY)) {
					return false;
				} else {
					return true; 
				}
			} else {
				return false;
			}
		} else if (blackPieceType(x, y) == 1) {	
			// Rook
			int xDifference = x - newX;
			int yDifference = y - newY;
			if(blackPieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				if(yDifference != 0) {
					// Horizontal
					// Do horizontal check
					return !horizontalChecker(x, y, newX, newY);
				} else {
					// Vertical
					// Do vertical check
					return !verticalChecker(x, y, newX, newY);
				}
			} else {
				return false;
			}
		} else if(blackPieceType(x, y) == 2) {
			// King
			if(whitePieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				return !isBlackThere(newX, newY);
			} else {
				return false;
			}
		} else if(blackPieceType(x, y) == 3) {
			// Queen
			if(blackPieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				// Check if diagonal
				int xBaseDifference = x - newX;
				int yBaseDifference = y - newY;
				int xDifference = xBaseDifference;
  			int yDifference = yBaseDifference;

				if(xDifference <= 0)
					xDifference *= -1;
				if(yDifference <= 0)
					yDifference *= -1;
				if(xDifference == yDifference) { 
					// Diagonal
					if(!diagonalChecker(x, y, newX, newY)) {
						return true;
					} else {
						return false;
					}
				} else if(xDifference == 0 && yDifference != 0) {
					//Horizontal
					return !horizontalChecker(x, y, newX, newY);
				} else if(xDifference != 0 && yDifference == 0) { 
					// Vertical
					return !verticalChecker(x, y, newX, newY);
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if(blackPieceType(x, y) == 4) {
			// Bishop
			if(whitePieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				return !diagonalChecker(x, y, newX, newY);
			} else {
				return false;
			}
		} else if(blackPieceType(x, y) == 5) {
			// Knight
			if(blackPieces.get(blackIndex(x, y)).canMove(newX, newY)) {
				return !isBlackThere(newX, newY);
			} else {
				return false;
			} 
		} else {
			System.out.println("Unsupported piece");
			System.exit(0);
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Draws the board on output
	public void drawBoard() {
		// Send chars to board
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				board[i][j] = "  ";

		for(int i = 0; i < whitePieces.size(); i++)
			board[whitePieces.get(i).getX()][whitePieces.get(i).getY()] = whitePieces.get(i).draw();

		for(int i = 0; i < blackPieces.size(); i++)
			board[blackPieces.get(i).getX()][blackPieces.get(i).getY()] = blackPieces.get(i).draw();

		System.out.println("    1    2    3    4    5    6    7    8");
		System.out.println("----------------------------------------");
		for(int i = 0; i < 8; i++) {
			System.out.println(i + 1 + "  " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " | " + board[i][3] 
				+ " | " + board[i][4] + " | " + board[i][5] + " | " + board[i][6] + " | " + board[i][7]);
			System.out.println("----------------------------------------");
		}
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Returns piece type
	/// @param x For finding index
	/// @param y For finding index
	public int whitePieceType(int x, int y) {
		return whitePieces.get(whiteIndex(x, y)).pieceType();
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Returns piece type
	/// @param x For finding index
	/// @param y For finding index
	public int blackPieceType(int x, int y) {
		return blackPieces.get(blackIndex(x, y)).pieceType();
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Returns piece index
	/// @param x For finding index
	/// @param y For finding index
	private int whiteIndex(int x, int y) {
		for(int i = 0; i < whitePieces.size(); i++)
			if((whitePieces.get(i).getX() == x) && (whitePieces.get(i).getY() == y))
				return i;

		// Piece not found
		System.out.println("Error, not a piece");
		System.exit(0);
		return 0;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Returns piece index
	/// @param x For finding index
	/// @param y For finding index
	private int blackIndex(int x, int y) {
		for(int i = 0; i < blackPieces.size(); i++)
			if((blackPieces.get(i).getX() == x) && (blackPieces.get(i).getY() == y))
				return i;

		// Piece not found
		System.out.println("Error, not a piece");
		System.exit(0);
		return 0;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Finds if black piece is at specific point
	/// @param x For finding if black piece is at a given point
	/// @param y For finding if black piece is at a given point
	public boolean isBlackThere(int x, int y) {
		for(int i = 0; i < blackPieces.size(); i++)
			if((blackPieces.get(i).getX() == x) && (blackPieces.get(i).getY() == y))
				return true;
		return false;
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Finds if white piece is at specific point
	/// @param x For finding if black piece is at a given point
	/// @param y For finding if black piece is at a given point
	public boolean isWhiteThere(int x, int y) {
		for(int i = 0; i < whitePieces.size(); i++)
			if((whitePieces.get(i).getX() == x) && (whitePieces.get(i).getY() == y))
				return true;
		return false;
	}

	// Private
	private String[][] board = new String[8][8]; //< Chess board
	private ArrayList<Piece> whitePieces = new ArrayList<Piece>(); //< White pieces on board
	private ArrayList<Piece> blackPieces = new ArrayList<Piece>(); //< Black pieces on board
		
	////////////////////////////////////////////////////////////////////////
	/// @brief Sets up black pieces on the board
	private void blackSetup() {
		blackPieces.add(new Rook(7, 0, 'B'));
		blackPieces.add(new Knight(7, 1, 'B'));
		blackPieces.add(new Bishop(7, 2, 'B'));
		blackPieces.add(new King(7, 3, 'B'));
		blackPieces.add(new Queen(7, 4, 'B'));
		blackPieces.add(new Bishop(7, 5, 'B'));
		blackPieces.add(new Knight(7, 6, 'B'));
		blackPieces.add(new Rook(7, 7, 'B'));
		blackPieces.add(new Pawn(6, 0, 'B'));
		blackPieces.add(new Pawn(6, 1, 'B'));
		blackPieces.add(new Pawn(6, 2, 'B'));
		blackPieces.add(new Pawn(6, 3, 'B'));
		blackPieces.add(new Pawn(6, 4, 'B'));
		blackPieces.add(new Pawn(6, 5, 'B'));
		blackPieces.add(new Pawn(6, 6, 'B'));
		blackPieces.add(new Pawn(6, 7, 'B'));
	}

	////////////////////////////////////////////////////////////////////////
	/// @brief Sets up white pieces on the board
	private void whiteSetup() {
		whitePieces.add(new Rook(0, 0, 'W'));
		whitePieces.add(new Knight(0, 1, 'W'));
		whitePieces.add(new Bishop(0, 2, 'W'));
		whitePieces.add(new King(0, 3, 'W'));
		whitePieces.add(new Queen(0, 4, 'W'));
		whitePieces.add(new Bishop(0, 5, 'W'));
		whitePieces.add(new Knight(0, 6, 'W'));
		whitePieces.add(new Rook(0, 7, 'W'));
		whitePieces.add(new Pawn(1, 0, 'W'));
		whitePieces.add(new Pawn(1, 1, 'W'));
		whitePieces.add(new Pawn(1, 2, 'W'));
		whitePieces.add(new Pawn(1, 3, 'W'));
		whitePieces.add(new Pawn(1, 4, 'W'));
		whitePieces.add(new Pawn(1, 5, 'W'));
		whitePieces.add(new Pawn(1, 6, 'W'));
		whitePieces.add(new Pawn(1, 7, 'W'));
	}
}