
public class Meeting extends Todo {

	private String location;
	private String time;

	/**
	 * Constructor to create a new Meeting object
	 * @param title String goes to super class to name of the object
	 * @param location String that puts this.location to value
	 * @param time String that puts this.time to value
	 */
	public Meeting(String title, String location, String time) {
		super(title);
		this.location = location;
		this.time = time;

	}
	
	/**
	 * Returns the current String value of the location variable
	 * @return this.location
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Returns the current String value of the time variable
	 * @return this.time
	 */
	public String getTime() {
		return this.time;
	}

	/**
	 * This method is an overriden method of the super class
	 * It will print out all of the important information about
	 * this specific object 
	 * 
	 */
	@Override
	public void printItem() {
		System.out.println(super.getTitle() + "(Location: " + getLocation() + ", Time: " + getTime() + ")");
	}

}
