import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Class that represents a player for a game of tic-tac-toe.
 */
public class Player {
	
	/**
	 * The name of this player.
	 */
	private String name;
	/**
	 * The Board that this player is a part of.
	 */
	private Board board;
	/**
	 * The player which this player is playing tic-tac-toe against.
	 */
	private Player opponent;
	/**
	 * The given mark of this player.
	 */
	private char mark;
	
	/**
	 * Operates in the same way as play(), but communicates through a server so that two 
	 * different clients can play the same game. Sends the board through an output stream at
	 * the beginning and end of a player's turn.
	 * @param playerIn The input stream of the current player.
	 * @param opponentIn The input stream of the opponent.
	 * @param playerOut The output stream of the player.
	 * @param opponentOut The output stream of the opponent.
	 * @param playerBoardOut Stream for outputting the board of the player.
	 * @param opponentBoardOut Stream for outputting the opponent's board.
	 */
	public void playServer(BufferedReader playerIn, BufferedReader opponentIn, PrintWriter playerOut,
			PrintWriter opponentOut, ObjectOutputStream playerBoardOut, ObjectOutputStream opponentBoardOut) {
		
		String message = "";
		
		//Sends the board to the client.
		sendBoard(playerBoardOut);
		
		//Checks win conditions.
		if(board.xWins()) {
			if(mark == 'X')
				message = "THE GAME IS OVER: " + name + " is the winner!";
			else
				message = "THE GAME IS OVER: " + opponent.name + " is the winner!";
			
			gameOver(message, playerOut, opponentOut, opponentBoardOut);
			return;
		} 
		else if(board.oWins()) {
			if(mark == 'O')
				message = "THE GAME IS OVER: " + name + " is the winner!";
			else
				message = "THE GAME IS OVER: " + opponent.name + " is the winner!";
			
			gameOver(message, playerOut, opponentOut, opponentBoardOut);
			return;
		}
		else if(board.isFull()) {
			message = "THE GAME IS OVER: Tie!";
			
			gameOver(message, playerOut, opponentOut, opponentBoardOut);
			return;
		}
		
		playerOut.println(name + " it is your turn to make a move.");
		makeMoveServer(playerIn, playerOut);
		
		//Sends the board to the client after the player has made their move.
		sendBoard(playerBoardOut);
		
		board.checkWinner(mark);
		opponent.playServer(opponentIn, playerIn, opponentOut, playerOut, opponentBoardOut, playerBoardOut);
	}
	
	/**
	 * Sends the player's board through the specified ObjectOutputStream.
	 * @param boardOut The output stream that board will be sent through.
	 */
	private void sendBoard(ObjectOutputStream boardOut) {
		try {
			boardOut.reset();
			boardOut.writeObject(board);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * If the game is over sends the final state of the board to the opponent, prints 
	 * the game over message and ends the game.
	 * @param message The game over message.
	 * @param playerOut Stream for outputting the message to the player.
	 * @param opponentOut Stream for outputting the message to the player.
	 * @param opponentBoardOut Stream for outputting the final state of the board to the opponent.
	 */
	private void gameOver(String message, PrintWriter playerOut, PrintWriter opponentOut, 
			ObjectOutputStream opponentBoardOut) {
		
		sendBoard(opponentBoardOut);
		playerOut.println(message);
		opponentOut.println(message);
	}
	
	/**
	 * Version of makeMove() used for a game running on a server with two clients.
	 * @param in The input stream of the player.
	 * @param out The output stream of the player.
	 */
	public void makeMoveServer(BufferedReader in, PrintWriter out){
		
		int row;
		int col;
		
		try {
//			out.println(name + ", what row should your next " + mark + " be placed in? ");
			row = Integer.parseInt(in.readLine());
//			out.println(name + ", what column should your next " + mark + " be placed in? ");
			col = Integer.parseInt(in.readLine());
			
			board.addMark(row, col, mark);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Assigns the opponent of this player.
	 * @param opponent the player which will be assigned as opponent.
	 */
	public void setOpponent(Player opponent) {this.opponent = opponent;}	
	/**
	 * Assigns the board which this player will play on.
	 * @param board the board which will be assigned to this player's board.
	 */
	public void setBoard(Board board) {this.board = board;}
	
	/**
	 * Constructor that sets the name of this player.
	 * @param name the name of the player.
	 * @param letter the assigned mark of this player.
	 */
	public Player(String name, char letter) {
		this.name = name;
		mark = letter;
	}
}
