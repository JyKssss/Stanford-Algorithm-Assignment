package course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Knapsack {
	static int size;
	static int objectsNum;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<object> objects = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\knapsack_big.txt");
		int optimalValue = knapsackSolution_big(objects);
		System.out.println(optimalValue);
	}
	private static ArrayList<object> readTxt(String txtPath) throws IOException {
    FileReader fileReader = new FileReader(txtPath);
    @SuppressWarnings("resource")
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	ArrayList<object> objects = new ArrayList<object>();
	String line;
    line = bufferedReader.readLine();
    size= Integer.parseInt(line.split(" ")[0]);
    objectsNum = Integer.parseInt(line.split(" ")[1]);
    while ((line = bufferedReader.readLine())!=null){
    	String[] vw=line.split(" ");
    	object addObject = new object(Integer.valueOf(vw[0]),Integer.valueOf(vw[1]));
    	objects.add(addObject);
    }
    
    return objects;

	}
	
	private static int knapsackSolution(ArrayList<object> objects) {
		int[][] optimalMatrix = new int[objectsNum+1][size+1];
		for (int i = 0; i < optimalMatrix[0].length; i++) {
			optimalMatrix[0][i] = 0;
		} 
		int cand1;
		int cand2;
		for (int i = 1; i < optimalMatrix.length; i++) {
			for (int x = 0; x < optimalMatrix[i].length; x++) {
				cand1 = optimalMatrix[i-1][x];
				if (x-objects.get(i-1).weight<0) {
					optimalMatrix[i][x] = cand1;
					continue;
				}
				cand2 = objects.get(i-1).value + optimalMatrix[i-1][x-objects.get(i-1).weight];
				optimalMatrix[i][x] = cand1 > cand2 ? cand1 :cand2;
			}
		}
		
		
		return optimalMatrix[objectsNum][size];
		
	}
	
	
	private static int knapsackSolution_big(ArrayList<object> objects) {
		ArrayList<ArrayList<Integer>> optimalMatrixList = new ArrayList<ArrayList<Integer>>();
		optimalMatrixList.add(new ArrayList<Integer>());
		for (int i = 0; i < size+1; i++) {
			optimalMatrixList.get(0).add(0);
		}
		int cand1;
		int cand2;
		for (int i = 1; i < objectsNum+1; i++) {
			optimalMatrixList.add(new ArrayList<Integer>());
			for (int x = 0; x < size+1; x++) {
				cand1 = optimalMatrixList.get(i-1).get(x);
				if (x-objects.get(i-1).weight<0) {
					optimalMatrixList.get(i).add(cand1);
					continue;
				}
				cand2 = objects.get(i-1).value + optimalMatrixList.get(i-1).get(x-objects.get(i-1).weight);
				optimalMatrixList.get(i).add(cand1 > cand2 ? cand1 :cand2);
			}
		}
		
		return optimalMatrixList.get(objectsNum).get(size);
		
	}
}

class object {
	int value;
	int weight;
	
	public object(int value,int weight) {
		// TODO Auto-generated constructor stub
		this.value =value;
		this.weight = weight;
	}
	
}
