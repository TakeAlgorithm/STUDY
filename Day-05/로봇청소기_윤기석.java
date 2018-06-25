import java.util.*;

//https://www.acmicpc.net/problem/14503
public class Main {

	static int arr[][];
	static boolean carr[][];

	static int N = 0;
	static int M = 0;
	static int x = 0;
	static int y = 0;
	static int d = 0;
	private static int Answer = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		x = sc.nextInt() + 1;
		y = sc.nextInt() + 1;
		d = sc.nextInt();

		arr = new int[N + 1][M + 1];
		carr = new boolean[N + 1][M + 1];

		// Input
		for (int i = 1; i <= N; i++) {
			Arrays.fill(carr[i], false);
			for (int j = 1; j <= M; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] > 0)
					carr[i][j] = true;
			}
		}

		while (true) {
			int nextD = -1;
			// 1.clean Start
			if (!cleaning(x, y))
				//후진했음
				;
				
			// Searchhing
			for (int i = 0; i < 4; i++) {
				d = d - 1 < 0 ? 3 : d - 1;

				boolean move = false;
				switch (d) {
				case 0:
					if (canMove(x - 1, y) && !isClean(x - 1, y))
						move = true;
					break;
				case 1:
					if (canMove(x, y + 1) && !isClean(x, y + 1))
						move = true;
					break;
				case 2:
					if (canMove(x + 1, y) && !isClean(x + 1, y))
						move = true;
					break;
				case 3:
					if (canMove(x, y - 1) && !isClean(x, y - 1))
						move = true;
					break;
				default:
					break;
				}
				if (move) {
					nextD = d;
					break;
				}
			}

			if (nextD >= 0)
				move(nextD);
			else if (!backMove())
				break;
		}
		System.out.println(Answer);

	}

	private static boolean backMove() {
		switch (d) {
		case 0:
			x++;
			break;
		case 1:
			y--;
			break;
		case 2:
			x--;
			break;
		case 3:
			y++;
			break;
		}
		if (!canMove(x, y))
			return false;
		return true;
	}

	private static void move(int d) {
		switch (d) {
		case 0:
			x--;
			break;
		case 1:
			y++;
			break;
		case 2:
			x++;
			break;
		case 3:
			y--;
			break;
		default:

			break;
		}
	}

	private static boolean cleaning(int x, int y) {
		if (!canMove(x, y))
			return false;

		// 청소여부
		if (isClean(x, y))
			return false;

		// arr[x][y] = 1;
		carr[x][y] = true;
		Answer++;
		return true;
	}

	private static boolean isClean(int x2, int y2) {
		if (carr[x2][y2])
			return true;
		return false;
	}

	private static boolean canMove(int x, int y) {
		// 외벽
		if (!(1 <= x && x <= N || 1 <= y && y <= M))
			return false;
		// 내벽
		if (arr[x][y] > 0)
			return false;

		return true;
	}

}
