package assignment2;

import java.util.LinkedList;
import java.util.ListIterator;

class ListMemberContent {
	ListMemberContent(String command, String memberName, String visibility){
		if (Stores.ContentMap.get(memberName) == null){ //Check that member actually has content or exists
			System.out.println("Member does not exist, or has no content");
		}
		else if(visibility.equalsIgnoreCase("Public") || visibility.equalsIgnoreCase("Private") || visibility.equalsIgnoreCase("Friends") ) {
			LinkedList<Content> list = Stores.ContentMap.get(memberName);
			LinkedList<Content> theList = new LinkedList<Content>();
			ListIterator<Content> itr = list.listIterator();
			while (itr.hasNext()){
				Content content = itr.next(); //iterate through all content owned by the member
				if ((content.visibility).equalsIgnoreCase(visibility)){ //store in theList
					theList.add(content);
				}
			}
			Print a = new Print(command);
			a.PrintListMemberContent(theList);
			a.PrintExceptions();
		} else {
			System.out.println("Invalid visibility, please try again"); //check visibility validness
		}
	}
}
