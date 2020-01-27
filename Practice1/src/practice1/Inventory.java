package practice1;

import java.util.*;
import java.io.*;

/**
 * Represents the inventory of the video store 
 * It provides methods to add a movie to the inventory, remove a movie from the inventory
 * (with SKU provided by the user), find and display a movie (given a SKU by the user),
 * and display all the movies in the inventory 
 *
 */
public class Inventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -139974605083857918L;
	private ArrayList<Movie> inventory = new ArrayList<Movie>();  // inventory

	/**
	 * Default constructor, creates an empty inventory
	 */
	public Inventory() {
	}

	/**
	 * Given the data for a single movie title, validates the values and
	 * adds a new movie to the end of the inventory.
     * @param SKU the unique SKU number of the movie.
     * @param quantity the number of copies in the inventory
     * @param price the selling price of the movie
     * @param title the title of the movie
	 */
	public void addMovie(int SKU, int quantity, double price, String title) {

        //check for uniqueness of product
		Movie movie = searchList(SKU);
        
		// if found, output error message
		if (movie!=null) {
			System.out.println("Unable to add, the SKU is not unique.");
        }
        
        // check for valid price and quantity
        else if (price <=0 || quantity < 0) {
            System.out.println(
                "Unable to add, price is not greater than 0" +
                               "or quantity is less than 0");
        }
        
        // if valid, add the movie
        else {
            movie = new Movie(SKU, title, price, quantity);
            inventory.add(movie);
        }
	}

	/**
	 * Displays the inventory on the screen as a table
	 */
	public void showInventory() {

		System.out.println();
		for (Movie m : inventory) {
			System.out.println(m);    //calls m.toString()
		}
		System.out.println();
	}

	/**
	 * Given a SKU, displays info for that movie from
	 * the inventory.  If not found, displays an error message.
     * @param SKU the SKU of the movie to be displayed.
	 */
	public void findMovie(int SKU) {

		// search the inventory for the SKU
		Movie movie = searchList(SKU);
		if (movie==null) {
			// not found, display message
			System.out.println();
			System.out.println("No movie found with this SKU. \n\n");
		} else {
			// found, display the movie
			System.out.println();
			System.out.println(movie.labeledString());
			System.out.println();
		}
	}

	/**
	 * Removes a movie from the inventory based on the given SKU.
	 * If no movie with that SKU is in the inventory, displays error message.
     * @param SKU the SKU of the movie to be removed
	 */
	public void removeMovie(int SKU) {

		// find the SKU in the array
		Movie movie = searchList(SKU);

		// if found, remove it, else output message
		if (movie == null) {
			System.out.println("No movie found with this SKU. \n\n");
		} else {
			inventory.remove(movie);
			System.out.println( "Removed movie with SKU: " + SKU );
			System.out.println();
		}
	}

	/**
	 * Uses linear search to find a certain movie in the list.
	 * @param value the target value to search for
	 * @return the Movie with SKU==value in the inventory, or null if not found
	 */
	private Movie searchList ( int value) {
		// iterate over the list until found
        for (Movie m : inventory) {
            if (m.getSKU() == value)
                return m;
        }
		return null;
	}

}
