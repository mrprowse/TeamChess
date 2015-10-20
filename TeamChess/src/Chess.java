public class Chess {

	Board board = new Board();
	
	// Play chess
	public void play() {
		test();
	}
	
	public void test() {
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
	}

}
