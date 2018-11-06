package leetcode;
import java.util.*;

public class SerializeDeserializeN {

	static class Node{
		String value;
		List<Node> child;
		public Node(String val){
			this.value=val;
			child=new ArrayList<>();
		}
	}
	
	public static String serialize(Node root) {
	  List<String> nodeList=new LinkedList<>();
	  
	  serializeHelper(root, nodeList);
	  return String.join(",", nodeList);
	}
	
	public static void serializeHelper(Node root, List<String> list) {
		if(root==null) {
			return;
		}
		else {
			list.add(String.valueOf(root.value));
			list.add(String.valueOf(root.child.size()));
			for(Node n: root.child) {
				serializeHelper(n,list);
			}
		}
	}
	
	
	public static Node  deserialize(String data) {
		if(data.isEmpty())
			return null;
		String[] ss=data.split(",");
		Queue<String> q=new LinkedList<>(Arrays.asList(ss));
		
		return deserializeHelper(q);
	}
	
	public static Node deserializeHelper(Queue<String> q) 
	{
		//String 
		Node node= new Node(q.poll());
		int size=Integer.parseInt(q.poll());
		node.child=new ArrayList<>();
		
		for(int i=0;i<size;i++) {
			node.child.add(deserializeHelper(q));
		}
		return node;
	}
}
