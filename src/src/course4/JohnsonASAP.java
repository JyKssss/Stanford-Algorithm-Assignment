package course4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import course4.Bellman_Ford;
import course4.DijkstraDirected;
public class JohnsonASAP {
	static int nodeNum;
	static int edgeNum;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<edge> edgeList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\input_random_6_4.txt");
		ArrayList<node> nodeList = initialNodes(nodeNum, edgeList);
		ArrayList<node> dijkstraList = new ArrayList<node>();
		int min =Integer.MAX_VALUE;
		addSource(nodeList);
		int[] reWeightArray = Bellman_Ford.bellmanFord(nodeList, nodeList.size());
		reWeight(nodeList,reWeightArray);
		removeSource(nodeList);
		
		for (node startNode : nodeList) {
			dijkstraList = DijkstraDirected.Dijkstra(nodeList, startNode.nodeId);
			System.out.println(dijkstraList.size());
			min = min < returnMin(dijkstraList, reWeightArray, startNode.nodeId) ? min : returnMin(dijkstraList, reWeightArray, startNode.nodeId);
			System.out.println(min);
			resetDijkstraDistance(nodeList);
		}
		System.out.println("最小值是 ： " + min);
	}
	
	private static ArrayList<edge> readTxt(String txtpath) throws IOException {
		FileReader fileReader = new FileReader(txtpath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<edge> edgeList = new ArrayList<edge>();
		String line;
        line = bufferedReader.readLine();
        nodeNum = Integer.parseInt(line.split(" ")[0]);
        edgeNum = Integer.parseInt(line.split(" ")[1]);
        while ((line = bufferedReader.readLine())!= null) {
			edge addEdge = new edge(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]),Integer.parseInt(line.split(" ")[2]));
			edgeList.add(addEdge);
		}
		
		return edgeList;
		
	}
	
	private static ArrayList<node> initialNodes(int nodeNum,ArrayList<edge>edgelist) {
		ArrayList<node> nodelist = new ArrayList<node>();
		for (int i = 0; i < nodeNum; i++) {
			node addNode = new node(i+1);
			nodelist.add(addNode);
		}
		int head, tail,distance;
		node headNode, tailnode;
		
		for (edge edge : edgelist) {
			head = edge.getHead();
			tail = edge.getTail();
			distance = edge.getDistance();
			headNode = nodelist.get(head -1);
			tailnode = nodelist.get(tail-1);
			headNode.addOut(tailnode,distance);
			tailnode.addIn(headNode,distance);
		}
		return nodelist;
		
	}
	
	private static void addSource(ArrayList<node> nodeLists) {
		node sourceNode = new node(nodeLists.size()+1); 		
		for (node node : nodeLists) {
			node.addIn(sourceNode, 0);
			sourceNode.addOut(node, 0);
		}
		nodeLists.add(sourceNode);
	}
	//改变权重
	private static void reWeight(ArrayList<node> nodesList , int[] reWeightArray) {
		for (node node : nodesList) {
			HashMap<node, Integer> inNodes = node.getINodes();
			HashMap<node, Integer> outNodes = node.getoutNodes();
			for (node inNode : inNodes.keySet()) {
				int oldDis =inNodes.get(inNode);
				inNodes.replace(inNode, oldDis+reWeightArray[inNode.nodeId-1]-reWeightArray[node.nodeId-1]);
			}
			
			for (node outNode : outNodes.keySet()) {
				int oldDis =outNodes.get(outNode);
				outNodes.replace(outNode,oldDis+reWeightArray[node.nodeId-1]-reWeightArray[outNode.nodeId-1]);
			}
		}
	}
	// 去掉加入的距离为0的起始点
	private static void removeSource(ArrayList<node> nodesList ) {
		node sourceNode = nodesList.get(nodesList.size()-1);
		nodesList.remove(sourceNode);
		for (node node : nodesList) {
			node.getINodes().remove(sourceNode);
			node.getoutNodes().remove(sourceNode);
		}
	}
	
	//重置每个node的distance
	private static void resetDijkstraDistance(ArrayList<node> nodesList ) {
		for (node node : nodesList) {
			node.dijkDistance =0;
			node.explored =false;
		}
	}
	
	//返回真实的最小值
	private static int returnMin(ArrayList<node>nodesList ,int[] reweightArray , int startID ) {
		node startNode = nodesList.get(startID -1);
		int pU = reweightArray[startNode.nodeId-1];
		for (node node : nodesList) {
			int pV = reweightArray[node.nodeId-1];
			node.dijkDistance = node.dijkDistance+pV-pU;  
		}
		Collections.sort(nodesList);
		
		return nodesList.get(0).dijkDistance;
	}
}


class edge{
	int tailId;
	int headId;
	int distance;
	
	public edge(int tailId ,int headId, int distance) {
		// TODO Auto-generated constructor stub
		this.tailId = tailId;
		this.headId = headId;
		this.distance = distance;
	}
	public int getTail() {
		return this.tailId;
	}
	public void setTail(int tailId) {
		this.headId =tailId;
	}
	public int getHead() {
		return this.headId;
	}
	public void setHead(int headId) {
		this.headId =headId;
	}
	public int getDistance() {
		return this.distance;
	}
	public void setDistance(int distance) {
		this.distance =distance;
	}
}

class node implements Comparable<node>{
	int nodeId;
	int dijkDistance;
	boolean explored;
	HashMap<node, Integer> inNodes;
	HashMap<node, Integer> outNodes;
	public node(int id) {
		// TODO Auto-generated constructor stub
		this.nodeId =id;
		this.inNodes = new HashMap<node, Integer>();
		this.outNodes = new HashMap<node, Integer>();
		this.explored = false;
		this.dijkDistance = Integer.MAX_VALUE/3;
	}
	public HashMap<node, Integer> getINodes() {
		return this.inNodes;
	}
	public HashMap<node, Integer> getoutNodes() {
		return this.outNodes;
	}
	
	public void addIn(node addNode,int distance) {
		this.inNodes.put(addNode, distance);
	}
	public void addOut(node addNode,int distance) {
		this.outNodes.put(addNode, distance);
	}
	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		if (this.dijkDistance>o.dijkDistance) {
			return 1;
		}
		else if (this.dijkDistance==o.dijkDistance) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	
}