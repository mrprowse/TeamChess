import java.util.Scanner;

public class Chess {

	private Scanner input = new Scanner(System.in);

	Board board = new Board();

	// Play chess
	public void play() {
		// test();
		// Set player to black
		String playerColour = "black";
		do {
			int startRow, startCol, endRow, endCol;
			// Get starting piece; loop until a piece is selected
			do {
				System.out.print(playerColour.toUpperCase() + " >>");
				// Get starting square
				System.out
						.println("Enter the row and column of the piece you want to move: ");
				startRow = Integer.parseInt(input.next().trim());
				startCol = Integer.parseInt(input.nextLine().trim());
			} while (board.squares[startRow][startCol] == null
					|| !board.squares[startRow][startCol].getColour()
							.equalsIgnoreCase(playerColour));

			do {
			// Get ending square
			System.out.println("Enter the destination row and column for the piece: ");
			endRow = Integer.parseInt(input.next().trim());
			endCol = Integer.parseInt(input.nextLine().trim());

			// Move if possible, loop if not
			} while (!board.squares[startRow][startCol].move(endRow, endCol));
			// If not checkmate, switch player
			
			System.out.println(board);
			
			playerColour = (playerColour.equalsIgnoreCase("black") ? "white" : "black");
		} while (true);
	}

	public void test() {
		try {
			System.out.println(board);
			board.squares[0][0].move(7, 0);
			System.out.println(board);
			board.squares[0][1].move(2, 0);
			System.out.println(board);
			board.squares[0][2].move(2, 4);
			System.out.println(board);
			board.squares[0][3].move(2, 5);
			System.out.println(board);
			board.squares[2][5].move(2, 6);
			System.out.println(board);
			board.squares[7][0].move(7, 1);
			System.out.println(board);
			board.squares[1][1].move(2, 1);
			System.out.println(board);
			board.squares[2][1].move(3, 1);
			System.out.println(board);
			board.squares[3][1].move(4, 1);
			System.out.println(board);
			board.squares[4][1].move(5, 1);
			System.out.println(board);
			board.squares[5][1].move(6, 2);
			System.out.println(board);
			board.squares[1][4].move(3, 4);
			System.out.println(board);
			board.squares[6][4].move(4, 4);
			System.out.println(board);
			board.squares[3][4].move(5, 4);
			System.out.println(board);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
