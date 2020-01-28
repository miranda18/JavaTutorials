package practice2;
/**
 * Represents toys in the inventory 
 * @author Miranda Sanchez
 *
 */
public class Toy extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7278024904614017475L;

	final static double TOY_COMMISSION_PERCENTAGE = 15;
	
	final static double BASE_SHIPPING_CREDIT = 4.49; 
	final static double SHIPPING_CREDIT_PER_POUND = .5;
	
	private int weight; // weight of item in ounces 
	
	/**
	 * construct from parameters
	 * @param SKU - the SKU of the toy 
	 * @param title - the title of the toy 
	 * @param price - the selling price of the toy
	 * @param quantity - the number of copies in stock 
	 * @param weight - the weight of the toy in ounces 
	 */
	public Toy(int SKU, String title, double price, int quantity, int weight){
		super(SKU, title, price, quantity);
		this.weight = weight; 
	}
	
	@Override
	public String showProductInfo(){
		return "Toy     "     + super.showProductInfo();
	}
	
	@Override
	public String labeledString(){
		return super.labeledString() + "\nWeight: " + weight; 
	}
	
	@Override
	public double commissionPercentage(){
		return TOY_COMMISSION_PERCENTAGE;
	}
	@Override
	public double shippingCredit(){
		return BASE_SHIPPING_CREDIT + SHIPPING_CREDIT_PER_POUND *(Math.ceil(weight/16.0));
	}
}
