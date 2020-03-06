package course4;

import java.util.ArrayList;
import java.util.Collections;
import course4.JohnsonASAP;
public class DijkstraDirected {
	public static ArrayList<node> Dijkstra(ArrayList<node> nodeList , int startID) {
		int globalMin;
		ArrayList<node> exploredNodes = new ArrayList<node>();
		ArrayList<node> unexploredNodes = new ArrayList<node>();
		node startNode = nodeList.get(startID-1);
		startNode.explored =true;
		startNode.dijkDistance =0;
		exploredNodes.add(startNode);
		for (node node : nodeList) {
			if (node.explored ==false) {
				unexploredNodes.add(node);
			}
		}
		for (node outNode : startNode.getoutNodes().keySet()) {
			outNode.dijkDistance = startNode.getoutNodes().get(outNode);
		}
		
		while (unexploredNodes.size()>0) {
			Collections.sort(unexploredNodes);
			node newExploreNode = unexploredNodes.get(0);
			newExploreNode.explored =true;
			unexploredNodes.remove(newExploreNode);
			exploredNodes.add(newExploreNode);
			for (node outNode : newExploreNode.getoutNodes().keySet()) {
				outNode.dijkDistance = outNode.dijkDistance<= newExploreNode.dijkDistance +newExploreNode.getoutNodes().get(outNode) ?
						outNode.dijkDistance : newExploreNode.dijkDistance+newExploreNode.getoutNodes().get(outNode);
			}
		}
		
		//返回最短的路径值 nodelist传回去之后还原真实distance
		
		
		return exploredNodes;
		
	}
}
