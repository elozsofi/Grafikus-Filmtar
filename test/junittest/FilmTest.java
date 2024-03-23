package junittest;

import org.junit.Assert;
import org.junit.Test;

import NHF.Film;
import NHF.FilmDB;
import NHF.SearchMovies;
import NHF.SearchUsers;
import NHF.User;
import NHF.UserDB;

public class FilmTest {

	//searchölő classok inicializálódását vizsgáló tesztek
	@Test
	public void userSearchInitTest() {
		SearchUsers us = new SearchUsers(null);
		Assert.assertEquals("USERSEARCH INICIALIZALAS FAIL",null, us.getUsers());
	}
	@Test
	public void filmSearchInitTest() {
		SearchMovies ms = new SearchMovies(null);
		Assert.assertEquals("MOVIESEARCH INICIALIZALAS FAIL",null, ms.getMovies());
	}
	@Test
	public void userSearchGetSetTest() {
		UserDB udb = new UserDB();
		udb = UserDB.readFromFile(udb);
		SearchUsers us = new SearchUsers(udb.getUsers());
		us.setUsers(udb.getUsers());
		Assert.assertEquals("USERSEARCH GET/SET FAIL",udb.getUsers(), us.getUsers());
	}
	@Test
	public void filmSearchGetSetTest() {
		FilmDB fdb = new FilmDB();
		fdb = FilmDB.readFromFile(fdb);
		SearchMovies ms = new SearchMovies(fdb.getFilms());
		ms.setMovies(fdb.getFilms());
		Assert.assertEquals("MOVIESEARCH GET/SET FAIL",fdb.getFilms(), ms.getMovies());
	}
	
	//FilmDB és UserDB tesztek
	@Test
	public void filmdbAddTest() {
		FilmDB fdb = new FilmDB();
		Film f = new Film();
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		Assert.assertTrue("FILMDB ADD FAIL", fdb.getFilms().contains(f));
	}
	@Test
	public void userdbAddTest() {
		UserDB udb = new UserDB();
		User u = new User();
		udb.userAdd(u.getUserName(), u.getPassword(), u.getBirthYear(), u.getNumOfMovies(), u.getMoviesSeen());
		Assert.assertTrue("USERDB ADD FAIL", udb.getUsers().contains(u));
	}
	@Test
	public void filmdbRemoveTest() {
		FilmDB fdb = new FilmDB();
		Film f = new Film();
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		fdb.movieRemove(f);
		Assert.assertFalse("FILMDB REMOVE FAIL", fdb.getFilms().contains(f));
	}
	@Test
	public void userdbRemoveTest() {
		UserDB udb = new UserDB();
		User u = new User();
		udb.userAdd(u.getUserName(), u.getPassword(), u.getBirthYear(), u.getNumOfMovies(), u.getMoviesSeen());
		udb.userRemove(u);
		Assert.assertFalse("USERDB REMOVE FAIL", udb.getUsers().contains(u));
	}
	@Test
	public void filmdbEditTest() {
		FilmDB fdb = new FilmDB();
		Film f = new Film();
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		fdb.movieEdit(f, "title test", 2023, 30, 5, 0, "category test", "description test");
		Film testFilm = new Film("title test", 2023, 30, 5, 0, "category test", "description test");
		Assert.assertEquals("FILMDB EDIT FAIL", testFilm.getTitle(), f.getTitle());
	}
	@Test
	public void userdbEditTest() {
		UserDB udb = new UserDB();
		User u = new User();
		udb.userAdd(u.getUserName(), u.getPassword(), u.getBirthYear(), u.getNumOfMovies(), u.getMoviesSeen());
		udb.userEdit(u, "username test", "password test", 2023, 0, "seen movies test");
		User testUser = new User("username test", "password test", 2023, 0, "seen movies test");
		Assert.assertEquals("USERDB EDIT FAIL", testUser.getUserName(), u.getUserName());
	}
	@Test
	public void filmdbOrderTest1() {
		FilmDB fdb = new FilmDB();
		Film f = new Film("title test", 2023, 30, 5, 1, "category test", "description test");
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		Assert.assertTrue("FILMDB ORDER FAIL 1", fdb.orderValid(f));
	}	
	@Test
	public void filmdbOrderTest2() {
		FilmDB fdb = new FilmDB();
		Film f = new Film("title test", 2023, 30, 5, 0, "category test", "description test");
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		Assert.assertFalse("FILMDB ORDER FAIL 2", fdb.orderValid(f));
	}
	@Test
	public void filmdbOrderTest3() {
		FilmDB fdb = new FilmDB();
		Film f = new Film("title test", 2023, 30, 5, 1, "category test", "description test");
		fdb.movieAdd(f.getTitle(), f.getReleaseYear(), f.getDuration(), f.getRating(), f.getDVDs(), f.getCategory(), f.getDescription());
		int dvdNum = f.getDVDs();
		fdb.order(f);
		Assert.assertEquals("FILMDB ORDER FAIL NOT SUBTRACTING", dvdNum-1, f.getDVDs());
	}	
	@Test
	public void userdbLoginTest1() {
		UserDB udb = new UserDB();
		User u = new User("tesztelek", "pass", 2023, 1,"film");
		udb.userAdd(u.getUserName(), u.getPassword(), u.getBirthYear(), u.getNumOfMovies(), u.getMoviesSeen());
		Assert.assertTrue("USERDB LOGIN FAIL 1", udb.loginSuccess("tesztelek", "pass"));
	}
	@Test
	public void userdbLoginTest2() {
		UserDB udb = new UserDB();
		User u = new User("tesztelek", "pass", 2023, 1,"film");
		udb.userAdd(u.getUserName(), u.getPassword(), u.getBirthYear(), u.getNumOfMovies(), u.getMoviesSeen());
		Assert.assertFalse("USERDB LOGIN FAIL 2", udb.loginSuccess("ajjajj", "rossz lesz"));
	}
}