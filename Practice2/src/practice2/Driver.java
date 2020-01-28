package practice2;

import java.io.*;
import java.util.*;


/**
 * Driver for the book/toy/video store inventory program 
 * Inputs stored inventory from a file, offers users menu of 7 options 
 * (add product, remove product, find product, display inventory sorted by SKU
 * or by title, process sale and quit) 
 * When the user selects quit, the inventory is saved to a file 
 * @author Miranda Sanchez
 *
 */
public class Driver {

	// the product inventory, initiated in intializeInv
	static Inventory inv;
	
	public static void main(String[] args){
		
		// try to load from file 
		initializeInventory();
		
		int val; // menu choice 
		int SKU; // SKU of product to manipulate 
		
		Scanner sc = new Scanner(System.in); // to handle input
		
		do{
			// Display the menu and get a choice 
			System.out.println("Online Store Inventory Menu");
			System.out.println();
			System.out.println("1. Add Product");
			System.out.println("2. Remove Product");
			System.out.println("3. Find product by SKU");
			System.out.println("4. Display inventory sorted by SKU");
			System.out.println("5. Display inventory sorted by title");
			System.out.println("6. Process a sale");
			System.out.println("7. Quit the program");
			System.out.println();
			System.out.println("Enter your choice: ");
			
			val = sc.nextInt();
			
			switch(val){
				case 1: 
					addProduct(sc);
					break;
				case 2: 
					// input SKU from the user to remove from the inventory
					System.out.println("Enter SKU of product to remove: ");
					SKU = sc.nextInt();
					
					inv.removeProduct(SKU);
					break;
				case 3:
					// input SKU from user to find it in the inventory
					System.out.println("Enter SKU of product to find: ");
					SKU = sc.nextInt();
					
					inv.findProduct(SKU);
					break;
				case 4: 
					inv.showInventoryBySKU();
					break;
				case 5: 
					inv.showInventoryByTitle();
					break;
				case 6: 
					processSale(sc);
					break;
				case 7: 
					System.out.println("Exit selected");
					break;
				default: 
					System.out.println("Invalid selection");
					break; 
			}
		}while( val != 7);
		sc.close();
		
		// save the inventory to file system 
		serializeInventory(); 
	}
	
	// Gets input from user and adds proper product type to inventory 
	private static void addProduct(Scanner sc){
		int SKU; 
		String title; 
		double price; 
		int quantity; 
		char productType;  // User's choice of type to input 
		
		// Determing which kind of object to input 
		System.out.println("Enter the product type.");
		System.out.println("Enter M for movie, B for Book, or T for Toy: ");
		
		productType = sc.next().charAt(0);
		
		if (productType == 'M' || productType == 'm' || productType == 'B' || productType == 'b' || productType == 'T' || productType == 't'){
			// get the common infor from the user
			System.out.println("Enter the SKU: ");
			SKU = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the title: ");
			title = sc.nextLine();
			System.out.println("Enter the price: ");
			price = sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter the quantity: ");
			quantity = sc.nextInt();
			sc.nextLine();
			
			// get product-specific information
			Product product = null; 
			switch(productType){
			// movie
				case 'M':
				case 'm':
					System.out.println("Enter the UPC: ");
					String UPC = sc.nextLine();
					product = new Movie(SKU, title, price, quantity, UPC);
					break; 
			// book
				case 'B':
				case 'b':
					System.out.println("Enter the ISBN: ");
					String ISBN = sc.nextLine();
					System.out.println("Enter the author: ");
					String author = sc.nextLine();
					product = new Book(SKU, title, price, quantity, ISBN, author);
					break; 
			// toy 
				case 'T':
				case 't':
					System.out.println("Enter the weight in ounces: ");
					int weight = sc.nextInt();
					product = new Toy(SKU, title, price, quantity, weight);
					break;	
			}
			System.out.println();
			
			inv.addProduct(product);
		}
		else{
			System.out.println("Invalid selection");
		}
	}
	
	// inputs information to process the sale, inventory processes it 
	private static void processSale(Scanner sc){
		int SKU;
		
		// input SKU, quantity, and cost to ship 
		System.out.println("Enter SKU of sold items: ");
		SKU = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter quantity of sold items: ");
		int quantitySold = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter cost to ship sold items: ");
		double shippingCost = sc.nextDouble();
		sc.nextLine();
		
		inv.processSale(SKU, quantitySold, shippingCost);
	}
	
	// reads inventory from file, or sets it to empty 
	private static void initializeInventory(){
		try{
			FileInputStream fis = new FileInputStream("inventory.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			inv = (Inventory) ois.readObject();
			fis.close();
		} catch (FileNotFoundException fnfe){
			// first time using this program, not an error 
			inv = new Inventory();
		} catch (IOException ioe){
			System.out.println("Error: " + ioe);
			inv = new Inventory();
		} catch (ClassNotFoundException cnfe){
			System.out.println("Error: " + cnfe);
			inv = new Inventory(); 
		}
	}
	
	// writes inventory to file 
	private static void serializeInventory(){
		try{
			FileOutputStream fos = new FileOutputStream("inventory.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(inv);
			fos.close();
		} catch (IOException ioe){
			System.out.println("Error: " + ioe);
		}
	}
	
}
