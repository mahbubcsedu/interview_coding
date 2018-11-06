package leetcode.dp;

class NumArray {

	int[] sumTable;
	
    public NumArray(int[] nums) {
    
    	sumTable=new int[nums.length+1];
    	sumTable[0]=0;
       //process once using dp or Segment tree
       for(int i=0;i<nums.length;i++) {
    	   sumTable[i+1]=sumTable[i]+nums[i];
       }
    }
    
    public int sumRange(int i, int j) {
    	
    	if(i>j)
    		return 0;
    	if(i>sumTable.length || j+1> sumTable.length)
    		return 0;
    	
		return sumTable[j+1]-sumTable[i];
        
    }
    
    
    public static void main(String agrs[]) {
    	int input[] = {-2, 0, 3, -5, 2, -1};
    	NumArray na=new NumArray(input);
    	
    	System.out.println(na.sumRange(0, 2));
    	System.out.println(na.sumRange(2, 5));
    	System.out.println(na.sumRange(0, 5));
    	
    }
}
