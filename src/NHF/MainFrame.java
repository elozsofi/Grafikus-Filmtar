package NHF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrame extends JFrame  {

	private static final long serialVersionUID = 1408394860633131081L;
	private final JTable filmTableModel;
	private FilmDB films;
	private final JTable userTableModel;
	private UserDB users;
	private SearchUsers userSearch;
	private SearchMovies movieSearch;
    private JPanel topPanel;
    private JPanel filmPanel;
    private JPanel userPanel;

	public MainFrame() {
		
		super("P3MDB");
		
		////////////////////
		// adattagok
		////////////////////
		filmTableModel = new JTable();
		userTableModel = new JTable();
		
		this.users = new UserDB();
		this.films = new FilmDB();

		topPanel = new JPanel();
		userPanel = new JPanel();
		filmPanel = new JPanel();
		
		
        //szerializálással beolvasás fájlból
        films = FilmDB.readFromFile(films);
        users = UserDB.readFromFile(users);
        
        //abstract table model beállítása
        filmTableModel.setModel(films.getFilmTable());
        userTableModel.setModel(users.getUserTable());
        
        //frame alap tulajdonságai
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(2000,2000);
        setLocationRelativeTo(null);
        
        
        userPanel.setAutoscrolls(true);
        filmPanel.setAutoscrolls(true);
        topPanel.setAutoscrolls(true);
        
        //panelek
        menu();
        showFilmFrame();
        
        ////////////////////////////
        /*  legfelső, csak címes panel
        /*////////////////////////
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout());
        firstPanel.setBackground(Color.RED);
        JLabel title = new JLabel("P3MDB");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        firstPanel.add(title);
        ////////////////////////////////
        
        this.add(firstPanel, BorderLayout.NORTH);
        this.pack();
	}
	
	// új film ablak
	private void newFilm() throws NumberFormatException {
		FilmFrame ff = new FilmFrame();
		int year = 0;
		int len = 0;
		double rat = 0;
		int dvds = 0;
		int addOrNo = JOptionPane.showConfirmDialog(null, ff.getPanel(), "Add a new movie", JOptionPane.OK_CANCEL_OPTION);
		if (addOrNo == JOptionPane.OK_OPTION) {
			try {
				year = Integer.parseInt(ff.getRelease());
				len = Integer.parseInt(ff.getLength());
				rat = Double.parseDouble(ff.getRating());
				dvds = Integer.parseInt(ff.getDvds());
			}
			catch (NumberFormatException e){
	            JOptionPane.showMessageDialog(null, "WRONG FORMAT", "Warning", JOptionPane.WARNING_MESSAGE);
	            return;
			}
			Film newFilm = new Film(ff.getTitle(), year, len, rat, dvds, ff.getCategory(), ff.getDescription());
			
			if (films.movieAdd(newFilm.getTitle(), newFilm.getReleaseYear(), newFilm.getDuration(), newFilm.getRating(), newFilm.getDVDs(), newFilm.getCategory(), newFilm.getDescription()));
			else {
				JOptionPane.showMessageDialog(null, "Wrong number format", "Warning", JOptionPane.WARNING_MESSAGE, new ImageIcon("warning.png"));
				return;
			}
		}
	}
	
	// új felhasználó ablak
	private void newUser() throws NumberFormatException {
		UserFrame uf = new UserFrame();
		int year = 0;
		int seen = 0;
		int addOrNo = JOptionPane.showConfirmDialog(null, uf.getPanel(), "Register a new user", JOptionPane.OK_CANCEL_OPTION);
		if (addOrNo == JOptionPane.OK_OPTION) {
			try {
				year = Integer.parseInt(uf.getBirthYear());
				seen = Integer.parseInt(uf.getNumOfMovies());
			}
			catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "WRONG FORMAT", "Warning", JOptionPane.WARNING_MESSAGE);
	            return;
			}
			User newUser = new User(uf.getUsername(), uf.getPassword(), year, seen, uf.getMovies());
			if (users.userAdd(newUser.getUserName(), newUser.getPassword(), newUser.getBirthYear(), newUser.getNumOfMovies(), newUser.getMoviesSeen()));
			else {
				JOptionPane.showMessageDialog(null, "Wrong number format", "Warning", JOptionPane.WARNING_MESSAGE, new ImageIcon("warning.png"));
				return;
			}
		}
	}
	
	//film törlés
	private void removeFilm() {
		if (filmTableModel.getSelectedRow() >= 0) {
			Film f = films.getFilms().get(filmTableModel.convertRowIndexToModel(filmTableModel.getSelectedRow()));
			int removeOrNo = JOptionPane.showConfirmDialog(null, "Confirm remove", "Confirm remove", JOptionPane.OK_CANCEL_OPTION);
			if (removeOrNo == JOptionPane.OK_OPTION) { 
				films.movieRemove(f); 
			}
		}
	}
	
	//felhasználó törlés
	private void removeUser() {
		if (userTableModel.getSelectedRow() >= 0) {
			User u = users.getUsers().get(userTableModel.convertRowIndexToModel(userTableModel.getSelectedRow()));
			int removeOrNo = JOptionPane.showConfirmDialog(null, "Confirm remove", "Confirm remove", JOptionPane.OK_CANCEL_OPTION);
			if (removeOrNo == JOptionPane.OK_OPTION) { 
				users.userRemove(u);
			}
		}
	}
	
	//film módosítás
	private void editFilm() throws NumberFormatException {
		Film f = films.getFilms().get(filmTableModel.convertRowIndexToModel(filmTableModel.getSelectedRow()));
		FilmFrame ff = new FilmFrame();
		ff.setTitle(f.getTitle());
		ff.setRelease(Integer.toString(f.getReleaseYear()));
		ff.setLength(Integer.toString(f.getDuration()));
		ff.setRating(Double.toString(f.getRating()));
		ff.setDvds(Integer.toString(f.getDVDs()));
		ff.setCategory(f.getCategory());
		ff.setDescription(f.getDescription());
		
		int editOrNo = JOptionPane.showConfirmDialog(null,ff.getPanel(), "Confirm edit", JOptionPane.OK_CANCEL_OPTION);
		if (editOrNo == JOptionPane.OK_OPTION) {
			try {
				if (films.movieEdit(f, ff.getTitle(), Integer.parseInt(ff.getRelease()), Integer.parseInt(ff.getLength()), Double.parseDouble(ff.getRating()), Integer.parseInt(ff.getDvds()), ff.getCategory(), ff.getDescription() ));

			}
			catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Wrong number format", "Warning", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		}
	}
	
	//dvd rendelés
	private void orderDvd() {
		Film f = films.getFilms().get(filmTableModel.convertRowIndexToModel(filmTableModel.getSelectedRow()));

		if(f.getDVDs() <= 0) { 	           
			JOptionPane.showMessageDialog(null, "Order failed, out of stock", "Warning", JOptionPane.WARNING_MESSAGE, new ImageIcon("warning.png"));
			return;
		}
		LoginFrame lf = new LoginFrame();
		int orderOrNo = JOptionPane.showConfirmDialog(null, lf.getPanel(), "Login to order movie dvd", JOptionPane.OK_CANCEL_OPTION);
		if (orderOrNo == JOptionPane.OK_OPTION) {
			String n = lf.getUsername();
			String p = lf.getPassword();
			if (users.loginSuccess(n,p)) {
				films.order(f);
			}
			else { 
	            JOptionPane.showMessageDialog(null, "Login failed", "Warning", JOptionPane.WARNING_MESSAGE, new ImageIcon("warning.png"));
			}
		}		
	}
	
	//felhasználó módosítás
	private void editUser() throws NumberFormatException {
		User u = users.getUsers().get(userTableModel.convertRowIndexToModel(userTableModel.getSelectedRow()));
		UserFrame uf = new UserFrame();
		uf.setUsername(u.getUserName());
		uf.setPassword(u.getPassword());
		uf.setBirthYear(Integer.toString(u.getBirthYear()));
		uf.setNumOfMovies(Integer.toString(u.getNumOfMovies()));
		uf.setMovies(u.getMoviesSeen());
		int editOrNo = JOptionPane.showConfirmDialog(null,uf.getPanel(), "Confirm edit", JOptionPane.OK_CANCEL_OPTION);
		if (editOrNo == JOptionPane.OK_OPTION) {
			try {
				users.userEdit(u, uf.getUsername(), uf.getPassword(), Integer.parseInt(uf.getBirthYear()), Integer.parseInt(uf.getNumOfMovies()), uf.getMovies() );
			}
			catch (NumberFormatException e){
	            JOptionPane.showMessageDialog(null, "Wrong number format", "Warning", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		}
	}

	//megjeleníti a felhasználókat
	private void showUserFrame() {
	    topPanel.setVisible(false);
	    filmPanel.setVisible(false);
	    userPanel.setVisible(true);
		userTableModel.setAutoCreateRowSorter(true);
		
		userPanel.removeAll();
		
		JScrollPane userScroll = new JScrollPane(this.userTableModel);
		
		JPanel userrPanel = new JPanel(new BorderLayout());
		userrPanel.setBorder(BorderFactory.createTitledBorder(null, "Users", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
		
		JPanel userComponent = new JPanel();
		userComponent.setLayout(new BoxLayout(userComponent, BoxLayout.X_AXIS));
		userrPanel.setBackground(Color.BLACK);
		//keresés
		userSearch = new SearchUsers(this.users.getUsers());
		userSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent de) {
				String searchFor = userSearch.getText();
				userTableModel.setRowSorter(users.userSearch(searchFor));
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				userTableModel.setRowSorter(users.userSearch(userSearch.getText()));
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				userTableModel.setRowSorter(users.userSearch(userSearch.getText()));	
			}
		});
	
		userComponent.add(userSearch);
		userrPanel.add(userComponent, BorderLayout.NORTH);
		userrPanel.add(userScroll, BorderLayout.CENTER);
		
		userPanel.add(userrPanel);
		this.add(userPanel, BorderLayout.CENTER);
	    userPanel.revalidate();
	    userPanel.repaint();
	}
	
	//megjeleníti a menübárt, opciókkal
	private void menu() {
		JMenuBar menuChoose = new JMenuBar();
		
		//mentés
		JMenu sav = new JMenu("Save");
		sav.setForeground(Color.WHITE);
		
		JMenuItem save = new JMenuItem("Save all");
        save.addActionListener(actionEvent -> {users.saveToFile();films.saveToFile();});
        
        JMenuItem saveUsers = new JMenuItem("Save users");
        saveUsers.addActionListener(actionEvent -> {users.saveToFile();});
        
        JMenuItem saveMovies = new JMenuItem("Save movies");
        saveMovies.addActionListener(actionEvent -> {films.saveToFile();});
       
        //váltás a nézetek között
        JMenu views = new JMenu("View...");
		views.setForeground(Color.WHITE);
        JMenuItem top10 = new JMenuItem("View top 10 movies");
        top10.addActionListener(actionEvent -> {
    		showFilmFrame();
        });
        JMenuItem movies = new JMenuItem("View movies");
        movies.addActionListener(actionEvent -> {
    		showFilmFrame();
        });
        JMenuItem people = new JMenuItem("View users");
        people.addActionListener(actionEvent -> {
    		showUserFrame();
        });
        
        JMenu userMenu = new JMenu("Users");
		userMenu.setForeground(Color.WHITE);
        JMenuItem userEdit = new JMenuItem("Edit a user");
        userEdit.addActionListener(actionEvent -> {
        	if (userTableModel.getSelectedRow() >= 0) { editUser();}
        });

        JMenuItem userDel = new JMenuItem("Delete a user");
        userDel.addActionListener(actionEvent -> {
        	if (userTableModel.getSelectedRow() >= 0) { removeUser();}
        });
        JMenuItem userAdd = new JMenuItem("Register new user");
        userAdd.addActionListener(ae -> newUser());
        
        JMenuItem filmEdit = new JMenuItem("Edit a movie");
        filmEdit.addActionListener(actionEvent -> {
        	if (filmTableModel.getSelectedRow() >= 0) {editFilm();}
        });       
        
        JMenu movieMenu = new JMenu("Movies");
		movieMenu.setForeground(Color.WHITE);
        JMenuItem newFilm = new JMenuItem("Add a movie");
        newFilm.addActionListener(al -> newFilm());
        
        JMenuItem movieDel = new JMenuItem("Delete a movie");
        movieDel.addActionListener(al -> removeFilm());
        
        JMenuItem movieOrder = new JMenuItem("Order DvD");
        movieOrder.addActionListener( al -> {
        	if (filmTableModel.getSelectedRow() >= 0) { orderDvd();}
        });
        
        JMenu ex = new JMenu("Exit");
		ex.setForeground(Color.WHITE);
        //mentés / kilépés
        JMenuItem exitwsave = new JMenuItem("Save and exit");
        exitwsave.addActionListener(actionEvent -> {
        	users.saveToFile();
        	films.saveToFile();
        	System.exit(0);
        });
        JMenuItem exitwosave = new JMenuItem("Exit without save");
        exitwosave.addActionListener(actionEvent -> {
        	System.exit(0);
        });
        
        sav.add(save);
        sav.add(saveMovies);
        sav.add(saveUsers);
        
        views.add(top10);
        views.add(movies);
        views.add(people);
        
        ex.add(exitwosave);
        ex.add(exitwsave);
        
        userMenu.add(userAdd);
        userMenu.add(userDel);
        userMenu.add(userEdit);
        
        movieMenu.add(newFilm);
        movieMenu.add(movieDel);
        movieMenu.add(filmEdit);
        movieMenu.add(movieOrder);
        
        menuChoose.add(userMenu);
        menuChoose.add(movieMenu);
        menuChoose.add(sav);
        menuChoose.add(ex);
        menuChoose.add(views);
        
        menuChoose.setBackground(Color.BLACK);
        this.setJMenuBar(menuChoose);
	}
	
	//megjeleníti a top 10 filmet
	@SuppressWarnings("unused")
	private void showTopFilmFrame() {
	    filmPanel.setVisible(false);
	    userPanel.setVisible(false);
	    topPanel.setVisible(true);
		filmTableModel.setAutoCreateRowSorter(true);
		
		topPanel.removeAll();
		
		JScrollPane topMovieScroll = new JScrollPane(filmTableModel);
		
		JPanel movies = new JPanel();
		movies.setBorder(BorderFactory.createTitledBorder(null, "Top 10 Movies", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
		movies.setLayout(new BorderLayout());
		movies.setBackground(Color.BLACK);
		
		JPanel firstPanel = new JPanel();
		firstPanel.add(Box.createHorizontalGlue());
		
		filmTableModel.setRowSorter(films.top10());
		movies.add(firstPanel, BorderLayout.NORTH);
		movies.add(topMovieScroll, BorderLayout.CENTER);
		
		topPanel.add(movies);
		this.add(topPanel, BorderLayout.CENTER);
	    revalidate();
	    repaint();
	}
	
	//megjeleníti a filmeket
	private void showFilmFrame() {
	    topPanel.setVisible(false);
	    userPanel.setVisible(false);
	    filmPanel.setVisible(true);
		filmTableModel.setAutoCreateRowSorter(true);
		
		filmPanel.removeAll();
		
		JScrollPane movieScroll = new JScrollPane(filmTableModel);
		
		JPanel movies = new JPanel();
		movies.setBorder(BorderFactory.createTitledBorder(null, "Movies", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, Color.WHITE));
		movies.setLayout(new BorderLayout());
		movies.setBackground(Color.BLACK);
		
		JPanel firstPanel = new JPanel();
		BoxLayout firstLayout = new BoxLayout(firstPanel, BoxLayout.X_AXIS);
		
		firstPanel.setLayout(firstLayout);
		firstPanel.add(Box.createHorizontalGlue());
		
		//keresés
		movieSearch = new SearchMovies(this.films.getFilms());
		movieSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent de) {
				String searchFor = movieSearch.getText();
				filmTableModel.setRowSorter(films.filmSearch(searchFor));
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				filmTableModel.setRowSorter(films.filmSearch(movieSearch.getText()));
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				filmTableModel.setRowSorter(films.filmSearch(movieSearch.getText()));	
			}
		});
		
		firstPanel.add(movieSearch);
		
		movies.add(firstPanel, BorderLayout.NORTH);
		movies.add(movieScroll, BorderLayout.CENTER);

		filmPanel.add(movies);
		this.add(filmPanel, BorderLayout.CENTER);
	    revalidate();  // Frissítse a layout-ot
	    repaint();     // Rajzolja újra a panelt
	}
	
	////////////////////////////
	
	public static void main(String[] args) {
		MainFrame mainframe = new MainFrame();
		mainframe.setVisible(true);
	}
}
