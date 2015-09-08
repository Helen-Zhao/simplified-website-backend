package assignment2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

class ListContent {
	
	ListContent(String command){ //This class lists all content
		Collection<LinkedList<Content>> names = Stores.ContentMap.values();
		Iterator<LinkedList<Content>> itr = names.iterator(); 
		LinkedList<Content> publicV = new LinkedList<Content>(); //Linked lists in visibility order
		LinkedList<Content> privateV = new LinkedList<Content>(); //For storage of final content objects
		LinkedList<Content> friendsV = new LinkedList<Content>();
		
		while (itr.hasNext()){ //iterate through content map and linked lists in them
			LinkedList<Content> temp = itr.next();
			Iterator<Content> llitr = temp.listIterator(0);
			while (llitr.hasNext()){
				Content element = llitr.next();
				String vis = element.visibility;
				if(vis.equalsIgnoreCase("Public")){ //sort by visibility into correct map
					publicV.add(element);
				} else if (vis.equalsIgnoreCase("Private")){
					privateV.add(element);
				} else if (vis.equalsIgnoreCase("Friends")){
					friendsV.add(element);
				}
			}
		}
		Print print = new Print(command);
		print.PrintListContent(publicV);
		print.PrintListContent(friendsV);
		print.PrintListContent(privateV);
		print.PrintExceptions();
	}
	
}
