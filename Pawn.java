////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the pawn class
////////////////////////////////////////////////////////////////////////////////
class Pawn extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for pawn
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public Pawn(int _x, int _y, char _color) {
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a Pawn can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y) {
		if(color == 'W') {
			// Color is white
			// Move forward 1
			if((x+1 == _x) && (y == _y)) {
				// Move forward 1 step
				firstMove = false;
				return true;
			} else if ((firstMove == true) && (x + 2 == _x) && (y == _y)) {
				// Jump forward 2 steps
				firstMove = false;
				return true;
			} else if((x+1 == _x) && ((y+1 == _y)||(y-1 == _y))) {
				// Take piece diagonaly
				firstMove = false;
				return true;
			} else {
				return false;
			}
		} else {
			// Color is black
			if((x-1 == _x) && (y == _y)) {
				// Move forward 1 step
				firstMove = false;
				return true;
			} else if ((firstMove == true) && (x - 2 == _x) && (y == _y)) {
				// Jump forward 2 steps
				firstMove = false;
				return true;
			} else if((x-1 == _x) && ((y+1 == _y)||(y-1 == _y))) {
				// Take piece diagonaly
				firstMove = false;
				return true;
			} else {
				return false;
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves pawn to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return pawn type
	public int pieceType() {return 0;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns pawn board icon
	public String draw() {
    return color == 'W' ? "WP" : "BP";
	}
  
	private boolean firstMove = true; //< First pawn move or not
}