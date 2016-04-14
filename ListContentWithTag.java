

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class ListContentWithTag {
	ListContentWithTag(String command, String memberName, String tag){
		if (Stores.MembersMap.get(memberName) == null){ //This function finds all content visible for a person
			System.out.println("Member does not exist"); //with a specific tag
		} else {
		Collection<LinkedList<Content>> contents = Stores.ContentMap.values();
		Iterator<LinkedList<Content>> itr = contents.iterator(); 
		LinkedList<Content> canSee = new LinkedList<Content>(); //iterate through all content 
		LinkedList<Content> canMaybeSee = new LinkedList<Content>(); //looking for tag
		while (itr.hasNext()){
			LinkedList<Content> temp = itr.next();
			Iterator<Content> llitr = temp.listIterator(0);
			while (llitr.hasNext()){
				Content element = llitr.next();
				String vis = element.visibility;
				String[] tags = element.tags;
				for (int i = 0; i < tags.length; i++){
					if (tags[i].equalsIgnoreCase(tag)) { //If the content is owned by person, add to canSee
						if (element.ownerName.equalsIgnoreCase(memberName)){
							canSee.add(element);
						} else {
							if ( vis.equalsIgnoreCase("Public")){ //If public, add to canSee
								canSee.add(element);
							} else if (vis.equalsIgnoreCase("Friends")){
								canMaybeSee.add(element); //If friends, add to canMaybeSee
							}
						}
					}
				}
			}
		}
		
		LinkedList<String> friends = Stores.FriendsMap.get(memberName);
		ListIterator<String> sitr = friends.listIterator();
		ListIterator<Content> litr = canMaybeSee.listIterator();
		while(litr.hasNext()){
			Content maybe = litr.next();
			while (sitr.hasNext()){ //iterate through the person's friends to determine if maybe
				String friendName = sitr.next(); //is a yes. Add to canSee if it is.
				if (maybe.ownerName.equalsIgnoreCase(friendName)){
					canSee.add(maybe);
				}
			}
		}
		Print print = new Print(command);
		print.PrintListContentTag(canSee);
		print.PrintExceptions();
		}
	}
}
