import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class that connects to a Server and is used to play tic-tac-toe with
 * two clients per game.
 * @author Magnus Lyngberg
 *
 */
public class Client {
	
	/**	
	 * BufferedReader for reading text from the server.
	 */
	private BufferedReader in;
	/**
	 * PrintWriter for writing text to the server.
	 */
	private PrintWriter out;
	/**
	 * ObjectInputStream for receiving a Board object from the server.
	 */
	private ObjectInputStream boardIn;
	/**
	 * Socket to connect to server.
	 */
	private Socket socket;
	/**
	 * Board that holds and displays the game's information.
	 */
	private Board board;
	
	private ClientController controller;
	
	/**
	 * Constructor that initializes all attributes and connects to a server of the
	 * specified name and port number.
	 * @param serverName the name of the server that will be connected to.
	 * @param portNum the port number that will be used to connect to a server.
	 */
	public Client(String serverName, int portNum) {
		
		try {
			socket = new Socket(serverName, portNum);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			boardIn = new ObjectInputStream(socket.getInputStream());
			board = new Board();
			controller = new ClientController(new ClientView(), new ClientModel(out));
			controller.addText("Connected to tic-tac-toe server.");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Method that is called when first connecting to a server. The server will
	 * sent three messages to the client which will be displayed on the console.
	 * The final message prompts the user to enter their name, which is scanned and
	 * sent to the server.
	 * @throws IOException
	 */
	private void setName() throws IOException{
		
		String line = "";
		
		//The server sends 2 messages before prompting the user to enter
		//their name.
		line = in.readLine();
		
		if(line.equals("You are the X player.")) {
			controller.setMark("X");
		}
		else {
			controller.setMark("O");
		}
		
		controller.addText(line);
		
		line = in.readLine();
		controller.addText(line);
		
		//Message asking for player's name.
		line = in.readLine();
		controller.addText(line);
		
		//Reads user response and sends it to the server.
		line = controller.setName();
		out.println(line);
		
		controller.addText("Waiting for other player.");
	}
	
	/**
	 * Method used when playing a game of tic-tac-toe, one call to the method is one game 
	 * turn. The board is displayed once at the beginning of the turn and once at the end.
	 * The server will send two messages which will be displayed on the console prompting 
	 * the user to enter the row and column respectively.
	 * @throws IOException
	 */
	private void playGame() throws IOException{
		
		String line = "";
		
		//Displays board recieved from the server.
		try {
			board = (Board)boardIn.readObject();
			controller.updateView(board);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Displays the player's turn.
		line = in.readLine();
		controller.addText(line);
		
		//Terminates the client if the game is over.
		if(line.contains("THE GAME IS OVER: ")){
			return;
		}
		controller.enableButtons();
		
		//Displays the updated board received from the server after the turn is
		//completed.
		try {
			board = (Board)boardIn.readObject();
			controller.updateView(board);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		controller.disableButtons();
		controller.addText("Waiting for other player.");
	}
	
	/**
	 * Method that is called to play a tic-tac-toe game on the connected server.
	 */
	public void play() {
		
		try {
			setName();
			while(true) {
				playGame();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Client client = new Client("localhost", 6969);
		client.play();
	}
}
