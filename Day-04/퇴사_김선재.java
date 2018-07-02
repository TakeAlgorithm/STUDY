package solving;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int ans = 0;
		
		Day[] days = new Day[n];
		
		for(int i = 0 ; i < n ; i++) {
			days[i] = new Day(sc.nextInt(),sc.nextInt());
		}
		
		for(int i = n-1 ; i >= 0 ; i--) {
			
			int tmp = 0;
			
			// 날짜 + 해당일의 일하는 날짜가 주어진 퇴사일보다 적을때
			if(days[i].T + i <= n) {
				days[i].max = days[i].P;
			}
			
			for(int j = days[i].T + i ; j < n; j++) {
				tmp = Math.max(tmp, days[j].max);
			}
			
			days[i].max = days[i].max + tmp;
			
			ans = Math.max(days[i].max, ans);
			
		}
		
		System.out.println(ans);
		
		sc.close();
	}
}

class Day{
	
	public Day(int t, int p) {
		super();
		T = t;
		P = p;
	}
	
	int T;
	int P;
	int max;
}
