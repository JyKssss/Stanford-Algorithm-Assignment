package course1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {
	static long numComparsion =0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[] inputArray = readTxt("QuickSort.txt");
		numComparsion+=inputArray.length-1;
		int[] outputArray = firstQuicksort(inputArray);
		
		System.out.println("The number of comparsion is: "+ numComparsion);
		System.out.println(outputArray[18]);
	}
	
	private static int[] readTxt(String txtPath ) throws IOException {
		FileReader fileReader = new FileReader(txtPath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		List<Integer> orgData = new ArrayList<Integer>() ;
		int i =0;
		while ((line = bufferedReader.readLine())!= null) {
			orgData.add(Integer.parseInt(line));
			i++;
		}
		System.out.println("The number of int is: "+i);
//		System.out.println(orgData.toString());
		int[] pre =new int[orgData.size()];
		for (int j = 0; j < orgData.size(); j++) {
			pre[j]=orgData.get(j).intValue();
		}
//		System.out.println(pre[43]);
		return pre;
		
	}
	
	private static int[] firstQuicksort(int [] unsorted ) {
		int i=0,j=0;
		int pivot;
		int exchange;
		int [] left = null;
		int [] right= null;
		int [] result = new int[unsorted.length];
		
		if (unsorted.length<=1) {
			return unsorted;
		}
		else if (unsorted.length>1) {
//			numComparsion+=unsorted.length-1;
			pivot = unsorted[0];
			for ( i = 1; i < unsorted.length; i++) {
				if (unsorted[i]>=pivot) {
					continue;
				}
				else if (unsorted[i]< pivot) {
					j++;
//					System.out.println(j);
					exchange = unsorted[i];
					unsorted[i]=unsorted[j];
					unsorted[j]=exchange;
				}
			}
//			System.out.println(pivot);
//			System.out.println(unsorted[j]);
			
			if (j>=1 && j<unsorted.length-1) {
				//copyOfRange 有下界无上界
				left = Arrays.copyOfRange(unsorted, 1,j+1);
				right = Arrays.copyOfRange(unsorted, j+1, unsorted.length);
//				System.out.println(right[0]);
				numComparsion+=left.length-1;
				numComparsion+=right.length-1;
				left = firstQuicksort(left);
				
				right = firstQuicksort(right);
				
				for (int k = 0; k < left.length; k++) {
					result[k] = left[k];
				}
				result[left.length] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+left.length+1] = right[k];
				}
			}
			else if (j==0) {
				right = Arrays.copyOfRange(unsorted, 1, unsorted.length);
				right = firstQuicksort(right);
				numComparsion+=right.length-1;
				result[0] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+1] = right[k];
				}
			}
			else if (j== unsorted.length-1) {
				left = Arrays.copyOfRange(unsorted, 1, unsorted.length);
				left = firstQuicksort(left);
				numComparsion+=left.length-1;
				for (int k = 0; k < left.length; k++) {
					result[k] = left [k];
				}
				result[unsorted.length-1] =pivot;
			}
			
			
		}
		return result;
		
	}
	
	private static int[] lastQuicksort(int [] unsorted) {
		int i=0,j=-1;
		int pivot;
		int exchange;
		int [] left = null;
		int [] right= null;
		int [] result = new int[unsorted.length];
		
		if (unsorted.length<=1) {
			return unsorted;
		}
		else if (unsorted.length>1) {
			pivot = unsorted[unsorted.length-1];
			for (i = 0;  i< unsorted.length-1; i++) {
				if (unsorted[i]>=pivot) {
					continue;
				}
				else if (unsorted[i]< pivot) {
//					System.out.println(j);
					j++;
					exchange = unsorted[i];
					unsorted[i]=unsorted[j];
					unsorted[j]=exchange;
					
				}
			}
			
			if (j>=0 && j < unsorted.length-2) {
				left = Arrays.copyOfRange(unsorted, 0, j+1);
				right = Arrays.copyOfRange(unsorted, j+1, unsorted.length-1);
				left = lastQuicksort(left);
				right = lastQuicksort(right);
				for (int k = 0; k < left.length; k++) {
					result[k] = left[k];
				}
				result[j+1] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+j+2] = right[k];
				}
			}
			else if (j==-1) {
				right = Arrays.copyOfRange(unsorted, j+1, unsorted.length-1);
				right = lastQuicksort(right);
				result[0] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+1]=right[k];
				}
			}
			else if (j==unsorted.length-2) {
				left = Arrays.copyOfRange(unsorted, 0, j);
				left = lastQuicksort(left);
				for (int k = 0; k < left.length; k++) {
					result[k]=left[k];
				}
				result[unsorted.length-1] = pivot;
			}
		}
		
		return result;
		
	}
	
	private static int[] medianQuicksort(int[] unsorted) {
		int i=0,j=0;
		int pivot;
		int exchange;
		int [] left = null;
		int [] right= null;
		int [] result = new int[unsorted.length];
		
		if (unsorted.length<=1) {
			return unsorted;
		}
		else if (unsorted.length>1) {
			
		
			int start = unsorted[0];
			int last  = unsorted[unsorted.length-1];
			int median = unsorted[unsorted.length/2-1];
			if ((start<=median&&median<=last)||(start>=median&&median>=last)) {
				unsorted[unsorted.length/2-1] = start;
				unsorted[0] =median;
			}
			else if ((median<=start&&start<=last)||(last<=start&&start<=median)) {			
			}
			else if ((start<=last&&last<=median)||(median<=last&&last<=start)) {
				unsorted[unsorted.length-1] = start;
				unsorted[0]= last;
			}
			pivot = unsorted[0];
			for ( i = 1; i < unsorted.length; i++) {
				if (unsorted[i]>=pivot) {
					continue;
				}
				else if (unsorted[i]< pivot) {
					j++;
//					System.out.println(j);
					exchange = unsorted[i];
					unsorted[i]=unsorted[j];
					unsorted[j]=exchange;
				}
			}
//			System.out.println(pivot);
//			System.out.println(unsorted[j]);
			
			if (j>=1 && j<unsorted.length-1) {
				//copyOfRange 有下界无上界
				left = Arrays.copyOfRange(unsorted, 1,j+1);
				right = Arrays.copyOfRange(unsorted, j+1, unsorted.length);
//				System.out.println(right[0]);
				numComparsion+=left.length-1;
				numComparsion+=right.length-1;
				left = medianQuicksort(left);
				
				right = medianQuicksort(right);
				
				for (int k = 0; k < left.length; k++) {
					result[k] = left[k];
				}
				result[left.length] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+left.length+1] = right[k];
				}
			}
			else if (j==0) {
				right = Arrays.copyOfRange(unsorted, 1, unsorted.length);
				numComparsion+=right.length-1;
				right = medianQuicksort(right);
				
				result[0] = pivot;
				for (int k = 0; k < right.length; k++) {
					result[k+1] = right[k];
				}
			}
			else if (j== unsorted.length-1) {
				left = Arrays.copyOfRange(unsorted, 1, unsorted.length);
				numComparsion+=left.length-1;
				left = medianQuicksort(left);
				
				for (int k = 0; k < left.length; k++) {
					result[k] = left [k];
				}
				result[unsorted.length-1] =pivot;
			}
		
		}
		
		return result;
		
	}
}
