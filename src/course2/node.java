package course2;

import java.util.ArrayList;

public class node {
	int nodeNo;
	ArrayList<Integer> linkedNodes;
	ArrayList<node> listNodes;
	boolean explored;
	
	public node(int no,int nodes) {
		// TODO Auto-generated constructor stub
		this.nodeNo =no;
		linkedNodes =new ArrayList<Integer>();
		listNodes = new ArrayList<>();
		this.linkedNodes.add(nodes);
		this.explored=false;
	}
	
	public node(int no) {
		// TODO Auto-generated constructor stub
		this.nodeNo =no;
		linkedNodes =new ArrayList<Integer>();
		listNodes = new ArrayList<>();
		this.explored=false;
	}
	
	public int getNo() {
		return this.nodeNo;
	}
	
	public ArrayList<Integer> getLinkedNodes() {
		return this.linkedNodes;
	}
	
	public ArrayList<node> getLinkNodes2() {
		return this.listNodes;
	}
	
	public boolean getExplored() {
		return this.explored;
	}
	
	public void addNodes(int k) {
		this.linkedNodes.add(k);
	}
	
	public void updateNo(int k) {
		this.nodeNo=k;
	}
	
	public void changExplored(boolean k) {
		this.explored =k;
	}
}
