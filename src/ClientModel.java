import java.io.PrintWriter;

public class ClientModel {

	private PrintWriter out;
	
	public ClientModel(PrintWriter out) {this.out = out;}
	
	public void makeMove(String row, String col) {
		
		out.println(row);
		out.println(col);
	}
}
