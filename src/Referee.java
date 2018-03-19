import java.io.BufferedReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Class that is used to initialize and start a game of tic-tac-toe.
 * @author 2016
 *
 */
public class Referee {
	
	/**
	 * The player which has the 'X' mark.
	 */
	private Player xPlayer;
	/**
	 * The player which has the 'O' mark.
	 */
	private Player oPlayer;
	/**
	 * The tic-tac-toe game's board.
	 */
	private Board board;
	
	/**
	 * Version of runTheGame() for a game running on a server with two clients.
	 * @param xIn The input stream of the 'X' player.
	 * @param oIn The input stream of the 'O' player.
	 * @param xOut The output stream of the 'X' player.
	 * @param oOut The output stream of the 'O' player.
	 * @param xBoardOut Stream for outputting the board to the 'X' player.
	 * @param oBoardOut Stream for outputting the board to the 'O' player.
	 */
	public void runTheServerGame(BufferedReader xIn, BufferedReader oIn, PrintWriter xOut, PrintWriter oOut,
			ObjectOutputStream xBoardOut, ObjectOutputStream oBoardOut) {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		xPlayer.playServer(xIn, oIn, xOut, oOut, xBoardOut, oBoardOut);
	}
	
	/**
	 * Sets the xPlayer.
	 * @param xPlayer the 'X' player.
	 */
	public void setxPlayer(Player xPlayer) {this.xPlayer = xPlayer;}
	/**
	 * Sets the oPlayer.
	 * @param oPlayer the 'O' player.
	 */
	public void setoPlayer(Player oPlayer) {this.oPlayer = oPlayer;}
	/**
	 * Sets the board.
	 * @param board the game board.
	 */	
	public void setBoard(Board board) {this.board = board;}
}
