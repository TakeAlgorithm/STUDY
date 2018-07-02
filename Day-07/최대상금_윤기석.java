import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// use it paste next line
// comment : 삽입 정렬 후 maximum 값이 나오도록 최적의 자리값 확인 
// 1. 삽입정렬로 arr.length 만큼 정렬 (정렬도중 N 보다 커진경우 정렬 중단)
// 2. 1번 sort 중 change 한 값 만큼  상위 인덱스 값 확인.
// 3. 2번의 상위 idx에서 동일 한 값이 있다면 그만큼 하위 idx 정렬
// 4. 정렬 한뒤 남은 count (더 swap 해야 하는 경우)가 짝수인지 홀수 인지 확인, 홀인 경우 최소값만 교환해줌
// 4- 1 : existSameV 에서 동일한 값이 존재한 경우, 4번경우 무시 ( 동일한 값끼리 교환시킴)
public class 최대상금 {

	private static int N;
	private static boolean isDebug;
	private static boolean existSameV;

	public static void main(String[] args) {

		isDebug = false;
		log("is debug Mod");
		if (isDebug)
			try {
				System.setIn(new FileInputStream(new File("input.txt")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		sc.nextLine();
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] dum = sc.nextLine().split(" ");

			int[] arr = new int[dum[0].length()];
			final int N = Integer.parseInt(dum[1]);

			for (int i = 0; i < dum[0].length(); i++) {
				int a = Integer.parseInt(dum[0].charAt(i) + "");
				if (isDebug)
					System.out.print(a);
				arr[i] = a;
			}
			if (isDebug)
				System.out.println();

			// 1. 삽입정렬로 arr.length 만큼 정렬 (정렬도중 N 보다 커진경우 정렬 중단)
			existSameV = false;
			int count = sort(arr, 0, arr.length, N);

			// 2. 1번 sort 중 change 한 값 만큼 상위 인덱스 값 확인.
			// 같은 값이 있다면 under sort 확인
			int nIdx = 0;
			// under sorting 을 위해 swap한 값중, 같은 값이 있었는 지 확인
			for (int i = 0; i < count; i++) {
				if (arr[i] == arr[i + 1])
					nIdx++;
			}

			// 3. 2번의 상위 idx에서 동일 한 값이 있다면 그만큼 하위 idx 정렬
			if (nIdx > 1) // under sort
				sort(arr, arr.length - count - 1, arr.length, nIdx / 2);

			int existC = N - count;
			// 4. 정렬 한뒤 남은 count (더 swap 해야 하는 경우)가 짝수인지 홀수 인지 확인, 홀인 경우 최소값만
			// 교환해줌
			if (!existSameV && existC > 0 && existC % 2 > 0) { // 짝인경우 같으므로
																// 홀인경우만 교환
				change(arr, arr.length - 1, arr.length - 2);

			}

			// result
			String result = "";
			for (int i = 0; i < arr.length; i++) {
				result += arr[i] + "";
			}

			System.out.printf("#%d %s\n", test_case, result);
		}

	}

	private static int sort(int[] arr, int start, int length, int N) {
		int count = 0;
		for (int idx = start; idx < length - 1; idx++) {
			if (count >= N)
				break;
			int mIdx = idx;
			for (int i = idx + 1; i < length; i++) {
				if (arr[mIdx] == arr[i])
					existSameV = true;
				mIdx = arr[mIdx] > arr[i] ? mIdx : i;

			}
			if (idx != mIdx && arr[idx] != arr[mIdx]) {
				change(arr, idx, mIdx);
				count++;
			}
		}

		return count;
	}

	private static void change(int[] arr, int idx, int m) {
		int temp = arr[idx];
		arr[idx] = arr[m];
		arr[m] = temp;
	}

	private static void log(String s) {
		if (isDebug)
			System.out.println(s);
	}

}
