import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientView extends JFrame{
	
	ImageIcon momo = new ImageIcon("momo.png");
	ImageIcon trash = new ImageIcon("trash-garbage.jpg");
	
	//Named row_col
	private JButton b_0_0  = new JButton("");
	private JButton b_0_1  = new JButton("");
	private JButton b_0_2  = new JButton("");
	private JButton b_1_0  = new JButton("");
	private JButton b_1_1  = new JButton("");
	private JButton b_1_2  = new JButton("");
	private JButton b_2_0  = new JButton("");
	private JButton b_2_1  = new JButton("");
	private JButton b_2_2  = new JButton("");
	
	private JLabel markLabel = new JLabel("Player Mark:     ");
	private JLabel playerNameLabel = new JLabel("Player Name:    ");
	private JLabel messageWindowLabel = new JLabel("Message Window: ");
	
	private JTextArea messages  = new JTextArea(0, 22);
	private JScrollPane scrollPane = new JScrollPane(messages);
	
	
	public ClientView() {
		
		JPanel buttonPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel eastPanel = new JPanel();
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonPanel.setLayout(new GridLayout(3, 3));
		
		buttonPanel.add(b_0_0);
		buttonPanel.add(b_0_1);
		buttonPanel.add(b_0_2);
		buttonPanel.add(b_1_0);
		buttonPanel.add(b_1_1);
		buttonPanel.add(b_1_2);
		buttonPanel.add(b_2_0);
		buttonPanel.add(b_2_1);
		buttonPanel.add(b_2_2);
		disableButtons();
		
		buttonPanel.setSize(400, 400);
		
		eastPanel.setLayout(new BorderLayout());
		messages.setEditable(false);
		eastPanel.add(messageWindowLabel, BorderLayout.NORTH);
		eastPanel.add(scrollPane, BorderLayout.CENTER);
		
		southPanel.setLayout(new GridLayout(2,0));
		southPanel.add(markLabel);
		southPanel.add(playerNameLabel);
		
		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(southPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void addText(String s) {
		messages.append(s + "\n");
	}
	
	public void setMark(String s) {markLabel.setText(markLabel.getText() + s);}
	public void setName(String s) {playerNameLabel.setText(playerNameLabel.getText() + s);}
	
	public void add_b_0_0_Listener(ActionListener a) {b_0_0.addActionListener(a);}
	public void add_b_0_1_Listener(ActionListener a) {b_0_1.addActionListener(a);}
	public void add_b_0_2_Listener(ActionListener a) {b_0_2.addActionListener(a);}
	public void add_b_1_0_Listener(ActionListener a) {b_1_0.addActionListener(a);}
	public void add_b_1_1_Listener(ActionListener a) {b_1_1.addActionListener(a);}
	public void add_b_1_2_Listener(ActionListener a) {b_1_2.addActionListener(a);}
	public void add_b_2_0_Listener(ActionListener a) {b_2_0.addActionListener(a);}
	public void add_b_2_1_Listener(ActionListener a) {b_2_1.addActionListener(a);}
	public void add_b_2_2_Listener(ActionListener a) {b_2_2.addActionListener(a);}
	
	public void set_b_0_0_Text(String s) {
		if(s.equals("X")){
			b_0_0.setIcon(momo);
		}
		else if(s.equals("O")){
			b_0_0.setIcon(trash);
		}
		else {
			b_0_0.setText(s);
		}
	}
	public void set_b_0_1_Text(String s) {
		if(s.equals("X")){
			b_0_1.setIcon(momo);
		}
		else if(s.equals("O")){
			b_0_1.setIcon(trash);
		}
		else {
			b_0_1.setText(s);
		}
	}
	public void set_b_0_2_Text(String s) {
		if(s.equals("X")){
			b_0_2.setIcon(momo);
		}
		else if(s.equals("O")){
			b_0_2.setIcon(trash);
		}
		else {
			b_0_2.setText(s);
		}
	}
	public void set_b_1_0_Text(String s) {
		if(s.equals("X")){
			b_1_0.setIcon(momo);
		}
		else if(s.equals("O")){
			b_1_0.setIcon(trash);
		}
		else {
			b_1_0.setText(s);
		}
	}
	public void set_b_1_1_Text(String s) {
		if(s.equals("X")){
			b_1_1.setIcon(momo);
		}
		else if(s.equals("O")){
			b_1_1.setIcon(trash);
		}
		else {
			b_1_1.setText(s);
		}
	}
	public void set_b_1_2_Text(String s) {
		if(s.equals("X")){
			b_1_2.setIcon(momo);
		}
		else if(s.equals("O")){
			b_1_2.setIcon(trash);
		}
		else {
			b_1_2.setText(s);
		}
	}
	public void set_b_2_0_Text(String s) {
		if(s.equals("X")){
			b_2_0.setIcon(momo);
		}
		else if(s.equals("O")){
			b_2_0.setIcon(trash);
		}
		else {
			b_2_0.setText(s);
		}
	}
	public void set_b_2_1_Text(String s) {
		if(s.equals("X")){
			b_2_1.setIcon(momo);
		}
		else if(s.equals("O")){
			b_2_1.setIcon(trash);
		}
		else {
			b_2_1.setText(s);
		}
	}
	public void set_b_2_2_Text(String s) {
		if(s.equals("X")){
			b_2_2.setIcon(momo);
		}
		else if(s.equals("O")){
			b_2_2.setIcon(trash);
		}
		else {
			b_2_2.setText(s);
		}
	}
	
	public void enableButtons() {
		b_0_0.setEnabled(true);
		b_0_1.setEnabled(true);
		b_0_2.setEnabled(true);
		b_1_0.setEnabled(true);
		b_1_1.setEnabled(true);
		b_1_2.setEnabled(true);
		b_2_0.setEnabled(true);
		b_2_1.setEnabled(true);
		b_2_2.setEnabled(true);
	}
	
	public void disableButtons() {
		b_0_0.setEnabled(false);
		b_0_1.setEnabled(false);
		b_0_2.setEnabled(false);
		b_1_0.setEnabled(false);
		b_1_1.setEnabled(false);
		b_1_2.setEnabled(false);
		b_2_0.setEnabled(false);
		b_2_1.setEnabled(false);
		b_2_2.setEnabled(false);
	}
	
}
