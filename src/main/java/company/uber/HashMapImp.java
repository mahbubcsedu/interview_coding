package company.uber;

import java.util.HashMap;
import java.util.TreeMap;

public class HashMapImp<Key, Time, Value> {
//we will do multiple implementation for practice, here we have three fields where key and t are composite ke
	
	/**
	 * 
	 * @author mahbub
	 *
	 * @param <Key>
	 * @param <Time>
	 * @param <Value>
	 */
	//public static class HashMapTime{
		
		private HashMap<Key, TreeMap<Time,Value>> map =new HashMap<Key, TreeMap<Time,Value>>();
		//get wil provide key and time as input and return value will be value with closes t. 
		//so get method will have only one parameter
		//this will return value for key and then search for closest t
		
		public Value get(Key key, Time time) 
		{
			final TreeMap<Time, Value> rbBST=map.get(key);
			if(rbBST==null) return null;
			final Time floorKey = rbBST.floorKey(time);
			return floorKey==null ? null : rbBST.get(floorKey);
		}
		
		//put method will require three parameters as key, time and value
		//so basically it will be pipeline of key and value. so first get one map, then another map
		public void put(Key key, Time time, Value value) {
			if(!map.containsKey(key)) {
				map.put(key, new TreeMap<Time, Value>());
			}
			map.get(key).put(time, value);//
		}	
		
		
		public static void hashMapOperation() {
			HashMapImp<String, Double, String> data =new HashMapImp<String, Double, String>();
			data.put("K", 1.0, "K1");
			data.put("K", 2.0, "K2");
			data.put("K", 2.5, "K2");
			data.put("K", 3.0, "K2");
			System.out.println(data.get("K",0.9));
		    System.out.println(data.get("K",1.0));
		    System.out.println(data.get("K",1.5));
		    System.out.println(data.get("K",2.0));
		    System.out.println(data.get("K",2.2));
		}
		
		
	
	
	public static void main(String args[]) {
		
		HashMapImp.hashMapOperation();
	}

	

	
}
