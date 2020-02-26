package course2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class TwoSum {
	public static void main(String[] args) throws Exception {
		ArrayList<Long> data = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\algo1-programming_prob-2sum.txt");
		HashSet<Long> repeatedData = new HashSet<Long>();
		Hashtable<Integer, Long> hashData = transToHash(data,repeatedData);
		System.out.println("loaded");
		int twoSum=0;
		for (int target = -10000; target < 10001; target++) {
			if (lookUpHash(target, data, hashData,repeatedData)==true) {
				twoSum++;
			}
		}
		System.out.println("Number of matched 2-Sum values : "+ twoSum);
    }

    private static ArrayList<Long> readTxt(String txtPath) throws IOException {
        FileReader fileReader = new FileReader(txtPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<Long> numList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine())!=null){
            numList.add(Long.parseLong(line));
        }

        return numList;
    }

    private static Hashtable<Integer,Long> transToHash(ArrayList<Long> numList,HashSet<Long>repeatedData){
        Hashtable<Integer,Long> dataHash = new Hashtable<>();
        int i =1;
        for (Long longInteger : numList) {
        	if (dataHash.containsValue(longInteger)==true) {
				repeatedData.add(longInteger);
			}
        	dataHash.put(i, longInteger);
			i++;
		}
        return dataHash;
    }
    
    private static boolean lookUpHash(long target, ArrayList<Long>data, Hashtable<Integer, Long> dataHash, HashSet<Long>repeatedData) {
		for (Long integer : data) {
			long num = integer.longValue();
			long searchValue = target -num;
			boolean existence =  dataHash.containsValue(searchValue);
			if (existence==true && searchValue!=num) {
				return true;
			}
			else if (existence==true && searchValue==num) {
				if (repeatedData.contains(searchValue)) {
					return true;
				}
			}
		}    	
    	return false;
		
	}
}
