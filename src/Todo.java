import java.io.Serializable;

public class Todo implements Serializable {
	private String title;
	
	/**
	 * <h1>Constructor for creation of a new Todo object<h1>
	 * 
	 * @param title String that will be set to it
	 */
	public Todo(String title) {
		this.title = title;
	}
	
	/**
	 * <h1>Current Title</h1>
	 * <p>
	 * Gets the current title of the class
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * <h1> Print Item</h1>
	 * <p>
	 * Prints the current item title name
	 */
	public void printItem() {
		System.out.println(this.title + " ");
	}

}
