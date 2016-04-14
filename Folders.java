

import java.util.ArrayList;
import java.util.Collection;

class Folders {
	String folderTitle;
	Collection<String> contentTitle = new ArrayList<String>();
	String ownerName;
	
	Folders(String _ownerName, String _folderName, String _contentName){
		folderTitle = _folderName;
		contentTitle.add(_contentName);
		ownerName = _ownerName;
	}
}
