package client;

import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String args[]) {
		Connection conn = null;
		boolean done = false;
		String host = "cslab-db.cs.wichita.edu";
		int port = 3306;
		String database = "dbuser1_database";
		String user = "dbuser1";
		String password = "***###*****###";

		String url = String.format(
			"jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
			host,
			port,
			database,
			user,
			password
		);

		do {
			try {
				conn = DriverManager.getConnection(url);
				System.out.println("Connected Successfully");
			} catch (SQLException e) {
				e.printStackTrace();
				break; // break out early to avoid infinite loops
			}

			try {
				runMainMenu(conn);
				done = true;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} while (!done);
	}

	static private void runMainMenu(Connection conn) throws SQLException {
		boolean done = false;
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("\nGame Library Main Menu");
			System.out.println("  [1] Display players");
			System.out.println("  [2] Display games");
			System.out.println("  [3] Display reviews for a game");
			System.out.println("  [4] Add a review for a game");
			System.out.println("  [5] Edit a review for a game");
			System.out.println("  [6] Delete a review for a game");
			System.out.println("  [7] Display the number of reviews for a game");
			System.out.println("  [8] Display the average rating for a game");
			System.out.println("  [9] Quit");

			int option = 0;
			try {
				System.out.print("\nEnter your selection: ");
				option = input.nextInt();

				switch (option) {
				case 1:
					runDisplayPlayersMenu(conn);
					break;

				case 2:
					runDisplayGamesMenu(conn);
					break;

				case 3:
					runDisplayReviewsMenu(conn, input);
					break;

				case 4:
					runAddReviewMenu(conn, input);
					break;

				case 5:
					runEditReviewMenu(conn, input);
					break;

				case 6:
					runDeleteReviewMenu(conn, input);
					break;

				case 7:
					runDisplayReviewCountMenu(conn, input);
					break;

				case 8:
					runDisplayAverageReviewRatingMenu(conn, input);
					break;

				case 9:
					System.out.println("\nQuitting...");
					done = true;
					break;

				default:
					System.err.println("Invalid selection - out of range");
				}
			} catch (InputMismatchException e) {
				System.err.println("Invalid selection - please enter an integer");
			}
		} while (!done);
	}

	static private void runDisplayPlayersMenu(Connection conn) throws SQLException {
		System.out.println("\nDisplay Players");

		Statement stmt = conn.createStatement();

		String query = "select * from Player";

		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String name = rs.getString("playerName");
			String email = rs.getString("email");
			int playerId = rs.getInt("playerId");
			
			System.out.format("%d %s %s\n", playerId, name, email);
		}

		rs.close();
	}

	static private void runDisplayGamesMenu(Connection conn) throws SQLException {
		System.out.println("\nDisplay Games\n");

		Statement stmt = conn.createStatement();

		String query = "select * from Game";

		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String title = rs.getString("title");
			int gameId = rs.getInt("gameId");
			int yearReleased = rs.getInt("yearReleased");
			String genre = rs.getString("genre");

			System.out.format("%s %d %d %s\n", title, gameId, yearReleased, genre);
		}

		rs.close();
	}

	static private int promptForId(Scanner input, String prompt) {
		boolean done = false;
		int option = 0;

		do {
			try {
				System.out.format("%s", prompt);
				option = input.nextInt();
				input.nextLine();
				done = true;
			} catch (InputMismatchException e) {
				System.err.println("Invalid ID - please enter an integer");
			}
		} while (!done);

		return option;
	}

	static private int promptForReviewId(Scanner input) {
		return promptForId(input, "Enter the Review ID: ");
	}

	static private int promptForGameId(Scanner input) {
		return promptForId(input, "Enter the Game ID: ");
	}

	static private int promptForPlayerId(Scanner input) {
		return promptForId(input, "Enter the Player ID: ");
	}

	static private float promptForRating(Scanner input) {
		boolean done = false;
		float rating = 0.f;

		do {
			try {
				System.out.format("Enter the Rating: ");
				rating = input.nextFloat();
				input.nextLine();
				done = true;
			} catch (InputMismatchException e) {
				System.err.println("Invalid Rating - please enter a float");
			}
		} while (!done);

		return rating;
	}

	static private boolean promptForRecommended(Scanner input) {
		boolean done = false;
		boolean recommended = false;

		do {
			try {
				System.out.format("Recommended [true/false]: ");
				recommended = input.nextBoolean();
				input.nextLine();
				done = true;
			} catch (InputMismatchException e) {
				System.err.println("Invalid Rating - please enter true or false");
			}
		} while (!done);

		return recommended;
	}

	static private String promptForReviewText(Scanner input) {
		boolean done = false;
		String reviewText = "";

		do {
			try {
				System.out.print("Review Text: ");
				reviewText = input.nextLine();
				done = true;
			} catch (InputMismatchException e) {
				System.err.println("Invalid Review Text - please enter a string");
			}
		} while (!done);

		return reviewText;
	}

	static private void runDisplayReviewsMenu(Connection conn, Scanner input) throws SQLException {
		System.out.println("\nDisplay Reviews");
		int gameId = promptForGameId(input);
		displayAllReviews(conn, gameId);
	}

	static private void runAddReviewMenu(Connection conn, Scanner input) throws SQLException {
		System.out.println("\nAdd Review");

		int playerId = promptForPlayerId(input);
		int gameId = promptForGameId(input);
		float rating = promptForRating(input);
		boolean recommended = promptForRecommended(input);
		String reviewText = promptForReviewText(input);

		addReview(conn, playerId, gameId, rating, recommended, reviewText);
	}

	static private void runEditReviewMenu(Connection conn, Scanner input) throws SQLException {
		System.out.println("\nEdit Review");

		int reviewId = promptForReviewId(input);
		float rating = promptForRating(input);
		boolean recommended = promptForRecommended(input);
		String reviewText = promptForReviewText(input);

		editReview(conn, reviewId, rating, recommended, reviewText);
	}

	static private void runDeleteReviewMenu(Connection conn, Scanner input) {
		System.out.println("\nDelete Review");
		int reviewId = promptForReviewId(input);
		deleteReview(conn, reviewId);
	}

	static private void runDisplayReviewCountMenu(Connection conn, Scanner input) {
		System.out.println("\nDisplay Review Count");
		int gameId = promptForGameId(input);

		displayReviewCount(conn, gameId);
	}

	static private void runDisplayAverageReviewRatingMenu(Connection conn, Scanner input) {
		System.out.println("\nDisplay Average Rating");
		int gameId = promptForGameId(input);

		displayAverageReviewRating(conn, gameId);
	}

	// Select all reviews for a game
	static private void displayAllReviews(Connection conn, int gameId) throws SQLException {
		Statement stmt = conn.createStatement();
		String qry = 	"select * "
				+
				"from Review "
				+
				"where Review.gameId = " + gameId;
		ResultSet rs = stmt.executeQuery(qry);

		System.out.format("%n");
		System.out.format("%-13s %-13s %-11s %-10s %-15s %-50s%n", "Review_ID", "Player_ID", "Game_ID", "Rating", "Recommended", "Review_message");

		while(rs.next())
		{
			int revId = rs.getInt("reviewId");
			int plyrId = rs.getInt("playerId");
			int reviewGameId = rs.getInt("gameId");
			float rating = rs.getFloat("rating");
			boolean recmd = rs.getBoolean("recommended");
			String revText = rs.getString("reviewText");
			System.out.format("%-13s %-13s %-11s %-10s %-15s %-50s%n", revId, plyrId, reviewGameId, rating, recmd, revText);
		}

		System.out.println();
		rs.close();
	}

	// Insert review for game
	static private void addReview(Connection conn, int playerId, int gameId, float rating, boolean recommended, String reviewText) throws SQLException {
		Statement stmt = conn.createStatement();
		String qry = 	String.format(
			"insert into Review(playerId, gameId, rating, recommended, reviewText) values(%d, %d, %f, %b, \'%s\')",
			playerId,
			gameId,
			rating,
			recommended,
			reviewText
		);
		ResultSet rs = stmt.executeQuery(qry);

		System.out.format("%n");

		System.out.println();
		rs.close();

	}

	// Update review for game
	static private void editReview(Connection conn, int reviewId, float rating, boolean recommended, String reviewText) throws SQLException {
		Statement stmt = conn.createStatement();
		String qry = 	String.format(
			"update Review set rating = %f, recommended = %b, reviewText = \'%s\' where reviewId = %d",
			rating,
			recommended,
			reviewText,
			reviewId
		);
		ResultSet rs = stmt.executeQuery(qry);

		System.out.format("%n");

		System.out.println();
		rs.close();

	}

	// Delete review for game
	static private void deleteReview(Connection conn, int reviewId) {
		try {
			Statement stmt = conn.createStatement();
			String qry = 	String.format(
				"delete from Review where reviewId = %d",
				reviewId
			);
			ResultSet rs = stmt.executeQuery(qry);

			System.out.format("%n");

			System.out.println();
			rs.close();

		} catch(Exception e) {
			System.out.println("Could not print game review average.");
			e.printStackTrace();
		}
	}

	// Count Aggregate - Display review count for game
	static private void displayReviewCount(Connection conn, int gameId) {
		try {
			Statement stmt = conn.createStatement();
			String qry = 	"select count(rating) as reviewCount "
					+
					"from Review "
					+
					"where Review.gameId = " + gameId;
			ResultSet rs = stmt.executeQuery(qry);

			while(rs.next())
			{
				int reviewCount = rs.getInt("reviewCount");
				System.out.format("Review Count: %d\n", reviewCount);
			}

			System.out.println();
			rs.close();

		} catch(Exception e) {
				System.out.println("Could not print game review count.");
				e.printStackTrace();
		}

	}

	// Average Aggregate - Display review average rating for game
	static private void displayAverageReviewRating(Connection conn, int gameId) {
		try {
			Statement stmt = conn.createStatement();
			String qry = 	"select avg(rating) as averageRating "
					+
					"from Review "
					+
					"where Review.gameId = " + gameId;
			ResultSet rs = stmt.executeQuery(qry);

			while(rs.next())
			{
				float rating = rs.getFloat("averageRating");
				System.out.format("Average Rating: %f\n", rating);
			}

			System.out.println();
			rs.close();

		} catch(Exception e) {
				System.out.println("Could not print game review average.");
				e.printStackTrace();
		}
	}
}
