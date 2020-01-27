/*
 * A class for producing simple greetings  
 * 
 */
public class Greeter {
	// string that holds a passed in name 
	private String name; 

	/*
	 * Constructs a Greeter Object that can greet a person or entity. 
	 * @param aName - the name of the person or entity who should be addressed in the
	 * 				  greetings.  
	 */
	public Greeter(String aName) {
		name = aName; 
	}

	/*
	 * Greet with a "Hello" message. 
	 * @return a message containing "Hello" and the name of the greeted person or entity
	 */
	public String sayHello() {
		return "Hello, " + name + "!"; 
	}
	
	public static void main(String[] args) {
		// creates instance of class Greeter in order to be able to access functions 
		// within class, and sets constructor Greeter to "World"
		Greeter worldGreeter = new Greeter("World");
		
		//string holding result from sayHello() 
		String greeting = worldGreeter.sayHello(); 
		
		// printing out end result 
		System.out.println(greeting); 
		
	}
	
}

