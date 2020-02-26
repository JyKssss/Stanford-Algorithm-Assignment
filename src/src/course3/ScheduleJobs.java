package course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ScheduleJobs {
	static int jobNums ;  
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<job> jobList = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\jobs.txt");
		sortJob(jobList);
		System.out.println(jobList.get(0).score +" "+ jobList.get(1121).score);
		System.out.println("Total sum of weighted completion times is : " + sumWeightedTime(jobList));
	}

	
	private static ArrayList<job> readTxt(String txtPath) throws IOException {
        FileReader fileReader = new FileReader(txtPath);
        @SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<job> jobList = new ArrayList<job>();
        String line;
        jobNums = Integer.parseInt(bufferedReader.readLine());
        while ((line = bufferedReader.readLine())!=null){
        	String[] weiLength=line.split(" ");
        	job addJob = new job(Integer.parseInt(weiLength[0]), Integer.parseInt(weiLength[1]),1);
        	jobList.add(addJob);

        }
        return jobList;
    }
	
	private static void sortJob(ArrayList<job>jobList) {
//		Collections.sort(jobList,Collections.reverseOrder());
		Collections.sort(jobList);
	}
	
	private static Long sumWeightedTime(ArrayList<job>jobList) {
		int completionTime =0;
		long  sumWT =0;
		Iterator<job> jobIterator = jobList.iterator();
		while (jobIterator.hasNext()) {
			job currentJob = jobIterator.next();
			completionTime += currentJob.getLength();
			sumWT += completionTime* currentJob.getWeight();
		}
		
		
		return sumWT;
		
	}
}
class job implements Comparable<job>{
	int weight;
	int length;
	double score;
//	parameter =  0  score = weight -length
	public job(int weight,int length,int parameter) {
		// TODO Auto-generated constructor stub
		this.weight =weight;
		this.length =length;
		if (parameter ==0) {
			this.score =weight - length;
		}
		else {
			double w = weight;
			double l = length;
			this.score = w/l;
		}
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public double getScore() {
		return this.score;
	}

	@Override
	public int compareTo(job o) {
		// TODO Auto-generated method stub
//		if (Double.doubleToLongBits(this.score)>Double.doubleToLongBits(o.score)) {
//			return -1;
//		}
		if (this.score>o.score) {
			return -1;
		}
		else if (this.score==o.score) {
			if (this.weight>o.weight) {
				return -1;
			}
			else if (this.weight==o.weight) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}		
	}
	

	
}