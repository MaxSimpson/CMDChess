////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the Knight class
////////////////////////////////////////////////////////////////////////////////
class Knight extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for Knight
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public Knight(int _x, int _y, char _color){
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a Knight can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y){
		int xDifference = x - _x;
		int yDifference = y - _y;

		if(xDifference < 0){
			xDifference *= -1;
		}
		if(yDifference < 0){
			yDifference *= -1;
		}

		if((xDifference == 2 && yDifference == 1) || (xDifference == 1 && yDifference == 2)){
			return true;
		}else{
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves Knight to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y){
		 x = _x;
		 y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return Knight type
	public int pieceType(){return 5;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns Knight board icon
	public String draw(){
		if(color == 'W'){
			return "WN";
		}else{
			return "BN";
		}
	}

}