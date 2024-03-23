package NHF;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserFrame extends JPanel{
	
	private static final long serialVersionUID = -2740267691449190096L;
	private JPanel userPanel;
	private JTextField username;
	private JTextField password;
	private JTextField birthyear;
	private JTextField numofmovies;
	private JTextField movies;
	
	public UserFrame() {
		
		userPanel = new JPanel(new BorderLayout(10,10));
		/*
		 * bevitel text fields
		 */
		JPanel IO = new JPanel(new GridLayout(0,1,2,2));
		username = new JTextField(30);
		username.setText("Username");
		password = new JTextField(20);
		password.setText("Password");
		
		birthyear = new JTextField(10);
		birthyear.setText("Year of birth");
		
		numofmovies = new JTextField(10);
		numofmovies.setText("Number of seen movies");
		
		movies = new JTextField(50);
		movies.setText("List of seen movies");
		
		IO.add(username);
		IO.add(password);
		IO.add(birthyear);
		IO.add(numofmovies);
		IO.add(movies);
		
		userPanel.add(IO, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {return userPanel;}
	public String getUsername() {return username.getText();}
	public String getPassword() {return password.getText();}
	public String getBirthYear() {return birthyear.getText();}
	public String getNumOfMovies() {return numofmovies.getText();}
	public String getMovies() {return movies.getText();}

	public void setUsername(String s) { username.setText(s);}
	public void setPassword(String s) { password.setText(s);}
	public void setBirthYear(String s) { birthyear.setText(s);}
	public void setNumOfMovies(String s) { numofmovies.setText(s);}
	public void setMovies(String s) { movies.setText(s);}

}
