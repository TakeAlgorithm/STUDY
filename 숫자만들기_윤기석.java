
import java.util.Scanner;

public class Solution {

	private static int N;
	private static int max;
	private static int min;
	private static int[] arg;
	private static int[] value;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {

			max = -1000000000; // maximum
			min = 1000000000; // maximum
			N = sc.nextInt();
			value = new int[4 + 1];

			// init
			for (int i = 1; i <= 4; i++) {
				value[i] = sc.nextInt();
			}

			arg = new int[N];
			for (int j = 0; j < N; j++) {
				arg[j] = sc.nextInt();
			}

			// 연산추가하기
			make(1, new int[N]);

			System.out.printf("#%d %d\n", test_case, max - min);
		}
	}

	private static void make(int round, int[] now) {
		// end
		if (round >= N) {
			end(now);
			// endTest(now);
			return;
		}

		for (int i = 1; i < value.length; i++) {
			if (value[i] < 1)
				continue;

			now[round] = i;
			value[i]--;
			make(round + 1, now);
			value[i]++;
		}

	}

	private static void end(int[] now) {
		int result = arg[0];
		for (int i = 1; i < now.length; i++) {
			switch (now[i]) {
			case 1:
				// +
				result += arg[i ];
				break;
			case 2:
				// -
				result -= arg[i ];
				break;
			case 3:
				// *
				result *= arg[i ];

				break;
			case 4: // /
				result /= arg[i ];
				break;
			}
		}
		min = Math.min(result, min);
		max = Math.max(result, max);
	}

	// private static void endTest(int[] now) {
	// // TODO Auto-generated method stub
	// for (int i = 0; i < now.length; i++) {
	// switch (now[i]) {
	// case 1:
	// // +
	// System.out.print("1 ");
	// break;
	// case 2:
	// // -
	// System.out.print("2 ");
	// break;
	// case 3:
	// // *
	// System.out.print("3 ");
	// break;
	// case 4: // /
	// System.out.print("4 ");
	// break;
	// }
	// }
	// System.out.println();
	// }

}
