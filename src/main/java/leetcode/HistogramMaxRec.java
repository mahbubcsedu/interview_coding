package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class HistogramMaxRec {

	
	
public static int largestRectangleDc(int[] heights) {
	
	return calculateArea(heights, 0, heights.length-1);
		
		
	}

public static int calculateArea(int[] heights, int start, int end) {
	if(start>end) {
		return 0;
	}
	int minIndex=start;
	
	for(int i=start;i<=end;i++) {
		if(heights[minIndex] > heights[i])
			minIndex=i;
	}
	return Math.max(heights[minIndex]*(end-start+1), Math.max(calculateArea(heights,start, minIndex-1), calculateArea(heights, minIndex+1,end)));
	
}




	public static int largestRectangle(int[] heights) {
		
		Deque<Integer> stack=new LinkedList<>();
		
		int maxArea=0;
		
		for(int i=0;i<=heights.length;i++) 
		{
			//
			if(!stack.isEmpty() && i< heights.length && heights[i]==heights[stack.peekFirst()]) 
			{
				stack.remove();
				stack.addFirst(i);;
				//maxArea=Math.max(maxArea, (i-stack.peek()-1)*heights[stack.pop()]);
				
			}
			
		while(!stack.isEmpty() && isNewOrReachedEnd(heights,i,stack.peekFirst())) {
			int height=heights[stack.removeFirst()];
			int width=stack.isEmpty()? i : i-stack.peekFirst()-1;
			maxArea=Math.max(maxArea, height*width);
		 }
		
		stack.addFirst(i);
		}
		
		return maxArea;
	}
	
	
	public static boolean isNewOrReachedEnd(int[] heights, int curIndex, int lastIndex) {
		//if we reached last or we have got something that is lower than previous one, so, time to calculate area and we cannnot past this point
		
		return curIndex < heights.length ? heights[curIndex] < heights[lastIndex] : true;
	}
	 public static  int largestRectangleArea(int[] heights) {
	        Stack < Integer > stack = new Stack < > ();
	        stack.push(-1);//this is important because, if stack top is empty, then the formula stack.peek() will return -1 which is usefull to get non zero value
	        int maxarea = 0;
	        for (int i = 0; i < heights.length; ++i) {
	            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) 
	            {
	                maxarea = Math.max(maxarea, heights[stack.peek()] * (i - stack.peek() - 1));
	                stack.pop();
	            }
	            stack.push(i);
	        }
	        while (stack.peek() != -1)
	            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
	        return maxarea;
	    }
	public static void main(String args[]) {
		int[] hist= {2,1,2,3,4,5,2,2,3,4,5};
		int max=largestRectangle(hist);
		System.out.println(max);
		
	}
}
