import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<int[]> wall;
	private static List<int[]> cc;

	private static int result = 0;
	private static int maxI;
	private static int maxJ;

	private static int[][] map;
	private static int[][] arr;

	private static final int WALL = 6;
	private static int WALLCOUNT = 0;
	private static final int I = 0;
	private static final int J = 1;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		maxI = sc.nextInt();
		maxJ = sc.nextInt();
		arr = new int[maxI][maxJ];
		map = new int[maxI][maxJ];

		wall = new ArrayList<int[]>();
		cc = new LinkedList<int[]>();

		for (int i = 0; i < maxI; i++) {
			for (int j = 0; j < maxJ; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] > 0) {
					if (arr[i][j] == WALL)
						wall.add(new int[] { i, j });
					else
						cc.add(new int[] { i, j });
				}
			}
		}

		// init
		WALLCOUNT = wall.size();
		result = maxI * maxJ - WALLCOUNT;

		if (cc.size() > 0)
			cal(0);

		System.out.println(result);

	}

	private static void cal(int count) {
		if (count >= cc.size()) {
			end();
			return;
		}
		int now[] = cc.get(count);

		for (int way = 1; way <= 4; way++) {
			change(now.clone(), 1, way);

			cal(count + 1);
			change(now.clone(), -1, way);
			// test
		}

	}

	private static void change(int[] arg, int value, int wayKey) {

		// shape에 따라 값 변화
		int shape = arr[arg[I]][arg[J]];
		boolean ways[] = check(shape, wayKey);

		for (int i = 1; i <= 4; i++) {
			int[] now = arg.clone();
			if (ways[i])
				switch (i) {
				case 1: // 북
					while (!isWall(now)) {
						map[now[I]][now[J]] += value;
						now[I]--;
					}
					break;
				case 2: // 동
					while (!isWall(now)) {
						map[now[I]][now[J]] += value;
						now[J]++;
					}
					break;

				case 3: // 남
					while (!isWall(now)) {
						map[now[I]][now[J]] += value;
						now[I]++;
					}
					break;

				case 4: // 서
					while (!isWall(now)) {
						map[now[I]][now[J]] += value;
						now[J]--;
					}
					break;
				}
		}
	}

	private static boolean[] check(int shape, int wayKey) {
		boolean[] r = new boolean[5];
		switch (shape) {
		case 2: // 양방향
			r[wayKey % 2 > 0 ? wayKey == 3 ? 1 : 3 : wayKey == 4 ? 2 : 4] = true;
		case 1: // 단방향
			r[wayKey] = true;
			break;
		case 3: // ㄴ
			r[wayKey] = true;
			r[wayKey + 1 > 4 ? 1 : wayKey + 1] = true;
			break;
		case 4: // ㅗ
			Arrays.fill(r, true);
			r[0] = false;
			r[wayKey % 2 > 0 ? wayKey == 3 ? 1 : 3 : wayKey == 4 ? 2 : 4] = false;
			break;
		case 5: // 4방향
			Arrays.fill(r, true);
			break;
		}
		return r;
	}

	private static boolean isWall(int[] now) {
		if (now[I] < 0 || now[J] < 0 || now[I] >= maxI || now[J] >= maxJ || arr[now[I]][now[J]] == WALL)
			return true;

		return false;
	}

	private static void end() {

		// map calcul
		int count = -WALLCOUNT; // 최소값
		for (int i = 0; i < maxI; i++) {
			for (int j = 0; j < maxJ; j++) {
				if (map[i][j] < 1)
					count++;
			}
		}

		result = Math.min(result, count);
	}

}
