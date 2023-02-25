package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	private String user = "root";

	private String pass = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found");
			throw new RuntimeException("Unable to load MySQL driver class");
		}
	}

	public PreparedStatement prepareStatement(Connection conn, String query, int param1) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, param1);
		return stmt;
	}
	

	public Film extractFilm(ResultSet rs, boolean detailed) throws SQLException {
		
		Film film =  Film.Builder.newInstance()
				.setId(rs.getInt("id"))
				.setTitle(rs.getString("title"))
				.setDescription(rs.getString("description"))
				.setReleaseYear(rs.getInt("release_year"))
				.setLanguageId(rs.getInt("language_id"))
				.setRentalDuration(rs.getInt("rental_duration"))
				.setRentalRate(rs.getDouble("rental_rate"))
				.setLength(rs.getInt("length"))
				.setReplacementCost(rs.getDouble("replacement_cost"))
				.setRating(rs.getString("rating"))
				.setLanguage(rs.getString("name"))
				.setSpecialFeatures(getFilmFeatures(rs))
				.setCast(findActorsByFilmId(rs.getInt("id")))
				.build();	
		
		if(detailed) {
			film.setCategory(rs.getString(17));
		}
		return film;
	}
	
	public Set<String> getFilmFeatures(ResultSet rs) throws SQLException{
		String[] featuresArr = rs.getString("special_features").split(",");
		return new HashSet<>(Arrays.asList(featuresArr));
	}

	@Override
	public Film findFilmById(int filmId) {
		
		String query = "SELECT * FROM film\n"
				+ "JOIN language on film.language_id = language.id \n"
				+ "JOIN film_category on film.id = film_category.film_id\n"
				+ "JOIN category on category_id = category.id \n"
				+ "where film.id = ?";
		
		Film film = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			PreparedStatement stmt = prepareStatement(conn, query, filmId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				film = extractFilm(rs, true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public Actor extractActor(ResultSet rs) throws SQLException {
		
		return Actor.Builder.newInstance()
				.setId(rs.getInt("id"))
				.setFirstName(rs.getString("first_name"))
				.setLastName(rs.getString("last_name"))
				.build();
	}

	@Override
	public Actor findActorById(int actorId) {
		String query = "SELECT * FROM actor WHERE id = ?";
		
		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			PreparedStatement stmt = prepareStatement(conn, query, actorId);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
				actor = extractActor(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actor;
	}
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		String query = "SELECT actor.* FROM film_actor\n"
				+ "JOIN actor on film_actor.actor_id = actor.id\n" 
				+ "WHERE film_id = ?";

		List<Actor> actors = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepareStatement(conn, query, filmId);
				ResultSet rs = stmt.executeQuery();) {
			
			while (rs.next()) {
				Actor actor = extractActor(rs);
				actors.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actors;
	}

	public PreparedStatement prepareStatement(Connection conn, String query, String param) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, "%" + param + "%");
		stmt.setString(2, "%" + param + "%");
		return stmt;
	}
	
	@Override
	public List<Film> findFilmsByKeyWord(String searchKeyWord){
		String query = "SELECT * from film\n"
				+ "JOIN language on film.language_id = language.id\n"
				+ "WHERE title like ? OR\n"
				+ "description like ?";
		
		List<Film> films = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = prepareStatement(conn, query,searchKeyWord);
				ResultSet rs = stmt.executeQuery();
			){

		
			if(rs.isBeforeFirst()) {	
				
				while(rs.next()) {
					Film film = extractFilm(rs, false);
					films.add(film);				
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return films;
	}
	
}
