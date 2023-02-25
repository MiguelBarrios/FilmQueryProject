package com.skilldistillery.filmquery.entities;

public class Actor {
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	public Actor() {
		
	}

	public Actor(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("%s %s id: %d", firstName, lastName, id);
	}
	
	public static class Builder{
		private int id;
		private String firstName;
		private String lastName;
		public Builder() {}
		
		public static Builder newInstance() {
			return new Builder();
		}

		public Builder setId(int id) {
			this.id = id;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Actor build() {
			Actor actor = new Actor();
			actor.setId(id);
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			return actor;
			
		}
		
	}
	
	
}
