package practice2;

/**
 * A movie in the inventory
 * @author Miranda Sanchez
 *
 */
public class Movie extends Product{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8069284994284339431L;
	final static double MOVIE_SHIPPING_CREDIT = 2.98; 
	final static double MOVIE_COMMISSION_PERCENTAGE = 12; 
	
	private String UPC; // the universal product code for the movie 
	
	/**
	 * constructs Movie from parameters
	 * @param SKU - SKU of movie
	 * @param title - title of the move
	 * @param price - the selling price of movie 
 	 * @param quantity - the number of copies in stock 
	 * @param UPC - the universal product code number of the movie
	 */
	public Movie(int SKU, String title, double price, int quantity, String UPC){
		super(SKU, title, price, quantity); 
		this.UPC = UPC;
	}
	
	@Override
	public String showProductInfo(){
		return "Movie " + super.showProductInfo(); 
	}
	
	@Override
	public String labeledString(){
		return super.labeledString() + "\nUPC: " + UPC; 
	}
	
	@Override
	public double commissionPercentage(){
		return MOVIE_COMMISSION_PERCENTAGE;
	}
	
	@Override
	public double shippingCredit(){
		return MOVIE_SHIPPING_CREDIT;
	}

}
