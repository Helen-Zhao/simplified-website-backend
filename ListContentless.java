package assignment2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

class ListContentless {
	ListContentless(String command){ //Get all member names
		Set<String> set = Stores.MembersMap.keySet();
		Iterator<String> itr = set.iterator();
		LinkedList<String> contentless = new LinkedList<String>();
		while (itr.hasNext()){
			String name = itr.next(); //Check contentMap to see if anyone has nothing
			if ((Stores.ContentMap.get(name) == null) && !name.equals("")){
				contentless.add(name); //if they do add to contentless linked list
			}
		}
		if (contentless.peekFirst() == null){ //print contentless if not empty
			Print print = new Print(command);
			System.out.println("No contentless members found");
			print.PrintExceptions();
		} else {
			Print print = new Print(command);
			print.PrintContentless(contentless);
			print.PrintExceptions();
		}
	}
}
