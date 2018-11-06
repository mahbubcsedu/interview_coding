package leetcode;
import java.util.*;


public class ErectFence587 {

	static class Point{
		int x;
		int y;
		Point(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public List<Point> outTrees(Point[] points){
	
		List<Point> res=new ArrayList<>();
		Set<Point> hull=new HashSet<>();
		
		//at least 3 points are needed to create a 2d space
		if(points.length<4) {
			for(Point p: points) {
				hull.add(p);
			}
			return new ArrayList<>(hull);
		}
		
		int left_most=0;
		//find the leftmost point
		for(int i=0;i<points.length;i++) {
			if(points[i].x < points[left_most].x) {
				left_most=i;
			}
		}
		
		int p=left_most; //keep the leftmost points for later comparison to come here
		
		do 
		{
			int q=(p+1)%points.length; //we dont know which one is leftmost, so, to access all, we can use modulo to access circular way
			for(int i=0; i<points.length;i++) 
			{
				if(orientation(points[p], points[i],points[q]) < 0) 
				{
					//q is more counterclosewise
					q=i;
				}
			}
			
			for(int i=0;i<points.length;i++) 
			{
				if(i!=p && i !=q && orientation(points[p], points[i],points[q])==0 && inBetween(points[p], points[i], points[q])) {
					hull.add(points[i]);//only add if these all condidtion fullfilled
				}
			}
			
			hull.add(points[q]);
			p=q;
		}while(p!=left_most);
		
		return new ArrayList<>(hull);
		
		
		
	}
	
	/**
	 * Jarvis algorithm depends on the orientation principle, which finds out that if you are at point p,
	 * and you are considering two points q and r which is more counterclockwise, one will return positive and other return negative
	 * @see Cormen book
	 * 
	 * Jarvis, algorithm check every points in the set and find out which one is more counterclockwise and select the top one for the next 
	 * step and thus cover all the boundary
	 * 
	 */
	
	
	public int orientation(Point p, Point q, Point r) {
		return (q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
		//for cross product matrix see the coremen as well vector pq and pr
	}
	
	/**
	 * Now, another problem could be that, two points may fall on same counterclockwise related to p, so, we need to find out the first
	 * one or the one which sits between p and j and we will do the same processing again. This ensure us that we are not missing anypoints
	 * on the boundary
	 */
	
	public static boolean inBetween(Point p, Point i, Point q) {
		//we will check which point is in between i or j
		//we will check for x coordinate first whether the i is in between p and q
		boolean a=i.x >=p.x && i.x <=q.x || i.x <=p.x && i.x >=q.x;
		//we will also check for the y axis
		boolean b=i.y>=p.y && i.y <=q.y || i.y <= p.y && i.y >=q.y;
		
		//if both are true, i is inbetween
		return a && b;
	}
	
}
