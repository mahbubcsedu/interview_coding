package leetcode;

import java.util.HashMap;

public class LRUWithoutLib {
	//double linkedlist node
	class Node{
        Node prev, next;
        int val, key;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    int capacity;
    HashMap<Integer, Node> map; //keep key-->node relations to search O(1) time
    Node head;
    Node tail;
    
    public LRUWithoutLib(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            insertHead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            insertHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if(map.size() <= this.capacity) {
                insertHead(node);
            } else {
                // Overfitting
                map.remove(tail.prev.key);
                removeNode(tail.prev);
                insertHead(node);
            }
        }
    }
    //insert recently used value in front of the list
    private void insertHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    
    public static void main(String args[]) {
    	LRUWithoutLib obj = new LRUWithoutLib(2);
    	
    	System.out.println("operations");
    	int param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(1,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(2,1);
    	obj.put(2,2);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(2);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(3,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(2);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(4,1);
    	//System.out.printf("Search result: %d",param_1);
    	param_1 = obj.get(1);
    	System.out.printf("Search result: %d",param_1);
    	obj.put(5,1);
    	//System.out.printf("Search result: %d",param_1);
    	//int param_1 = obj.get(1);
    	//int param_1 = obj.get(1);
    	//int param_1 = obj.get(1);
    	obj.put(2,2);
    }
}
