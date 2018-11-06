package leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache {
/**
 * same process LRU can be applied here but update the position by frequency, so, if we use 
 * double linkedlist, we have to search for frequency rather than used and we cannot directly 
 * remove the node and put to the front. 
 * 
 * But double linked list node can be accessed O(1) time if we have to node but where we will
 * put the node. Lets say (2,6) (3,4) (4,3) (5,1) now 5 is used, so, its frequency will be 2
 * and still be there, next access to 5 will be tie with 4 and 4 have to be removed on next insert
 * 
 * 
 * Only think I can think of is, the items are sorted based on the frequency in double linked list
 * So, if one is access, its frequency will increased by one and it will still remain less than
 * it's predecessor or if equal, will swap position. If, for insert case, after swap last element
 * will be evicted
 */

 class Node{
	 Node prev,next;
	 int value;
	 int key;
	 int frequency;
	 Node(int key, int value,int frequency){
		 this.value=value;
		 this.frequency=1;
		 this.next=null;
		 this.prev=null;
		 this.key=key;
		 
	 }
	 
 }
 
 
 Map<Integer, Node> nodeMap=new HashMap<>();
 Node head, tail;
 int capacity;
 Map<Integer, Integer> cache;
 //Each frequency will have a mapping to a set same frequency as the frequency with same, we need to find
 //the insert order which linkedHashSet will maintain.
 Map<Integer,LinkedHashSet<Node>> insertOrder=new HashMap<>();
 
 public LFUCache(int capacity) {
	 
	 this.capacity=capacity;
	 this.head.next=tail;
	 this.tail.prev=head;
	 head=new Node(0,0,0);
	 tail=new Node(0,0,0);
	 this.cache=new HashMap<>();
	 //intialize the frequency order holder with frequency 1 and ampty set,
	 
	 insertOrder.put(1, new LinkedHashSet<>());//
 }
 
 public void put(int key, int value) 
 {
	 if(!nodeMap.containsKey(key)) 
	 {
		 
		 Node nNode=new Node(key,value,0);
		 nodeMap.put(key, nNode);
		 
		 addNodeToTail(nNode);
		 updateNode(nNode);//after add check for last two node for equal frequency,if so, swap
		 
	 }else {
		 Node nNode=nodeMap.get(key);
		 nNode.frequency=nNode.frequency+1;
		 //nodeMap.put(key, nNode);
		 
		 updateNode(nNode); 
	 }
	 
	 if(nodeMap.size()>this.capacity) {
		 removeTail();
	 }
 }
 
 public void addNodeToTail(Node n) {
	 tail.prev.next=n;
	 n.prev=tail.prev;
	 n.next=tail;
	 tail.prev=n;
 }
 
 public void removeTail() {
	 
	 tail.prev.prev.next=tail;
	 tail.prev=tail.prev.prev;
	 tail.next=null;
	 
 }
 public void updateNode(Node n) {
	 //n.frequency=n.frequency+1;
	 //find the set with frequency that the current node currently is
	 //before this we need to check for existence
	 
	 insertOrder.get(n.frequency).remove(n);
	 n.frequency=n.frequency+1;
	 insertOrder.get(n.frequency).add(n);
	 
	 
	 //swap the node with same frequency
	 if(n.frequency>=n.prev.frequency) {
		 n.prev.next=n.next;
		 n.next.prev=n.prev;
		 
		 n.prev.prev.next=n;
		 n.prev.prev=n;
		 n.next=n.prev;
		 n.prev=n.prev.prev;
		 	 
	 }
	 
 }
 
}
