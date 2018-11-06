package leetcode.bfs;
import java.util.*;

/*
 * https://leetcode.com/problems/reconstruct-itinerary/discuss/126425/Graph-Based-Solution-15-ms
 * 
 * Note: still not correct, wrong results
 */
public class ReconstructItinerary332 {
	
	class City implements Comparable<City>{
	    String source;
	    ArrayList<City> destination;
	    
	    public City(String source){
	        this.source = source;
	        destination = new ArrayList<City>();
	    }
	    
	    public void addEdge(City city2){
	        this.destination.add(city2);
	    }
	    
	    //public int compare(City o1, City o2) {
	     //   return o1.source.compareTo(o2.source);
	    //}

		@Override
		public int compareTo(City o) {
			// TODO Auto-generated method stub
			return this.source.compareTo(o.source);
		}
	}
	
	
	
	public boolean dFS(City source, int counter,List<String> result){
        if(counter == 0) {
            return true;
        }
        
        ArrayList<City> destination = new ArrayList<>(source.destination);
        
        for(int i=0; i<destination.size(); i++){
            City neighbor = destination.get(i);
            result.add(neighbor.source);
            destination.remove(neighbor);
            if(dFS(neighbor, counter-1,result))
                return true;
            result.remove(result.size()-1);
            destination.add(i, neighbor);
        }
        return false;
    }
	
	public List<String> findItinerary(String[][] tickets) 
	{
        HashMap<String, City> travelled = new HashMap<String, City>();
        
        List<String> result=new ArrayList<>();
        
        City source = new City("JFK");
        
        travelled.put("JFK", source);
        //build the graph as key value pair [city--- list of destination]
        
        for(int i=0; i<tickets.length; i++)
        {
            if(!travelled.containsKey(tickets[i][0])){
            	travelled.put(tickets[i][0], new City(tickets[i][0]));   
            }
            if(!travelled.containsKey(tickets[i][1])){
            	travelled.put(tickets[i][1], new City(tickets[i][1]));   
            }
            //if they exists, then add as neibour or destination
            travelled.get(tickets[i][0]).addEdge(travelled.get(tickets[i][1]));
        }
        result.add("JFK");
        //for(String key : travelled.keySet())
        //    Collections.sort(travelled.get(key).destination);//, new CustomComparator());
        dFS(source, tickets.length,result);
        return result;
    }
	
	
	//you have to have global variable as this will determine the breaking condition of the dfs loop
	//if I put the condition on bfs loop, eventually, it will have different value for different loop and the fixed storage result 
	//value will be updated based on the loop and utilmately will return wrong results
	int totalTickets;
	int ticketUsed;
	
	public List<String> findItinerary2(String[][] tickets){
		 this.totalTickets=tickets.length;
		 this.ticketUsed=0;
		
		Map<String,List<String>> routMap=new HashMap<>();
		for(String[] singleRoute:tickets) {
			if(!routMap.containsKey(singleRoute[0])) {
				
				List<String> neighbors=new ArrayList<>();
				neighbors.add(singleRoute[1]);
				//you cannnot add inside hashmap put as the add arraylist return boolean
				routMap.put(singleRoute[0], neighbors);
				
			}else {
				routMap.get(singleRoute[0]).add(singleRoute[1]);
			}
		} //now we have build the graph, we can now DFS on  this graph and lexical order should be maintained as second importance
		
		for(String key:routMap.keySet()) {
			//sort hashmap keyset in natural order of String which is itself lexical order
			Collections.sort(routMap.get(key));
		}
		
		//now will start processing the part and start with JFK
		
		List<String> result=new ArrayList<>();
		String start="JFK";
		result.add(start);
		dfsItinerary(routMap,start,result);
		return result; //we assuming here that there will be at least a way otherwise, empty set will be returnd
		//return 
	}

	
	public void dfsItinerary(Map<String, List<String>> routeMap,String key, List<String> res) {
		//the new rout is not available and we are at deadend or end
		if(!routeMap.keySet().contains(key)) {
			return;
		}
		
		//if(totalTicket < ticketUsed)
		//	return;
		
		//the keys are sorted lexically and we will try all of them one by one to find the route until we visit all the routes 
		for(int i=0; i<routeMap.get(key).size();i++) {
			String nextStart=routeMap.get(key).get(i);
			res.add(nextStart);
			routeMap.get(key).remove(i);//we will remove that to maintain that we already have visited
			this.ticketUsed++;
			//we call dfs for next stop if we have tickets
			
			
			
			dfsItinerary(routeMap,nextStart,res);
			
			if(this.totalTickets==ticketUsed) 
				return;
			//}else {
			//if not and also we have bounce back from dfs call, this path does not have the solutions and we can try using different path
			this.ticketUsed--;
			//we will put back the selected destination selected before and will go for i+1
			routeMap.get(key).add(i,nextStart);
			res.remove(res.size()-1);//we also will remove the last added path from the final itinerary		
			//}
		}
	}
	
	
	
	
	/**
	 * fastest solution in leetcode
	 * @param args
	 */
	
	
	Map<String, PriorityQueue<String>> map;
    LinkedList<String> result = new LinkedList<>();
    
    public List<String> findItinerary3(String[][] tickets) {
         map = generateMapping(tickets);
         dfs("JFK");
        return result;
    }
    
    public Map<String, PriorityQueue<String>> generateMapping(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
         for (String[] ticket: tickets) {
            String key = ticket[0];
            if (!map.containsKey(key)) {
                PriorityQueue<String> pQueue = new PriorityQueue<String>();
                map.put(key, pQueue);
            }
            map.get(key).add(ticket[1]);
        }
        return map;
    }
    //start with source and then again go for any options for all city
    public void dfs(String source) {
        
            PriorityQueue<String> destinations = map.get(source);
            
            while (destinations != null && !destinations.isEmpty()) {
                dfs(destinations.poll());
            }
            result.addFirst(source);
        
    }
	public static void main(String args[]) {
		String[][] tickets= {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		
		ReconstructItinerary332 sn=new ReconstructItinerary332();
		List<String> res=sn.findItinerary(tickets);
		System.out.println(Arrays.deepToString(res.toArray()));
		
		String[][] t2= {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		List<String> res2=sn.findItinerary2(t2);
		System.out.println(Arrays.deepToString(res2.toArray()));
	}
	
	}
	
	
	
	

