import java.util.Scanner;

//https://www.acmicpc.net/problem/12100
public class Main {

	private static final int COUNTMAX = 5;
	static int N;
	static int max; // result
	static boolean[] canMove;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int arr[][] = new int[N][N];

		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();

				checkMax(arr[i][j]);
			}


		}

		// cal 5 move
		// go(1);

		go(arr, 1, 0);
		go(arr, 3, 0);
		go(arr, 6, 0);
		go(arr, 9, 0);

		System.out.println(max);

	}

	private static void checkMax(int val) {
		if (max < val)
			max = val;
	}

	private static void go(int[][] arg, int away, int count) {
		// b[i][j] = 0;
		int newArr[][] = new int[N][N];
		
		if (count >= COUNTMAX)
			return;

		if (away > 0) {
			// N 번쨰 행 확인
			// encry
			for (int i = 0; i < N; i++) {
				int[] arr = new int[N];

				//// Cal one array
				switch (away) {
				case 1: // up
					for (int j = 0; j < N; j++) {
						arr[j] = arg[j][i];
					}
					break;
				case 6: // down
					for (int j = N - 1; j >= 0; j--) {
						arr[N - j - 1] = arg[j][i];
					}
					break;
				case 3: // right
					for (int j = N - 1; j >= 0; j--) {
						arr[N - j - 1] = arg[i][j];
					}
					break;
				case 9: // left
					for (int j = 0; j < N; j++) {
						arr[j] = arg[i][j];
					}
					break;
				}

				arr = moveTile(arr);

				switch (away) {
				case 9: // W - default
					newArr[i] = arr;
					break;
				case 1: // N
					for (int j = 0; j < N; j++) {
						newArr[j][i] = arr[j];
					}
					break;
				case 3: // E
					for (int j = N - 1; j >= 0; j--) {
						newArr[i][N - j - 1] = arr[j];
					}
					break;
				case 6: // S
					for (int j = N - 1; j >= 0; j--) {
						newArr[N - j - 1][i] = arr[j];
					}
					break;
				}
			}

		}

		boolean checkValuable = false;
		if (count >= COUNTMAX)
			checkValuable = true;
		else
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (newArr[i][j] != arg[i][j]) {
						checkValuable = true;
						i = N;
						break;
					}
				}
			}

		if (!checkValuable) {
			return;
		}
		// go new Away
		go(newArr, 9, count + 1);
		go(newArr, 1, count + 1);
		go(newArr, 3, count + 1);
		go(newArr, 6, count + 1);
	}

	private static int[] moveTile(int[] arg) {
		// cal Move - merge or move nor stay

		int idx = 0;
		for (int i = 1; i < arg.length; i++) {
			if (arg[i] < 1) {
				continue;
			}

			if (arg[idx] > 0) {
				// merge
				if (arg[idx] == arg[i]) {
					arg[idx] *= 2;
					arg[i] = 0;

					checkMax(arg[idx++]);
				} else { // Move
					boolean canMove = false;
					for (int j = idx + 1; j < i; j++) {
						if (arg[j] < 1) {
							idx = j;
							canMove = true;
							break;
						}
						if (j - 1 == i) // end
							;
					}

					if (!canMove)
						// stay ( next Value idx up)
						idx++;
					else {
						// Move
						arg[idx] = arg[i];
						arg[i] = 0;
					}
				}
			}
			// 0 (Move)
			else {
				arg[idx] = arg[i];
				arg[i] = 0;
			}

		}
		return arg;
	}
}
