package NHF;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import java.io.*;

public class UserDB implements Serializable{
	private static final long serialVersionUID = 2248232194440860702L;
	private final List<User> users;
	private transient UserTableModel userTable;
	
    public UserDB() {
        users = new ArrayList<>();
        userTable = new UserTableModel(users);
    }
    
    public UserDB(List<User> userek) {
    	users = userek;
    }
    
    public List<User> getUsers(){ return users;}
    public UserTableModel getUserTable() {return userTable;}
    
    ///fájlba kimentő szerializálással
    public void saveToFile(){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("users.libdat"));
            os.writeObject(this);
            os.close();
            System.out.println("USERS SIKRERES MENTÉS!");

        }
        catch (Exception ioexception){
            System.out.println("USERS MENTÉSI HIBA LÉPETT FEL! " + ioexception.getMessage());
        }
    }

    ///fájlból beolvasó szerializálással
    public static UserDB readFromFile(UserDB userdb){
        userdb = new UserDB();
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("users.libdat"));
            userdb = (UserDB) is.readObject();
            is.close();
            System.out.println("USERS SIKRERES BEOLVASÁS!");
        }
        catch (Exception exception){
            System.out.println("USERS BEOLVASÁSI HIBA LÉPETT FEL! " + exception.getMessage());
        }
        userdb.userTable = new UserTableModel(userdb.users);
        return userdb;
    }
    
    //keresés felhasználó neve alapján
    public RowSorter<UserTableModel> userSearch(String wasistdas){
    	RowFilter<UserTableModel, Integer> searcher;
   		searcher = new RowFilter<UserTableModel, Integer>() {
   			public boolean include(Entry<? extends UserTableModel, ? extends Integer> entry) {
   				User u = users.get(entry.getIdentifier());
                return u.getUserName().contains(wasistdas);
            }
        };
        TableRowSorter<UserTableModel> userFilter = new TableRowSorter<>(userTable);
        userFilter.setRowFilter(searcher);
        return userFilter;
    }
    
    //érvényes bejelentkező adatok ellenőrző fgv.
    public boolean loginSuccess(String usern, String pass) {
    	for(User u : users) {
    		if (u.getUserName().equals(usern) && u.getPassword().equals(pass)) {
    			return true;
    		}
    	}
    	return false;
    }
    //eltávolítás
    public void userRemove(User u) {
	    userTable.removeUserFromTable(u); 
	    System.out.println("SIKERES TÖRLÉS");
    }
    //hozzáadás
    public boolean userAdd(String nam, String pass, int y, int num, String mov) {
    	if (userFormat(nam,pass,y,num,mov)) {
	    	User u = new User(nam, pass, y, num, mov);
	    	userTable.addUserToTable(u);
	    	System.out.println("SIKERES HOZZÁADÁS");
    		return true;
    	}
    	return false;
    }
    //szerkesztés setterekkel
    public boolean userEdit(User u, String nam, String pass, int year, int num, String mov) {
    	if (userFormat(nam,pass,year,num,mov)) {
    		u.setUserName(nam);
	    	u.setPassword(pass);
	    	u.setBirthYear(year);
	    	u.setNumOfMovies(num);
	    	u.setMoviesSeen(mov);
	    	userTable.fireTableDataChanged();
	    	return true;
    	}
    	return false;
    }
    
    public boolean userFormat(String nam, String pass, int y, int num, String seen) {
    	if (nam.equals("") || pass.equals("") || seen.equals("") || y < 0 || y > 2023 || num < 0) {
    		return false;
    	}
    	return true;
    }
}