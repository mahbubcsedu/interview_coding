package hackerearth;
import java.util.*;
public class HolidaySeason {

	
	public static void main(String args[]) {
		
		
		// Write your code here
        Scanner s = new Scanner(System.in);
        //StringTokenizer st=new StringTokenizer(s.nextLine());
        
        int sLength=Integer.parseInt(s.nextLine());
        String str=s.nextLine();
        
        //System.out.println(str);
        
        //char p1,p2,p3,p4;
        int count=0;
        for(int i=0;i<str.length()-3;i++){
             
            for(int l=i+2;l<str.length()-1;l++)
             {
                //p2=str.charAt(i+1);
             if(str.charAt(i)==str.charAt(l))
                {
                 System.out.println(i+" "+l);
                for(int j=i+1;j<str.length()-2;j++)
                   {
                    
                    for(int k=l+1;k<str.length() && j<k;k++){
                        
                        if(str.charAt(l)==str.charAt(k))
                        {
                         count++;
                         System.out.println(i+":"+j+" "+l+" "+k);
                         //break;
                        }
                    }
                 
                }
             }
                //p4=str.charAt(i+3);
             }
             
            // while()
        }
        
        System.out.println(count);
        
	}
}
