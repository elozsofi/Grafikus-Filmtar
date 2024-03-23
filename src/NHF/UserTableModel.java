package NHF;

import javax.swing.table.*;

import java.util.*;

public class UserTableModel extends AbstractTableModel{
	
	//adattagok
	private static final long serialVersionUID = 2535529903521744139L;

	String[] columnNames = {"Username", "Password", "Birth year", "# of seen movies", "Movies seen"};
	
	private List<User> users;
	
	/////////////////////////////
	///		konstr   ///////////
	public UserTableModel(List<User> array) {
		users = array;
	}
	public UserTableModel() {
		users = new ArrayList<>();
	}
	
	//##################################
	//fgvek
	public void removeUserFromTable(User u) {
		users.remove(u);
		fireTableDataChanged();
	}
	public void addUserToTable(User u) {
		users.add(u);
		fireTableDataChanged();
	}
	
	
	//////////////////
	/// abs table model
	// szükséges metódusai
	////////////////////
	@Override
    public String getColumnName(int col) {
		if(col == 0) { return "Username";}
		if(col == 1) { return "Password";}
		if(col == 2) { return "Born on";}
		if(col == 3) { return "# of seen movies";}
		if(col == 4) { return "Movies seen";}
		return null;
    }
	@Override
	public Class<?> getColumnClass(int cidx) {
		if (cidx == 0) { return String.class;}
		if (cidx == 1) { return String.class;}
		if (cidx == 2) { return Integer.class;}
		if (cidx == 3) { return Integer.class;}
		if (cidx == 4) { return String.class;}
		return null;
	}
    @Override
    public boolean isCellEditable(int ridx, int cidx) {
        return true;
    }
	@Override
	public void setValueAt(Object newValue, int row, int col) {
		if(col == 0) {users.get(row).setUserName((String)newValue);}
		if(col == 1) { users.get(row).setPassword((String)newValue);}
		if(col == 2) { users.get(row).setBirthYear((int) newValue);}
		if(col == 3) { users.get(row).setNumOfMovies((int)newValue);}
		if(col == 4) { users.get(row).setMoviesSeen((String)newValue);}
        fireTableDataChanged();
    }
	@Override
	public int getColumnCount() {
		return 5;
	}
	@Override
	public int getRowCount() {
		return users.size();
	}
	@Override
	public Object getValueAt(int ridx, int cidx) {

		if(cidx == 0) { return users.get(ridx).getUserName();}
		if(cidx == 1) { return users.get(ridx).getPassword();}
		if(cidx == 2) { return users.get(ridx).getBirthYear();}
		if(cidx == 3) { return users.get(ridx).getNumOfMovies();}
		if(cidx == 4) { return users.get(ridx).getMoviesSeen();}
		return null;
	}
}
