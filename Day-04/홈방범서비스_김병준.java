package swexpert;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class N2117 {

//	static final boolean isForSubmission = false;
	static final boolean isForSubmission = true;
	static int N;
	static int M;
	static int[][] house = null;

	static int[] cost = new int[21];
	static int count = 0;
	static int rent = 0;
	static int max_house = 0;

	public static void log(String s) {
		if (!isForSubmission)
			System.out.println(s);
	}

	public static void main(String[] args) throws Exception {

//		if (!isForSubmission)
	//		System.setIn(new FileInputStream(".\\src\\swexpert_txt\\N2117_sample_input.txt"));
		System.setIn(new FileInputStream(".\\src\\swexpert_txt\\N2117_sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());

		for (int i = 0; i < cost.length; i++) {
			cost[i] = (i + 1) * (i + 1) + i * i;
		}

		for (int k = 0; k < T; k++) {
			N = sc.nextInt(); // 도시크기 NxN
			M = sc.nextInt(); // 지불비용 M
			house = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					house[i][j] = sc.nextInt();
					if (house[i][j] == 1)
						count++;
				}
			}
			log("N: " + N);
			log("M: " + M);
			int min = 0;
			while (true) {
				if (cost[min] >= count * M || min==N)
					break;
				min++;
			}
			//System.out.printf("cost: %d, count*M: %d\n",cost[min],count*M);
			security(min + 1);

			System.out.println("#" + (int) (k + 1) + " " + max_house);
			house = null;
			init();
		}

	}

	public static void security(int max) {
		if (max < 1)
			return;
		if (max_house > cost[max - 1])
			return;

		log("cost: " + cost[max - 1]);
		log("count: " + count + " 총 지불가능액: " + count * M);
		log("max: " + max);

		int blank = (int) (max / 2);
		log("blank: " + blank);
		int max_range = N - blank;
		if (max_range <= blank) max_range=blank+2;
		for (int i = blank; i < max_range; i++) {
			for (int j = blank; j < max_range; j++) {
				setSecurityRange(i, j, max);
				if(cost[max-1]>rent*M) {
					rent = 0;
					continue;
				}
				max_house = Math.max(max_house, rent);
				rent = 0;
			}
		}
		log("rent: " + rent);
		log("max_house: " + max_house);
		security(max - 1);
	}

	public static void setSecurityRange(int x, int y, int k) {
		if (k < 1)
			return;
	//	System.out.println(">> pass");
		for (int i = -k+1; i < k; i++) {	// x: [-k+1,k-1]
			for (int j = -(k-1-Math.abs(i)); j < k-Math.abs(i) ; j++) { // y: [-k+1+abs(x),k-1-abs(x)]
				if (x + i >= 0 && x + i < N && y + j >= 0 && y + j < N) {
					//System.out.printf("i: %d j: %d\n",i,j);
					if (house[x + i][y + j] == 1) {
						rent++;
//						System.out.printf("#1. x: %d y: %d k: %d rent: %d\n", x + i, (int) (y + j), k, rent);
					}
				}
			}
		}
	}

	public static void init() {
		max_house = 0;
		rent = 0;
		count = 0;
	}

}
