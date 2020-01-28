package practice2;

import java.util.*; 

/**
 * Used to sort products by Title
 * @author Miranda Sanchez
 *
 */
public class ProductByTitle implements Comparator<Product>{

	public int compare(Product lhs, Product rhs){
		return lhs.getTitle().compareTo(rhs.getTitle());
	}
}
