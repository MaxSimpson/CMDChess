////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the Bishop class
////////////////////////////////////////////////////////////////////////////////
class Bishop extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for Bishop
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public Bishop(int _x, int _y, char _color){
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a Bishop can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y){
		int xBaseDifference = x - _x;
		int yBaseDifference = y - _y;

		int xDifference = xBaseDifference;
		int yDifference = yBaseDifference;

		if(xDifference <= 0){
			xDifference *= -1;
		}
		if(yDifference <= 0){
			yDifference *= -1;
		}

		if(xDifference == yDifference){
			return true;
		}else{
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves Bishop to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y){
		 x = _x;
		 y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return Bishop type
	public int pieceType(){return 4;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns Bishop board icon
	public String draw(){
		if(color == 'W'){
			return "WB";
		}else{
			return "BB";
		}
	}

}