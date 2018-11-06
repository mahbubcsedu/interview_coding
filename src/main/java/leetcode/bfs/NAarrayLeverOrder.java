package leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NAarrayLeverOrder {

	static public class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val,List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public static List<List<Integer>> levelOrder(Node root) {

		List<List<Integer>> result=new ArrayList<>();

		if(root==null)
			return result;

		//List<Node> head=root.children;

		Queue<Node> q=new LinkedList<>();

		List<Integer> temp=new ArrayList<>();

		//temp.add(root.val);
		//result.add(temp);
		q.add(root);

		while(!q.isEmpty()) {
			temp=new ArrayList<>();
			//keep how long will have to iterate for current level
			int size=q.size();
			//second while loop will run until size becomes zero
			while(size>0) {
				Node curNode=q.poll();
				temp.add(curNode.val);
				//List<Node> children=curNode.children;

				//if(children==null)
				//	continue;
				if(curNode.children!=null) 
				{
					Iterator<Node> children=curNode.children.iterator();
					while(children!=null && children.hasNext()) 
					{
						q.add(children.next());
					}
				}
				size--; //processed one node in the queue
			}
			//now the current level finished
			//so add this to final results
			result.add(new ArrayList<>(temp));

		}

		return result;
	}

	
	
	public void traverse(Node node,int depth, List<List<Integer>> levels){
        if(node == null) 
        	return ;
        if(depth==levels.size()) 
        	levels.add(new ArrayList<>());
        
        levels.get(depth).add(node.val);
        
        for(int i=0; i<node.children.size();i++){
            traverse(node.children.get(i), depth+1, levels);
        }
    }
    
    public List<List<Integer>> levelOrderFastest(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        /*
        if(root == null) return ans;
        List<Node> level = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        level.add(root); vals.add(root.val);
        while(!level.isEmpty()){
            ans.add(vals);
            List<Node> nxt_nodes = new ArrayList<>();
            List<Integer> nxt_vals = new ArrayList<>();
            
            for(int i=0; i<level.size(); i++){
                Node node = level.get(i);
                for(int j=0; j<node.children.size();j++){
                    nxt_nodes.add(node.children.get(j));
                    nxt_vals.add(node.children.get(j).val);
                }
            }
            
            level = nxt_nodes;
            vals = nxt_vals;
        }
        */
        traverse(root, 0, ans);
        return ans;
    }

	public static void main(String args[]) {
		Node root=new Node(1, null);

		/**
		 * Tree creation in this way still has problems, will see later
		 */
		//Node curHead=root;
		List<Node> child=new ArrayList<>();
		child.add(new Node(3,null));
		child.add(new Node(2,null));
		child.add(new Node(4,null));
		root.children=child;

		child=new ArrayList<>();
		child.add(new Node(5,null));
		child.add(new Node(6,null));
		root.children.get(0).children=child;

		child=new ArrayList<>();
		child.add(new Node(2,null));
		//child.add(new Node(6,null));
		root.children.get(1).children=child;

		child=new ArrayList<>();
		child.add(new Node(4,null));
		//child.add(new Node(6,null));
		root.children.get(2).children=child;



		List<List<Integer>> res=levelOrder(root);
		System.out.println(Arrays.deepToString(res.toArray()));


	}

}
