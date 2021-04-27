////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the King class
////////////////////////////////////////////////////////////////////////////////
class King extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for king
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public King(int _x, int _y, char _color) {
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a King can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y) {
		int xbaseDifference = x - _x;
		int ybaseDifference = y - _y;
		
		int xDifference = xbaseDifference;
		int yDifference = ybaseDifference;
		if(xDifference <= 0)
			xDifference *= -1;
		if(yDifference <= 0)
			yDifference *= -1;

		// Out of range
		if(xDifference > 1 || yDifference > 1)
			return false;

		int totalDifference = xDifference + yDifference;
    return (totalDifference == 2) || (xDifference == 1) || (yDifference == 1);
	}
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves King to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return King type
	public int pieceType() {return 2;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns King board icon
	public String draw() {
    return color == 'W' ? "WK" : "BK";
	}
}