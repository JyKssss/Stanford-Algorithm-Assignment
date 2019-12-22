package course1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.Line;

public class CountInversion {
	static long inversionNum =0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[] preData = readTxt("C:/Users/TAN/Desktop/IntegerArray.txt");
		int[] sorted = counteMerge(preData);
		System.out.println(inversionNum);
//		System.out.println(sorted[99999]);
		
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
	
	private static int[] counteMerge(int[] pre) {
		int n = pre.length/2;
		while (pre.length==1) {
			return pre;
		}
		int [] cus1 = new int[n];
		int [] cus2= new int[pre.length-n];
		for (int i = 0; i < n; i++) {
			cus1[i] =  pre[i];
		}
		for (int i = 0; i < pre.length-n; i++) {
			cus2[i]= pre[i+n];
		}
		int[]a =counteMerge(cus1);
		int[]b =counteMerge(cus2);
		
		return mergeProcess(a, b);
		
	}
	
	private static int[] mergeProcess(int[] a, int[] b) {		
		int l = a.length;
		int r = b.length;
		int[] sort = new int [l+r];
		int i=0,j=0;
		for (int k = 0; k < sort.length; k++) {
			if (i<=l&& j<=r) {
				if (i<l&&j<r&&a[i]<=b[j]) {
					sort[k] = a[i];
					i++;
				}
				else if (i<l&&j<r&&b[j]< a[i]) {
					sort[k] = b[j];
					j++;
					inversionNum+= l-i;

				}
				else if (i==l&&j<r) {
					sort[k]=b[j];
					j++;
				}
				else if (i<l&&j==r) {
					sort[k]=a[i];
					i++;

				}
			}
		}
		
		return sort;
		
	}
	
}
