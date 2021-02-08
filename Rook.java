////////////////////////////////////////////////////////////////////////////////
/// @brief Class for the Rook class
////////////////////////////////////////////////////////////////////////////////
class Rook extends Piece {
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Constructor for rook
	/// @param _x X position
	/// @param _y Y position
	/// @param _color Color of piece
	public Rook(int _x, int _y, char _color){
		super.x = _x;
		super.y = _y;
		super.color = _color;
	}
	
	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns if a Rook can move to a point
	/// @param _x Possible new x
	/// @param _y Possible new y
	public boolean canMove(int _x, int _y){
		if((x == _x && y != _y) || (x != _x && y == _y)){
			return true;
		}else{
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Moves Rook to a point
	/// @param _x New x
	/// @param _y New y
	public void move(int _x, int _y){
		x = _x;
		y = _y;
	}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Return Rook type
	public int pieceType(){return 1;}

	////////////////////////////////////////////////////////////////////////////
	/// @brief Returns Rook board icon
	public String draw(){
		if(color == 'W'){
			return "WR";
		}else{
			return "BR";
		}
	}

}