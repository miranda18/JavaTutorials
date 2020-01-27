package practice1;

import java.io.*;
import java.util.*; 


/**
 * Driver for the video store inventory program.
 * Inputs stored inventory from a file, offers users a menu of 5 options
 * 1. Add movie 
 * 2. Remove Movie 
 * 3. Find Movie 
 * 4. Display Inventory 
 * 5. Quit 
 * When the user selects quit, the inventory is saved to a file 
 *
 */
public class Driver {

	public static void main(String[] args) {
		
Inventory inv;  // the movie inventory, initialized in try/catch below
        
		// try to load from file
		try {
			FileInputStream fis = new FileInputStream("inventory.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
            
			inv = (Inventory) ois.readObject();
			fis.close();
		} catch (FileNotFoundException fnfe) {
			// first time using this program, not an error
			inv = new Inventory();
		} catch (IOException ioe) {
			System.out.println("Error: " + ioe);
            return; //exit main
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error: " + cnfe);
            return; //exit main
		}
        
		int val,          // Menu choice
            SKU,          // SKU of video to manipulate
            quantity; 	  // quantity of video to add
        String title;     // title of video to add
        double price;     // price of video to add
       

		Scanner sc = new Scanner(System.in);  // To handle input
        
		do {
			// Display the menu and get a choice.
			System.out.println("Video Store Inventory Menu");
			System.out.println();
			System.out.println("1. Add Movie");
			System.out.println("2. Remove Movie");
			System.out.println("3. Find Movie by SKU");
			System.out.println("4. Display inventory");
			System.out.println("5. Quit the Program");
			System.out.println();
			System.out.println("Enter your choice: ");
            
			val = sc.nextInt();
            
			// Respond to the user's menu selection.
			switch (val) {
                case 1:
                    // get the info from the user
                    System.out.println();
                    System.out.println("Enter the SKU: ");
                    SKU = sc.nextInt();
                    System.out.println("Enter the title: ");
                    sc.nextLine();  //consume newline after SKU value
                    title = sc.nextLine();
                    System.out.println("Enter the price: ");
                    price = sc.nextDouble();
                    System.out.println("Enter the quantity: ");
                    quantity = sc.nextInt();
                    System.out.println();
                    
                    inv.addMovie(SKU,quantity,price,title);
                    break;
                case 2:
                    // input SKU from the user to remove from the inventory
                    System.out.println( "Enter SKU of movie to remove: ");
                    SKU = sc.nextInt();

                    inv.removeMovie(SKU);
                    break;
                case 3:
                    // input SKU from user to find it in the inventory
                    System.out.println( "Enter SKU of movie to find: ");
                    SKU = sc.nextInt();

                    inv.findMovie(SKU);
                    break;
                case 4:
                    inv.showInventory();
                    break;
                case 5:
                    System.out.println("Exit selected");
                    break;
                default:
                    System.out.println("Invalid selection");
                    break; // This break is not really necessary
			}
            
		} while (val != 5);
		
		//save the inventory to file system
		try {
			FileOutputStream fos = new FileOutputStream("inventory.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(inv);
			fos.close();
            
		} catch (IOException ioe) {
			System.out.println("Error: " + ioe);
		}
        
	}

}
