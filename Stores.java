

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class Stores {
	static HashMap<String, Members> MembersMap = new HashMap<String, Members>(); //Create storage 
	static HashMap<String, LinkedList<Content>> ContentMap = new HashMap<String, LinkedList<Content>>(); 
	static HashMap<String, LinkedList<Folders>> FoldersMap = new HashMap<String, LinkedList<Folders>>();
	static HashMap<String, LinkedList<String>> FriendsMap = new HashMap<String, LinkedList<String>>();
	
	void Store(String line, int type, int lineNo) throws ArrayIndexOutOfBoundsException {
		
		try {
			String[] split;
			if (line.endsWith("\t")){
				line = line + " ";
				split = line.split("\t"); //ensure lines aren't truncated if they end with \t
				split[split.length - 1] = "";
			} else {
				split = line.split("\t");
			}
			
			if (type == 1){ //If member
				if (split.length > 3){
					String error = "Too many fields for type MEMBER (line " + lineNo + ")";
					Exceptions.addException(error);
				} else if (split[1].equals("")){
					String error = "Missing member name, this field can not be blank (line " + lineNo + ")";
					Exceptions.addException(error);
				} else {
					Members member = new Members(split[1], split[2]);
					if (MembersMap.get(split[1]) == null) {
						MembersMap.put(split[1], member); 
					} else {
						String error = "Duplicate member name, member names must be unique (line " + lineNo + ")";
						Exceptions.addException(error);
					}
				}
			}
			
			if (type == 2){ //If folder
				if (split.length > 4){
					String error = "Too many fields for type FOLDER (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else if (MembersMap.get(split[1]) == null){
					String error = "Folder owner does not exist (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else if (split[2].equals("")){
					String error = "Missing folder name, field can not be blank (line "+ lineNo + ")";
					Exceptions.addException(error);
				} 
				else {
					Collection<LinkedList<Folders>> folderList = FoldersMap.values();
					Iterator<LinkedList<Folders>> itr = folderList.iterator();
					boolean contentExists = false; //iterating and checking for duplication
					boolean duplicateFolderName = false;
					while (itr.hasNext()){
						LinkedList<Folders> list = itr.next();
						ListIterator<Folders> fitr = list.listIterator();
						while (fitr.hasNext()){
							Folders folder = fitr.next();
							if (folder.folderTitle.equals(split[2]) && (folder.ownerName.equals(split[1]) == false)){
								duplicateFolderName = true;
							}
						}
					}
					
					Collection<LinkedList<Content>> contentList = ContentMap.values();
					Iterator<LinkedList<Content>> llcitr = contentList.iterator();
					while (llcitr.hasNext()){ //iterating contentmap, checking if content exists
						LinkedList<Content> clist = llcitr.next();
						ListIterator<Content> llitr = clist.listIterator();
						while (llitr.hasNext()) {
							Content content = llitr.next();
							if (split[3].equals(content.title)){
								contentExists = true;
								break;
							} 
						}
					}
					
					if (contentExists == true && duplicateFolderName == false){
						LinkedList<Folders> currentList = FoldersMap.get(split[1]);
						boolean found = false; //store if not duplicate and content exists
						if (currentList == null){
							Folders folder = new Folders(split[1], split[2], split[3]);
							LinkedList<Folders> list = new LinkedList<Folders>(); //Check if a linked list exists
							list.add(folder);	//If not, make one
							FoldersMap.put(split[1], list);
						} else {
							LinkedList<Folders> list = FoldersMap.get(split[1]); 
							Iterator<Folders> iter = list.iterator();
							while (iter.hasNext()){
								Folders folder1 = iter.next();
								if((folder1.folderTitle).equals(split[2])){
									folder1.contentTitle.add(split[3]);
									found = true;
									break;
								}
							}
							if (found == false){
								Folders folder = new Folders(split[1], split[2], split[3]);
								list.add(folder);
							}
						}
					} else if (duplicateFolderName){
						String error = "Duplicate folder name, field must be unique (line " + lineNo + ")";
						Exceptions.addException(error);
					} else if (contentExists == false){
						String error = "Content does not exist (line " + lineNo + ")";
						Exceptions.addException(error);
					}
				}
			}
			
			if (type == 3){ //If content
				if (split[1].equals("")){ //Check data is valid, if not make exception
					if (MembersMap.get("") == null){
						Members member = new Members("","");
						MembersMap.put("", member);
					}
				}
				if (split.length > 5){	
					String error = "Too many fields for type CONTENT (line " + lineNo + ")";
					Exceptions.addException(error);
				} 
				else if (split.length < 5){
						String error = "Invalid format, missing fields (line " + lineNo + ")";
						Exceptions.addException(error);
				}
				else if (split[2].equals("")){
					String error = "Missing content name, this field can not be blank (line " + lineNo + ")";
					Exceptions.addException(error);
				} 
				else if (MembersMap.get(split[1]) == null){
					String error = "Content owner does not exist (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else if (!split[3].equalsIgnoreCase("Friends") && !split[3].equalsIgnoreCase("Public") && !split[3].equalsIgnoreCase("Private")) {
					String error = "Invalid visibility (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else {
					String tags;
					boolean err = false;
					tags = split[4]; //Iterate and check for duplication
					Collection<LinkedList<Content>> A = ContentMap.values();
					Iterator<LinkedList<Content>> itr = A.iterator();
					while (itr.hasNext()){
						LinkedList<Content> list = itr.next();
						ListIterator<Content> litr = list.listIterator();
						while (litr.hasNext()){
							Content content = litr.next();
							if (content.title.equalsIgnoreCase(split[2])) {
								String error = "Duplicate content, titles must be unique (line " + lineNo + ")";
								Exceptions.addException(error);		
								err = true;
							}
						}
					}
					if (err == false) { //Store if all good
						Content content = new Content(split[0], split[1], split[2], split[3], tags);
						if (ContentMap.get(split[1]) == null){
							LinkedList<Content> list = new LinkedList<Content>(); //Check if a linked list exists
							list.add(content);	//If not, make one
							ContentMap.put(split[1], list);
						} else {
							LinkedList<Content> list = ContentMap.get(split[1]); 
							list.add(content);
						}
					}
				}
			}
			
			if (type == 4){ //If friend
				if (split.length > 3){	//Invalid data checks
					String error = "Too many fields for type FRIEND (line " + lineNo + ")";
					Exceptions.addException(error);
				} 
				else if (split[2].equals("")){
					String error = "Missing friend name, field can not be blank (line " + lineNo + ")";
					Exceptions.addException(error);
				} else if (split[1].equalsIgnoreCase(split[2])) {
					String error = "Can not be friends with self (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else if (MembersMap.get(split[2]) == null){
					String error = "Friend does not exist (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else if (MembersMap.get(split[1]) == null) {
					String error = "Member does not exist (line " + lineNo + ")";
					Exceptions.addException(error);
				}
				else {
					//if valid, check if linked list exists already
					if (FriendsMap.get(split[2]) == null){
						LinkedList<String> list = new LinkedList<String>();
						list.add(split[1]); //if not make it
						FriendsMap.put(split[2], list);
					} else {
						LinkedList<String> list = FriendsMap.get(split[2]);
						Iterator<String> itr = list.iterator();
						boolean duplicate = false;
						while (itr.hasNext()){
							String friendName = itr.next();
							if (friendName.equalsIgnoreCase(split[1])){
								duplicate = true;
							}
						} //Checking for duplication
						if (duplicate == true){
							String error = "Duplicate friend, friends must be unique (line " + lineNo + ")";
							Exceptions.addException(error);
						} else {
							list.add(split[1]);
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException missingField) {
			String error = "Invalid format, missing fields (line " + lineNo + ")";
			Exceptions.addException(error);
		}

	}
	
}
