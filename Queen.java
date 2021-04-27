////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the Queen class
////////////////////////////////////////////////////////////////////////////////
class Queen extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for Queen
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public Queen(int _x, int _y, char _color) {
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a Queen can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y) {
		int xBaseDifference = x - _x;
		int yBaseDifference = y - _y;

		int xDifference = xBaseDifference;
		int yDifference = yBaseDifference;

		if(xDifference <= 0)
			xDifference *= -1;
		if(yDifference <= 0)
			yDifference *= -1;

		// Horizontal, vertical, or diagonal change
    return x == _x || y == _y || xDifference == yDifference;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves Queen to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y) {
		x = _x;
		y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return Queen type
	public int pieceType() {return 3;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns Queen board icon
	public String draw() {
    return color == 'W' ? "WQ" : "BQ";
	}
}