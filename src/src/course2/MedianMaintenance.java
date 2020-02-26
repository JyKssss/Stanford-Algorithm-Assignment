package course2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MedianMaintenance {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Integer> data = readTxt("C:/Users/TAN/Desktop/stanford_alg/Median.txt");
		ArrayList<Integer> maxHeap = new ArrayList<>();
		ArrayList<Integer> minHeap = new ArrayList<>();
		ArrayList<Integer> median = new ArrayList<>(); 
		int max;
		int med;
		int a1 = data.get(0);
		int a2 = data.get(1);
		if (a1<=a2) {
			maxHeap = initializeHeap(a1);
			minHeap = initializeHeap(a2);
			median.add(a1);
			median.add(a1);
		}
		else {
			maxHeap = initializeHeap(a2);
			minHeap = initializeHeap(a1);
			median.add(a1);
			median.add(a2);
		}
		int i=0;
		for (Integer node : data) {
			i++;
			if (i<=2) {
				continue;
			}
			max = maxHeap.get(0);
			if (node.intValue()<=max) {
				maxHeap=addToMaxHeap(node, maxHeap);
				balanceHeaps(maxHeap, minHeap);
				med = medianOutput(maxHeap, minHeap);
//				System.out.println(med);
				median.add(med);
			}
			else if (node.intValue()>max) {
				minHeap=addToMinHeap(node, minHeap);
				balanceHeaps(maxHeap, minHeap);
				med = medianOutput(maxHeap, minHeap);
//				System.out.println(med);
				median.add(med);
			}

		}
		int sumMed=0;
		for (Integer integer : median) {
			sumMed+=integer.intValue();
		}
		System.out.println(median.toString());
		System.out.println("Final Result: "+(sumMed%10000));
	}
	
	
	private static ArrayList<Integer> readTxt(String url) throws Exception {
		FileReader fileReader = new FileReader(url);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		ArrayList<Integer> data = new ArrayList<Integer>();
		while ((line = bufferedReader.readLine()) != null) {
			data.add(Integer.parseInt(line));
		}		
		return data;
		
	}
	
	private static int medianOutput(ArrayList<Integer>maxHeap,ArrayList<Integer>minHeap) {
		int max = maxHeap.get(0);
		int min = minHeap.get(0);
		int maxSize = maxHeap.size();
		int minSize = minHeap.size();
		int median;
		if (maxSize>minSize) {
			median = max;
		}
		else if (maxSize<minSize) {
			median = min;
		}
		else {
			median = max;
		}
		
		return median;
		
	}
	
	private static ArrayList<Integer> initializeHeap(int node) {
		ArrayList<Integer> heap = new ArrayList<>();
		heap.add(node);
		return heap;		
	}
	
	private static ArrayList<Integer> addToMinHeap(int node, ArrayList<Integer>heap) {
		heap.add(node);
		int size = heap.size();
		heap = minBubbleUp(size, heap);
		return heap;
		
	}
	
	private static ArrayList<Integer> minBubbleUp(int index, ArrayList<Integer> heap) {
		int parent;
		if (index%2 ==0) {
			parent = index/2;
		}
		else {
			parent = (index-1)/2;
		}
		int childValue, parentValue;
		childValue =heap.get(index-1);
		parentValue =heap.get(parent-1);
		if (parentValue>childValue) {
			heap.set(parent-1, childValue);
			heap.set(index-1, parentValue);
			if (parent==1) {
				return heap;
			}
			minBubbleUp(parent, heap);
		}
		else  {
			return heap;
		}
		return heap;		
	}
	
	private static ArrayList<Integer> addToMaxHeap(int node, ArrayList<Integer>heap) {
		heap.add(node);
		int size = heap.size();
		heap =maxBubbleUp(size, heap);
		return heap;
		
	}
	
	private static ArrayList<Integer> maxBubbleUp(int index, ArrayList<Integer> heap) {
		int parent;
		if (index%2 ==0) {
			parent = index/2;
		}
		else {
			parent = (index-1)/2;
		}
		int childValue, parentValue;
		childValue =heap.get(index-1);
		parentValue =heap.get(parent-1);
		if (parentValue<childValue) {
			heap.set(parent-1, childValue);
			heap.set(index-1, parentValue);
			if (parent==1) {
				return heap;
			}
			maxBubbleUp(parent, heap);
		}
		else  {
			return heap;
		}
				
		return heap;
		
	}
	
	private static int extractMin(ArrayList<Integer>minHeap) {
		int min = minHeap.get(0); 
		minHeap.set(0, minHeap.get(minHeap.size()-1));
		minHeap.remove(minHeap.size()-1);
		minHeap = minBubbleDown(1, minHeap);
		return min;		
	}
	
	private static ArrayList<Integer> minBubbleDown(int index, ArrayList<Integer>minHeap) {
		int leftChildIndex = (index)*2;
		int rightChildIndex = index*2+1;
		if (rightChildIndex<=minHeap.size()&& leftChildIndex<=minHeap.size()) {					
			int leftChildValue = minHeap.get(leftChildIndex-1);
			int rightChildValue = minHeap.get(rightChildIndex-1);
			int min = Math.min(leftChildValue, rightChildValue);
			if (minHeap.get(index-1)>min&&leftChildValue<=rightChildValue) {
				minHeap.set(leftChildIndex-1, minHeap.get(index-1));
				minHeap.set(index-1, leftChildValue);
				minBubbleDown(leftChildIndex, minHeap);
			}
			else if(minHeap.get(index-1)>min&&leftChildValue>rightChildValue) {
				minHeap.set(rightChildIndex-1, minHeap.get(index-1));
				minHeap.set(index-1, rightChildValue);
				minBubbleDown(rightChildIndex, minHeap);
			}
			else if (minHeap.get(index-1)<=min) {
				return minHeap;
			}
		}
		else if (rightChildIndex>minHeap.size()&&leftChildIndex==minHeap.size()) {
			int leftChildValue = minHeap.get(leftChildIndex-1);
			
			if (leftChildValue>=minHeap.get(index)-1) {
				return minHeap;
			}
			else if (leftChildValue<minHeap.get(index-1)) {
				minHeap.set(leftChildIndex-1, minHeap.get(index-1));
				minHeap.set(index-1, leftChildValue);
				return minHeap;
			}
			
		}
		else if (rightChildIndex>minHeap.size()&&leftChildIndex>minHeap.size()) {
			return minHeap;
		}
		return minHeap;
	}
	
	
	private static int extractMax(ArrayList<Integer>maxHeap) {
		int max = maxHeap.get(0); 
		maxHeap.set(0, maxHeap.get(maxHeap.size()-1));
		maxHeap.remove(maxHeap.size()-1);
		maxHeap = maxBubbleDown(1, maxHeap);
		return max;		
	}
	
	private static ArrayList<Integer> maxBubbleDown(int index, ArrayList<Integer>maxHeap) {
		int leftChildIndex = (index)*2;
		int rightChildIndex = index*2+1;
		if (rightChildIndex<=maxHeap.size()&& leftChildIndex<=maxHeap.size()) {					
			int leftChildValue = maxHeap.get(leftChildIndex-1);
			int rightChildValue = maxHeap.get(rightChildIndex-1);
			int max = Math.max(leftChildValue, rightChildValue);
			if (maxHeap.get(index-1)<max&&leftChildValue>=rightChildValue) {
				maxHeap.set(leftChildIndex-1, maxHeap.get(index-1));
				maxHeap.set(index-1, leftChildValue);
				maxBubbleDown(leftChildIndex, maxHeap);
			}
			else if(maxHeap.get(index-1)<max&&leftChildValue<rightChildValue) {
				maxHeap.set(rightChildIndex-1, maxHeap.get(index-1));
				maxHeap.set(index-1, rightChildValue);
				maxBubbleDown(rightChildIndex, maxHeap);
			}
			else if (maxHeap.get(index-1)>=max) {
				return maxHeap;
			}
		}
		else if (rightChildIndex>maxHeap.size()&&leftChildIndex==maxHeap.size()) {
			int leftChildValue = maxHeap.get(leftChildIndex-1);
			
			if (leftChildValue<=maxHeap.get(index)-1) {
				return maxHeap;
			}
			else if (leftChildValue>maxHeap.get(index-1)) {
				maxHeap.set(leftChildIndex-1, maxHeap.get(index-1));
				maxHeap.set(index-1, leftChildValue);
				return maxHeap;
			}
			
		}
		else if (rightChildIndex>maxHeap.size()&&leftChildIndex>maxHeap.size()) {
			return maxHeap;
		}
		return maxHeap;
	}
	
	private static void balanceHeaps(ArrayList<Integer>maxHeap, ArrayList<Integer>minHeap) {
		int sizeMax = maxHeap.size();
		int sizeMin = minHeap.size();
		if (sizeMax-sizeMin>1) {
			int max = extractMax(maxHeap);
			addToMinHeap(max, minHeap);
		}
		else if (sizeMin-sizeMax>1) {
			int min = extractMin(minHeap);
			addToMaxHeap(min, maxHeap);
		}
	}
	
}
