

import java.util.Comparator;
import java.text.Collator;

class Content implements Comparator<Content>, Comparable<Content>{
	String contenttype;
	String ownerName;
	String title;
	String visibility;
	String[] tags;
	
	Content(String content_type, String owner_name, String _title, String _visibility, String _tagsline) {
		contenttype = content_type;
		ownerName = owner_name;
		title = _title;
		visibility = _visibility;
		tags = _tagsline.split(",");
	}

	@Override
	public int compare(Content arg0, Content arg1) {
		return Collator.getInstance().compare(arg0, arg1);
	}

	@Override
	public int compareTo(Content o) {
		return (this.title).compareTo(o.title);
	}	
}
