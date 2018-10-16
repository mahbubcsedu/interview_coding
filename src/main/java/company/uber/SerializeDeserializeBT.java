package company.uber;

import java.util.StringTokenizer;

public class SerializeDeserializeBT {

	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode(int value){
			this.value=value;
			this.left=null;
			this.right=null;
		}
	}
	StringBuilder sb =new StringBuilder();
	
	public void serialize(TreeNode root, StringBuilder sb) {
		
		if(root ==null) {
			sb.append(root.value+" ");
			serialize(root.left,sb);
			serialize(root.right,sb);
		}
	}
	
	public TreeNode deSerialize(String s) {
		if(s==null || s.length()==0) return null;
		StringTokenizer st=new StringTokenizer(s," ");
		return deSerialize(st);
		
	}
	
	public TreeNode deSerialize(StringTokenizer st) {
		if(!st.hasMoreTokens())
			return null;
		String val=st.nextToken();
		if(val.equals("#"))
			return null;
		
		TreeNode root =new TreeNode(Integer.parseInt(val));
		root.left=deSerialize(st);
		root.right=deSerialize(st);
		return root;
	}
	
	public static void main(String args[]) {
		
	}
	//
}
