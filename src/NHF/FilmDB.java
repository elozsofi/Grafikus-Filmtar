package NHF;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import java.io.*;

public class FilmDB implements Serializable{
    private static final long serialVersionUID = -4876071254800381645L;
	private final List<Film> films;
	private transient FilmTableModel filmTable;

    public FilmDB() {
        this.films = new ArrayList<>();
        this.filmTable = new FilmTableModel(films);
    }
    
    public FilmDB(List<Film> movies) {
        films = movies;
    }

    public List<Film> getFilms(){ return films;}
    public FilmTableModel getFilmTable() {return filmTable;}

    //top10 film megjelenítése
    public RowSorter<FilmTableModel> top10() {
    	return filmTable.top10();
    }
    //fájlba kimentő szerializálással
    public void saveToFile(){
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("films.libdat"));
            os.writeObject(this);
            os.close();
            System.out.println("FILMS SIKRERES MENTÉS!");

        }
        catch (Exception ioexception){
            System.out.println("FILMS MENTÉSI HIBA LÉPETT FEL! " + ioexception.getMessage());
        }
    }

	//fájlból beolvasó szerializálással
    public static FilmDB readFromFile(FilmDB filmdb){
        filmdb = new FilmDB();
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("films.libdat"));
            filmdb = (FilmDB) is.readObject();
            is.close();
            System.out.println("FILMS SIKRERES BEOLVASÁS!");
        }
        catch (Exception exception){
            System.out.println("FILMS BEOLVASÁSI HIBA LÉPETT FEL! " + exception.getMessage());
        }
        filmdb.filmTable = new FilmTableModel(filmdb.films);
        return filmdb;
    }
    
    ///dvd szám ellenőrző fgv
    public boolean orderValid(Film f) {
    	if (f.getDVDs() <= 0) {	return false; }
    	return true;
    }
    
    ///rendelő fgv.
    public boolean order(Film f) {
    	if (orderValid(f)) { 
    		int uj = f.getDVDs();
    		uj -= 1;
    		f.setDVDs(uj);
    		System.out.println("SIKERES RENDELÉS");
    		filmTable.fireTableDataChanged();
    		return true;
    	}
    	else System.out.println("RENDELÉS SIKERTELEN");
    	return false;
    }
    
    //keresés film címe alapján
    public RowSorter<FilmTableModel> filmSearch(String wasistdas){
    	RowFilter<FilmTableModel, Integer> searcher;
   		searcher = new RowFilter<FilmTableModel, Integer>() {
   			public boolean include(Entry<? extends FilmTableModel, ? extends Integer> entry) {
   				Film f = films.get(entry.getIdentifier());
                return f.getTitle().contains(wasistdas);
            }
        };
        TableRowSorter<FilmTableModel> filmFilter = new TableRowSorter<>(filmTable);
        filmFilter.setRowFilter(searcher);
        return filmFilter;
    }
    
    //eltávolítás
    public void movieRemove(Film f) {
    	filmTable.removeFilmFromTable(f); 
    	System.out.println("SIKERES TÖRLÉS");
    }
    //hozzáadás
    public boolean movieAdd(String tit, int y, int dur, double rat, int dvds, String cat, String desc) {
	    if(filmFormat(tit,y,dur,rat,dvds,cat,desc)) {
	    	Film f = new Film(tit,y,dur,rat,dvds,cat,desc);
    		filmTable.addFilmToTable(f);
	    	System.out.println("SIKERES HOZZÁADÁS");
	    	return true;
	    }
	    return false;
    }
    //szerkesztés setterekkel
    public boolean movieEdit(Film f, String tit, int y, int dur, double rat, int dvds, String cat, String desc) {
    	if (filmFormat(tit,y,dur,rat,dvds,cat,desc)) {
	    	f.setTitle(tit);
	    	f.setReleaseYear(y);
	    	f.setRating(rat);
	    	f.setDVDs(dvds);
	    	f.setCategory(cat);
	    	f.setDescription(desc);
	    	f.setDuration(dur);
	    	filmTable.fireTableDataChanged();
	    	return true;
    	}
    	return false;
    }
    
    public boolean filmFormat(String tit, int y, int dur, double rat, int num, String cat, String desc) {
    	if (tit.equals("") || cat.equals("")  || desc.equals("") || y < 0 || y > 2023 || rat < 0 || rat > 10 ||num < 0 || dur <= 0) {
    		return false;
    	}
    	return true;
    }
}