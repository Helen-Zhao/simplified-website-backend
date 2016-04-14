

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

class ListFolder {

	ListFolder (String command, String folderName){
		Collection<LinkedList<Folders>> folders = Stores.FoldersMap.values();
		Iterator<LinkedList<Folders>> itr = folders.iterator();
		Folders theOne = null; //get all folders, iterate through them until theOne is found
		while (itr.hasNext()) {
			LinkedList<Folders> llfolder = itr.next();
			Iterator<Folders> llitr =  llfolder.iterator();
			while (llitr.hasNext()){
				Folders folder = llitr.next();
				if (folder.folderTitle.equalsIgnoreCase(folderName)){
					theOne = folder; //theOne is the one that matches the input name
					break;
				}
			}
			if(theOne != null){
				break;
			}
		}
		

			if (theOne == null){ //if it isn't found, print it
				System.out.println("Folder name \"" + folderName + "\" not found");
			} else {
				LinkedList<Content> llc = Stores.ContentMap.get(theOne.ownerName);
				Iterator<Content> citr = llc.iterator(); //if it is, grab the linked list of content
				Collection<String> lls = theOne.contentTitle;
				LinkedList<Content> inFolderPublic = new LinkedList<Content>(); //make visibility linked lists
				LinkedList<Content> inFolderPrivate = new LinkedList<Content>();
				LinkedList<Content> inFolderFriends = new LinkedList<Content>();
				
				while (citr.hasNext()){ //looking for the content names in the folder
					Content content = citr.next();							
					Iterator<String> sitr = lls.iterator();
					while (sitr.hasNext()){ //looping through all content owned by the member
						String name = sitr.next();  //folders can only contain members' things
						if ((content.title).equalsIgnoreCase(name)){
							if((content.visibility).equalsIgnoreCase("Public")){
								inFolderPublic.add(content);
							} else if ((content.visibility).equalsIgnoreCase("Friends")){
								inFolderFriends.add(content);
							} else {
								inFolderPrivate.add(content);
							}
						}
					}
				}
				Print a = new Print(command); 
				a.PrintListFolderContent(inFolderPublic, folderName);
				a.PrintListFolderContent(inFolderFriends, folderName);
				a.PrintListFolderContent(inFolderPrivate, folderName);
				a.PrintExceptions();
		}
	}
}
