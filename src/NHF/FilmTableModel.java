package NHF;

import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.*;

import java.util.*;

public class FilmTableModel extends AbstractTableModel{
	
	//adattagok
	private static final long serialVersionUID = 6066779695097889982L;

	String[] columnNames = {"Title", "Released", "Length", "Rating", "# of DVDs", "Category", "Description"};
	
	private List<Film> films;
	
	/////////////////////////////
	///		konstr   ///////////
	public FilmTableModel(List<Film> array) {
		films = array;
	}
	public FilmTableModel() {
		films = new ArrayList<>();
	}
	
	//##################################
	//fgvek
	public void removeFilmFromTable(Film f) {
		films.remove(f);
		fireTableDataChanged();
	}
	public void addFilmToTable(Film f) {
		films.add(f);
		fireTableDataChanged();
	}
	
	//komparatorral top 10 filmmel tér vissza
	private List<Film> sortFilmsByRating() {
		List<Film> sortedFilms = new ArrayList<Film>(films);
		
		Comparator<Film> ratingComparator = Comparator.comparingDouble(Film::getRating).reversed();
		sortedFilms.sort(ratingComparator);

		if(sortedFilms.size() > 10) {
			sortedFilms = sortedFilms.subList(0, 10);
		}
		
		return sortedFilms;
	}
	
	//csak a top 10 filmeket jeleníti meg, rating alapján rendezve
    public RowSorter<FilmTableModel> top10() {

    	List<Film> sortedFilms = sortFilmsByRating();
    	
        TableRowSorter<FilmTableModel> topTenSorter = new TableRowSorter<>(this);
        topTenSorter.setComparator(3, Comparator.comparingDouble(Double::parseDouble).reversed());
        topTenSorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.DESCENDING)));

        RowFilter<FilmTableModel, Integer> topFilter = new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends FilmTableModel, ? extends Integer> entry) {
                int modelRow = entry.getIdentifier();
                return sortedFilms.contains(films.get(modelRow));
            }
        };

        topTenSorter.setRowFilter(topFilter);
        return topTenSorter;
    }
	
	//////////////////
	/// abs table model
	// szükséges metódusai
	////////////////////
	@Override
    public String getColumnName(int col) {
		if(col == 0) { return "Title";}
		if(col == 1) { return "Release year";}
		if(col == 2) { return "Length (min)";}
		if(col == 3) { return "Rating (0-10)";}
		if(col == 4) { return "# of DVDs";}
		if(col == 5) { return "Category";}
		if(col == 6) { return "Description";}
		return null;
    }
	@Override
	public Class<?> getColumnClass(int cidx) {
		if (cidx == 0) { return String.class;}
		if (cidx == 1) { return Integer.class;}
		if (cidx == 2) { return Integer.class;}
		if (cidx == 3) { return Double.class;}
		if (cidx == 4) { return Integer.class;}
		if (cidx == 5) { return String.class;}
		if (cidx == 6) { return String.class;}
		return null;
	}
    @Override
    public boolean isCellEditable(int ridx, int cidx) {
        return true;
    }
	@Override
	public void setValueAt(Object newValue, int row, int col) {
		if(col == 0) { films.get(row).setTitle((String)newValue);}
		if(col == 1) { films.get(row).setReleaseYear((int)newValue);}
		if(col == 2) { films.get(row).setDuration((int) newValue);}
		if(col == 3) { films.get(row).setRating((int)newValue);}
		if(col == 4) { films.get(row).setDVDs((int)newValue);}
		if(col == 5) { films.get(row).setCategory((String)newValue);}
		if(col == 6) { films.get(row).setDescription((String)newValue);}
        fireTableDataChanged();
    }
	@Override
	public int getColumnCount() {
		return 7;
	}
	@Override
	public int getRowCount() {
		return films.size();
	}
	@Override
	public Object getValueAt(int ridx, int cidx) {

		if(cidx == 0) { return films.get(ridx).getTitle();}
		if(cidx == 1) { return films.get(ridx).getReleaseYear();}
		if(cidx == 2) { return films.get(ridx).getDuration();}
		if(cidx == 3) { return films.get(ridx).getRating();}
		if(cidx == 4) { return films.get(ridx).getDVDs();}
		if(cidx == 5) { return films.get(ridx).getCategory();}
		if(cidx == 6) { return films.get(ridx).getDescription();}
		return null;
	}
}
