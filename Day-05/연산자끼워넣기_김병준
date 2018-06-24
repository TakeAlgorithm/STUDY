import java.util.Arrays;
import java.util.Scanner;

public class P14888 {

	static int min = 1000000000, max= -1000000000;
	static int[] a=null;
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		int N = keyboard.nextInt();
		a = new int[N];
		int[] op = new int[4];

		for (int i = 0; i < N; i++) {
			a[i] = keyboard.nextInt();
		}

		int sum = 0;
		char[] cop = null;
		for (int i = 0; i < 4; i++) {
			op[i] = keyboard.nextInt();
			sum += op[i];
		}

		for (int i = 0; i < 4; i++) {
			char[] temp = operators(i, op[i]);
			cop = append(cop, temp);
		}

		keyboard.close();
		
		permutation(cop, 0, N-1, N-1);
		System.out.println(max);
		System.out.println(min);
	}

	public static char[] operators(int op, int num) {
		if (num == 0)
			return null;
		char[] cop = new char[num];
		for (int i = 0; i < num; i++) {
			switch (op) {
			case 0:
				cop[i] = '+';
				break;
			case 1:
				cop[i] = '-';
				break;
			case 2:
				cop[i] = '*';
				break;
			case 3:
				cop[i] = '/';
				break;
			default:
				break;
			}
		}

		return cop;
	}

	public static char[] append(char[] curr, char[] add) {
		if (add == null)
			return curr;
		if (curr == null)
			return add;
		char[] next = new char[curr.length + add.length];
		for (int i = 0; i < curr.length; i++)
			next[i] = curr[i];
		int from = 0;
		for (int i = curr.length; i < curr.length + add.length; i++)
			next[i] = add[from];
		from++;
		return next;
	}

	public static void permutation(char[] arr, int depth, int n, int k) {
		if (depth == k) { // 한번 depth 가 k로 도달하면 사이클이 돌았음. 출력함.
//			System.out.println(Arrays.toString(arr));
			int val = calc(arr,a);
			min = Math.min(min, val);
			max = Math.max(max, val);
			return;
		}
		for (int i = depth; i < n; i++) {
			swap(arr, i, depth);
			permutation(arr, depth + 1, n, k);
			swap(arr, i, depth);
		}
	}
	
	public static void swap(char[] arr, int i, int f) {
		char temp = arr[i];
		arr[i] = arr[f];
		arr[f] = temp;
	}
	
	public static int calc(char[] arr, int[] a) {
		int temp = a[0];
		for(int i=0;i<arr.length;i++) {
			switch (arr[i]) {
			case '+':
				temp = temp+a[i+1]; 
				break;
			case '-':
				temp = temp-a[i+1]; 
				
				break;
			case '*':
				temp = temp*a[i+1]; 
				
				break;
			case '/':
				temp = temp/a[i+1]; 
				
				break;

			default:
				break;
			}
		}
		return temp;
	}
}
