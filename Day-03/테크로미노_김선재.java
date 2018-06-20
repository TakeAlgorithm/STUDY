import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int ans;
	static int arr[][];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		go(0);
		
		System.out.println(ans);
		
		sc.close();
	}
	
	public static void go(int i) {
		
		if(i >= N - 1) {
			return;
		}
		
		int j = 1;
		while(j + 1 < M) {
			
			// 기준을 두칸으로
			int c = arr[i][j] + arr[i + 1][j];
			int sum = c;
			
			// ㅁ
			if(j == 1) {
				sum += arr[i][j - 1];
				sum += arr[i + 1][j - 1];
				ans = Math.max(ans, sum);
			}
			
			// ㅁ
			sum = c;
			sum += arr[i][j + 1];
			sum += arr[i + 1][j + 1];
			ans = Math.max(ans, sum);
			
			// ㄱㄴ
			sum = c;
			sum += arr[i][j - 1];
			sum += arr[i + 1][j + 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[i][j + 1];
			sum += arr[i + 1][j - 1];
			ans = Math.max(ans, sum);
			
			// 기준을 세칸으로
			if(i < N - 2) {
			
			if(j == 1) {
				c = arr[i][j-1] + arr[i + 1][j-1] + arr[i + 2][j-1];
				
				sum = c;
				sum += arr[i][j];
				ans = Math.max(ans, sum);
				
				sum = c;
				sum += arr[i+1][j];
				ans = Math.max(ans, sum);
				
				sum = c;
				sum += arr[i+2][j];
				ans = Math.max(ans, sum);
			}
			
			if(j == M - 2) {
				c = arr[i][j+1] + arr[i + 1][j+1] + arr[i + 2][j+1];
				
				sum = c;
				sum += arr[i][j];
				ans = Math.max(ans, sum);
				
				sum = c;
				sum += arr[i+1][j];
				ans = Math.max(ans, sum);
				
				sum = c;
				sum += arr[i+2][j];
				ans = Math.max(ans, sum);
			}
			
			c = arr[i][j] + arr[i + 1][j] + arr[i + 2][j];
			
			//ㄱ
			sum = c;
			sum += arr[i][j - 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[i][j + 1];
			ans = Math.max(ans, sum);
			
			//ㅓ, ㅏ
			sum = c;
			sum += arr[i + 1][j - 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[i + 1][j + 1];
			ans = Math.max(ans, sum);
			
			//ㄴ
			sum = c;
			sum += arr[i + 2][j - 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[i + 2][j + 1];
			ans = Math.max(ans, sum);
			}
			
			//ㅣ
			if(i < N - 3) {
				
				if(j == 1) {
					sum = arr[i][j-1] + arr[i + 1][j-1] + arr[i + 2][j-1] + arr[i + 3][j-1];
					ans = Math.max(ans, sum);
				}
				
				if(j == M - 2) {
					sum = arr[i][j+1] + arr[i + 1][j+1] + arr[i + 2][j+1] + arr[i + 3][j+1];
					ans = Math.max(ans, sum);
				}
				
				sum = arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j];
				ans = Math.max(ans, sum);
			}
			j++;
		} // while
		
		
		j = 1;
		while(j + 1 < N) {
			
			// 기준을 두칸으로
			int c = arr[j][i] + arr[j][i + 1];
			int sum = c;
			
			// ㄱㄴ
			sum = c;
			sum += arr[j - 1][i];
			sum += arr[j + 1][i + 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[j + 1][i];
			sum += arr[j - 1][i + 1];
			ans = Math.max(ans, sum);
			
			// 기준을 세칸으로
			if(i < M - 2) {
				
				if(j == 1) {
					c = arr[j-1][i] + arr[j-1][i+1] + arr[j-1][i+2];
					
					sum = c;
					sum += arr[j][i];
					ans = Math.max(ans, sum);
					
					sum = c;
					sum += arr[j][i+1];
					ans = Math.max(ans, sum);
					
					sum = c;
					sum += arr[j][i+2];
					ans = Math.max(ans, sum);
				}
				
				if(j == M - 2) {
					c = arr[j+1][i] + arr[j+1][i+1] + arr[j+1][i+2];
					
					sum = c;
					sum += arr[j][i];
					ans = Math.max(ans, sum);
					
					sum = c;
					sum += arr[j][i+1];
					ans = Math.max(ans, sum);
					
					sum = c;
					sum += arr[j][i+2];
					ans = Math.max(ans, sum);
				}
				
			c = arr[j][i] + arr[j][i + 1] + arr[j][i + 2];
			
			//ㄱ
			sum = c;
			sum += arr[j - 1][i];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[j + 1][i];
			ans = Math.max(ans, sum);
			
			//ㅓ, ㅏ
			sum = c;
			sum += arr[j - 1][i + 1];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[j + 1][i + 1];
			ans = Math.max(ans, sum);
			
			//ㄴ
			sum = c;
			sum += arr[j - 1][i + 2];
			ans = Math.max(ans, sum);
			
			sum = c;
			sum += arr[j + 1][i + 2];
			ans = Math.max(ans, sum);
			}
			
			//ㅣ
			if(i < M - 3) {
				
				if(j == 1) {
					sum = arr[j-1][i] + arr[j-1][i+1] + arr[j-1][i+2] + arr[j-1][i+3];
					ans = Math.max(ans, sum);
				}
				
				if(j == M - 2) {
					sum = arr[j+1][i] + arr[j+1][i+1] + arr[j+1][i+2] + arr[j+1][i+3];
					ans = Math.max(ans, sum);
				}
				
				sum = arr[j][i] + arr[j][i+1] + arr[j][i+2] + arr[j][i+3];
				ans = Math.max(ans, sum);
			}
			j++;
		} // while
		
		go(++i);
	}
}
