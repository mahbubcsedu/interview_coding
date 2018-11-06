package leetcode;

import java.util.StringTokenizer;

public class CompareVersion165 {

	
public static int compareVersion(String version1, String version2) 
{
	
	   StringTokenizer st1=new StringTokenizer(version1,".");
	   StringTokenizer st2=new StringTokenizer(version2,".");
	    
	   while(st1.hasMoreTokens() && st2.hasMoreTokens()) {
		   int v1=Integer.parseInt(st1.nextToken());
		   int v2=Integer.parseInt(st2.nextToken());
		   
		   if(v1> v2) {
			   return 1;
		   }else if(v2>v1) {
			   return -1;
		   }
	   }
	   
	   if(!st1.hasMoreTokens() && !st2.hasMoreTokens())
		   return 0;
	   //if anyone has more token but other than zero, that will be bigger else equal
	   if(st1.hasMoreTokens()) {
		   while(st1.hasMoreTokens()) {
			   if(Integer.parseInt(st1.nextToken())!=0)
				   return 1;
		   }
		   return 0;
	   }
	   
	   if(st2.hasMoreTokens()) {
		   while(st2.hasMoreTokens()) {
			   if(Integer.parseInt(st2.nextToken())!=0)
				   return -1;
		   }
		   return 0;
	   }
	   
	   //if(st1.hasMoreTokens() && (Integer.parseInt(st1.nextToken())!=0 || st1.hasMoreTokens()))
		//   return 1;
	   //else if(st2.hasMoreTokens() && (Integer.parseInt(st2.nextToken())!=0 || st2.hasMoreTokens()))
	   //   return -1;
	   else return 0;
	   
       // return 0;
}

public static void smallTest() {
	String version1 = "7.5.2.4", version2 = "7.5.3";
	/*System.out.println(compareVersion(version1,version2)==-1);
	
	version1 = "7.5.2.4"; version2 = "7.5.2";
	System.out.println(compareVersion(version1,version2)==1);
	
	version1 = "7.5.2.4"; version2 = "7.5.2.4";
	System.out.println(compareVersion(version1,version2)==0);
	
	version1 = "1.0.1"; version2 = "1";
	System.out.println(compareVersion(version1,version2)==1);
	
	version1 = "1.0.1"; version2 = "";
	System.out.println(compareVersion(version1,version2)==1);
	
	version1 = "1"; version2 = "0";
	System.out.println(compareVersion(version1,version2)==1);
	
	version1 = "1.0"; version2 = "1";
	System.out.println(compareVersion(version1,version2)==0);*/
	
	version1 = "1.0.0.0.0"; version2 = "1";
	System.out.println(compareVersion(version1,version2)==0);
	
	version1="19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
	version2="19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
	System.out.println(compareVersion(version1,version2)==0);
}


public static void main(String args[]) {
	smallTest();
}
}
