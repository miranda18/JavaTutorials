package practice2;

import java.io.Serializable; 
import java.text.DecimalFormat; 
import java.util.*;

/**
 * Represents the inventory of the online store. It provides methods to add a 
 * product to the inventory, remove a product from the inventory (given a SKU
 * from the user), find and display a product (given a SKU from the user), 
 * display all the products in the inventory, and process a sale. 
 * @author Miranda Sanchez
 *
 */
public class Inventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1760345639708402556L;
	// List of products 
	private ArrayList<Product> inventory = new ArrayList<Product>();

	// Default constructor, creates an empty inventory 
	public Inventory(){}
	
	/**
	 * Given a product instance, validates the values and 
	 * adds a new product to the end of the inventory. 
	 * @param product - the item to validate and add to the inventory 
	 */
	public void addProduct(Product product){
		
		// check for uniquness of product 
		Product item = searchList(product.getSKU());
		
		// if SKU is found, output an error message 
		if(item!=null){
			System.out.println("Unable to add, the SKU is not unique.");
		}
		// checks if price and quantity is valid 
		else if(!product.isValid()){
			System.out.println("Unable to add, price is not greater than 0 "+
								"or quantity is less than 0.");
		}
		// if valid, add the product to the inventory
		else{
			inventory.add(product);
		}
	}
	/**
	 * Displays the inventory on the screen as a table, sorted by SKU
	 */
	public void showInventoryBySKU(){
		Collections.sort(inventory);
		System.out.println();
		for(Product p : inventory){
			System.out.println(p.showProductInfo());
		}
		System.out.println();
	}
	
	/**
	 * Displays the inventory on the screen as a table, sorted by title 
	 */
	public void showInventoryByTitle(){
		Collections.sort(inventory, new ProductByTitle());
		System.out.println();
		for (Product p : inventory){
			System.out.println(p.showProductInfo());
		}
		System.out.println();
	}
	
	/**
	 * Given a SKU, displays complete infor for that product from the inventory.
	 * If not found, displays an error message
	 * @param SKU - the SKU of the product to be displayed 
	 */
	public void findProduct(int SKU){
		// Search the inventory for the SKU
		Product item = searchList(SKU);
		if (item == null){
			// not found, display message
			System.out.println();
			System.out.println("No product found with this SKU. \n\n");
		}
		else{
			// found, display the product
			System.out.println();
			System.out.println(item.labeledString());
			System.out.println();
		}
	}
	
	/**
	 * removes a movie from the inventory based on the given SKU
	 * if no product with that SKU is in the inventory, displays error message
	 * @param SKU - SKU of the product to be removed
	 */
	public void removeProduct(int SKU){
		// find the SKU in the array 
		Product product = searchList(SKU); 
		
		// if found, remove it, else output message
		if(product == null){
			System.out.println("No product found with this SKU, \n\n");
		}
		else{
			inventory.remove(product);
			System.out.println("Removed product with SKU:" + SKU);
			System.out.println();
		}
	}
	
	/**
	 * Uses linear search to find a product with a certain SKU. 
	 * @param value - the target SKU of the product to search for 
	 * @return - the Product with SKU == value in the inventory or null if not found
	 */
	private Product searchList(int value){
		// iterate over the list until found 
		Iterator<Product> it = inventory.listIterator();
		while(it.hasNext()){
			Product prod = it.next();
			if (prod.getSKU() == value){
				return prod;
			}
		}
		return null; 
	}
	
	/**
	 * Generates a simple report based on the sale of some quantity of product
	 * Acceps SKU, quantity sold, and cost of shipping from the caller
	 * Checks if the product in the inventory and decrements quantity by 
	 * quantity sold, if quantity is large enough (otherwise aborts with error
	 * message) 
	 * Uses values from the product object and input values to compute various
	 * statistics, including profit
	 * @param SKU - SKU of sold product 
	 * @param quantitySold - quantity of this product that was sold 
	 * @param shippingCost - total cost of shipping the items 
	 */
	public void processSale(int SKU, int quantitySold, double shippingCost){
		
		// make sure SKU is in inventory 
		Product item = searchList(SKU);
		if (item == null){
			System.out.println("No product found with this SKU. \n\n");
		}
		else{
			item.processSale(quantitySold, shippingCost);
		}
	}
	
}
