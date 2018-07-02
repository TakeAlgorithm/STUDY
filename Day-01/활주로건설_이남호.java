import java.io.FileInputStream;
import java.util.Scanner;

public class 활주로_이남호 {
	static final boolean isForSubmission = false;
	
	public static void main(String[] args) throws Exception {
		
		
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int numCases = sc.nextInt();
		
		for(int T = 0; T < numCases; T++ ) {
			int N = sc.nextInt();
			int X = sc.nextInt();
	
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N ; i++) {
				for(int j =0 ; j < N ; j++) {
					
					map[i][j] = sc.nextInt();
					
				}
			}
			
			int availiblecount = 0;
			
			
			A:for(int i = 0 ; i < 2*N ; i++) {
			
				int[] built = new int[N];
				int[] line;
				
				if(i>=N) {
					line = new int[N];
					int k = i-N;
					for(int j = 0 ; j < N ; j++) {
						line[j] = map[j][k];
					}
				}else {
					line = map[i];	
				}
				
				int prev = line[0];
				B:for(int j = 0 ; j < N ; j++) {
					if(line[j]!=prev) {
						int gap = line[j]-prev;
						switch(gap) {
						case(1):
							for(int k = j-1; k >= j-X ; k--) {
								if(k<0) {
									continue A;
								}else {
									if(line[j-1] == line[k] && built[k] == 0) {
										built[k] = 1;
									}else {
										continue A;
									}
								}
							}
							break;
						case(-1):
							for(int k = j; k <= j+X-1 ; k++) {
								if(k>=N) {
									continue A;
								}else {
									if(line[j] == line[k] && built[k] == 0) {
										built[k] = 1;
									}else {
										continue A;
									}
								}
							}
							break;
						default:
							continue A;
						}
						prev = line[j];
					}
				}
				
				availiblecount++;
			}
			
			
			System.out.println("#"+(T+1)+" "+availiblecount);
		}
		
	}
	
}