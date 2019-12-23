package course2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Dijkstra {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Vertex> graph = new ArrayList<>();
		graph = readTxt("dijkstraData.txt");
		HashMap<Integer,Vertex> hashGraph = createHashmapGraph(graph);
		int[] target = {7,37,59,82,99,115,133,165,188,197};
		for (int i = 0; i < target.length; i++) {
			System.out.println("Min distance of node "+target[i]+": "+dijkstraAlg(hashGraph.get(target[i]), hashGraph));
			changeExplored(hashGraph);
		}
		
	}

	private static int dijkstraAlg(Vertex target, HashMap<Integer, Vertex>hashGraph ) {
		int globalLength =0 ;
		HashMap<Integer, Vertex> exploredVertex  =new HashMap<>();
		Vertex startV = hashGraph.get(1);
		startV.setDistance(0);
		startV.setExplored(true);
		exploredVertex.put(startV.getId(), startV);
		int k =0;
		while(true){
			k++;
			int minHead =0; 
			int minDis =1000000;
			Iterator<Map.Entry<Integer, Vertex>>explored = exploredVertex.entrySet().iterator();
			//Update distance information
			
			while (explored.hasNext()) {
				
				Vertex currentV = explored.next().getValue();
	//			System.out.println("tail Id: "+currentV.getId());
				ArrayList<Edge> edges = currentV.getEdgeArray();
				for (Edge edge : edges) {
					Vertex head = hashGraph.get(edge.getHead());
					int dis = currentV.getDistance() + edge.getDistance();
					if (dis<=head.getDistance()&head.getExplored()==false) {
						head.setDistance(dis);
						if (dis<minDis) {
							minDis =dis;
							minHead = head.getId();
//							System.out.println("Present min : "+minHead);
						}
					}
				}
			}
			if (k==200) {
				System.out.println("Not reachable Vertex: "+target.getId());
				break;
				
			}			
			if (minHead==0) {
				continue;
			}
			Vertex addV = hashGraph.get(minHead);
			addV.setDistance(minDis);
			addV.setExplored(true);
			exploredVertex.put(addV.getId(), addV);
//			System.out.println(target.equals(addV));
			if (target.equals(addV)) {
				globalLength = addV.getDistance();
				return globalLength;
			}
			
		}
		return 1000000;
	}

	private static ArrayList<Vertex>  readTxt(String txtPath) throws IOException {
		FileReader fileReader = new FileReader(txtPath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		ArrayList<Vertex>  graph = new ArrayList<>();
		while ((line = bufferedReader.readLine())!= null) {
			StringTokenizer tokens = new StringTokenizer(line);
			Vertex vertex = new Vertex(Integer.valueOf(tokens.nextToken()),1000000);
			ArrayList<Edge> edges = new ArrayList<>();
			while (tokens.hasMoreTokens()) {
				String head = tokens.nextToken();
				StringTokenizer commaToken = new StringTokenizer(head, ",");
				
				int node = Integer.valueOf(commaToken.nextToken());
				int dis = Integer.valueOf(commaToken.nextToken());
				Edge edge = new Edge(node, dis);
				edges.add(edge);
			}
			vertex.setEdgeArray(edges);
			graph.add(vertex);
		}
		bufferedReader.close();
		return graph;
		
	}
	
	private static void changeExplored(HashMap<Integer, Vertex> graph) {
		Iterator<Map.Entry<Integer, Vertex>>nodes = graph.entrySet().iterator();
		while (nodes.hasNext()) {
			Vertex node = nodes.next().getValue();
			node.setExplored(false);
		}		

		
	}
	
	
	private static HashMap<Integer,	Vertex> createHashmapGraph(ArrayList<Vertex> graph) {
		HashMap<Integer,Vertex> hashMapGraph = new HashMap<>();
		for (Vertex vertex : graph) {
			vertex.setExplored(false);
			vertex.setDistance(1000000);
			hashMapGraph.put(vertex.getId(), vertex);
		}
		
		
		
		return hashMapGraph;
		
	}
}

class Vertex{
	int distance;
	boolean explored;	
	int id;
	ArrayList<Edge> edges;
	
	public Vertex(int id,int distance) {
		// TODO Auto-generated constructor stub
		this.id =id;
		this.distance = distance;
		this.edges = new ArrayList<Edge>();
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Edge> getEdgeArray() {
		return this.edges;
	}
	public void setEdgeArray(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	public int getDistance() {
		return this.distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public boolean getExplored() {
		return this.explored;
	}
	public void setExplored(boolean exploration) {
		this.explored = exploration;
	}
}

class Edge{
	int headId;
	int distance;
	
	public Edge(int headId, int distance) {
		// TODO Auto-generated constructor stub
		this.headId = headId;
		this.distance = distance;		
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
