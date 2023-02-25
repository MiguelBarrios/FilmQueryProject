package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Film {
	private int id;

	private String title;

	private String rating;

	private Integer releaseYear;

	private int languageId;

	private String language;

	private String category;

	private String description;

	private Integer length;

	private List<Actor> cast = null;

	private Set<String> specialFeatures;

	private int rentalDuration;

	private double rental_rate;

	private double replacementCost;

	public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDuration,
			double rental_rate, int length, double replacementCost, String rating, Set<String> specialFeatures,
			String language) {
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
		this.language = language;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Film() {
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void basicDisplay() {
		System.out.printf("%s(%s) %s Language: %s\n", title, rating, releaseYear, language);
		System.out.println("Description: " + description);
		System.out.printf("Cast: %s\n\n", cast);
	}

	public void detailedView() {
		System.out.println("\n------ Detailed View -------");
		String res = title + "(" + rating + ")" + "id=" + id + " releaseYear=" + releaseYear + ", languageId="
				+ languageId + ", language=" + language + ", category=" + category + ", length=" + length
				+ "\ndescription:" + description + "\ncast: " + cast + "\nspecialFeatures: " + specialFeatures
				+ "\nRental Details: " + "rentalDuration=" + rentalDuration + ", rental_rate=" + rental_rate
				+ ", replacementCost=" + replacementCost + "\n" + "----------------------------";

		System.out.println(res);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", rating=" + rating + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", language=" + language + ", category=" + category + ", description="
				+ description + ", length=" + length + ", cast=" + cast + ", specialFeatures=" + specialFeatures
				+ ", rentalDuration=" + rentalDuration + ", rental_rate=" + rental_rate + ", replacementCost="
				+ replacementCost + "]";
	}

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private int releaseYear;
        private int languageId;
        private int rentalDuration;
        private double rentalRate;
        private int length;
        private double replacementCost;
        private String rating;
        private String language;
        private Set<String> specialFeatures;
        private List<Actor> cast;
        private String category;

        public Builder() {}
        
        public static Builder newInstance() {
        	return new Builder();
        }
        
        

        public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setReleaseYear(int releaseYear) {
			this.releaseYear = releaseYear;
			return this;
		}

		public Builder setLanguageId(int languageId) {
			this.languageId = languageId;
			return this;
		}

		public Builder setRentalDuration(int rentalDuration) {
			this.rentalDuration = rentalDuration;
			return this;
		}

		public Builder setRentalRate(double rentalRate) {
			this.rentalRate = rentalRate;
			return this;
		}

		public Builder setLength(int length) {
			this.length = length;
			return this;
		}

		public Builder setReplacementCost(double replacementCost) {
			this.replacementCost = replacementCost;
			return this;
		}

		public Builder setRating(String rating) {
			this.rating = rating;
			return this;
		}

		public Builder setLanguage(String language) {
			this.language = language;
			return this;
		}

		public Builder setSpecialFeatures(Set<String> specialFeatures) {
            this.specialFeatures = specialFeatures;
            return this;
        }

        public Builder setCast(List<Actor> cast) {
            this.cast = cast;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Film build() {
            Film film = new Film();
            film.setId(id);
            film.setTitle(title);
            film.setDescription(description);
            film.setReleaseYear(releaseYear);
            film.setLanguageId(languageId);
            film.setRentalDuration(rentalDuration);
            film.setRental_rate(rentalRate);
            film.setLength(length);
            film.setReplacementCost(replacementCost);
            film.setRating(rating);
            film.setLanguage(language);
            film.setSpecialFeatures(specialFeatures);
            film.setCast(cast);
            film.setCategory(category);
            return film;
        }
    }
}
