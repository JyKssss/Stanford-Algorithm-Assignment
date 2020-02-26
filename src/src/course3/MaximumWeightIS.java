package course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MaximumWeightIS {
	static int nodesNum;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<nodeMWIS> preList1 = new ArrayList<nodeMWIS>();
		ArrayList<nodeMWIS> preList2 = new ArrayList<nodeMWIS>();
		
		//"C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\mwis.txt"
		//test  "C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\course3\\assignment3HuffmanAndMWIS\\question3\\input_random_1_10.txt"
		ArrayList<nodeMWIS> nodesList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\mwis.txt");
		int pre1=0;
		int pre2=0;
		int present =0;
		pre1 = nodesList.get(0).getWeight();
		preList1.add(nodesList.get(0));
		pre2 = nodesList.get(1).getWeight()>pre1 ? nodesList.get(1).getWeight() : pre1;
		preList2.add(nodesList.get(1).getWeight()>pre1 ? nodesList.get(1) : preList1.get(0));
		int i =0;
		for (nodeMWIS node : nodesList) {
			if (i<2) {
				i++;
				continue;
			}
			present = node.getWeight()+pre1> pre2 ? node.getWeight()+pre1 : pre2;

			ArrayList<nodeMWIS> presentList = new ArrayList<nodeMWIS>();
			if (node.getWeight()+pre1> pre2) {
				
				presentList = (ArrayList<nodeMWIS>) preList1.clone();
				presentList.add(node);
				preList1 = (ArrayList<nodeMWIS>) preList2.clone();
				preList2 = (ArrayList<nodeMWIS>) presentList.clone();
//				System.out.println(node.weight);
			}
			else {
				presentList = (ArrayList<nodeMWIS>) preList2.clone();;
				preList1 = (ArrayList<nodeMWIS>) preList2.clone();;
				preList2 = (ArrayList<nodeMWIS>) presentList.clone();
			}
			pre1 = pre2 ;
			pre2 =present;	
		}
		int[] judgeSet = {1, 2, 3, 4, 17, 117, 517,997};
		for (int j = 0; j < judgeSet.length; j++) {
			int k = preList2.contains(nodesList.get(judgeSet[j]-1)) ?1 :0;
			System.out.print(String.valueOf(k));
		}
		//test
//		for (nodeMWIS nodeMWIS : nodesList) {
//			System.out.print(preList2.contains(nodeMWIS)?1 :0);
//		}
//		System.out.println("   " + present);
	}

	private static ArrayList<nodeMWIS> readTxt(String txtpath) throws IOException {
		FileReader fileReader = new FileReader(txtpath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<nodeMWIS> nodesList = new ArrayList<nodeMWIS>();
		String line;
        line = bufferedReader.readLine();
        nodesNum = Integer.parseInt(line);
        while ((line = bufferedReader.readLine())!= null) {
        	nodeMWIS addnNode = new nodeMWIS(Integer.parseInt(line));
			nodesList.add(addnNode);
		}
		
		return nodesList;
		
	}
	
	private static void deepCopy(ArrayList<nodeMWIS> newList,ArrayList<nodeMWIS> oldList) {
		for (nodeMWIS nodeMWIS : oldList) {
			newList.add(nodeMWIS);
		}
	}
	
}

class nodeMWIS {
	int weight;
	public nodeMWIS(int weight) {
		// TODO Auto-generated constructor stub
		this.weight =weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
}