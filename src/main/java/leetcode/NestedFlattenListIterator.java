package leetcode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedFlattenListIterator implements Iterator{

	Deque<NestedInteger> stack=new LinkedList<>();
	
	public NestedFlattenListIterator(List<NestedInteger> nestedList) {
		for(int i=nestedList.size()-1;i>=0;i--) {
			stack.push(nestedList.get(i));
		 }
	
		}
	
	@Override
	public Integer next() {
		return stack.pop().getInteger();
	}
	
	@Override
	public boolean hasNext() {
		while(!stack.isEmpty()) {
			NestedInteger cur=stack.peek();
			if(cur.isInteger()) {
				return true;
			}
			stack.pop();
			for(int i=cur.getList().size()-1;i>=0;i--) 
			{
				stack.push(cur.getList().get(i));
			}
		}
		return true;
	}
	
	//we don't know how it is implemented, so don't know how to do some test cases on it
	
	public interface NestedInteger {
		 
		      // @return true if this NestedInteger holds a single integer, rather than a nested list.
		      public boolean isInteger();
		 
		      // @return the single integer that this NestedInteger holds, if it holds a single integer
		      // Return null if this NestedInteger holds a nested list
		      public Integer getInteger();
		 
		     // @return the nested list that this NestedInteger holds, if it holds a nested list
		      // Return null if this NestedInteger holds a single integer
		      public List<NestedInteger> getList();
		  }
}
