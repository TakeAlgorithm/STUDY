
import java.util.Scanner;

//sw_2112
//보호필름
public class Solution {

	static int I;
	static int J;
	static int K;
	private static boolean[] visit;
	private static int[][] map;
	private static boolean end = false;
	// private static int result;

	private static final int A = 1;
	private static final int B = 2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int result = 0;

			J = sc.nextInt();
			I = sc.nextInt();
			K = sc.nextInt();

			int ori[][] = new int[J][I];
			for (int i = 0; i < J; i++) {
				for (int j = 0; j < I; j++) {
					ori[i][j] = sc.nextInt() + 1; // A는 1
				}
			}
			convertMap(ori);

			// 1.

			// 2.
			for (int count = 0; count <= K; count++) {
				if (cal(count, -1, map)) {
					result = count;
					break;
				}
			}

			// pass = new boolean[J]; // J개의 통과여부
			// mList = new int[J]; // J개의 최고 합격 값

			// end;
			System.out.println("#" + test_case + " " + result);

		} // Next case
	}

	private static void convertMap(int[][] ori) {
		map = new int[I][];
		int r[][] = new int[I][J];

		for (int i = 0; i < I; i++) {
			int[] dum = new int[J];
			for (int j = 0; j < J; j++) {
				dum[j] = ori[j][i];
			}
			map[i] = dum.clone();
		}

	}

	private static boolean cal(int count, int lastIdx, int[][] arr) {
		if (count < 1) {
			if (end(arr))
				return true;
			else
				return false;
		}

		for (int i = lastIdx + 1; i < J; i++) {
			int[] rollback = new int[I];
			// backup
			for (int j = 0; j < I; j++) {
				rollback[j] = arr[j][i];
			}

			// A넣기
			// inputA(i, true);
			for (int j = 0; j < rollback.length; j++) {
				arr[j][i] = A;
			}
			if (cal(count - 1, i, arr))
				return true;

			// B넣기
			// inputA(i, false);
			for (int j = 0; j < rollback.length; j++) {
				arr[j][i] = B;
			}
			if (cal(count - 1, i, arr))
				return true;

			// rollback
			for (int j = 0; j < rollback.length; j++) {
				arr[j][i] = rollback[j];
			}
		}

		return false;
	}

	private static int[] backup(int[] arr) {
		return arr;
		// int result[] = new int[I];
		// for (int i = 0; i < ; i++) {
		// arr[]
		// }
		// // TODO Auto-generated method stub
		// return result;
	}

	private static boolean end(int[][] arr) {
		for (int i = 0; i < I; i++) {
			int start = 0;
			int next = 0;
			int count = 0;
			for (int j = 0; j < J; j++) {
				if (start < 1)
					start = arr[i][j];
				else {
					next = arr[i][j];

					if (start > 0 && next > 0)
						if (start == next) {
							count++;
							if (count >= K - 1) // 합격조건 count == K
								break;
						} else { // 같지않은 경우, 해당 위치부터 다시 K까지 합격여부 확인
							start = next;
							count = 0;
						}
				}
			}

			if (count < K - 1) // break가 아닌경우 불합격
				return false;
		}
		return true;
	}

}
