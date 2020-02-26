package course3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HuffmanCode {
static int nodesNum;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<nodeHuffman> nodesList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\huffman.txt");
		ArrayList<nodeHuffman> treeList = new ArrayList<nodeHuffman>();
		ArrayList<nodeHuffman> smallest2Nodes = new ArrayList<nodeHuffman>(); 
		int returnsize;
		while (true) {
			smallest2Nodes = find2SmallestNodes(nodesList, treeList);
			returnsize = smallest2Nodes.size();
			if (returnsize<=1) {
				break;
			}
			merge(smallest2Nodes, nodesList, treeList);
		}
		nodeHuffman root = treeList.get(0);
		System.out.println(root.weight);
	}
	
	private static ArrayList<nodeHuffman> readTxt(String txtpath) throws IOException {
		FileReader fileReader = new FileReader(txtpath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<nodeHuffman> nodesList = new ArrayList<nodeHuffman>();
		String line;
        line = bufferedReader.readLine();
        nodesNum = Integer.parseInt(line);
        while ((line = bufferedReader.readLine())!= null) {
			nodeHuffman addnNode = new nodeHuffman(Integer.parseInt(line));
			nodesList.add(addnNode);
		}
		
		return nodesList;
		
	}
	
	private static ArrayList<nodeHuffman> find2SmallestNodes(ArrayList<nodeHuffman>nodesList, ArrayList<nodeHuffman>treeList) {
		int i = nodesList.size() + treeList.size();
		int nodesLength = nodesList.size();
		int treeLengthh = treeList.size();
		ArrayList<nodeHuffman> returnNodesArrayList = new ArrayList<nodeHuffman>();
		if (i>=2) {
			if (nodesLength>=2 && treeLengthh>=2) {
				returnNodesArrayList.add(nodesList.get(0));
				returnNodesArrayList.add(nodesList.get(1));
				returnNodesArrayList.add(treeList.get(0));
				returnNodesArrayList.add(treeList.get(1));
				Collections.sort(returnNodesArrayList);
				returnNodesArrayList.remove(3);
				returnNodesArrayList.remove(2);
			}
			else if (nodesLength>=2 && treeLengthh==1) {
				returnNodesArrayList.add(nodesList.get(0));
				returnNodesArrayList.add(nodesList.get(1));
				returnNodesArrayList.add(treeList.get(0));
				Collections.sort(returnNodesArrayList);
				returnNodesArrayList.remove(2);
			}
			else if (nodesLength>=2 && treeLengthh==0) {
				returnNodesArrayList.add(nodesList.get(0));
				returnNodesArrayList.add(nodesList.get(1));				
			}
			else if (treeLengthh>=2 && nodesLength==1 ) {
				returnNodesArrayList.add(treeList.get(0));
				returnNodesArrayList.add(treeList.get(1));
				returnNodesArrayList.add(nodesList.get(0));
				Collections.sort(returnNodesArrayList);
				returnNodesArrayList.remove(2);
			}
			else if (treeLengthh>=2 && nodesLength==0) {
				returnNodesArrayList.add(treeList.get(0));
				returnNodesArrayList.add(treeList.get(1));
			}
			else if (treeLengthh==1 && nodesLength==1) {
				returnNodesArrayList.add(treeList.get(0));
				returnNodesArrayList.add(nodesList.get(0));
			}
			
		}
		else {
			if (treeLengthh==1 && nodesLength==0) {
				returnNodesArrayList.add(treeList.get(0));
			}
			else if (treeLengthh==0 && nodesLength==1) {
				returnNodesArrayList.add(nodesList.get(0));
			}
		}
		
		
		return returnNodesArrayList;
		
	}
	
	private static void merge(ArrayList<nodeHuffman> smallest2Nodes, ArrayList<nodeHuffman> nodesList , ArrayList<nodeHuffman> treeList) {
		nodeHuffman addTree = new nodeHuffman(smallest2Nodes.get(0), smallest2Nodes.get(1));
		treeList.add(addTree);
		Collections.sort(treeList);
		nodesList.remove(smallest2Nodes.get(0));
		nodesList.remove(smallest2Nodes.get(1));
	}
}


class nodeHuffman implements Comparable<nodeHuffman>{
	long weight ;
	nodeHuffman leftChild;
	nodeHuffman rightChild;
	nodeHuffman parent;
	boolean isRoot;
	boolean isLeaf;
	
	public nodeHuffman(long weight) {
		// TODO Auto-generated constructor stub
		this.weight = weight;
		this.isLeaf = true;
		this.isRoot = false;
		this.leftChild = null;
		this.rightChild = null;
		this.parent =null;
	}
	
	public nodeHuffman(nodeHuffman leftNode, nodeHuffman rightNode) {
		// TODO Auto-generated constructor stub
		this.weight = leftNode.weight + rightNode.weight;
		this.leftChild = leftNode;
		this.rightChild = rightNode;
		this.parent = null;
		leftNode.isRoot =false;
		rightNode.isRoot =false;
		leftNode.parent = this;
		rightNode.parent = this;
		this.isRoot =true;
	}

	@Override
	public int compareTo(nodeHuffman o) {
		// TODO Auto-generated method stub
		if (this.weight> o.weight) {
			return 1;
		}
		else if (this.weight == o.weight) {
			return 0;
		}
		else {
			return -1;
		}
		

	}
}