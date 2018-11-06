package company.uber;

public class FindCycleInALinkedList {
 static class ListNode{
	 int val;
	 ListNode next;
	 
	 public ListNode(int val) {
		 this.val=val;
		 this.next=null;
	 }
 }
 
 public static boolean hasCycle(ListNode head) {

	 ListNode slow=head;
	 ListNode fast=head;
	 /**
	  * slow.next!=null will first evaluate, so only one node will be enough to check the cycle
	  * if we ignore slow.next!=null check, and list has one element it will fail if it has no cycle
	  * Also need a check for null
	  */
	 if( head== null) return true;
	 //you cannot get the cycle if you check fast.next.next and slow.next.next as for these cases you will require at least 4 nodes
	 //so check for current node as null if not if it's next is null, you are fine to navigate to the next with this check
	 while(slow!=null && fast.next!=null)
	 {
		 fast=fast.next.next;
		 slow=slow.next;
		 if(fast==slow) 
			 return true;
	 }
	 return false;
 }
 
 public static int getCyclePos(ListNode head) {
	 ListNode slow=head;
	 ListNode fast=head;
	 /**
	  * slow.next!=null will first evaluate, so only one node will be enough to check the cycle
	  * if we ignore slow.next!=null check, and list has one element it will fail if it has no cycle
	  * Also need a check for null
	  */
	 if( head== null)
		 return -1;
	 
	 while(slow.next!=null && fast.next.next!=null)
	 {
		 fast=fast.next.next;
		 slow=slow.next;
		 if(fast==slow) 
			 break;
	 }
	 
	 slow=head;
	 
	 int count=0;
	 
	 while(slow!=fast) {
		 slow=slow.next;
		 fast=fast.next.next;
		 count++;
	 }
	 return count;
 }
 public static void main(String args[]) {
	 ListNode t=new ListNode(1);
	 t.next=new ListNode(2);
	 t.next.next=new ListNode(3);
	 t.next.next.next=new ListNode(4);
	 t.next.next.next=t.next.next;
	 
	 
	 
	 //System.out.println(hasCycle(t) ? "has cycle":"no cycle");
	 
	 if(hasCycle(t)) {
	 System.out.println(getCyclePos(t));
	 }
 }
 
}
