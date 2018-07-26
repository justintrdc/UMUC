package homework1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class HW1 {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HW1 window = new HW1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public HW1() {
		initialize();
	}
	

		

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(169, 70, 116, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(169, 101, 116, 20);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username =  txtUsername.getText();
				String password = txtPassword.getText();
				String timeStamp = new SimpleDateFormat("MM/dd/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
				String login;
				
				if(username.equals("Justin") && password.equals("123"))
				{
					JOptionPane.showMessageDialog(frame, "Login successful!");
					login = "Successful";
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
					login = "Unsuccessful";
				}		
				
		        try{
		            BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt",true));
		            bw.write("Username: " + txtUsername.getText());
		            bw.write(" Password: " + txtPassword.getText());
		            bw.write(" Date_Time: " + timeStamp);
		            bw.write(" Login Attempt: " + login);
		            bw.newLine();
		            bw.close();
		        } catch(Exception ex){
		            ex.printStackTrace();
		        }
		        
			}
		});
		btnLogin.setBounds(183, 146, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(97, 72, 62, 17);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(97, 104, 62, 14);
		frame.getContentPane().add(lblPassword);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		btnClear.setBounds(183, 182, 89, 23);
		frame.getContentPane().add(btnClear);
			
		}
	
	public String combo(String user, String pass) {
		return user + pass;
	}
	
	public String none() {
		String pass = null;
		return pass;
	}
	
	public String user(String user) {
		user = "Justin";
		
		return user;
	}
	
	public String pass(String pass) {
		pass = "123";
		
		return pass;
	}
}
