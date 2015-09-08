package assignment2;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


class Print { //Constructor prints timestamp
	Print(String command){
		Date date = new Date();
		System.out.println(date.toString());
		System.out.println(command);
	}
	
	void PrintListContent(LinkedList<Content> ll){ //Print script for every command
		Collections.sort(ll);
		Iterator<Content> itr = ll.listIterator();
		while (itr.hasNext()){
			Content element = itr.next();
			System.out.println(element.contenttype + ":" + element.title);
		}
	}
	
	void PrintListFolderContent(LinkedList<Content> ll, String foldername){
		Collections.sort(ll);
		Iterator<Content> itr = ll.listIterator();
		while (itr.hasNext()){
			Content element = itr.next();
			System.out.println(element.visibility + " " + element.title + "(" + foldername +")");
		}
	}
	
	void PrintListMemberContent(LinkedList<Content> ll) {
		Collections.sort(ll);
		Iterator<Content> itr = ll.listIterator();
		while (itr.hasNext()){
			Content element = itr.next();
			System.out.println(element.ownerName + " " + element.title + "(" + element.visibility +")");
		}
	}
	
	void PrintListMembers(LinkedList<Members> ll){
		Collections.sort(ll);
		Iterator<Members> mitr = ll.iterator();
		while(mitr.hasNext()){
			Members mem = mitr.next();
			System.out.println(mem.name + "(" + mem.nickname + ")");
		}
	}
	 void PrintListVisibleContent(LinkedList<Content> ll){
		 Collections.sort(ll);
		 Iterator<Content> itr = ll.listIterator();
			while (itr.hasNext()){
				Content element = itr.next();
				System.out.println(element.title + " (" + element.ownerName + ", " + element.visibility +")");
			}
	 }
	 
	 void PrintListContentTag(LinkedList<Content> ll) {
		 Collections.sort(ll);
		 Iterator<Content> itr = ll.listIterator();
			while (itr.hasNext()){
				Content element = itr.next();
				String tags[] = element.tags;
				String line = tags[0];
				for (int i = 1; i < tags.length; i++){
					line = line + "," + tags[i];
				}
				System.out.println(element.title + " (" + element.ownerName + ", " + element.visibility + ", " + line + ")");
			}
	 }
	 
	 void PrintContentless(LinkedList<String> ll){
		 Collections.sort(ll);
		 ListIterator<String> itr = ll.listIterator();
		 while (itr.hasNext()){
			 System.out.println(itr.next());
		 }
	 }

	public void PrintListUnowned(LinkedList<Content> ll) {
		 Collections.sort(ll);
		 ListIterator<Content> itr = ll.listIterator();
		 while (itr.hasNext()){
			 Content element = itr.next();
			 System.out.println(element.title + " (" + element.visibility + ")");
		 }
	}
	
	void PrintExceptions(){
		if (Exceptions.errors.isEmpty() == false){
			Iterator<String> itr = Exceptions.errors.iterator();
			System.out.println("\nInput file contained the following errors: ");
			while (itr.hasNext()){
				System.out.println(itr.next());
			}
		}
	}
}
