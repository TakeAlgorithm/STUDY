import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//https://www.acmicpc.net/problem/14501
public class Main {

	static int[] t;
	static int[] p;
	static int N;
	static List<int[]> results;
	private static boolean[] deadT;
	private static int[] maxP;

	public static void main(String[] args) {

		init();
		int result = 0;
		
		// cal dp
		for (int i = N; i > 0; i--) {
			if (deadT[i]) {
				maxP[i] = result; // 계산할 필요 없음. 이전 max값과 동일.
				continue;
			}

			maxP[i] = p[i];

			// i날 일을 한 뒤 다른날 추가로 일을 할 때
			if (t[i] + i <= N) {
				maxP[i] += maxP[t[i] + i];
			}

			// Max(i날 일한 때 최대값, i날 일하지 않고 다음날부터 일한 최대값)
			if (i + 1 <= N)
				maxP[i] = Math.max(maxP[i], maxP[i + 1]);

			result = Math.max(result, maxP[i]);
		}
		
		System.out.println(result);
	}

	private static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		t = new int[N + 1];
		p = new int[N + 1];
		results = new ArrayList<int[]>();

		deadT = new boolean[N + 1];
		maxP = new int[N + 1];

		// Input
		sc.nextLine(); // erase buffer
		for (int i = 1; i <= N; i++) {
			String[] s = sc.nextLine().split(" ");
			t[i] = Integer.parseInt(s[0]);
			p[i] = Integer.parseInt(s[1]);

			// 상담을 할 수 없는 일
			if (t[i] > 1 && t[i] + i > N + 1)
				deadT[i] = true;
		}
	}
}
