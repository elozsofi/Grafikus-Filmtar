package NHF;

import java.io.Serializable;

public class Film implements Serializable{
    private static final long serialVersionUID = -9212516973542402478L;
	private String title;
    private int releaseYear;
    private int duration;
    private double rating;
    private int numOfDVDs;
    private String category;
    private String description;

    //param nélküli konstr
    public Film(){
        this.title = "ures";
        this.releaseYear = 0;
        this.duration = 0;
        this.rating = 0;
        this.numOfDVDs = 0;
        this.category = "ures";
        this.description = "ures";
    }

    //paraméteres konstr
    public Film(String s, int i, int ii, double d, int dvd, String c, String de){ title = s; releaseYear = i; duration = ii; rating = d; numOfDVDs = dvd; category = c; description = de;}

    //másoló konstr
    public Film(Film f){
        title=f.getTitle();
        releaseYear=f.getReleaseYear();
        duration=f.getDuration();
        rating=f.getRating();
        numOfDVDs = f.getDVDs();
        category = f.getCategory();
        description = f.getDescription();
    }

    //getterek
    public String getTitle(){ return title;}
    public int getReleaseYear(){ return releaseYear;}
    public int getDuration(){ return duration;}
    public double getRating(){ return rating;}
    public int getDVDs(){ return numOfDVDs;}
    public String getCategory(){ return category;}
    public String getDescription(){ return description;}

    //setterek
    public void setTitle(String s){ this.title = s;}
    public void setReleaseYear(int i){ this.releaseYear = i;}
    public void setDuration(int i){ this.duration = i;}
    public void setRating(double d){ this.rating = d;}
    public void setDVDs(int i){ this.numOfDVDs = i;}
    public void setCategory(String s){ this.category = s;}
    public void setDescription(String s){ this.description = s;}
}