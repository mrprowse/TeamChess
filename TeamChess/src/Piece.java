public class Piece {

	private String colour; // white or black

	private int row, col; // the x and y coordinate of the piece

	private String name; // name of the piece (Pawn, Queen, Bishop, etc.)
	
	private Board board; // a handle back to the chess board

	/**
	 * @param board The board this piece belongs to
	 */
	
	/**
	 * Constructor sets a handle to the board in
	 * every piece so that it can move to another
	 * position. Creates piece types and colours 
	 * based on the row and col starting positions
	 * NOTE: How could this constructor handle pawns
	 * that make it to the other end of the board?
	 * @param board The Board object this piece is on
	 * @param row The row where the piece is created
	 * @param col The col where the piece is created
	 */
	public Piece(Board board, int row, int col) {
		this.board = board;
		this.row = row; 
		this.col = col;
		this.colour = (row < 2) ? "black" : "white";
		if (this.row == 1 || this.row == 6) {
			name = "Pawn";
		} else if (this.col == 0 || this.col == 7){
			name = "Castle";
		} else if (this.col == 1 || this.col == 6){
			name = "Knight";
		} else if (this.col == 2 || this.col == 5){
			name = "Bishop";
		} else if (this.col == 4) {
			name = (this.colour.equalsIgnoreCase("white")) 
					? "Queen" : "King";
		} else {
			name = (this.colour.equalsIgnoreCase("white")) 
					? "King" : "Queen";			
		}
	}
	
	/**
	 * Attempt to move this piece to the board
	 * location at (row,col) and return result.
	 * This method must use the piece name and
	 * colour to correctly determine if it can
	 * move to the requested location.
	 * @param row Board column of the piece
	 * @param col Board row of the piece
	 * @return True if piece can move to (row,col)
	 * @Precondition colour, row, and col properties set
	 * @Postcondition Piece's (row,col) is changed if 
	 * move is allowed, or not if not
	 */
	public boolean move(int row, int col) {
		// Save the current state in case 
		// the move puts the player into check
		int oldRow = this.row, oldCol = this.col;
		Piece oldPiece = this.board.squares[row][col];
		// If it's a valid square to move to
		if (this.board.squares[row][col] == null
				|| !this.board.squares[row][col].colour.equalsIgnoreCase(this.colour)) {
			// If this kind of piece can move there
			if (isValidMove(row, col)) { // @TODO: add logic based on piece name
				// Remove the piece from the old location 
				this.board.squares[this.row][this.col] = null;
				// Move the piece to the new location
				this.board.squares[row][col] = this;
				// Update the piece's internal row and col
				this.row = row;
				this.col = col;
				// Undo the move if necessary
				if (this.board.isInCheck(this.colour)) {
					System.out.println("That move puts you in check.");
					// undo move
					undoMove(oldPiece, oldRow, oldCol);
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Undo the last move the player did
	 * @param takenPiece The piece that was at the location 
	 * the player just moved to; could be null
	 * @param oldRow The row the player moved from
	 * @param oldCol The col the player moved from
	 */
	private void undoMove(Piece takenPiece,	int oldRow, int oldCol) {
		System.out.println("Undoing move...");
		this.board.squares[this.row][this.col] = takenPiece;
		this.board.squares[oldRow][oldCol] = this;
		this.row = oldRow;
		this.col = oldCol;
	}
	
	private boolean isValidMove(int row, int col) {
		if (name.equalsIgnoreCase("CASTLE")) {
			return isCastleMove(row, col);
		} else if (name.equalsIgnoreCase("BISHOP")) {
			return isBishopMove(row, col);
		} else if (name.equalsIgnoreCase("KNIGHT")) {
			return isKnightMove(row, col);
		} else if (name.equalsIgnoreCase("QUEEN")) {
			return isQueenMove(row, col);
		} else if (name.equalsIgnoreCase("PAWN")) {
			return isPawnMove(row, col);
		}
		return false;
	}
	
	private boolean isPawnMove(int row, int col) {
		int rowOff = (colour.equalsIgnoreCase("WHITE")) ? -1 : 1;
		// Check for two row move off the bat
		if (((rowOff == -1 && this.row == 6) 
			|| (rowOff == 1 && this.row == 1)) 
			&& this.col == col && this.row + (2 * rowOff) == row
				&& this.board.squares[row][col] == null) {
			return true;
		// Check for one row move
		} else if (this.row + rowOff == row) { 
			// Check for straight move
			if (this.col == col 
					&& this.board.squares[row][col] == null) {
				return true;
			// Check for attack
			} else if (Math.abs(this.col - col) == 1
					&& this.board.squares[row][col] != null
					&& !this.board.squares[row][col].colour.equalsIgnoreCase(this.colour)) {
				return true;
			}
		}
		return false;
	}

	private boolean isQueenMove(int row, int col) {
		return isCastleMove(row, col) || isBishopMove(row, col);
	}

	private boolean isKnightMove(int row, int col) {
		return Math.abs(row - this.row) < 3
				&& Math.abs(row - this.row) > 0
				&& Math.abs(col - this.col) < 3
				&& Math.abs(col - this.col) > 0;
	}

	private boolean isCastleMove(int row, int col) {
		// @TODO: check if the row/col is clear
		return row == this.row || col == this.col;		
	}
	
	private boolean isBishopMove(int row, int col) {
		// @TODO: check if the diagonal is clear
		return Math.abs(row - this.row) == Math.abs(col - this.col);
	}
	
	/**
	 * Get the shortened name of this piece for
	 * printing on the chess board, accoring to
	 * this chart:
	 * King = K			Queen = Q
	 * Bishop = B		Knight = H
	 * Castle = C		Pawn = P
	 * Black = b		White = w
	 * For example, a white castle is Cw, a black 
	 * queen is Qb and a white bishop is Bw.
	 * @return A two-letter shortened name
	 */
	public String getShortName() {
		return (name.equalsIgnoreCase("Knight") ? "H" : name.substring(0, 1))
				+ colour.substring(0, 1);
	}
	
	public String getColour() {
		return this.colour;
	}
	// 
	public String toString() {
		return colour.toUpperCase() + " "
				+ name.toUpperCase();
	}

}
