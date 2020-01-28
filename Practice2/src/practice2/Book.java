package practice2;

/**
 * A book in the inventory
 * @author Miranda Sanchez
 *
 */
public class Book extends Product {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3466671879693140342L;
	final static double BOOK_SHIPPING_CREDIT = 3.99; 
	final static double BOOK_COMMISSION_PERCENTAGE = 15; 
	
	private String isbn; 	// international standard book number
	private String author;  // author(s) of the book 
	
	
	/**
	 * constuct book from parameteres
	 * @param SKU - the stock keeping unit of the book
	 * @param title - title of the book
	 * @param price - selling price of the book
	 * @param quantity - the number of copies in stock
	 * @param isbn - international standard book number of the book 
	 * @param author - the name of the author of the book
	 */
	public Book(int SKU, String title, double price, int quantity, String isbn, String author){
		super(SKU, title, price, quantity); 
		this.isbn = isbn;
		this.author = author;
	}
	
	@Override
	public String showProductInfo(){
		return "Book     " + super.showProductInfo(); 
	}
	
	@Override
	public String labeledString(){
		return super.labeledString() 
			   + "\nISBN: " + isbn  
			   + "\nAuthor: "  + author;
	}
	
	@Override
	public double commissionPercentage(){
		return BOOK_COMMISSION_PERCENTAGE; 
	}
	
	@Override
	public double shippingCredit(){
		return BOOK_SHIPPING_CREDIT; 
	}
	
}
