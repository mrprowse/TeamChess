public class Board {

	// The grid of board squares the game plays on
	public Piece[][] squares = new Piece[8][8]; // array to hold board of Square objects
	int boardSize = 8;
	
	/**
	 * Constructor will create all the 
	 * Squares on the Board and setup the
	 * pieces in the correct positions
	 */
	public Board() {
		makeBoard();
	}
	
	/**
	 * Create the 8x8 board of Squares
	 */
	public void makeBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (row > 1 && row < 6) {
					squares[row][col] = null;
				} else {
					squares[row][col] = new Piece(this, row, col);
				}
			}
		}
	}

	/**
	 * Create the Pieces for each side and
	 * add them to the correct Squares
	 */
	public void loadBoard() {
		// create the player pieces
		
	}

	/**
	 * Return a text representation of 
	 * the current state of the chess board.
	 * See the Piece class for more information
	 * @return A text-grid board representation  
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < boardSize; i++) {
			result += "+--+--+--+--+--+--+--+--+\n|";
			for (int j = 0; j < boardSize; j++) {
				if (squares[i][j] != null) {
					result += squares[i][j].getShortName();
				} else {
					result += "  ";
				}
				result += "|";
			}
			result += "\n";
		}
		result += "+--+--+--+--+--+--+--+--+\n";
		return result;
		
	}
	
}
