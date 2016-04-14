

import java.text.Collator;
import java.util.Comparator;


class Members implements Comparator<Members>, Comparable<Members>{
	String name; //object type
	String nickname; 
	
	Members(String n, String nn) {
		name = n;
		nickname = nn;
	}

	@Override
	public int compareTo(Members o) {
		return (this.name).compareTo(o.name);
	}

	@Override
	public int compare(Members o1, Members o2) {
		return Collator.getInstance().compare(o1, o1);
	}
	
}
