package NHF;

import java.util.List;

import javax.swing.JTextField;

public class SearchMovies extends JTextField{
	private static final long serialVersionUID = -1494036370683022151L;
	public List<Film> filmek;
	
	public SearchMovies(List<Film> films){
		this.filmek = films;
		
		setText("Search for a movie...");		
	}
	public List<Film> getMovies() {return filmek;}
	public void setMovies(List<Film> films) {filmek = films;}
}
