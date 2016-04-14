/**
 * Date Modified: 3/30/2016                                		                                *
 * Class: CPSC 224_02 Object-Oriented Programming           
 * Prof: Bruce Worobec                                 		
 * Assignment: #5 (Todo-Planner) w/ GUI
 *  														
 * Description:	
 * <p>											
 * This program is designed to be a todo planner for the user
 * It will run menu that opens up and will keep going until user tells it to quit
 * This todo planner will allow you to add things such as:
 * Task, Meetings and Deadlines
 * You can add them, remove them and display them.	
 * Now you can save your named file and load it back.	
 * Now this does it with a GUI
 * 
 *            
 * @author  Kevin Tran
 * @version 1.6
 * @since   3/30/16										
 * */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class TodoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JList list;
	private int selectedTodoIndex = -1;
	private JPanel panel;
	private JLabel lblTodo;
	private JLabel lblName;
	private JLabel lblPrio;
	private JLabel lblDate;
	private JLabel lblLocation;
	private Task tsk = new Task("", 2);
	private Deadline dLine = new Deadline("", "");
	private Meeting meet = new Meeting("", "", "");
	private JComboBox comboBox;

	private static FileHandler fh = new FileHandler(); // File handler

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TodoGUI frame = new TodoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TodoGUI() {
		setTitle("Todo Planner -");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedTodoIndex = list.getSelectedIndex();
				String selectedTitle = list.getSelectedValue().toString();

				for (Todo t : TodoPlanner.listOfTodos) {
					if (t.getTitle().equals(selectedTitle)) {
						if (t.getClass().isInstance(tsk)) {
							lblTodo.setText("Task");
							Task now = (Task) t;
							lblPrio.setVisible(true);
							comboBox.setVisible(true);
							lblDate.setVisible(false);
							lblLocation.setVisible(false);
							textField_1.setVisible(false);
							textField_2.setVisible(false);
							textField.setText(now.getTitle());
							if (now.getPriority().equals(Task.Priority.URGENT))
								comboBox.setSelectedIndex(0);
							else if (now.getPriority().equals(Task.Priority.IMPORTANT))
								comboBox.setSelectedIndex(1);
							else
								comboBox.setSelectedIndex(2);

						} else if (t.getClass().isInstance(dLine)) {
							lblTodo.setText("Deadline");
							Deadline noww = (Deadline) t;
							textField_1.setVisible(true);
							textField.setText(noww.getTitle());
							textField_1.setText(noww.getDueDate());
							comboBox.setVisible(false);
							lblLocation.setVisible(false);
							lblPrio.setVisible(false);
							textField_2.setVisible(false);
							lblDate.setVisible(true);

						} else {
							Meeting nowwww = (Meeting) t;
							textField.setText(nowwww.getTitle());
							textField_1.setVisible(true);
							textField_1.setText(nowwww.getTime());
							textField_2.setVisible(true);
							textField_2.setText(nowwww.getLocation());
							lblTodo.setText("Meeting");
							comboBox.setVisible(false);
							lblPrio.setVisible(false);
							lblLocation.setVisible(true);
							lblDate.setVisible(true);
						}
					}
				}

			}
		});
		list.setBounds(10, 11, 195, 189);
		contentPane.add(list);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] taskType = { "Task", "Meeting", "Deadline" };
				String selectedTaskType = (String) JOptionPane.showInputDialog(null, "Todo", "Pick: ",
						JOptionPane.INFORMATION_MESSAGE, null, taskType, taskType[0]);

				if (selectedTaskType.equals("Task")) {
					String taskTitle = JOptionPane.showInputDialog("Please enter task title");
					if (taskTitle != null) {
						String[] prority = { "Urgent", "Important", "Someday" };
						String selectedPrority = (String) JOptionPane.showInputDialog(null, "Select To Do Item Type",
								"Select", JOptionPane.INFORMATION_MESSAGE, null, prority, prority[0]);
						int i = 0;
						if (selectedPrority != null) {
							if (selectedPrority.equals("Urgent"))
								i = 1;
							else if (selectedPrority.equals("Important"))
								i = 2;
							else
								i = 3;
							Task newTask = new Task(taskTitle, i);
							TodoPlanner.addTodos(newTask);
							loadToDoTitleList();
						}
					}
				}

				else if (selectedTaskType.equals("Meeting")) {
					String meetingLocation = null, meetingTime = null;
					String meetingTitle = JOptionPane.showInputDialog("Please enter meeting title");
					if (meetingTitle != null) {
						meetingLocation = JOptionPane.showInputDialog("Please enter meeting location");
					}
					if (meetingLocation != null) {
						meetingTime = JOptionPane.showInputDialog("Please enter meeting time");
						if (meetingTime != null) {
							Meeting meet = new Meeting(meetingTitle, meetingLocation, meetingTime);
							TodoPlanner.addTodos(meet);
							loadToDoTitleList();
						}
					}
				}

				else {
					String deadline = null;
					String deadlineTitle = null;
					deadlineTitle = JOptionPane.showInputDialog("Please enter deadline title");
					if (deadlineTitle != null) {
						deadline = JOptionPane.showInputDialog("Please enter deadline ");
						if (deadline != null) {
							Deadline dead = new Deadline(deadlineTitle, deadline);
							TodoPlanner.addTodos(dead);
							loadToDoTitleList();
						}
					}
				}
			}
		});
		button.setBounds(10, 211, 48, 23);
		contentPane.add(button);

		JButton btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select_index = list.getSelectedIndex();
				if (select_index > -1) {
					String selectedTitle = list.getSelectedValue().toString();
					int answer = JOptionPane.showConfirmDialog(null,
							"Are you sure to remove do item " + selectedTitle + " ?", "Confirm Removeing To DO Item",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (answer == 0) {
						TodoPlanner.removeItem(select_index + 1);
						loadToDoTitleList();
					}
				}
			}
		});
		btnNewButton.setBounds(60, 211, 48, 23);
		contentPane.add(btnNewButton);

		JButton btnProperties = new JButton("Properties");
		btnProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plannerName = JOptionPane.showInputDialog("Enter todo planner name: ");
				if (plannerName != null) {
					setTitle("Todo Planner - " + plannerName);
				}
			}
		});
		btnProperties.setBounds(118, 211, 103, 23);
		contentPane.add(btnProperties);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTodoIndex = list.getSelectedIndex();
				String selectedTitle = list.getSelectedValue().toString();

				for (int i = 0; i < TodoPlanner.listOfTodos.size(); i++) {
					if (TodoPlanner.listOfTodos.get(i).getTitle().equals(selectedTitle)) {
						if (TodoPlanner.listOfTodos.get(i).getClass().isInstance(tsk)) {
							Task t = new Task(textField.getText(), comboBox.getSelectedIndex() + 1);
							TodoPlanner.listOfTodos.setElementAt(t, i);
							JOptionPane.showMessageDialog(null, "Updated Sucessfully");
							loadToDoTitleList();

						} else if (TodoPlanner.listOfTodos.get(i).getClass().isInstance(dLine)) {
							Deadline d = new Deadline(textField.getText(), textField_1.getText());
							TodoPlanner.listOfTodos.set(i, d);
							JOptionPane.showMessageDialog(null, "Updated Sucessfully");
							loadToDoTitleList();

						} else {
							Meeting m = new Meeting(textField.getText(), textField_1.getText(), textField_2.getText());
							TodoPlanner.listOfTodos.set(i, m);
							JOptionPane.showMessageDialog(null, "Updated Sucessfully");
							loadToDoTitleList();

						}
					}
				}

			}
		});
		btnSave.setBounds(308, 211, 89, 23);
		contentPane.add(btnSave);

		panel = new JPanel();
		panel.setBounds(215, 11, 209, 189);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTodo = new JLabel("Todo:");
		lblTodo.setBounds(10, 11, 86, 14);
		panel.add(lblTodo);

		lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 46, 14);
		panel.add(lblName);

		lblPrio = new JLabel("Prio:");
		lblPrio.setBounds(10, 63, 46, 14);
		panel.add(lblPrio);

		lblDate = new JLabel("Date:");
		lblDate.setBounds(10, 88, 46, 14);
		panel.add(lblDate);

		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(10, 119, 57, 14);
		panel.add(lblLocation);

		textField = new JTextField();
		textField.setBounds(77, 33, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(77, 85, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(77, 116, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Urgent", "Important", "Someday" }));
		comboBox.setBounds(75, 60, 88, 20);
		panel.add(comboBox);

	}

	/**
	 * <h1>Load To Do Title</h1>
	 * <p>
	 * This function will remove the old list and update it with the current one
	 * 
	 * @return void
	 */
	private void loadToDoTitleList() {
		Vector<String> to_do_title_vecotor = TodoPlanner.getTodoTitleList();
		list.removeAll();
		list.setListData(to_do_title_vecotor);
	}
}
