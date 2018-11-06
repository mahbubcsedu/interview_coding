package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * {@linkplain https://www.programcreek.com/2013/03/hashset-vs-treeset-vs-linkedhashset/}
 * @author mahbub
 *
 */
public class LoggerRateLimitter {

	public Map<String, Integer> map;
	int lastSecond=0;
	final int DEFAULT_SIZE=100;
	public LoggerRateLimitter() {
		map = new LinkedHashMap<String, Integer>(DEFAULT_SIZE,0.7f, true) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> e) {
				return lastSecond-e.getValue() >10; //evict messages whose value is greater than 10 seconds
			}
		};
	}
	
	
	public boolean shouldPrintMessage(int timeStamp, String messge) {
		lastSecond=timeStamp;
		if(!map.containsKey(messge) || timeStamp-map.get(messge)>=10)
		{
			map.put(messge, timeStamp);
			return true;
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		LoggerRateLimitter ob=new LoggerRateLimitter();
		ob.shouldPrintMessage(1, "message");
		ob.shouldPrintMessage(2, "message");
		ob.shouldPrintMessage(3, "message");
		ob.shouldPrintMessage(4, "message");
		ob.shouldPrintMessage(5, "message");
		ob.shouldPrintMessage(6, "message");
		ob.shouldPrintMessage(7, "message");
		ob.shouldPrintMessage(8, "message");
		ob.shouldPrintMessage(9, "message");
		ob.shouldPrintMessage(10, "message");
		ob.shouldPrintMessage(14, "message");
		ob.shouldPrintMessage(17, "message");
		ob.shouldPrintMessage(19, "message");
		ob.shouldPrintMessage(20, "message");
		ob.shouldPrintMessage(24, "message");
		ob.shouldPrintMessage(40, "message");
		
	}
}
