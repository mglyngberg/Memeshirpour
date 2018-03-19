import java.io.*;
import java.net.Socket;


/**
 * Class that holds all the classes required for a tic-tac-toe game.
 * Implements Runnable so that multiple threads can be run on a server.
 */
public class Game implements Constants, Runnable{

	/**
	 * The game's Board.
	 */
	private Board theBoard;
	/**
	 * The game's Referee class.
	 */
	private Referee theRef;
	
	/**
	 * BufferedReaders for getting input from the clients.
	 */
	private BufferedReader xIn, oIn;
	/**
	 * PrintWriter for sending strings to the clients.
	 */
	private PrintWriter xOut, oOut;
	/**
	 * ObjectOutputStreams for sending the game's board to the clients.
	 */
	private ObjectOutputStream xBoardOut, oBoardOut;
	
	/**
	 * Constructor that initializes all local attributes using the sockets of the X player and
	 * the O player.
	 * @param xSocket X player's socket.
	 * @param oSocket O player's socket.
	 */
    public Game(Socket xSocket, Socket oSocket) {
    	
        theBoard  = new Board();
        try {
	        xIn = new BufferedReader(new InputStreamReader(xSocket.getInputStream()));
	        xOut = new PrintWriter(xSocket.getOutputStream(), true);
	        xBoardOut = new ObjectOutputStream(xSocket.getOutputStream());
	        xOut.println("You are the X player.");
	        xOut.println("Starting game.");
	        
	        oIn = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
	        oOut = new PrintWriter(oSocket.getOutputStream(), true);
	        oBoardOut = new ObjectOutputStream(oSocket.getOutputStream());
	        oOut.println("You are the O player.");
			oOut.println("Starting game. Waiting for other player.");
	        
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
    
    /**
     * Sets the theRef, calls runTheServerGame(..) to start the game.
     * @param r the Referee to be assigned to theRef.
     * @throws IOException if fails to assign Referee.
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheServerGame(xIn, oIn, xOut, oOut, xBoardOut, oBoardOut);
    }
	
    /**
     * Executes a thread that runs a game of tic-tac-toe between two clients connected on a server.
     */
    @Override
    public void run() {
    	
    	try {
    		
    		Referee theRef;
    		Player xPlayer, oPlayer;
    		
    		//Sets the name of the 'X' player.
	    	xOut.println("Please enter your name.");
	    	
	    	String name= xIn.readLine();
	    	
			while (name == null) {
				System.out.print("Please try again: ");
				name = xIn.readLine();
			}
			
			xPlayer = new Player(name, LETTER_X);
			xPlayer.setBoard(theBoard);
			
			//Sets the name of the 'O' player.
			oOut.println("Please enter your name.");
	    	name= oIn.readLine();
			while (name == null) {
				System.out.print("Please try again: ");
				name = oIn.readLine();
			}
			
			oPlayer = new Player(name, LETTER_O);
			oPlayer.setBoard(theBoard);
			
			theRef = new Referee();
			theRef.setBoard(theBoard);
			theRef.setxPlayer(xPlayer);
			theRef.setoPlayer(oPlayer);
			
			//Starts the game.
			this.appointReferee(theRef);
			
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
