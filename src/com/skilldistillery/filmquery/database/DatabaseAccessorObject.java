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

	private String user = "student";

	private String pass = "student";

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
	
	public Film extractFilm(ResultSet rs) throws SQLException {
		
		Film film = new Film();
		film.setId(rs.getInt("id"));
		film.setTitle(rs.getString("title"));
		film.setDescription(rs.getString("description"));
		film.setReleaseYear(rs.getInt("release_year"));
		film.setLanguageId(rs.getInt("language_id"));
		film.setRentalDuration(rs.getInt("rental_duration"));
		film.setRental_rate(rs.getDouble("rental_rate"));
		film.setLength(rs.getInt("length"));
		film.setReplacementCost(rs.getDouble("replacement_cost"));
		film.setRating(rs.getString("rating"));
		film.setLanguage(rs.getString("name"));
		film.setCategory(rs.getString(17));

		String[] featuresArr = rs.getString("special_features").split(",");
		Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
		film.setSpecialFeatures(featuresSet);

		List<Actor> cast = findActorsByFilmId(film.getId());
		film.setCast(cast);
		return film;
	}

	@Override
	public Film findFilmById(int filmId) {
//		String query = "SELECT * FROM film\n"
//				+ "JOIN language on film.language_id = language.id\n"
//				+ "WHERE film.id = ?";
		
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
				film = extractFilm(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public Actor extractActor(ResultSet rs) throws SQLException {
		Actor actor = new Actor();
		actor.setId(rs.getInt("id"));
		actor.setFirstName(rs.getString("first_name"));
		actor.setLastName(rs.getString("last_name"));
		return actor;
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

		String query = "SELECT actor.id, actor.first_name, actor.last_name FROM film_actor\n"
				+ "JOIN actor on film_actor.actor_id = actor.id\n" + "WHERE film_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			PreparedStatement stmt = prepareStatement(conn, query, filmId);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst())
				return new ArrayList<>();

			List<Actor> actors = new ArrayList<>();
			while (rs.next()) {
				Actor actor = extractActor(rs);
				actors.add(actor);
			}
			return actors;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
		
		try(Connection conn = DriverManager.getConnection(URL, user, pass)){
			PreparedStatement stmt = prepareStatement(conn, query,searchKeyWord);
			ResultSet rs = stmt.executeQuery();
						
			// no results
			if (!rs.isBeforeFirst())
				return null;
			
			List<Film> films = new ArrayList<>();

			while(rs.next()) {
				Film film = extractFilm(rs);
				films.add(film);				
			}
			return films;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
