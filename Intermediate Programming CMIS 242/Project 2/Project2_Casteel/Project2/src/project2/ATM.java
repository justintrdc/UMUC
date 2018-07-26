/**
 * Name: Justin Casteel
 * Class: CMIS 242 6382 Intermediate Programming Project 2 
 * Platform: Windows 10
 * Compiler: Eclipse Mars.2 4.5.2
 */

package project2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ATM extends JFrame {
	
	static final int WINDOWWIDTH = 350, WINDOWHEIGHT = 200, TEXTWIDTH = 250, TEXTHEIGHT = 25;
	
	//Creates buttons, radio buttons, a text field, groups the radio's together and the pane.
	private JButton withdrawButton = new JButton("Withdraw");
    private JButton depositButton = new JButton("Deposit");
    private JButton transferToButton = new JButton("Transfer To");
    private JButton balanceButton = new JButton("Balance");
    private JRadioButton checkingRadio = new JRadioButton("Checking");
    private JRadioButton savingsRadio = new JRadioButton("Savings");
    private ButtonGroup radios = new ButtonGroup();
    private JTextField entry = new JTextField("");   
    private JOptionPane frame = new JOptionPane();
    
    //Numbers shown to the user will be formatted for dollar amounts.
    private static DecimalFormat df = new DecimalFormat("$0.00");
    
    //Creates two objects, one for a savings account and one for a checking account.
    private static Account savings = new Account().new Checking();
    private static Account checking = new Account().new Savings();
    
    //This method will create one checking and one savings account with starting balances.
    public static void createAccounts(double checkingStartBalance, double savingsStartBalance) {
    	checking.setBalance(checkingStartBalance);
    	savings.setBalance(savingsStartBalance);
    }
            
    //Shows user an error if the user has used blanks, letters or negative numbers as input.
    public void invalidNumber() {
    	JOptionPane.showMessageDialog(frame, "You have entered an invalid amount." + "\nPlease use $20 increments when withdrawing or depositing funds.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    //Withdraw button action listener
    class WithdrawButtonListener implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e) {
    		try {
    			//The first check is to ensure the value in the text field isn't a negative number. Next a check must be made to ensure the amount is in increments of $20.
    			if (getInputValue() > 0 && getInputValue() % 20 == 0) {
    				//Checks which radio button is selected by the user.
    				if (checkingRadio.isSelected()) {
    					checking.withdraw(getInputValue());
    					checking.serviceFee();
    					JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " withdrawn from Checking.");
    				} else if (savingsRadio.isSelected()) {
    					savings.withdraw(getInputValue());
    					savings.serviceFee();
    					JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " withdrawn from Savings.");
    				}
    				clearInputValue();
    			} else invalidNumber();
    			clearInputValue();
    		} catch (InsufficientFunds insufficientFunds) {
    			}
    		}
    	}
    
    
  //Deposit button action listener, the program will check for an amount entered greater than 0 and deposit into the currently selected account.
    class DepositButtonListener implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e) {
    			//Checks to see that the number entered by the user is positive.
    			if (getInputValue() > 0) {
    				//Checks which radio button is selected by the user.
    				if (checkingRadio.isSelected()) {
    					checking.deposit(getInputValue());
    					JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " deposited into Checking.");
    				} else if (savingsRadio.isSelected()) {
    					savings.deposit(getInputValue());
    			    	JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " deposited into Savings.");
    				}
    				clearInputValue();
    			} else invalidNumber();
    			clearInputValue();
    		}
    	}
    
  //Transfer button action listener, the program will check for an amount entered greater than 0 and transfer that amount from one account to the other, whichever is selected by the user.
    class TransferToButtonListener implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e) {
    		try {
			//Checks to see that the number entered by the user is positive.
			if (getInputValue() > 0) {
				//Checks which radio button is selected by the user.
				if (checkingRadio.isSelected()) {
					savings.transferFrom(getInputValue());
					checking.transferTo(getInputValue());
					JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " has been transferred from savings to checking account.");
				} else if (savingsRadio.isSelected()) {
					savings.transferTo(getInputValue());
					checking.transferFrom(getInputValue());
					JOptionPane.showMessageDialog(frame, df.format(getInputValue()) + " has been transferred from checking to savings account.");
				}
				clearInputValue();
			} else invalidNumber();
			clearInputValue();
    	} catch (InsufficientFunds insufficientFunds) {
    		
    	}
	}
}
    
    //Checks the current balance of the account currently selected by the user.
    class BalanceButtonListener implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e) {
    		//Checks which radio button is selected by the user.
    		if (checkingRadio.isSelected()) {
    			JOptionPane.showMessageDialog(frame, "Your checking account balance is: " + df.format(checking.getBalance()));
    		} else if (savingsRadio.isSelected()) {
    			JOptionPane.showMessageDialog(frame, "Your savings account balance is: " + df.format(savings.getBalance()));
    		} else invalidNumber();
    		clearInputValue();
    		}
    	}
    
    //Creates the ATM machine with buttons, text entry, and radio buttons with appropriate layout for the user to see and interact with.
    public ATM(double checkingStartBalance, double savingsStartBalance) {
    
    	super("ATM Machine");
        setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        setupFrame(WINDOWWIDTH, WINDOWHEIGHT);
        JPanel buttonPanel = new JPanel();
        JPanel textEntry = new JPanel();
        setResizable(false);
        layout.gridy = 2;
        add(buttonPanel);
        add(textEntry, layout);
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(transferToButton);
        buttonPanel.add(balanceButton);
        buttonPanel.add(checkingRadio);
        buttonPanel.add(savingsRadio);
        radios.add(checkingRadio);
        radios.add(savingsRadio);
        checkingRadio.setSelected(true);
        entry.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
        textEntry.setLayout(new GridLayout(1, 1));
        textEntry.add(entry);
                
    // Creates the checking and savings accounts
    createAccounts(checkingStartBalance, savingsStartBalance);

    // Action listeners
    withdrawButton.addActionListener(new WithdrawButtonListener());
    depositButton.addActionListener(new DepositButtonListener());
    transferToButton.addActionListener(new TransferToButtonListener());
    balanceButton.addActionListener(new BalanceButtonListener());
}
    
    
    /**
     * Methods
     */
          
    //Returns the text in the text entry field as a double.
    public double getInputValue() {
        try {
            return Double.parseDouble(entry.getText());
        } catch (NumberFormatException e) {
            clearInputValue();
            return 0;
        }
    }
        
    //Clears the text entry field for the user.
    public void clearInputValue() {
    	entry.setText("");
    }
    
    //Sets the size, location, and closing of the frame.
    private void setupFrame(int width, int height) {
    	setSize(width,height);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Displays the frame to the user when the new ATM object is created at runtime.
    public void display() {
    	setVisible(true);
    }
    
    public static void main(String[] args) {
        // Creates an ATM with starting values of 200$ in both checking and savings accounts.
        ATM frame = new ATM(200, 200);
        frame.display();
    }
}
