package assignment2;

import java.util.LinkedList;
import java.util.ListIterator;

class ListUnowned {
	ListUnowned(String command){
		LinkedList<Content> publicv = new LinkedList<Content>(); //make linked lists for found unowned content
		LinkedList<Content> privatev = new LinkedList<Content>();
		LinkedList<Content> friendsv = new LinkedList<Content>();
		if (Stores.ContentMap.get("") != null){	 //if ther actually is unowned content
			LinkedList<Content> all = Stores.ContentMap.get(""); //get it
			ListIterator<Content> itr = all.listIterator();
			while (itr.hasNext()){
				Content content = itr.next();
				if (content.visibility.equalsIgnoreCase("Public")){ //iterate through and sort by visibility
					publicv.add(content); //into the linked lists
				} else if (content.visibility.equalsIgnoreCase("Private")){
					privatev.add(content);
				} else if (content.visibility.equalsIgnoreCase("Friends")){
					friendsv.add(content);
				}
			}
			Print print = new Print(command);
			print.PrintListUnowned(publicv);
			print.PrintListUnowned(friendsv);
			print.PrintListUnowned(privatev);
			
		} else {
			Print print = new Print(command);
			System.out.println("No unowned files found");
			print.PrintExceptions();
		}
		
		
	}
}
