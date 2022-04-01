package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Film {
	private int id;

	private String title;

	private String description;

	private Integer releaseYear;

	private int languageId;

	private int rentalDuration;

	private double rental_rate;

	private Integer length;

	private double replacementCost;

	private String rating;

	private Set<String> specialFeatures;
	
	private List<Actor> cast = null;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return id == other.id;
	}

	public Film() {
		
	}

	public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDuration,
			double rental_rate, int length, double replacementCost, String rating, Set<String> specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRental_rate() {
		return rental_rate;
	}

	public void setRental_rate(double rental_rate) {
		this.rental_rate = rental_rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Set<String> getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(Set<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	
	@Override
	public String toString() {
		String header = String.format("%s\n", title);
		String filmInfo = String.format("\nRated: %s, Runtime %d minutes, releaseYear: %d, bonusFeatures: %s\n"
				,rating, length, releaseYear, specialFeatures);
		String cast = "Cast: " + this.cast + "\n";
		String rentalInfo = String.format("RentalInfo: id %d, languageId %d, rental Rate: %.2f, replacment cost: %.2f, rental duration: %d"
				, id, languageId, rental_rate, replacementCost, rentalDuration); 
		return header + description + filmInfo + cast + rentalInfo;
//		 rental_rate,replacementCost, rentalDuration );
	}
	
	public List<Actor> getCast() {
		return cast;
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public void basicDisplay() {
		System.out.printf("%s(%s) %s\n%s\n\n", title, rating,releaseYear , description);
	}

}
