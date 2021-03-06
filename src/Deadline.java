
public class Deadline extends Todo {

	private String dueDate;

	/**
	 * <p>
	 * Constrcutor to make a new Deadline Object
	 * @param title String value that goes to super class
	 * @param dueDate String value that will be stored for time in dueDate variable
	 */
	public Deadline(String title, String dueDate) {
		super(title);
		this.dueDate = dueDate;
	}
	
	/**
	 * <h1>Get Due Date</h1>
	 * <p>
	 * Returns the current String value of the dueDate variable
	 * @return this.dueDate
	 */
	public String getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * <h1>Overriden Print Item</h1>
	 * <p>
	 * This method is an overriden method of the super class
	 * It will print out all of the important information about
	 * this specific object 
	 * 
	 */
	@Override
	public void printItem() {
		System.out.println(super.getTitle() + "(Deadline Date: " + getDueDate() + ")");
	}
}
