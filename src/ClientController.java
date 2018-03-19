import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ClientController {
	
	private ClientView view;
	private ClientModel model;
	
	
	public ClientController(ClientView v, ClientModel m) {
		
		view = v;
		model = m;
		
		addListeners(view);
	}
	
	public void updateView(Board board) {
		board.updateView(view);
	}
	
	public void addText(String s) {
		view.addText(s);
	}
	
	public String setName() {
		
		String name = JOptionPane.showInputDialog(view, "Please enter player name: ");
		view.setName(name);
		return name;
	}
	
	public void setMark(String s) {
		view.setMark(s);
	}
	
	public void enableButtons() {
		view.enableButtons();
	}
	
	public void disableButtons() {
		view.disableButtons();
	}
	
	private void addListeners(ClientView view) {
		
		view.add_b_0_0_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("0", "0");
			}
		});
		
		view.add_b_0_1_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("0", "1");
			}
		});
		view.add_b_0_2_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("0", "2");
			}
		});
		view.add_b_1_0_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("1", "0");
			}
		});
		view.add_b_1_1_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("1", "1");
			}
		});
		view.add_b_1_2_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("1", "2");
			}
		});
		view.add_b_2_0_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("2", "0");
			}
		});
		view.add_b_2_1_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("2", "1");
			}
		});
		view.add_b_2_2_Listener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.makeMove("2", "2");
			}
		});
		
	}
	
	
	
}
