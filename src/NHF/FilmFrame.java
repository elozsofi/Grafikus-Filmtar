package NHF;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilmFrame extends JPanel{

	private static final long serialVersionUID = 1405165967854404407L;
	
	private JPanel moviePanel;
	private JTextField title;
	private JTextField released;
	private JTextField length;
	private JTextField rating;
	private JTextField dvds;
	private JTextField category;
	private JTextField description;
	
	public FilmFrame() {
		
		moviePanel = new JPanel(new BorderLayout(10,10));
		
		/*
		 * bevitel text fields
		 */
		JPanel IO = new JPanel(new GridLayout(0,1,2,2));
		title = new JTextField(30);
		title.setText("Title");
		released = new JTextField(10);
		released.setText("Release Year");
		length = new JTextField(10);
		length.setText("Length (min)");
		rating = new JTextField(10);
		rating.setText("Rating (0-10)");
		dvds = new JTextField(10);
		dvds.setText("Number of DvDs");
		category = new JTextField(30);
		category.setText("Category");
		description = new JTextField(50);
		description.setText("Description");
		
		IO.add(title);
		IO.add(released);
		IO.add(length);
		IO.add(rating);
		IO.add(dvds);
		IO.add(category);
		IO.add(description);
		
		moviePanel.add(IO, BorderLayout.CENTER);
	}
	
	//// gettere, setterek....
	public JPanel getPanel() {return moviePanel;}
	public String getTitle() {return title.getText();}
	public String getRelease() {return released.getText();}
	public String getLength() {return length.getText();}
	public String getRating() {return rating.getText();}
	public String getDvds() {return dvds.getText();}
	public String getCategory() {return category.getText();}
	public String getDescription() {return description.getText();}

	public void setTitle(String s) { title.setText(s); }
	public void setRelease(String s) { released.setText(s);}
	public void setLength(String s) { length.setText(s);}
	public void setRating(String s) { rating.setText(s);}
	public void setDvds(String s) { dvds.setText(s);}
	public void setCategory(String s) { category.setText(s);}
	public void setDescription(String s) { description.setText(s);}
}
