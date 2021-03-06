////////////////////////////////////////////////////////////////////////////////
/// @brief Abstract class for defining what a piece is
////////////////////////////////////////////////////////////////////////////////
abstract class Piece {
	// Public members

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns drawing char
	public abstract String draw();

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a point can be moved to
	/// @param _x Possible new x
	/// @param _y Possible new y
	public abstract boolean canMove(int _x, int _y);

	////////////////////////////////////////////////////////////////////////////
	/// @brief Move to a new point
	/// @param _x New x
	/// @param _y New y
	public abstract void move(int _x, int _y);

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns the type of piece something is
	public abstract int pieceType();

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns x of piece
	public int getX() {return x;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns y of piece
	public int getY() {return y;}

	// Private member variables
	int x; //< X coordinate on the board
	int y; //< Y coordinate on the board
	char color; //< Color of piece
	char type; //< Type of piece
}
