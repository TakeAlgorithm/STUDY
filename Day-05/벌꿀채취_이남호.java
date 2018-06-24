import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final boolean isForSubmission = true;
		
	
	public static void log(String s) {
		if(!isForSubmission)System.out.println(s);
	}
	
	
	
	static int calcProfit(int M, int maxHarvest, int sum, int index, boolean[] willHarvest, int[] ary) {
		log("a");
		if(sum> maxHarvest) {
			return 0;
		}else {
			if(index == M-1) {
				int profit = 0;
				for(int i = 0; i< M ; i++) {
					if(willHarvest[i]) {
						profit+=ary[i]*ary[i];
					}
				}
				return profit;
			}else {
				int a = 0;
				int b = 0;
				index++;
				
				willHarvest[index] = false;
				b = calcProfit(M,maxHarvest,sum,index,willHarvest,ary);
				if(b>a) a = b;
				
				willHarvest[index] = true;
				sum+=ary[index];
				b = calcProfit(M,maxHarvest,sum,index,willHarvest,ary);
				if(b>a) a = b;
				
				
				return a;
				
			}
		}		
	}
	
	
	static int calcMaxProfit(int i0,int j0,int[][] map,int M, int maxHarvest) {
		
		int[] ints = new int[M];
		for(int i = 0;i<M;i++) {
			ints[i] = map[i0][j0+i];
		}
		
		return calcProfit(M, maxHarvest, 0, -1, new boolean[M], ints);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for(int T = 0 ;T<numCases;T++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			int C = sc.nextInt();
			
			int [][] map = new int[N][N];
				
			
			for(int i = 0 ; i < N ;i++) {
				for(int j = 0; j< N ; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int[][] profitMap = new int[N][N];
			int profitMapRowSize = N-(M-1);
			
			for(int i = 0; i<N ;i++) {
				for(int j = 0 ; j < profitMapRowSize;j++) {
					profitMap[i][j] = calcMaxProfit(i,j,map,M,C);
				}		
			}
			
			int currentBest = 0;
			for(int i = 0; i<N ;i++) {
				for(int j = 0 ; j < profitMapRowSize;j++) {
					int profA=profitMap[i][j];
					
					for(int k = j+M ; k< profitMapRowSize;k++) {
						int profB = profitMap[i][k];
						int sum = profA+profB;
						if(sum>currentBest) {
							currentBest = sum;
						}			
					}
					
					for(int k= i+1; k<N ;k++) {
						for(int l = 0 ; l < profitMapRowSize;l++) {
							int profB = profitMap[k][l];
							int sum = profA+profB;
							if(sum>currentBest) {
								currentBest = sum;
							}
						}		
					}
				}		
			}
			
			
			
			System.out.println("#"+(T+1)+" "+currentBest);
		}
	}
}