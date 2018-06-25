import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int N;
	private static int M;
	private static int[][] map;
	// private static int[][] wall;
	private static int result;
	private static Queue<int[]> v;

	private static final int I = 0;
	private static final int J = 1;

	public static void main(String[] args) {

		v = init();

		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					visit[i][j] = true;
			}
		}

		// 1. 벽세우기
		cal(new int[3][2], 0, visit);

		System.out.println(result);
	}

	private static void cal(int[][] wall, int wallIdx, boolean[][] visit) {
		if (wallIdx < 3) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visit[i][j]) {
						wall[wallIdx] = new int[] { i, j };
					} else
						continue;
					make(wall, true, wallIdx, visit);
					cal(wall, wallIdx + 1, visit);

					// // 원복
					make(wall, false, wallIdx, visit);
				}
			}
		} else
			dfs(new ArrayDeque<int[]>(v), visit);

	}

	private static void dfs(Queue<int[]> v, boolean[][] r) {
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (r[i][j])
					visit[i][j] = true;
			}
		}
		// 2. 바이러스퍼뜨리기
		while (!v.isEmpty()) {
			int[] arr = v.poll();

			int i = arr[I];
			int j = arr[J];
			visit[i][j] = true;

			if (i - 1 >= 0 && !visit[i - 1][j])
				v.add(new int[] { i - 1, j });
			if (j - 1 >= 0 && !visit[i][j - 1])
				v.add(new int[] { i, j - 1 });
			if (i + 1 < N && !visit[i + 1][j])
				v.add(new int[] { i + 1, j });
			if (j + 1 < M && !visit[i][j + 1])
				v.add(new int[] { i, j + 1 });
		}

		// 3. end
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j])
					count++;
			}
		}
		result = Math.max(result, count);
	}

	private static void make(int[][] wall, boolean b, int idx, boolean[][] visit) {
		visit[wall[idx][I]][wall[idx][J]] = b;
	}

	private static Queue<int[]> init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		result = 0;

		Queue<int[]> v = new ArrayDeque<int[]>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] > 1)
					v.add(new int[] { i, j });
			}
		}

		return v;
	}

}
