package NHF;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 4869617010645120645L;
	private String userName;
    private String password;
    private int birthYear;
    private int numOfMovies;
    private String moviesSeen;

    //param nélküli konstr
    public User(){
        this.userName = "ures";
        this.password = "ures";
        this.birthYear = 0;
        this.numOfMovies = 0;
        this.moviesSeen = "ures";
    }
    
    ///paraméteres konstr
    public User(String s, String s2, int i, int ii, String l){
        userName=s; password=s2; birthYear=i;numOfMovies = ii; moviesSeen=l;
    }
    
    ///másoló konstr
    public User(User u) {
    	this.userName = u.getUserName();
    	this.password = u.getPassword();
    	this.birthYear = u.getBirthYear();
    	this.numOfMovies = u.getNumOfMovies();
    	this.moviesSeen = u.getMoviesSeen();
    }

    //getterek
    public String getUserName(){ return userName;}
    public String getPassword(){ return password;}
    public int getBirthYear(){ return birthYear;}
    public int getNumOfMovies(){ return numOfMovies;} 
    public String getMoviesSeen(){ return moviesSeen;}

    //setterek
    public void setUserName(String s){ userName = s;}
    public void setPassword(String s){ password = s;}
    public void setBirthYear(int i){ birthYear = i;}
    public void setNumOfMovies(int i){ numOfMovies = i;}
    public void setMoviesSeen(String l){ moviesSeen = l;}
}