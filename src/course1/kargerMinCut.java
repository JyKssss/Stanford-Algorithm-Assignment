package course1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class kargerMinCut {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> graph = readTxt("kargerMinCut.txt");
		int min = Integer.MAX_VALUE;
		int times = (int) (graph.size()*graph.size()*(Math.log(graph.size())));
		for (int i = 0; i <times; i++) {
			int cuts = karger(graph);
			if (cuts<min) {
				min= cuts;
				System.out.println(min);
			}
		}
		
		System.out.println("final min"+"   "+min);
	}
	private static ArrayList<ArrayList<String>>  readTxt(String txtPath) throws IOException {
		FileReader fileReader = new FileReader(txtPath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<ArrayList<String>> graph = new ArrayList<ArrayList<String>>();
		String line;
		while ((line = bufferedReader.readLine())!= null) {
			String[] str = line.split("	");
			ArrayList<String> listLine = new ArrayList<>();
			for (int i = 0; i < str.length; i++) {
				listLine.add(str[i]);
			}
			graph.add(listLine);
		}
//		System.out.println(graph.get(0).get(0));
		System.out.println(graph.size());
		return graph ;
		
	}
	
	private static int randomNum(int length) {
		
		Random random = new Random();
		
		int ran =  (int)(random.nextInt(length));

		return ran;
		
	}
	
	private static int karger(ArrayList<ArrayList<String>> graph) {
		
		ArrayList<ArrayList<String>> mergedNodes =  new ArrayList<ArrayList<String>>();
		ArrayList<String> nodes1 = new ArrayList<String>();
		ArrayList<String> nodes2 = new ArrayList<String>();
		int size = graph.size() ;
//		System.out.println(size);
		int ran1 = randomNum(10000);
		for (int i = 0; i < size; i++) {
			
			if (randomNum(10000)<ran1) {
				nodes1.add(String.valueOf(i+1));
			}
			else  {
				nodes2.add(String.valueOf(i+1));
			}
		}
		mergedNodes.add(nodes1);
		mergedNodes.add(nodes2);
		if (nodes1.size()==0||nodes2.size()==0) {
			return Integer.MAX_VALUE;
		}
		int cuts = countCuts(mergedNodes, graph);
//		while (true) {
//			int seed1 = randomNum(graph.size(),1);
//			int seed2 = randomNum(graph.size(),2);
//			if (seed1==seed2) {
//				continue;
//			}
//			int i1=-1, i2=-1;
//			for (int i = 0; i < mergedNodes.size(); i++) {
//				boolean k=mergedNodes.get(i).contains(String.valueOf(seed1));
//				if (mergedNodes.get(i).contains(String.valueOf(seed1))==true) {
//					i1 = i;
//				}
//				if (mergedNodes.get(i).contains(String.valueOf(seed2))==true) {
//					i2 = i;
//				}
//			}
//			if (i1==-1&&i2==-1) {
//				ArrayList<String> newList = new ArrayList<>();
//				newList.add(String.valueOf(seed1));
//				newList.add(String.valueOf(seed2));
//				mergedNodes.add(newList);
//			}
//			else if (i1>=0&&i2==-1) {
//				mergedNodes.get(i1).add(String.valueOf(seed2));
//			}
//			else if (i1==-1&&i2>=0) {
//				mergedNodes.get(i2).add(String.valueOf(seed1));
//			}
//			else if (i1>=0&&i2>=0&&i1!=i2) {
//				mergedNodes.get(i1).addAll(mergedNodes.get(i2));
//				mergedNodes.remove(i2);
//			}
//			else if (i1>=0&&i2>=0&&i1==i2) {
//				continue;
//			}
//			
//			if (mergedNodes.size()==2&&(mergedNodes.get(0).size()+mergedNodes.get(1).size())==graph.size()) {
//				break;
//			}
//			System.out.println(mergedNodes.get(0));
//			System.out.println(mergedNodes.get(0).size());
//			System.out.println(mergedNodes.size());
////			if (mergedNodes.size()>=2) {
////				System.out.println(mergedNodes.size()+"  "+(mergedNodes.get(0).size()+mergedNodes.get(1).size()));
////			}
//			
//		}
//		System.out.println(mergedNodes.size());
		
		return cuts;
		
	}
	
	private static int countCuts(ArrayList<ArrayList<String>> mergedNodes,ArrayList<ArrayList<String>> graph) {
		ArrayList<String> nodes1 = mergedNodes.get(0);
		ArrayList<String> copylist = new ArrayList<String>();
		int cut=0;
		for (String node : nodes1) {
			ArrayList<String> list =  graph.get(Integer.valueOf(node)-1);

			for (int i = 1; i < list.size(); i++) {
				copylist.add(list.get(i));
			}
			for (String str : copylist) {
				if (nodes1.contains(str)) {
					continue;
				}
				else {
					cut++;
				}
			}
			copylist.clear();
		}
		
		return cut;
		
	}
}
