
public class Task extends Todo {
	
	/**
	 * 1 - URGENT
	 * 2 - IMPORTANT
	 * 3 - SOMEDAY
	 */
	public enum Priority{
		URGENT,
		IMPORTANT,
		SOMEDAY;
	}
	
	private int prio;
	
	/**
	 * Constructor to make a Task Object
	 * @param title Name of the task Object 
	 * @param prio Number for the priority that is needed for task
	 */
	public Task(String title, int prio) {
		super(title);
		this.prio = prio;
		
	}
	
	/**
	 * Returns a priority that is currently set to the Task Object that 
	 * we have currently
	 * Uses a switch to determine what we have
	 * @return Priority or null if none
	 */
	public Priority getPriority(){
		switch(prio){
		case 1:
			return Priority.URGENT;
		case 2:
			return Priority.IMPORTANT;
		case 3:
			return Priority.SOMEDAY;
		default:
			break;
		}
		return null;
	}
	
	/**
	 * This method is an overriden method of the super class
	 * It will print out all of the important information about
	 * this specific object 
	 * 
	 */
	@Override
	public void printItem() {
		System.out.println(super.getTitle() + "(Priority: " + getPriority() + ")");
	}
}
