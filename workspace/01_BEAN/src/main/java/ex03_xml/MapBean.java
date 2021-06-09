package ex03_xml;

import java.util.Map;

public class MapBean {

	// 1. map = Key : value 에서 KeySet() -> set -> map.get(set의 itr.next)
	// 2. entrySet()의 getKey와 getValue 사용
	
	// field(property)
	private Map<String, String> map;
	
	// constructor
	
	// method
	public void info() {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		/*
		 	Set<String> keys = map.keySet();
		 	for (String key : keys) {
		 		System.out.println(key + " : " + map.get(key));
		 	}
		*/
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
