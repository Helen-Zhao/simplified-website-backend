package assignment2;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

class ListMembers {
	ListMembers(String command){ //iterate through all members
		Collection<Members> names = Stores.MembersMap.values();
		Iterator<Members> itr = names.iterator();
		LinkedList<Members> ll = new LinkedList<Members>();
		while(itr.hasNext()){
			Members mem = itr.next();
			if (!mem.name.equals("")){ //store in linked list, ignore memberless member
				ll.add(mem);
			}
		}
		
		Print print = new Print(command);
		print.PrintListMembers(ll);
		print.PrintExceptions();
	}
}
