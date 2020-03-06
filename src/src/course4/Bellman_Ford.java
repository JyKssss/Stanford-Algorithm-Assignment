package course4;

import java.util.ArrayList;
import java.util.HashMap;

import course4.JohnsonASAP;

public class Bellman_Ford {
	public static int[] bellmanFord(ArrayList<node> nodeList, int source ) throws Exception {
		int nodeNum = nodeList.size();
		System.out.println(nodeNum);
		int [] preMatrix = new int[nodeNum];
		int [] curMatrix = new int[nodeNum];
		node sourceNode = nodeList.get(source-1);
		//initialize 1st 
		for (int i = 0; i < preMatrix.length; i++) {
			if (i==source-1) {
				preMatrix[i] =0;
			}
			else {
				preMatrix[i] = Integer.MAX_VALUE/3;
				
			}
		}
		
		node curNode;
		for (int i = 0; i < nodeNum; i++) {
			int earlyStop =0;
			for (int j = 0; j < curMatrix.length; j++) {
				curNode = nodeList.get(j);
				curMatrix[j] = findMinIn(curNode, preMatrix);
				if (curMatrix[j]!= preMatrix[j]) {
					earlyStop++;
				}
				//在这里可以加提前终止条件
			}
			preMatrix = curMatrix;
			curMatrix = new int[nodeNum];
			if (earlyStop==0) {
				System.out.println("提前终止");
				break;
			}
			System.out.println(i +" : "+preMatrix[1]);
			
			//判断是否含有Negative Circle
			if (i==nodeNum-1 && earlyStop!=0) {
				throw new Exception("Negative Circle Exists");
			}
		}
		
		return preMatrix;
	}
	
	private static int findMinIn(node curNode, int[]pre) {
		HashMap<node, Integer> inNodes = curNode.getINodes();
		int dis1,dis2;
		int finalDis = Integer.MAX_VALUE;
		// 注意整数溢出
		for (node keyNode : inNodes.keySet()) {
			dis1 = inNodes.get(keyNode);
			dis2 = pre[keyNode.nodeId-1];
			if (dis1+dis2 < finalDis) {
				finalDis =dis1 +dis2;
			}
		}
		
		if (finalDis > pre[curNode.nodeId-1]) {
			finalDis = pre[curNode.nodeId-1];
		}
		return finalDis;
	}
}

