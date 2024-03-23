package NHF;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JPanel{

	private static final long serialVersionUID = -997662140614024403L;
	private JPanel loginPanel;
	private JTextField username;
	private JTextField password;
	
	public LoginFrame() {
		
		loginPanel = new JPanel(new BorderLayout(10,10));
		/*
		 * bevitel text fields
		 */
		JPanel IO = new JPanel(new GridLayout(0,1,2,2));
		username = new JTextField(30);
		username.setText("Username");
		password = new JTextField(20);
		password.setText("Password");
		
		IO.add(username);
		IO.add(password);
		
		loginPanel.add(IO, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() { return loginPanel;}
	public String getUsername() { return username.getText();}
	public String getPassword() { return password.getText();}

	public void setUsername(String s) { username.setText(s);}
	public void setPassword(String s) { password.setText(s);}
	
}
