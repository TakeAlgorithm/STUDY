import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static int[] cmd;
	private static int[] now;
	private static boolean runDebug;

	private final static int BOT = 0;
	private final static int TOP = 5;

	public static void main(String[] args) {

		runDebug = false;

		int[] start = init();

		for (int i = 0; i < K; i++) {
			if (move(start, cmd[i]))
				System.out.println(now[TOP]);
		}
	}

	private static boolean move(int[] location, int way) {
		if (!canGo(location, way))
			return false;

		int mapValue = map[location[0]][location[1]];
		int nextBottom = now[TOP];

		switch (way) {
		case 1: // 3
			now = new int[] { now[1], nextBottom, now[BOT], now[3], now[4], now[2] };
			break;
		case 2: // 9
			now = new int[] { now[2], now[BOT], nextBottom, now[3], now[4], now[1] };
			break;
		case 3: // 12
			now = new int[] { now[3], now[1], now[2], nextBottom, now[BOT], now[4] };
			break;
		case 4: // 6
			now = new int[] { now[4], now[1], now[2], now[BOT], nextBottom, now[3] };
			break;
		}

		// map change
		if (mapValue < 1)
			map[location[0]][location[1]] = now[BOT];
		else {
			now[BOT] = mapValue;
			map[location[0]][location[1]] = 0;
		}
		if (runDebug)
			System.out.println(Arrays.toString(now));
		return true;
	}

	private static boolean canGo(int[] location, int way) {
		// move dice
		switch (way) {
		case 1: // 3
			if (location[1] + 1 >= M)
				return false;
			location[1]++;
			break;
		case 2: // 9
			if (location[1] - 1 < 0)
				return false;
			location[1]--;
			break;
		case 3: // 12
			if (location[0] - 1 < 0)
				return false;
			location[0]--;
			break;
		case 4: // 6
			if (location[0] + 1 >= N)
				return false;
			location[0]++;
			break;
		}

		return true;
	}

	private static int[] init() {
		if (runDebug)
			try {
				System.setIn(new FileInputStream(new File("input.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		int[] start = new int[] { sc.nextInt(), sc.nextInt() };

		K = sc.nextInt();

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		cmd = new int[K];
		for (int i = 0; i < K; i++) {
			cmd[i] = sc.nextInt();
		}

		// make dice init
		// {bottom, 1, 2, 3, 4, top}
//		now = new int[] { 6, 3, 4, 2, 5, 1 };
		now = new int[6];

		return start;
	}

}
