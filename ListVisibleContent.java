
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class ListVisibleContent {
	ListVisibleContent(String command, String memberName){
		if (Stores.MembersMap.get(memberName) == null){ //Check the member exists
			System.out.println("Member does not exist");
		} else {
		Collection<LinkedList<Content>> contents = Stores.ContentMap.values();
		Iterator<LinkedList<Content>> itr = contents.iterator();  //Go through contentMap
		LinkedList<Content> canSee = new LinkedList<Content>();
		while (itr.hasNext()){
			LinkedList<Content> temp = itr.next();
			Iterator<Content> llitr = temp.listIterator(0);
			while (llitr.hasNext()){
				Content element = llitr.next();
				if (element.ownerName.equalsIgnoreCase(memberName)){ //if member's own stuff, add to canSee
					canSee.add(element);
				} else {
					String vis = element.visibility;
					if(vis.equalsIgnoreCase("Public") && ((element.ownerName).equalsIgnoreCase(memberName) == false)){
						canSee.add(element); //if public visibility, add to canSee
					}
				}
			}
		}
		
		if (Stores.FriendsMap.get(memberName) != null) { //Go through the member's friends' content
			LinkedList<String> lls = Stores.FriendsMap.get(memberName); //add anything that's "friends" visibility
			ListIterator<String> litr = lls.listIterator();
			while(litr.hasNext()){
				String name = litr.next();
				if (Stores.ContentMap.get(name) != null){
					LinkedList<Content> llc = Stores.ContentMap.get(name);
					ListIterator<Content> citr = llc.listIterator();
					while (citr.hasNext()){
						Content content = citr.next();
						if ((content.visibility).equalsIgnoreCase("Friends")){
							canSee.add(content);
						}
					}
				}
			}
		}
		Print print = new Print(command);
		print.PrintListVisibleContent(canSee);
		print.PrintExceptions();
		}
	}
}
