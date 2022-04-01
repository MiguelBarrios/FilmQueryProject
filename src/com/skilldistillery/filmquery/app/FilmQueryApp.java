package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		System.out.println();

		Actor actor = db.findActorById(2);
		System.out.println(actor);
		System.out.println();

		List<Actor> actors = db.findActorsByFilmId(3);
		System.out.println(actors);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		String userInput = "";
		do {
			displayMainMenu();
			userInput = input.nextLine();
			if (userInput.equals("1")) {
				searchForFilmbyId(input);
			} else if (userInput.equals("2")) {
				searchForFilmByKeyword(input);
			} else if (userInput.equals("3")) {
				System.out.println("Goodbye");
				return;
			} else {
				System.out.println("Invalid input");
			}

		} while (!userInput.equals("3"));
	}

	private void displayMainMenu() {
		System.out.println("Main Menu");
		System.out.println("1) Look up film by film id");
		System.out.println("2) Search for films by keyword");
		System.out.println("3) Exit");
		System.out.print("Selection: ");

	}

	private void searchForFilmbyId(Scanner input) {
		System.out.println("\n## Lookup Film by Id ##");
		System.out.print("Enter film id: ");

		int id = 0;
		try {
			id = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input");
			return;
		}
		
		Film film = db.findFilmById(id);
		
		if(film == null) {
			System.out.println("Film with id: " + id + " not found\n");
		}else {
			film.basicDisplay();
		}
	}

	private void searchForFilmByKeyword(Scanner input) {

	}

}
