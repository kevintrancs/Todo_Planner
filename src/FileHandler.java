import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileHandler implements Serializable {
	
	/**
	 * @serial serialVersionUID
	 * UID for because using Serializable impl.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <h1>Save File</h1>
	 * <p>
	 * This program will take in a String value that will be the file name
	 * Once the string is passed the string value will have .txt
	 * added to it in order to make it a txt file
	 * 
	 * @param s - String for time name to add
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void saveFile(String s)throws IOException, ClassNotFoundException{
		
		FileOutputStream fileOut = new FileOutputStream(s+".txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		
		out.writeObject(TodoPlanner.getList());
		out.close();
		fileOut.close();
		
	}
	/**
	 * <h1>Load File</h1>
	 * <p>
	 * This program will take in a String value that is the file name
	 * Once the string is passed the string value will have .txt
	 * added to it in order to make it a txt file
	 * <p>
	 * To avoid using a public static vairable, I made a clone
	 * Clone will allow me to copy contents of the list into another
	 * 
	 * @param s
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void loadFile(String s)throws IOException, ClassNotFoundException{
		
		InputStream fileIn = new FileInputStream(s + ".txt");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		ArrayList<Todo> copyList = new ArrayList<Todo>();
		
		copyList = (ArrayList<Todo>) in.readObject();
		TodoPlanner.copyArrayList(copyList);
		in.close();
        fileIn.close();
        
	}

}
