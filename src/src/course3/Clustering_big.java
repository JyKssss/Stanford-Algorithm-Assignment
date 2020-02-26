package course3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;



public class Clustering_big {
	static HashMap<Integer,Integer> par;
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\clustering_big.txt"));
		
		HashSet<Integer> set = new HashSet<Integer>();
		par = new HashMap<Integer,Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++)
		{
			st = new StringTokenizer(br.readLine());
			int node = 0;
			for(int j=23; j>=0; j--)
			{
				int bit = Integer.parseInt(st.nextToken());
				node += bit * ( 1 << j); //1 <<j 等价于  1*(2^j) 
			}
			
			set.add(node);
			par.put(node,-1);
		}
		
		java.util.Iterator ir =  set.iterator();
		
		while(ir.hasNext())
		{
			int x = (int) ir.next(); // 寻找汉明距离为1 或2的node
			for(int i=0; i<24; i++)
			{
				int tmp = x;
				tmp ^= (1<<i);
				if(set.contains(tmp))
				{	
					//System.out.println(x+" "+tmp);
					merge(tmp,x);
					
				}
			}
			
			for(int i=0; i<24; i++)
			{
				for(int j=0; j<24; j++)
				{
					if(i == j ) continue;
					int tmp = x;
					
					tmp ^= (1<<i);
					tmp ^= (1<<j);
					
					if(set.contains(tmp))
					{
						
						merge(tmp,x);
						
					}
				}
			}
		}
		
		ir = set.iterator();
		HashSet <Integer> clusters = new HashSet<Integer>();
		
		while(ir.hasNext())
		{
			int x = root((int) ir.next());
			//System.out.println(x);
			clusters.add(x);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Running Time : "+ (endTime-startTime)+ "ms");
		System.out.println(clusters.size());
	}
	
	static void merge(int x,int y)
	{
		x = root(x);
		///System.out.println("---");
		y = root(y);
		
		if(x == y) return;
		
		if(par.get(x) < par.get(y))
		{
			int tmp = x;
			x = y;
			y = tmp;
		}
		//System.out.println("(((");
		//System.out.println(x+" "+y);
		//System.out.println(par.get(x)+" "+par.get(y));
//		par.put(y, par.get(y)+ par.get(x));
		par.put(x,y);
		//if(y == 5767287)
		//	System.out.println("***");
	}
	
	static int root(int x)
	{	
		//System.out.println(x);
		//System.out.println(par.containsKey(x));
		//System.out.println(x+" "+par.get(x));
		//return par.get(x) < 0 ? x : (par.put(x, root(par.get(x))));
		if(par.get(x) < 0)
			return x;
		else
		{
			int p  = root(par.get(x));
			par.put(x, p);
			return p;
		}
	}
	
	
	
}

