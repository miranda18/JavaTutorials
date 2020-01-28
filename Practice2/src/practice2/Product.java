package practice2;

import java.io.Serializable;
import java.text.DecimalFormat; 

/**
 * Represents a product in an inventory 
 * Stores the SKU, quantity, price, and title
 * @author Miranda Sanchez
 *
 */
public abstract class Product implements Serializable, Comparable<Product>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1647017448188342753L;
	private int SKU; 		// store's SKU number 
	private String title; 	// product title
	private double price; 	// selling price of the product
	private int quantity; 	// number of copies in inventory 
	
	
	/**
	 * Constructs Product from parameters 
	 * @param SKU - the stock keeping unit of the item 
	 * @param title - the title of the item 
	 * @param price - the selling price of the item 
	 * @param quantity - the number of copies in stock 
	 */
	public Product(int SKU, String title, double price, int quantity){
		this.SKU = SKU; 
		this.title = title; 
		this.price = price; 
		this.quantity = quantity; 
	}
	
	
	/**
	 * Gets the SKU value
 	 * @return the SKU of the item 
	 */
	public int getSKU(){
		return SKU; 
	}
	
	
	/**
	 * Gets the title value
	 * @return
	 */
	public String getTitle(){
		return title; 
	}
	
	/**
	 * Ensures that price is a positive value, and quantity is not negative
	 * @return true if price is positive and quantity is not negative 
	 */
	public boolean isValid(){
		return price > 0 && quantity >= 0;
	}
	
	/**
	 * Constructs a string containing all the common data values on one line, 
	 * suitable for display as row in a table 
	 * @return a string with the common data values 
	 */
	public String showProductInfo(){
		DecimalFormat dfMoney = new DecimalFormat("$0.00");
		return String.format("%5s %5d %8s  %-20s", SKU, quantity, dfMoney.format(price), title);
	}

	/**
	 * Constructs a string containing all the data values and their labels
	 * (one per line) 
	 * @return a string with all the field values labelled
	 */
	public String labeledString(){
		DecimalFormat dfMoney = new DecimalFormat("$0.00");
		return "SKU: " + SKU + "\nTitle: " + title + "\nPrice: " 
				+ dfMoney.format(price) +  "\nQuantity: " + quantity; 
	}
	
	/**
	 * Returns the amount of credit (in dollars/cents) given to the selled for this product
	 * @return the shipping credit for this product
	 */
	public abstract double shippingCredit();
	
	/**
	 * Returns the fraction of the commission charged to the seller as a percentage
	 * @return the commission as a percentage
	 */
	public abstract double commissionPercentage();
	
	
	/**
	 * Returns the commission as a fraction of the price (not a percentage)
	 * @return the amount of commission charged to the seller for this product
	 */
	public double commission(){
		return commissionPercentage()/100*price; 
	}
	
	/**
	 * Outputs a report based on the sale of some quantity of this product
	 * Accents quantity sold and cost of shipping from the called
	 * Decrements quantity by quantity sold, if quantity is large enough 
	 * (otherwise aborts with error message)
	 * @param quantitySold - quantity of this product that was sold
	 * @param shippingCost - total cost of shipping in items
	 */
	public void processSale(int quantitySold, double shippingCost){
		// make sure there is enough items in stock 
		if(quantity < quantitySold){
			System.out.println("Not able to fulfill order. There is only " + quantity + " in stock");
		}
		else{
			// decrement the quantity
			quantity = quantity - quantitySold;
			
			// calculate shipping credit, commission, and profit 
			double shippingCreditSale = shippingCredit() * quantitySold;
			double commissionSale = commission() * quantitySold;
			double priceSale = price * quantitySold; 
			double profit = priceSale + shippingCreditSale - commissionSale - shippingCost; 
			
			// output report 
			DecimalFormat dfMoney = new DecimalFormat("$0.00");
			System.out.format("Total Price:          %8s%n"
							 , dfMoney.format(priceSale));
			
			System.out.format("Total shipping credit:          %8s%n"
					 , dfMoney.format(shippingCreditSale));
			
			System.out.format("Total commission:          %8s%n"
					 , dfMoney.format(commissionSale));
			
			System.out.format("Profit:          %8s%n"
					 , dfMoney.format(profit));
			System.out.println();
			System.out.println();
		}
	}
	
	/**
	 * Compares this Product with the specified Product for order. Uses the SKUs
	 * to determine order. Used for sorting 
	 * 
	 * @param arg0 - the Product to be compared
	 * @returns a negative integer, zero, or a positive integer as this product
	 * is less than, equal to, or greated than the specifed Product.
	 */
	@Override
	public int compareTo(Product arg0){
		Integer n = new Integer(SKU);
		return n.compareTo(arg0.SKU); 
	}
	
}
