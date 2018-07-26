package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class Main extends JFrame {
		
	//Allows for setting the frame to a specific size.
	static final int WIDTH = 300, HEIGHT = 200;
		
	//Allows for creating a standard dialog box that prompts the user.
	private JOptionPane frame = new JOptionPane();
	
	//Combobox options the user can select from.
	String[] options = {"Insert", "Delete", "Find", "Update"};
	
	//Creates labels, text fields, a combo box, and a process request button. 
	private JLabel idLabel = new JLabel(" Id:");
	private JLabel nameLabel = new JLabel(" Name:");
	private JLabel majorLabel = new JLabel(" Major:");
	private JLabel selectionLabel = new JLabel(" Choose Selection:");
	private JTextField idOutput = new JTextField("");
	private JTextField nameOutput = new JTextField("");
	private JTextField majorOutput = new JTextField("");
	private JComboBox combobox = new JComboBox(options);
	private JButton processButton = new JButton("Process Request");
	
	//Sets the frames width and height and centers the window.
	private void setFrame(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	//Creates a new HashMap, with the ID field as the key and a student record consisting of a name and major as the value. 
	HashMap<Integer, Student> map = new HashMap<Integer, Student>();

	//Creates the GUI.
	public Main() {
		super("Project 4");
		setFrame(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		JPanel mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new GridLayout (5, 2, 0, 10));
		mainPanel.add(idLabel);
		mainPanel.add(idOutput);
		mainPanel.add(nameLabel);
		mainPanel.add(nameOutput);
		mainPanel.add(majorLabel);
		mainPanel.add(majorOutput);
		mainPanel.add(selectionLabel);
		mainPanel.add(combobox);
		mainPanel.add(processButton);
		
		//Adds an action listener to the Process Request button.
		processButton.addActionListener(new ProcessButtonListener());
	}
	
	class ProcessButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int id = Integer.parseInt(idOutput.getText());				    
				
				if(combobox.getSelectedItem().equals("Insert"))
					comboInsert(id);
				else if(combobox.getSelectedItem().equals("Delete"))
					comboDelete(id);
				else if(combobox.getSelectedItem().equals("Find"))
					comboFind(id);
				else if(combobox.getSelectedItem().equals("Update"))
					comboUpdate(id);
				}
				catch (NumberFormatException e1) {
				     //Not an integer
				}
			}
		}

	
	//This method is called when the user selects the Insert option in the combo box.
	public void comboInsert(int id) {
		String name = nameOutput.getText();
		String major = majorOutput.getText();
		Student studentInfo = new Student(name, major);
		
		//Checks to make sure a positive number has been entered for the  field.
		if (id <= 0) {
			JOptionPane.showMessageDialog(frame, "Student Id must be a positive number.");
			return;
		} else if (map.containsKey(id)) {
			JOptionPane.showMessageDialog(frame, "This Id already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else map.put(id, studentInfo);
			JOptionPane.showMessageDialog(frame, "Student has been added to the database!");
			clearText();
	}
		
	
	//This method is called when the user selects the Delete option in the combo box.
	public void comboDelete(int id) {
		
		//Checks to make sure a positive number has been entered for the text field.
		if (id <= 0) {
			JOptionPane.showMessageDialog(frame, "Student Id must be a positive number.");
			return;
		} else if (map.containsKey(id)) {
			map.remove(id);
			JOptionPane.showMessageDialog(frame, "Student has been deleted from the database.");
		} else JOptionPane.showMessageDialog(frame, "This student was not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
		clearText();
	}
			
	//This method is called when the user selects the Find option in the combo box.
	public void comboFind(int id) {	
		if (id <= 0) {
			JOptionPane.showMessageDialog(frame, "Student Id must be a positive number.");
			return;
		} else if (map.containsKey(id)) {
		    JOptionPane.showMessageDialog(frame, map.get(id));	    
		} else JOptionPane.showMessageDialog(frame, "This student was not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
		clearText();
	}
	
	//This method is called when the user selects the Update option in the combo box.
	public void comboUpdate(int id) {  
		
		String[] grade = { "A", "B", "C", "D", "F" };
		String[] credit = { "3", "6" };
		
		 if (id <= 0) {
				JOptionPane.showMessageDialog(frame, "Student Id must be a positive number.");
				
		 } else if (map.containsKey(id)) {		
			 String letterGrade = (String) JOptionPane.showInputDialog(null, "Choose grade:", "", JOptionPane.INFORMATION_MESSAGE, null, grade, "");
			 String credits = (String) JOptionPane.showInputDialog(null, "Choose credits:", "", JOptionPane.INFORMATION_MESSAGE, null, credit, "");
			 Student updateStudent = map.get(id);
			 updateStudent.courseCompleted(letterGrade, Integer.parseInt(credits));
			 } else JOptionPane.showMessageDialog(frame, "This student was not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
			clearText();
	}
	
	//Clears the text fields for the Id, Student Name, and Major.
	public void clearText() {
		idOutput.setText("");
		nameOutput.setText("");
		majorOutput.setText("");
	}
	
	//Runs the program.
	public static void main(String[] args) {
		Main createAndShowGUI = new Main();
	}
}
	
