

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class ReadIn {

	public static void main(String[] args){

		try{
			String fileName =  args[0]; // Get the datafile name
			String line;
			FileReader fr = new FileReader(fileName);
			BufferedReader br =  new BufferedReader(fr);
			ReadIn newri = new ReadIn();
			int counter = 0;

			while ((line = br.readLine()) != null) { // Read the file line by line
				counter++;
				if (newri.CheckComment(line, counter) == false) { // Check if it's a comment
					String[] splitted = line.split("\t"); // Split line by tab character
					Stores new1 =  new Stores();
					if (splitted[0].equalsIgnoreCase("MEMBER")) {
						//Do something if member
						new1.Store(line, 1, counter);
					} else if (splitted[0].equalsIgnoreCase("FOLDER")) {
						//Do stuff with folders
						new1.Store(line, 2, counter);
					} else if (newri.CheckContent(splitted[0])){
						//Do something for content
						new1.Store(line, 3, counter);
					} else if (splitted[0].equalsIgnoreCase("FRIEND")) {
						//Do something if friend
						new1.Store(line, 4, counter);
					} else {
						//Return an error
						String error = "Invalid input, please refer to format template (line " + counter + ")";
						Exceptions.addException(error);
					}
				}
			}
			br.close();
			fr.close();

			String command = args[1];
			GetCommand gc = new GetCommand();
			gc.ExecuteCommand(command);
		} catch (IOException io) {
			System.out.println("ERROR: Cannot read file!");
		} catch (ArrayIndexOutOfBoundsException ae){
			System.out.println("ERROR: Input syntax incorrect, please check application arguments.");
		} catch (Exception e){
			System.out.println("ERROR: Something has gone wrong. Please check arguments and whether the input file exists.");
		}
	}

	private boolean CheckComment(String line, int lineNo) {
		String sub; //Ignores comments and blank lines
		if (line.length() == 0){
			String error = "Blank line, invalid input (line " + lineNo + ")";
			Exceptions.addException(error);
			return true;
		} else {
			sub = line.substring(0,1);
			if (sub.equals("#")) {
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean CheckContent(String line) {
		if(line.equalsIgnoreCase("TEXT")) { //Checks if the line is content type
			return true;
		} else if (line.equalsIgnoreCase("MUSIC")) {
			return true;
		} else
			return false;
	}

}


