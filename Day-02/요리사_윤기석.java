
import java.util.Scanner;

public class Solution {

	static int[][] map = null;
	private static int N;
	private static int result;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {

			result = 1000000000; // maximum
			N = sc.nextInt();
			map = new int[N][N];

			// init
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// 팀만들기
			make(0, new boolean[N], 0);

			System.out.printf("#%d %d\n", test_case, result);
		}
	}

	private static void make(int round, boolean[] teamA, int idx) {
		if (round >= N / 2) {
			end(teamA);
			return;
		}

		for (int i = idx; i < N; i++) {
			teamA[i] = true;
			make(round + 1, teamA, i + 1);
			teamA[i] = false;
		}

	}

	private static void end(boolean[] teamAll) {
		// Test
		// for (int i = 0; i < N; i++) {
		// System.out.print(teamA[i] ? "0 " : "1 ");
		// }
		// System.out.println();

		int[] teamA = getTeam(teamAll, true);
		int[] teamB = getTeam(teamAll, false);

		int a = getValue(teamA);
		int b = getValue(teamB);

		result = Math.min(Math.abs(a - b), result);

	}

	private static int getValue(int[] team) {
		int result = 0;
		for (int i = 0; i < team.length; i++) {
			for (int j = i + 1; j < team.length; j++) {
				result += map[team[i]][team[j]] + map[team[j]][team[i]];
			}
		}

		return result;
	}

	private static int[] getTeam(boolean[] teamAll, boolean b) {
		int result[] = new int[N / 2];
		int idx = 0;
		for (int i = 0; i < teamAll.length; i++) {
			if (idx >= N / 2)
				break;
			if (teamAll[i] == b)
				result[idx++] = i;
		}
		return result;
	}
}
