
/**
 * Date Modified: 2/15/2016                                		                                *
 * Class: CPSC 224_02 Object-Oriented Programming           
 * Prof: Bruce Worobec                                 		
 * Assignment: #4 (Todo-Planner) w/ File I/O			
 *  														
 * Description:	
 * <p>											
 * This program is designed to be a todo planner for the user
 * It will run menu that opens up and will keep going until user tells it to quit
 * This todo planner will allow you to add things such as:
 * Task, Meetings and Deadlines
 * You can add them, remove them and display them.	
 * Now you can save your named file and load it back.	
 *            
 * @author  Kevin Tran
 * @version 1.5
 * @since   2/21/16											
 * */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TodoPlannerTextUI extends JFrame implements ActionListener, ListSelectionListener {
	private static boolean isRunning = true; // Boolean to decide if we will
												// continue to run
	private static Scanner input = new Scanner(System.in); // Scanner object to
															// take user input
	private static FileHandler fh = new FileHandler(); // File handler


	public static void main(String[] args) {

	}

	/**
	 * <h1>Handle Choice</h1>
	 * <p>
	 * This function is a boolean that will take the int that is entered and put
	 * it in a switch statement to choose which to execute Also a boolean called
	 * completedAction to ensure once it is true that we can reshow the menu
	 * again to prove that is a successful run
	 * 
	 * @param choiceNum
	 *            int choice of the user to choose what to do in planner
	 * @return completedAction Will always come true to confirm if it is done
	 */
	private static boolean processChoice(int choiceNum) {
		boolean completedAction = false;
		switch (choiceNum) {
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
			saveListToFile();
			completedAction = true;
			break;
		case 7:
			loadListFromFile();
			completedAction = true;
			break;
		case 8:
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
	 * <h1>Process Titles</h1>
	 * <p>
	 * This function processes the title of the todo item It takes in user input
	 * and uses a planner function To check if it is not taken
	 * 
	 * @return uTitle String of the title of Todo item
	 */
	private static String processUserTitle() {
		input.nextLine();
		String uTitle = input.nextLine();
		while (TodoPlanner.isExist(uTitle)) {
			System.out.println("This item already exist, try again: ");
			uTitle = input.nextLine();
		}
		return uTitle;
	}

	/**
	 * <h1>Removal Processer</h1>
	 * <p>
	 * This takes user input for the item that the user wishes to have removed
	 * While loop to each if we can remove this item
	 * 
	 */
	private static void processRemoval() {
		int rChoice = input.nextInt();
		while (!TodoPlanner.canRemove(rChoice)) {
			System.out.println("Invalid, please try again: ");
			rChoice = input.nextInt();
		}
		TodoPlanner.removeItem(rChoice);
	}

	/**
	 * <h1>Priority Chooser</h1>
	 * <p>
	 * This function processes the priority choice of the addTask() function It
	 * has a int rChoice to take the value of the user It has a boolean called
	 * done to stay in a while loop until we have a valid input from the user
	 * 
	 * @return rChoice returns the correct int that the user wants for priority
	 */
	private static int processPriorityChoice() {
		boolean done = false;
		int rChoice = 0; // init value

		while (!done) {
			try {
				rChoice = input.nextInt();
				if (rChoice > 3 || rChoice < 1) {
					System.out.print("Invalid choice, please try again: ");
				} else
					done = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number: ");
				input.nextLine(); // clear scanner of input
				continue;
			}
		}
		return rChoice;
	}

	/**
	 * <h1>Task</h1>
	 * <p>
	 * This function will take all of the methods used above: processUserTitle()
	 * and processPriority() combined In order to add this item to the
	 * TodoPlanner(Task) Then will spit out info that was the item user had
	 * decided to add
	 * 
	 */
	private static void addTask() {
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
	 * <h1>Meeting</h1>
	 * <p>
	 * This function will take all of the methods used above: processUserTitle()
	 * In order to add this item to the TodoPlanner(Meeting) Then will spit out
	 * info that was the item user had decided to add
	 * 
	 */
	private static void addMeeting() {
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
	 * <h1>Deadline</h1>
	 * <p>
	 * This function will take all of the methods used above: processUserTitle()
	 * In order to add this item to the TodoPlanner(deadline) Then will spit out
	 * info that was the item user had decided to add
	 * 
	 */
	private static void addDeadline() {
		System.out.println("Please enter a deadline title: ");
		String uTitle = processUserTitle();
		System.out.println("Please enter a deadline date: ");
		String uDate = input.nextLine();

		Deadline newDeadline = new Deadline(uTitle, uDate);
		TodoPlanner.addTodos(newDeadline);
		System.out.println("Added deadline: ");
		newDeadline.printItem();
	}

	/**
	 * <h1>Save to File</h1>
	 * <p>
	 * 
	 * This function will take your string of what you want the file to be
	 * named.
	 * <p>
	 * Then will attempt to save it to somewhere in your direc. If not or it
	 * already exist then it will print error
	 * 
	 */
	private static void saveListToFile() {
		System.out.println("Enter name you wish to save File to(do not add .txt): ");
		String fileName = processUserTitle();

		try {
			fh.saveFile(fileName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <h1>Load From File</h1>
	 * <p>
	 * 
	 * This function will take your string of what you want the file to be
	 * loaded is.
	 * <p>
	 * Then will attempt to load it from somewhere in your direc. If not or DNE
	 * then it will print error
	 * 
	 */
	private static void loadListFromFile() {
		System.out.println("Enter name of file you wish to load(do not add .txt): ");
		String fileName = processUserTitle();

		try {
			fh.loadFile(fileName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent event) {
	
	}
}
