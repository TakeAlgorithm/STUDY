import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	private static final int I = 0;
	private static final int J = 1;
	private static int manCount;
	private static int sCount;
	private static int[][] p;
	private static int SIZE;
	private static int[][] map;
	private static int[][] stair;
	private static int result;

	public static void main(String args[]) throws FileNotFoundException {

		//System.setIn(new FileInputStream(new File("src/sample_input")));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {

			SIZE = sc.nextInt();

			p = new int[10][2]; // maximum person
			stair = new int[2][2];
			manCount = 0;
			sCount = 0;
			result = 10000000; // dummy result
			map = new int[SIZE + 1][SIZE + 1];

			// init
			for (int i = 1; i <= SIZE; i++) {
				for (int j = 1; j <= SIZE; j++) {
					int v = map[i][j] = sc.nextInt();
					if (v > 0) {
						if (v == 1) {
							p[manCount++] = new int[] { i, j };
						} else {
							stair[sCount++] = new int[] { i, j };
						}

					}
				}
			}

			// 방향정하기
			go(0, new boolean[manCount]);

			System.out.printf("#%d %d\n", test_case, result);
		}
	}

	private static void go(int round, boolean[] goToA) {
		if (round >= manCount) {
			end(goToA);
			return;
		}

		go(round + 1, goToA);

		goToA[round] = true;
		go(round + 1, goToA);
		goToA[round] = false;	//rollback

	}

	private static void end(boolean[] way) {

		int[] a = new int[manCount];
		int idxA = 0;

		int[] b = new int[manCount];
		int idxB = 0;

		// 계단 이동시간 : | PR - SR | + | PC - SC | + 1
		for (int idx = 0; idx < way.length; idx++) {
			if (way[idx])
				a[idxA++] = 1 + Math.abs(p[idx][I] - stair[0][I]) + Math.abs(p[idx][J] - stair[0][J]);
			else {
				b[idxB++] = 1 + Math.abs(p[idx][I] - stair[1][I]) + Math.abs(p[idx][J] - stair[1][J]);
			}
		}

		sort(a, idxA);
		sort(b, idxB);

		//testOutput
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(b));
		
		int maxA = getMax(a, idxA, map[stair[0][I]][stair[0][J]]);
		int maxB = getMax(b, idxB, map[stair[1][I]][stair[1][J]]);

//		testOutput
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(b));
		
		result = Math.min(Math.max(maxA, maxB), result);
	}

	private static int getMax(int[] value, int length, int coast) {
		// 3 part and plus before value

		// first Init
		for (int i = 0; i < 3; i++) {
			if (i >= length)
				break;
			value[i] += coast;
		}

		if (length > 3)
			for (int len = 4; len <= length; len++) {
				if (value[(len - 1) - 3] > value[len - 1])
					value[len - 1] = value[(len - 1) - 3] + coast;
				else
					value[len - 1] += coast;
			}
		return length < 1 ? 0 : value[length - 1];
	}

	private static void sort(int[] value, int length) {

		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {
				if (value[i] > value[j]) {
					int temp = value[i];
					value[i] = value[j];
					value[j] = temp;
				}
			}
		}

	}

}
