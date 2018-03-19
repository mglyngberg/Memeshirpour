import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that can support multiple clients connecting and running multiple threads of a game of
 * tic-tac-toe. Each game thread requires two clients to start.
 * @author Magnus Lyngberg
 *
 */
public class Server {
	
	/**
	 * ServerSocket used to connect to clients.
	 */
	private ServerSocket serverSocket;
	private Socket xSocket, oSocket;
	private ExecutorService pool;
	
	/**
	 * Constructor that initializes the server with the giver port number.
	 * @param portNum The port number that the server will connect to.
	 */
	public Server(int portNum) {
		
		try {
			serverSocket = new ServerSocket(portNum);
			pool = Executors.newCachedThreadPool();
			
			System.out.println("Server is running.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that runs the server and starts a new game for each two clients that connect.
	 */
	public void runServer() {
		try {
			while(true) {
				
				xSocket = serverSocket.accept();
				System.out.println("X player connected.");
				oSocket = serverSocket.accept();
				System.out.println("O player connected.");
				Game game = new Game(xSocket, oSocket);
				pool.execute(game);
				
			}
		} catch (IOException e0) {
			e0.printStackTrace();
			
			pool.shutdown();
			
			try {
				xSocket.close();
				oSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}
	
	public static void main(String[] args) {
		Server ticTacToeServer = new Server(6969);
		ticTacToeServer.runServer();
	}
}
