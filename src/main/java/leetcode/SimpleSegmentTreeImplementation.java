package leetcode;

import java.util.Arrays;

public class SimpleSegmentTreeImplementation {

	//define segment tree node and put different instance variable based on the requirements
	class Node{
		int sum;
		int min;
		//we will store value which will be propagated lazily
		Integer pendingVal = null;
		int from;
		int to;
		
		int size() {
			return to-from+1;
		}
	}
	
	
	//this will store the actual tree
	private Node[] heap;
	//the array is the input array to build the tree
	private int[] array;
	private int size;
	
	//construct a segment tree first
	public SimpleSegmentTreeImplementation(int[] array) {
		this.array=Arrays.copyOf(array, array.length);
		//the max size of the segment tree will be 2*2^log2(n)+1
		size=(int)(2*Math.pow(2.0, Math.floor((Math.log((double) array.length)/Math.log(2.0))+1)));
		heap = new Node[size];//this is actual segment tree storage and keeping the actual array intact
		//now we will build the tree
		build(1,0,array.length);
	}
	
	//size will return actual array size
	public int size() {
		return array.length;
	}
	//build will take actual array and create the heap or actual segment tree
	//recursively build segment tree starting from range from and until size
	private void build(int v, int from, int size) {
		
		heap[v] = new Node();
		heap[v].from=from;
		heap[v].to= from + size-1;
		
		if(size==1) {
			heap[v].sum=array[from];
			heap[v].min=array[from];
		}else {
			build(2*v,from,size/2);
			build(2*v+1,from+size/2,size-size/2);
			
			//these are customizable based on problem, here we handle sum query and min range query
			heap[v].sum = heap[2*v].sum + heap[2*v+1].sum;
			heap[v].min = Math.min(heap[2*v].min , heap[2*v+1].min);			
		}
	}
	
	/**
	 * Update sigle node
	 * This requires to go that specific node and change the value
	 * All precedent node value should be updated as well and we can do this recursively
	 */
	
	private void updateSingleNode(int Node, int value) {
		updateSingleNode(1,0,array.length-1, value);
	}
	
	
	private void updateSingleNode(int v, int to, int from,int value) {
		Node n=heap[v];
		if(n.size()==1) {
			n.sum=n.sum+value;
			n.min=n.min+value;
			return;
		}
	   //Todo update to continue after dynamic problem finished
	}
	/**
	 * Update can be done each time a node value changes or it can be done lazyly only when a query is done
	 */
	
	private void updateInstant(int from, int to, int value) {
		updateInstant(1, from, to , value);
	}
	
	private void updateInstant(int v, int from, int to, int value) {
		//The node of the heap tree represented a range of the array with bounds [n.from, n.to]
		Node n=heap[v];
		
	}
	
	
	
	
	
}
