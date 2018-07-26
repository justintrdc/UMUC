/**
 * Justin Casteel
 * June 26, 2016
 * Project 3
 * INTERMEDIATE PROGRAMMING CMIS 242 6382
 * ECLIPSE IDE Mars.2 Release (4.5.2)
 */

package project3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {
	
	//Allows for setting the frame to a specific size.
	static final int WINDOWWIDTH = 350, WINDOWHEIGHT = 200;
	
	//Create labels for components.
	private JLabel enterNLabel = new JLabel("Enter n:");
	private JLabel resultLabel = new JLabel("Result:");
	private JLabel efficiencyLabel = new JLabel("Efficiency:");
	private JLabel emptyLabel = new JLabel("");
	
	//Output text fields for efficiency and result.
	private JTextField efficiencyOutput = new JTextField("");
    private JTextField resultOutput = new JTextField("");
	
	//Create a compute button for the user to click once a number is entered.
	private JButton computeButton = new JButton("Compute");
	
	//Text field for user entry.
	private JTextField userEntry = new JTextField("");
	
	//Create radio buttons for user selection.
	private JRadioButton iterativeRadio = new JRadioButton("Iterative");
	private JRadioButton recursiveRadio = new JRadioButton("Recursive");
	private ButtonGroup radios = new ButtonGroup();
		
	//Allows for creating a standard dialog box that prompts the user.
	private JOptionPane frame = new JOptionPane();
	
	//Holds the users input value.
	private int entryValue;
	
	//Shows the frame to the user.
	public void display() {
		setVisible(true);
	}
	
	//Sets the frames width and height and centers the window.
	private void setFrame(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	//FileWriter writes to the following logs.
	private static FileWriter fileWriter;
	private File log = new File("log.csv");
	private File outIterative = new File("Iterative.txt");
	private File outRecursive = new File("Recursive.txt");
	
	/**The entry and efficiency values are stored as strings in 
	 * the following array lists before being written to the log.csv 
	 * file.
	**/
	private ArrayList<String> logList = new ArrayList<>();
	private ArrayList<String> listIterative = new ArrayList<>();
	private ArrayList<String> listRecursive = new ArrayList<>();
	
	//creates the GUI.
	public Main() {
		super("Project 3");
		setFrame(WINDOWWIDTH, WINDOWHEIGHT);
		setResizable(false);
		iterativeRadio.setSelected(true);
		radios.add(iterativeRadio);
        radios.add(recursiveRadio);
        JPanel mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new GridLayout (5, 2, 0, 10));
		mainPanel.add(iterativeRadio);
        mainPanel.add(recursiveRadio);
		mainPanel.add(enterNLabel);
        mainPanel.add(userEntry);
        mainPanel.add(emptyLabel);
        mainPanel.add(computeButton);
        mainPanel.add(resultLabel);
        mainPanel.add(resultOutput);
        mainPanel.add(efficiencyLabel);
        mainPanel.add(efficiencyOutput);
                
        //Action listener
        computeButton.addActionListener(new ComputeButtonListener());
        exitOverride close = new exitOverride();
        addWindowListener(close);
    }
	
	/**
	 * The ComputeButtonListener first determines that the number entered by the user
	 * is valid, in this case a number cannot be higher than 10 or lower than 0.
	 */
		class ComputeButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				setEntryValue();
				if (entryValue > 10 || entryValue < 0) {
					error();
				} else if (iterativeRadio.isSelected()) {
					resultOutput.setText(String.valueOf(Sequence.computeIterative(entryValue)));
					efficiencyOutput.setText(String.valueOf(Sequence.getEfficiency()));
					addtoCSV("Iterative");
					addtoListIterative();
				} else if (recursiveRadio.isSelected()) {
					resultOutput.setText(String.valueOf(Sequence.computeRecursive(entryValue)));
					efficiencyOutput.setText(String.valueOf(Sequence.getEfficiency()));
					addtoCSV("Recursive");
					addtoListRecursive();
				}
				clearEntryValue();
			}
		}
		
		//This replaces the EXIT_ON_CLOSE method.  
		   class exitOverride extends WindowAdapter {

		        @Override
		        public void windowClosing(WindowEvent e) {
		            try {
		                if (!logList.isEmpty()) {
		                    writeFiles();
		                    fileWriter.close();
		                }
		            
		            } catch (IOException ex) {
		                ex.printStackTrace();
		       
		                System.exit(0);
		            } catch (NullPointerException e1) {
		                e1.printStackTrace();
		             
		                System.exit(0);
		            }
		            System.exit(0);
		        }
		    }
		
		
		//Adds a label for radio selection, efficiency, and entry to the logList array.
		public void addtoCSV(String choice) {
			logList.add(choice + ", " + Sequence.getEfficiency() + ", " + entryValue);
		}
		
		//Adds the efficiency and try value to the listIterative array.
		public void addtoListIterative() {
			listIterative.add(Sequence.getEfficiency() + ", " + entryValue);
		}
		
		//Adds the efficiency and try value to the listRecursive array.
		public void addtoListRecursive() {
			listRecursive.add(Sequence.getEfficiency() + ", " + entryValue);
		}
		
		//The following method writes 3 files to the hard drive, a CSV file and .txt files for the arrays.
		public void writeFiles() {
			try {
				
				//Writes to the CSV file.
				fileWriter = new FileWriter(log);
				
				for (String l : logList) {
					fileWriter.write(l + System.getProperty("line.separator"));
				}
				fileWriter.close();
				
				//Writes to the outIterative.txt file.
				fileWriter = new FileWriter(outIterative);
				
				for (String l : listIterative) {
					fileWriter.write(l + System.getProperty("line.separator"));
				}
				
				fileWriter.close();
				
				//Writes to the outRecursive.txt file.
				fileWriter = new FileWriter(outRecursive);
				
				for (String l : listRecursive) {
					fileWriter.write(l + System.getProperty("line.separator"));
				}
				
				fileWriter.close();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		
		//Method that returns the text in the text entry field as an integer.
		public int getEntryValue() {
			try {
				return Integer.parseInt(userEntry.getText());
			} catch (NumberFormatException e) {
				clearEntryValue();
				return 11; //prevents file logging.
			}
		}
		
		public void setEntryValue() {
			this.entryValue = getEntryValue();
		}
	
        //Clears any text in the text entry field.
		public void clearEntryValue() {
			userEntry.setText("");
		}
		
		/**Throws an error message when the user enters anything other than a number or
		 * a number below 0 or above 10.
		 */	
		public void error() {
			JOptionPane.showMessageDialog(frame, "Invalid input.");
		}
		
		//Runs the program.
        public static void main(String[] args) {
            Main GUI = new Main();
            GUI.display();
       		
        }
	}
