package study;

import java.util.Arrays;
import java.util.Scanner;

public class P13460 {
	class Pass {
		int num; // 기울인 횟수
		char[][] arr;
		int[][] status = new int[3][2]; // O(0),B(1),R(2) placements
		Pass prev;
		Pass next;
		
		public Pass() {}
		public Pass(int num, char[][] arr) {
			this.num = num;
			this.arr = arr.clone();
			status[0] = this.find('O');
		}
		
		public void slant(int direction) {
			status[1] = this.find('B');
			status[2] = this.find('R');
			
			switch (direction) {
			case 0: //왼쪽으로 
				while(true) {
					if(this.arr[status[2][1]][status[2][0]-1]=='#') {
						this.num--;
						break;
					}
				}
				break;
			case 1: //오른쪽으로 
				
				break;
			case 2: //위쪽으로 
				
				break;
			case 3: //아래쪽으로 
				
				break;

			default:
				break;
			}
		}
		
		public int[] find(char c) {
			int x=0,y=0;
			for (int i=0; i<this.arr.length; i++) {
				for (int j=0; j<this.arr[i].length; j++) {
					switch (c) {
					case 'R':
						if(this.arr[i][j] == c) {
							x=j; y=i;
						}
						break;
					case 'B':
						if(this.arr[i][j] == c) {
							x=j; y=i;
						}
						break;
					case 'O':
						if(this.arr[i][j] == c) {
							x=j; y=i;
						}
						break;
					default:
						break;
					}
				}
			}
			return new int[] {x, y};
		}
	}
	
	
	public static void loop() {
		
	}
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		int N, M;
		N = keyboard.nextInt();
		M = keyboard.nextInt();
		keyboard.nextLine();
		
		char[][] arr = new char[N][M];
		for(int i=0;i<N;i++) {
			char[] l = keyboard.nextLine().toCharArray();
			if(l.length!=M) {
				System.out.println("please input correct arrays");
				return;
			}
			arr[i] = l; 
		}
		System.out.println(Arrays.deepToString(arr));
		if(keyboard!=null) keyboard.close();
		
		
	}

}
