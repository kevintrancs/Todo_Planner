import java.util.ArrayList;

public class TodoPlanner {
	
	//Name of current Planner
	private static String nameOfPlanner;
	//ArrayList of the todo objects
	protected static ArrayList<Todo> listOfTodos = new ArrayList<Todo>();
	
	/**
	 * <h1>Set Planner Name</h1>
	 * <p>
	 * This function takes a String argument
	 * Then uses what was in the name to name the current planner list
	 * 
	 * @param s - Name of Planner
	 */
	public static void setPlannerName(String s){
		nameOfPlanner = s;
	}
	
	/**
	 * <h1>Get List</h1>
	 * <p>
	 * This methods returns the list that we have of Todos
	 * @return listOfTodos<Todo>
	 */
	public static ArrayList<Todo> getList() {
		return listOfTodos;
	}
	
	/**
	 * <h1>Clone List</h1>
	 * <p>
	 * This method allows us to copy the contents of another array list
	 * Then it makes the copy clone and puts it into the list that we have
	 * Currently now
	 * 
	 * @param l - Arraylist<Todo>
	 */
	@SuppressWarnings("unchecked")
	public static void copyArrayList(ArrayList<Todo> l){
		listOfTodos = (ArrayList<Todo>)l.clone();
	}

	/**
	 * <h1>Add to List</h1>
	 * <p>
	 * This method gets called when an item is being added into
	 * the function. It only takes Todo objects
	 * 
	 * @param d Object name to add 
	 */
	public static void addTodos(Todo d) {
		listOfTodos.add(d);
	}
	
	/**
	 * <h1>Removal Check</h1>
	 * <p>
	 * Returns false if we can't use the item
	 * because it is bigger than our size or less than
	 * Otherwise returns true if nothing is wrong
	 * 
	 * @param i index of the item we want to remove
	 * @return boolean if we can remove it
	 */
	public static boolean canRemove(int i) {
		if(i > listOfTodos.size() || i < 0)
			return false;
		else
			return true;
	}
	
	/**
	 * <h1>Remove Item</h1>
	 * <p>
	 * Removes the item at the set location
	 * @param iindex of the item we want to remove
	 */
	public static void removeItem(int i){
		listOfTodos.remove(i-1);
	}
	
	/**
	 * <h1>Display</h1>
	 * <p>
	 * Enhanced for loop that will iterate through
	 * each Todo item inside of the listOfTodos that we have
	 * On each iteration we show the @overriden method of print
	 * 
	 */
	public static void displayItems(){
		int index = 1;
		for(Todo t : listOfTodos){
			System.out.print(index + ". ");
			t.printItem();
			index++;
		}
	}
	
	/**
	 * <h1>Checks Existance</h1>
	 * <p>
	 * This method will check if the item that we have to
	 * input already exist in the todo planner
	 * Returns true if it already exist and false otherwise
	 * 
	 * @param s String that we will input to check
	 * @return boolean if the item that we have already exists
	 */
	public static boolean isExist(String s){
		for(Todo t: listOfTodos){
			if(t.getTitle().equals(s))
				return true;
		}
		return false;
	}
}
