import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[][][] syns;
	
	
	
	static int logcount = 100000;
	static void log(String s) {
		if(logcount<1000) {
			System.out.print(s);
		}
		logcount++;
	}
	static void logln() {
		log("\n");
	}
	
	public static int gogo(boolean[] isInAList,int inACount,int index,int size,int[][] syn, int sumSynA,int sumSynB) {
		
		
		if(inACount>(size/2)) {
			return 30000;
		}else if((inACount+((size-1)-index))<(size/2)) {
			return 30000;
		}else {
			
			if(index>=1) {
				
				int sumSyn = 0;
				if(isInAList[index]) {	
					sumSyn = sumSynA;
				}else {
					sumSyn = sumSynB;
				}
				
				for(int i = 0; i<index;i++) {
					if(isInAList[i] == isInAList[index]) {
						
						logln();
						log("adding "+syn[i][index]+" and " + syn[index][i] + " to " + (isInAList[index]?"sumA":"sumB"));
						sumSyn+=syn[i][index];
						sumSyn+=syn[index][i];
					}
				}
				
				if(isInAList[index]) {
					sumSynA = sumSyn;
				}else {
					sumSynB = sumSyn;
				}
				
			}
			
			if(index >= size-1) {

				logln();
				
				
				log("index is : " + index + " so summing up ");
				
				int gap = sumSynA-sumSynB;
				
				gap = gap<0?-gap:gap;

				log(" for ");
				for(int j = 0 ; j < size ; j++) {
					log(isInAList[j]?"T":"F");
				}
				
				
				log(" sum A is "+sumSynA+ " sum B is "+ sumSynB);
				
				log (" returning " + gap);

				return gap; 
			
			
			}else {
				
				int a = 300000;
				int b = 300000;
				
				isInAList[index+1] = true;
				b = gogo(isInAList,inACount+1,index+1,size,syn,sumSynA,sumSynB);
				if(b<a) a = b;
				
				isInAList[index+1] = false;
				b = gogo(isInAList,inACount,index+1,size,syn,sumSynA,sumSynB);
				if(b<a) a = b;
				
				return a;	
			}
		}
	}
	
	
	
	public static void main(String args[]) throws Exception
	{
	
		System.setIn(new FileInputStream("input.txt"));

		
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		syns = new int[numCases][][];
		int[] sizes = new int[numCases];
		
		for(int i = 0 ; i < numCases;i++) {
			int size = sc.nextInt();
			sizes[i] = size;
			syns[i] = new int[size][size];
			for(int j = 0 ; j < size; j++) {
				for(int k = 0 ; k < size ; k++) {
					syns[i][j][k] = sc.nextInt();
				}
			}
		}
		
		
		for(int T = 0 ; T<numCases ; T++) {
			int[][] syn = syns[T];
			int size = sizes[T];
			
			System.out.println("#"+(T+1)+" "+gogo(new boolean[size],0,-1,size,syn,0,0));
		}
		
		
		
	}
		
}
