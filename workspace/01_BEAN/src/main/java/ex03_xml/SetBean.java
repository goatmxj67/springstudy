package ex03_xml;

// import java.util.Iterator;
import java.util.Set;

public class SetBean {

	// field(property)
	private Set<String> set;
	
	// constructor
	
	// method
	public void info() {
		/*
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		*/
		for (String s : set) {
			System.out.println(s);
		}
	}
	
	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}
	
}
