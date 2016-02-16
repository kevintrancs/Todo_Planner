import java.util.ArrayList;

public class TodoPlanner {
	//ArrayList of the todo objects
	private static ArrayList<Todo> listOfTodos = new ArrayList<Todo>();
	
	/**
	 * This methods returns the list that we have of Todos
	 * @return listOfTodos<Todo>
	 */
	public static ArrayList<Todo> getList() {
		return listOfTodos;
	}

	/**
	 * This method gets called when an item is being added into
	 * the function. It only takes Todo objects
	 * @param d Object name to add 
	 */
	public static void addTodos(Todo d) {
		listOfTodos.add(d);
	}
	
	/**
	 * Returns false if we can't use the item
	 * because it is bigger than our size or less than
	 * Otherwise returns true if nothing is wrong
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
	 * Removes the item at the set location
	 * @param iindex of the item we want to remove
	 */
	public static void removeItem(int i){
		listOfTodos.remove(i-1);
	}
	
	/**
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
