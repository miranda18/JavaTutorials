package practice1;

import java.text.DecimalFormat;
import java.io.*;


/**
 * Represents a movie title in an inventory 
 * Stores the SKU, quantity, price, and title 
 *
 */
public class Movie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9072652297337901693L;
	private int SKU;                  // store's stock keeping unit number
    private String title;             // movie title
    private double price;             // selling price of the movie
    private int quantity;             // number of copies in inventory

	/**
	 * Constructs Movie from parameters.
	 * @param SKU the stock keeping unit of the movie
	 * @param quantity the numbe of copies in stock
	 * @param price  the selling price of the movie
	 * @param title the title of the movie
	 */
	public Movie(int SKU, String title, double price, int quantity) {
	
		this.SKU = SKU;
        this.title = title;
        this.price = price;
		this.quantity = quantity;
	}

	/**
     * Gets the SKU value.
	 * @return the SKU
	 */
	public int getSKU() {
		return SKU;
	}

	@Override
	public String toString() {
		
		DecimalFormat dfmoney = new DecimalFormat("$0.00");
        return String.format("%5s %5d %8s  %-20s",
                                 SKU, quantity, dfmoney.format(price),title);
	}
	
    /**
     * Constructs a string containing the data values and their labels.
     * @return a string with all of the field values labelled.
     */
	public String labeledString () {
		DecimalFormat dfmoney = new DecimalFormat("$0.00");
		return "SKU=" + SKU + ",\n" +
        "title=" + title + ",\n" +
        "price=" + dfmoney.format(price) + ",\n" +
        "quantity=" + quantity + "\n" ;
	}

}
