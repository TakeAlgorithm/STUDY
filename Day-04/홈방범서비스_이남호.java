import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final boolean isForSubmission = false;
	
	public static void main(String[] args) throws Exception {
		
		//if(!isForSubmission) System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for(int T = 0 ;T<numCases;T++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int [][][] map = new int[N][N][2*N];
				
			
			for(int i = 0 ; i < N ;i++) {
				for(int j = 0; j<N ; j++) {
					int a = sc.nextInt();
					if(a == 1) {
						for(int k = 0; k < N ; k++) {
							for(int l =0 ; l < N ; l++) {
									int yDist = i-k;
									yDist = yDist<0?-yDist:yDist;
									int xDist = j-l;
									xDist = xDist<0?-xDist:xDist;
									int Dist = xDist+yDist;
									
									int[] ints = map[k][l];
									ints[Dist]++;
							}
						}
					}
				}
			}
			
			
			int currentBest = 0;
			for(int i = 0 ; i <N ; i++) {
				for(int j = 0 ; j <N ; j++) {
					int[] ints = map[i][j];
					
					int sum = 0;
					for(int k = 0; k < 2*N ; k++ ) {
						sum+=ints[k];
						int K = k+1;
						int cost = K*K+(K-1)*(K-1);
						int money = M*sum;
						if(cost<=money) {
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