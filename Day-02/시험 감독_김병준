import java.util.Scanner;

public class Main {

		public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int N = keyboard.nextInt();
		int[] A = new int[N];
		for(int i=0;i<N;i++) {
			A[i] = keyboard.nextInt(); 
		}
		int B = keyboard.nextInt();
		int C = keyboard.nextInt();
		
		long min = 0L;
		
		for(int i=0;i<N;i++) {
			if(A[i]>B) {
				min++;
				min += (A[i]-B)/C;
				if((A[i]-B)%C>0) min++;
			}
			else {
				min++;
				continue;
			}
		}
		
		System.out.println(min);
	}

}
