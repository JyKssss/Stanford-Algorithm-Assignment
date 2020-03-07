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
		// 修改 判断最小值函数 和 不联通的情况
		if (startNode.getoutNodes().keySet().size()==0) {
			startNode.dijkDistance =Integer.MAX_VALUE/3;
			System.out.println(" 不连通  ");
			unexploredNodes.add(startNode);
			return unexploredNodes;
		}
		for (node outNode : startNode.getoutNodes().keySet()) {
			outNode.dijkDistance = startNode.getoutNodes().get(outNode);
		}
		
		while (unexploredNodes.size()>0) {
			Collections.sort(unexploredNodes);
			node newExploreNode = unexploredNodes.get(0);
			if (newExploreNode.dijkDistance> Integer.MAX_VALUE/4) {
				return exploredNodes;
			}
			newExploreNode.explored =true;
			unexploredNodes.remove(newExploreNode);
			exploredNodes.add(newExploreNode);
			for (node outNode : newExploreNode.getoutNodes().keySet()) {
				//更新加入点的最短路径
				if (outNode.explored==true) {
					continue;
				}
				outNode.dijkDistance = outNode.dijkDistance<= newExploreNode.dijkDistance +newExploreNode.getoutNodes().get(outNode) ?
						outNode.dijkDistance : newExploreNode.dijkDistance+newExploreNode.getoutNodes().get(outNode);
			}
		}
		
		//返回最短的路径值 nodelist传回去之后还原真实distance
		
		
		return exploredNodes;
		
	}
}
