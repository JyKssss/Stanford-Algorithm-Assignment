package course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PrimMST {
static int numNode;
static int numEdge;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<edge> edgeList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\edges.txt");
		ArrayList<node> nodesList = initializeNodes(edgeList);
		ArrayList<node> nodeMST = new ArrayList<node>();
		ArrayList<node> nodeRemain = new ArrayList<node>();
		ArrayList<edge> edgeMST = new ArrayList<edge>();
		
		nodeRemain.addAll(nodesList);		
		nodeMST.add(nodesList.get(0));
		nodesList.get(0).inMST =true;
		nodeRemain.remove(nodesList.get(0));
		initializeShortest(nodesList, nodesList.get(0));
		while (nodeMST.size()<numNode) {
			node addNode = findShortest(nodeRemain);
			addNode.inMST =true;
			edgeMST.add(addNode.shortestEdge);
			nodeMST.add(addNode);
			nodeRemain.remove(addNode);
			updateShortest(nodesList, addNode);
		}
		long sumWeight =0;
		for (edge edge : edgeMST) {
			sumWeight+=edge.weight;
		}
		System.out.println("Total Sum Weight of MST :  "+ sumWeight);
		
		
	}
	private static ArrayList<edge> readTxt(String txtPath) throws IOException {
        FileReader fileReader = new FileReader(txtPath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<edge> edgeList = new ArrayList<edge>();
        String line;
        line = bufferedReader.readLine();
        System.out.println(line);
        numNode = Integer.parseInt(line.split(" ")[0]);
        numEdge = Integer.parseInt(line.split(" ")[1]);
        while ((line = bufferedReader.readLine())!=null){
        	String[] htw=line.split(" ");
        	edge addEdge = new edge(Integer.parseInt(htw[0]), Integer.parseInt(htw[1]), Integer.parseInt(htw[2]));
        	edgeList.add(addEdge);
        }
        return edgeList;
    }
	
	private static ArrayList<node> initializeNodes(ArrayList<edge> edgeList) {
		ArrayList<node> nodesList = new ArrayList<node>();
		for (int i = 0; i < numNode; i++) {
			node addNode = new node(i+1);
			nodesList.add(addNode);
		}
		for (edge edge : edgeList) {
			int head = edge.head;
			int tail = edge.tail;
			nodesList.get(head-1).addEdges(edge);
			nodesList.get(tail-1).addEdges(edge);
		}
		
		return nodesList;
		
	}
	
	private static void initializeShortest(ArrayList<node>nodesList,node startNode) {
		int nodeNum = startNode.nodeNum;
		ArrayList<edge> edges = startNode.linkedEdges;
		for (edge edge : edges) {
			int linkNodeNum = edge.head == nodeNum ? edge.tail: edge.head;
			nodesList.get(linkNodeNum-1).shortestLinkNode = startNode;
			nodesList.get(linkNodeNum-1).shortestPath = edge.weight;
			nodesList.get(linkNodeNum-1).shortestEdge =edge;
		}
	}
	
	private static void updateShortest(ArrayList<node>nodesList,node addNode) {
		int nodeNum = addNode.nodeNum;
		ArrayList<edge> edges = addNode.linkedEdges;
		for (edge edge : edges) {
			int linkNodeNum = edge.head == nodeNum ? edge.tail: edge.head;
			node potentialNode = nodesList.get(linkNodeNum-1);
			if (potentialNode.inMST ==false) {
				if (potentialNode.shortestPath>edge.weight) {
					potentialNode.shortestPath =edge.weight;
					potentialNode.shortestLinkNode = addNode;
					potentialNode.shortestEdge =edge;
				}
			}
		}
	}
	
	//也可以用Comparable接口来计算最短边
	private static node findShortest(ArrayList<node> remainNodes) {
		node targetNode =null;
		int weight = Integer.MAX_VALUE;
		for (node node : remainNodes) {
			if (node.inMST==false & (node.shortestPath<weight)) {
				targetNode =node;
				weight = node.shortestPath;
			}
		}
		return targetNode;
		
	}
	//接口排序
	private static node findShortest2(ArrayList<node> remainNodes) {
		Collections.sort(remainNodes);
		
		return remainNodes.get(0);
	}
}

class edge{
	int head;
	int tail;
	int weight;
	
	public edge(int head, int tail, int weight ) {
		// TODO Auto-generated constructor stub
		this.head = head;
		this.tail = tail;
		this.weight = weight;
	}
	
}

class node implements Comparable<node>{
	int nodeNum;
	int shortestPath;
	node shortestLinkNode;
	edge shortestEdge;
	ArrayList<edge> linkedEdges;
	boolean inMST;
	
	public node(int nodenum) {
		// TODO Auto-generated constructor stub
		this.nodeNum = nodenum;
		this.shortestPath = Integer.MAX_VALUE;
		this.shortestEdge =null;
		this.shortestLinkNode = null;
		this.linkedEdges = new ArrayList<edge>();
		inMST =false;
	}
	
	public void addEdges(edge addingEdge) {
		this.linkedEdges.add(addingEdge);
	}

	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		if (this.shortestPath> o.shortestPath) {
			return 1;
		}
		else if (this.shortestPath== o.shortestPath) {
			return 0;
		}
		else {
			return -1;
		}
		
	}
}

