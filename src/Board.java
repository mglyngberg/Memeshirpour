import java.io.Serializable;

/**
 * Class representing a tic-tac-toe board, prints the board to the console
 * and keeps track of the positions of all marks.
 */
public class Board implements Constants, Serializable {
	
	private static final long serialVersionUID = 12;
	
	/** 
	 * Holds the position of the current marks.
	 */
	private char theBoard[][];
	/**
	 * Tracks the current number of marks.
	 */
	private int markCount;

	/**
	 * Constructor initializes the board with no marks, theBoard is filled
	 * with spaces.
	 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Returns the mark at a given position.
	 * @param row the row of the mark.
	 * @param col the column of the mark.
	 * @return the char value of the mark at the given position.
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Returns true if all spaces on the board are full.
	 * @return true if all spaces are full.
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Returns if the X win condition has been met.
	 * @return true if LETTER_X has met the win conditions.
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Returns if the O win condition has been met
	 * @return true if LETTER_O has met the win conditions.
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Displays the game board on the console with current mark positions.
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Adds a mark at the given position.
	 * @param row the row of the added mark.
	 * @param col the column of the added mark.
	 * @param mark the char value of the added mark.
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears the board, filling all spaces with SPACE_CHAR.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks if the given mark has met the game win conditions ie. 3 marks
	 * in a row, column or diagonally.
	 * @param mark the mark to be checked.
	 * @return true if the mark has met the win conditions.
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Prints the headers for the boards columns.
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

	/**
	 * Prints hyphens to the game board.
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

	/**
	 * Prints the empty spaces for the game board.
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
	
	
	public void updateView(ClientView view){
		
		view.set_b_0_0_Text(String.valueOf(theBoard[0][0]));
		view.set_b_0_1_Text(String.valueOf(theBoard[0][1]));
		view.set_b_0_2_Text(String.valueOf(theBoard[0][2]));
		view.set_b_1_0_Text(String.valueOf(theBoard[1][0]));
		view.set_b_1_1_Text(String.valueOf(theBoard[1][1]));
		view.set_b_1_2_Text(String.valueOf(theBoard[1][2]));
		view.set_b_2_0_Text(String.valueOf(theBoard[2][0]));
		view.set_b_2_1_Text(String.valueOf(theBoard[2][1]));
		view.set_b_2_2_Text(String.valueOf(theBoard[2][2]));
	}
	
}
