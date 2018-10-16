package company.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimultaneousEvents {

   public static class Event{
	   int start;
	   int end;
	   public Event(int s,int e){
		   this.start=s;
		   this.end=e;
	   }
	   
   }
   
   /**
    * Each event has two end points, but we are consider both side, so we will create another class which will consider the both end
    * points and it's time.
    * Instance variable time= start or end point time
    * boolean isStart will be true if its start of the event otherwise false
    * @author mahbub
    *
    */
   private static class EndPoint implements Comparable<EndPoint>{
	   public int time;
	   boolean isStart;
	   /**
	    * So, we will sort based on this comparison, if both time different from each other, order based on the time
	    * otherwise, if this is isStart and next is not, keep the order, otherwise, if the next one is isStart and current is endtime, 
	    * swap, otherwise do nothing
	    */
	   public int compareTo(EndPoint e) {
		   if(e.time !=time) {
			  return Integer.compare(time, e.time); 
		   }
		   return isStart & !e.isStart ? -1 : !isStart && e.isStart ? 1: 0;
	   }
	   
	   public EndPoint(int time, boolean isStart){
		   this.time=time;
		   this.isStart=isStart;
	   }
   }
   
   public static int findMaxSimulteneousEvents(List<Event> A) {
	   List<EndPoint> e=new ArrayList<>();
	   
	   for(Event event: A) {
		   e.add(new EndPoint(event.start, true));
		   e.add(new EndPoint(event.end, false));
	   }
	   
	   Collections.sort(e);
	   
	   int maxSimEvent=0;
	   int numSimEvent=0;
	   
	   for(EndPoint end: e) {
		   if(end.isStart) {
			   numSimEvent++;
			   maxSimEvent=Math.max(maxSimEvent, numSimEvent);
		   }
		   else numSimEvent--;
	   }
	   return maxSimEvent;
   }
	
   
   public static void main(String args[]) {
	   List<Event> A=new ArrayList<>();
	   
	   Event e=new Event(1,2);
	   A.add(e=new Event(2,4));
	   A.add(e=new Event(5,7));
	   A.add(e=new Event(4,9));
	   A.add( e=new Event(1,7));
	   A.add(e=new Event(1,3));
	   A.add(e=new Event(4,5));
	   //A.add(new Event(1,2));
	   
	   System.out.println(findMaxSimulteneousEvents(A));
   }
}
