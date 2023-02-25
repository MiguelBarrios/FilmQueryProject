package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor dao;
	Scanner scanner;
	
	{
		dao = new DatabaseAccessorObject();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
		app.scanner.close();
	}


	private void launch() {
		startUserInterface();
	}

	private void startUserInterface() {
		String input = "";
		do {
			displayMainMenu();
			input = scanner.nextLine();
			switch (input) {
				case "1":
					searchForFilmbyId();
					break;
				case "2":
					searchForFilmByKeyword();
					break;
				case "3":
					System.out.println("Goodbye");
					return;
				default:
					System.out.println("Invalid input");
			}

		} while (!input.equals("3"));
	}

	private void displayMainMenu() {
		System.out.println("---------- Main Menu ----------");
		System.out.println("1) Look up film by film id");
		System.out.println("2) Search for films by keyword");
		System.out.println("3) Exit");
		System.out.print("Selection: ");
	}

	private void searchForFilmbyId() {

		System.out.println("\n------ Search Film by Id ------\nEnter film id: ");

		String userInput = "";
		int id = 0;
		try {
			id = scanner.nextInt();
			Film film = dao.findFilmById(id);

			if (film == null) {
				System.out.println("Film with id: " + id + " not found\n");
				return;
			}

			film.basicDisplay();
			System.out.println("--------- Film menu -----------");
			System.out.println("1) View film details");
			System.out.println("Or press any key to return to main menu");
			System.out.print("Selection: ");
			userInput = scanner.nextLine();
			if (userInput.equals("1")) {
				film.detailedView();
			}
		} catch (InputMismatchException e) {
			System.out.println("No films with id: " + userInput + " found");
		}



	}

	private void searchForFilmByKeyword() {
		System.out.println("\n---- Search for Film By Keyword ----");
		System.out.print("Enter keyword: ");
		String keyword = scanner.nextLine();
		List<Film> films = dao.findFilmsByKeyWord(keyword);
		if (films == null) {
			System.out.println("No films with keyword \"" + keyword + "\" found");
		} else {
			System.out.println("------ Films containing: " + keyword + " ------");
			films.forEach(film -> film.basicDisplay());
		}

	}

}
