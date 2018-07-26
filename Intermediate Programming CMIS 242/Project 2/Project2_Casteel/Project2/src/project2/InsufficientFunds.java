package project2;

import javax.swing.JOptionPane;

public class InsufficientFunds extends Exception {
	
	//Insufficient funds is called when the balance is less than zero.
	public InsufficientFunds() {
		JOptionPane pane = new JOptionPane();
		JOptionPane.showMessageDialog(pane, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
