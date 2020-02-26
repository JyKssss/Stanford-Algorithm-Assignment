package course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Clustering1 {
static int numNode;
static int clusterPartNum =4;
static int maxMinLength ;
static ArrayList<nodeC> nodes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<edgeC>edgeList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\clustering1.txt");
		sortEdge(edgeList);
		int targetNum = numNode - clusterPartNum +1;
		int i =0;
		for (edgeC edge : edgeList) {
			nodeC head = edge.head;
			nodeC tail = edge.tail;
			nodeC headLeader = findLeader(head);
			nodeC tailLeader = findLeader(tail);
			if (headLeader.equals(tailLeader)==false & i<targetNum) {
				unionCluster(headLeader, tailLeader);
				i++;
			}
			if (i==targetNum) {
				if (headLeader.equals(tailLeader)==false) {
					maxMinLength = edge.length;
					break;
				}
				else {
					continue;
				}
				
			}
		}
		
		System.out.println("Maximum spacing of a 4-clustering is :  "+ maxMinLength);
	}
	
	private static ArrayList<edgeC> readTxt(String txtPath) throws IOException {
        FileReader fileReader = new FileReader(txtPath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<edgeC> edgeList = new ArrayList<edgeC>();
        String line;
        line = bufferedReader.readLine();
        System.out.println(line);
        numNode = Integer.parseInt(line);
        for (int i = 0; i < numNode; i++) {
        	nodeC addNode = new nodeC(i+1);
        	addNode.assignLeader(addNode);
			nodes.add(addNode);
		}
        while ((line = bufferedReader.readLine())!=null){
        	String[] htw=line.split(" ");
        	int headNum = Integer.parseInt(htw[0]);
        	int tailNum = Integer.parseInt(htw[1]);
        	edgeC addEdge = new edgeC(nodes.get(headNum-1), nodes.get(tailNum-1),Integer.parseInt(htw[2]));
        	edgeList.add(addEdge);
        }
        return edgeList;
    }
	
	private static void sortEdge(ArrayList<edgeC> edgeList) {
		Collections.sort(edgeList);
	}
	
	private static nodeC findLeader(nodeC node) {
		nodeC leader = node.leader;
		while (leader.leader.equals(leader)==false) {
			leader = leader.leader;
		}
		return leader;
	}
	
	private static void unionCluster(nodeC leader1, nodeC leader2) {
		if (leader1.clusterSize>= leader2.clusterSize) {
			leader2.assignLeader(leader1);
			leader1.clusterSize+=leader2.clusterSize;
		}
		else {
			leader1.assignLeader(leader2);
			leader2.clusterSize+=leader1.clusterSize;
		}
	}
}

class nodeC{
	int no;
	int clusterSize;
	nodeC leader;
	public nodeC(int no) {
		// TODO Auto-generated constructor stub
		this.no = no;
		this.clusterSize =1;
		this.leader =null ;
	}
	
	public void assignLeader(nodeC leader) {
		this.leader =leader;
	}
}

class edgeC implements Comparable<edgeC>{
	nodeC head;
	nodeC tail;
	int length;
	
	public edgeC(nodeC head, nodeC tail, int length) {
		// TODO Auto-generated constructor stub
		this.head = head;
		this.tail = tail;
		this.length = length;
	}

	@Override
	public int compareTo(edgeC o) {
		// TODO Auto-generated method stub
		if (this.length< o.length) {
			return -1;
		}
		else if (this.length == o.length) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
}