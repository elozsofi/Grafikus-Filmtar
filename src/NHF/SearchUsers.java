package NHF;

import java.util.List;

import javax.swing.JTextField;

public class SearchUsers extends JTextField{
	private static final long serialVersionUID = 3394339766009972075L;
	public List<User> userek;
	
	public SearchUsers(List<User> users){
		this.userek = users;
		
		setText("Search for a user...");		
	}
	
	public List<User> getUsers() {return userek;}
	public void setUsers(List<User> users) {userek = users;}
}
