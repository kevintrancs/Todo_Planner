
public class Todo {
	private String title;
	
	/**
	 * Constructor for creation of a new Todo object
	 * 
	 * @param title String that will be set to it
	 */
	public Todo(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the current title of the class
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Prints the current item title name
	 */
	public void printItem() {
		System.out.println(this.title + " ");
	}

}
