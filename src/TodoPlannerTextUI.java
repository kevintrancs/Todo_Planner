/**
 * Author: Kevin Tran	                                    
 * Date Created: 2/14/2016                                   		                                *
 * Class: CPSC 224_02 Object-Oriented Programming           
 * Prof: Bruce Worobec                                 		
 * Assignment: #3 (Todo-Planner)			
 *  														
 * Description:												
 * This program is designed to be a todo planner for the user
 * It will run menu that opens up and will keep going until user tells it to quit
 * This todo planner will allow you to add things such as:
 * Task, Meetings and Deadlines
 * You can add them, remove them and display them.		
 *          												
 * */

import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoPlannerTextUI {
	private static boolean isRunning = true; //Boolean to decide if we will continue to run
	private static Scanner input = new Scanner(System.in); //Scanner object to take user input

	public static void main(String[] args) {

		System.out.println("Welcome to the Todo Planner!");
		System.out.println("----------------------------");

		while (isRunning) {
			boolean validChoice = false;
			printMenu();
			int choice = 0; // get user answer
			while(!validChoice){ // make sure valid choice
				try{
					choice	= input.nextInt();
					if(choice > 6 || choice < 0){	
						System.out.println("Invalid choice, please try again: ");
						choice = input.nextInt();
					}
					else
						validChoice = true;
					}
					catch(InputMismatchException e){
						System.out.println("Please enter a number(1-6): ");
						input.next(); //clear scanner of input
						continue;
					}
			}
			if (processChoice(choice)) //If we are successful with operations
				System.out.println("----------------------------");
		}

	}

	/**
	 * This function prints the "Menu"
	 * Goes from 1-6 for item choice and then
	 * your choice at the end.
	 * 
	 */
	private static void printMenu() {
		System.out.println("Main Menu: ");
		System.out.println("1. Create a Task");
		System.out.println("2. Create a Meeting");
		System.out.println("3. Create a Deadline");
		System.out.println("4. Remove a Todo Item");
		System.out.println("5. Display Todo Items");
		System.out.println("6. Exit");
		System.out.println("Choice: ");
	}
	
	/**
	 * This function is a boolean that will take the 
	 * int that is entered and put it in a switch statement to choose which to execute
	 * Also a boolean called completedAction to ensure once it is true that we 
	 * can reshow the menu again to prove that is a successful run
	 * 
	 * @param choiceNum int choice of the user to choose what to do in planner
	 * @return completedAction Will always come true to confirm if it is done
	 */
	private static boolean processChoice(int choiceNum){
		boolean completedAction = false;
		switch(choiceNum){
		case 1:
			addTask();
			completedAction = true;
			break;
		case 2:
			addMeeting();
			completedAction = true;
			break;
		case 3:
			addDeadline();
			completedAction = true;
			break;
		case 4:
			TodoPlanner.displayItems();
			System.out.print("Select which item to remove: ");
			processRemoval();
			completedAction = true;
			break;
		case 5:
			TodoPlanner.displayItems();
			completedAction = true;
			break;
		case 6:
			System.out.println("Thanks for running, Good Bye");
			isRunning = false;
			completedAction = true;
			break;
		default:
			break;
		}
		return completedAction;
	}
	
	/**
	 * This function processes the title of the todo item
	 * It takes in user input and uses a planner function 
	 * To check if it is not taken
	 * 
	 * @return uTitle String of the title of Todo item
	 */
	private static String processUserTitle(){
		input.nextLine();
		String uTitle = input.nextLine();
		while(TodoPlanner.isExist(uTitle)){
			System.out.println("This item already exist, try again: ");
			uTitle = input.nextLine();
		}
		return uTitle;
	}
	
	/**
	 * This takes user input for the item that the user
	 * wishes to have removed
	 * While loop to each if we can remove this item
	 * 
	 */
	private static void processRemoval(){
		int rChoice = input.nextInt();
		while(!TodoPlanner.canRemove(rChoice)){
			System.out.println("Invalid, please try again: ");
			rChoice =input.nextInt();
		}
		TodoPlanner.removeItem(rChoice);
	}
	
	/**
	 * This function processes the priority choice of the addTask()
	 * function
	 * It has a int rChoice to take the value of the user
	 * It has a boolean called done to stay in a while loop until we have a 
	 * valid input from the user
	 * 
	 * @return rChoice returns the correct int that the user wants for priority
	 */
	private static int processPriorityChoice(){
		boolean done = false;
		int rChoice = 0; //init value
		
		while(!done){
			try{
			rChoice	= input.nextInt();
			if(rChoice > 3 || rChoice < 0){	
				System.out.println("Invalid choice, please try again: ");
				rChoice = input.nextInt();
			}
			else
				done = true;
			}
			catch(InputMismatchException e){
				System.out.println("Please enter a number: ");
				input.next(); //clear scanner of input
				continue;
			}
		}
		return rChoice;
	}
	
	/**
	 * This function will take all of the methods used above:
	 * processUserTitle() and processPriority() combined
	 * In order to add this item to the TodoPlanner(Task)
	 * Then will spit out info that was the item user had decided to add
	 * 
	 */
	private static void addTask(){
		System.out.print("Please enter a task title: ");
		String uTitle = processUserTitle();
		System.out.println("Please enter a priority(1 - Urgent, 2 - important, 3 - someday): ");
		int prio = processPriorityChoice();
		
		Task newTask = new Task(uTitle, prio);
		TodoPlanner.addTodos(newTask);
		System.out.println("Added task:");
		newTask.printItem();
	}
	
	/**
	 * This function will take all of the methods used above:
	 * processUserTitle()
	 * In order to add this item to the TodoPlanner(Meeting)
	 * Then will spit out info that was the item user had decided to add
	 * 
	 */
	private static void addMeeting(){
		System.out.println("Please enter a meeting title: ");
		String uTitle = processUserTitle();
		System.out.println("Please enter a meeting Location: ");
		String uLoc = input.nextLine();
		System.out.println("Please enter a meeting time: ");
		String uTime = input.nextLine();
		
		Meeting newMeet = new Meeting(uTitle, uLoc, uTime);
		TodoPlanner.addTodos(newMeet);
		System.out.println("Added meeting:");
		newMeet.printItem();
	}
	
	/**
	 * This function will take all of the methods used above:
	 * processUserTitle()
	 * In order to add this item to the TodoPlanner(deadline)
	 * Then will spit out info that was the item user had decided to add
	 * 
	 */
	private static void addDeadline(){
		System.out.println("Please enter a deadline title: ");
		String uTitle = processUserTitle();
		System.out.println("Please enter a deadline date: ");
		String uDate = input.nextLine();
		
		Deadline newDeadline = new Deadline(uTitle, uDate);
		TodoPlanner.addTodos(newDeadline);
		System.out.println("Added deadline: ");
		newDeadline.printItem();
	}
}
