package course2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SCC {
	static int t;
	static int sccSize;
	static int leader;
	static ArrayList<Integer> leaders =new ArrayList<>();
	static ArrayList<Integer> scc =new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<node> graph = readTxt("C:/Users/TAN/Desktop/stanford_alg/input_mostlyCycles_33_1600.txt");
		dfs_Loop(graph);
		scc.clear();
		ArrayList<node> secondGraph = sortNodes(graph);
		secondGraph = reverseGraph(secondGraph);
		secondGraph = refreshExplore(secondGraph);
		System.out.println("Strong CC :");
		dfs_Loop(secondGraph);
		sortArray(scc);
		
		System.out.println(scc.toString());
		System.out.println(leaders.toString());
	
	}
	private static ArrayList<node>  readTxt(String txtPath) throws IOException {
		FileReader fileReader = new FileReader(txtPath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<node> initialGraph = new ArrayList<node>(); 
		ArrayList<Integer> list = new ArrayList<>();
		String line;
		int pre =0;
		while ((line = bufferedReader.readLine())!= null) {
			
			String[] str = line.split(" ");
			int a =Integer.parseInt(str[0]);
			int b =Integer.parseInt(str[1]);
			
			if (a==pre) {
				initialGraph.get(initialGraph.size()-1).addNodes(b);
			}
			else if (a>pre) {
				if (a-pre>1) {
					for (int i = 1; i < a-pre; i++) {
						node voidnode = new node(pre+i,pre+i);
						initialGraph.add(voidnode);
					}
				}
				node nadd = new node(a,b);
				initialGraph.add(nadd);
			}
			pre=a;
		}
		System.out.println(initialGraph.size());
//		System.out.println(initialGraph.get(0).linkedNodes.toString());
		for (node node : initialGraph) {
			list =node.linkedNodes;
			for (int i = 0; i < list.size(); i++) {
				node.listNodes.add(initialGraph.get(list.get(i)-1));
			}
		}
		return initialGraph ;
		
	}
	
	private static void dfs_Loop(ArrayList<node> graph) {
		int size = graph.size();
//		ArrayList<Integer> leaders =new ArrayList<>();
		t=0;
		for (int i = size; i >0; i--) {
			if (graph.get(i-1).explored==false) {
				sccSize =1;
				leader = i;
				leaders.add(leader);
				dfs(graph, i);
				scc.add(sccSize);
				System.out.println("end of a graph ");
			}
		}
	}
	
	private static void dfs(ArrayList<node> graph, int start) {
		graph.get(start-1).explored = true;
		ArrayList<node> linkednodes = graph.get(start-1).getLinkNodes2();
		for (node node : linkednodes) {
			if (node.getExplored() ==false) {
				dfs(graph, node.getNo());
				sccSize++;
			}
		}
		
		t++;
		System.out.println(graph.get(start-1).nodeNo);
		graph.get(start-1).nodeNo =t;
		
	}
	
	private static ArrayList<node> sortNodes(ArrayList<node> graph) {
		node[] tempNodes  = new node[graph.size()];
		for (int i = 0; i < graph.size(); i++) {
			int rank =  graph.get(i).nodeNo;
			tempNodes[rank-1] = graph.get(i);
		}
		
		ArrayList<node> sortedGraph = new ArrayList<node>();
		for (int i = 0; i < tempNodes.length; i++) {
			sortedGraph.add(tempNodes[i]);
		}
		return sortedGraph;
	}
	
	private static ArrayList<node> reverseGraph(ArrayList<node> graph) {
		node[] temp = new node[graph.size()];
		ArrayList<node> reversedGraph = new ArrayList<node>();
		for (int i = 0; i < graph.size(); i++) {
			temp[i] = new node(i+1);			
		}
		for (int i = 0; i < graph.size(); i++) {
			ArrayList<node>  linkednodes = new ArrayList<>();
			linkednodes = graph.get(i).getLinkNodes2();

			for (node node : linkednodes) {
				temp[node.getNo()-1].listNodes.add(temp[i]);
			}			

		}
		for (int i = 0; i < temp.length; i++) {
			reversedGraph.add(temp[i]);
		}
		
		return reversedGraph;
		
	}
	
	private static ArrayList<node> refreshExplore(ArrayList<node> graph) {
		for (node node : graph) {
			node.changExplored(false);
		}
		
		return graph;
	}
	
	private static void sortArray(ArrayList<Integer> scc) {
		for (int i = 0; i < scc.size()-1; i++) {
			for (int j = i+1; j < scc.size(); j++) {
				if (scc.get(i)<scc.get(j)) {
					int k = scc.get(i);
					scc.set(i, scc.get(j));
					scc.set(j, k);
				}
			}
		}
		
		
		
	}
}


