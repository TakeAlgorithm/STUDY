import java.util.Scanner;

public class P14499 {

	static int N,M;
	static int x = 0;
	static int y = 0;
	static int[][] map=null;
	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		N = keyboard.nextInt();
		M = keyboard.nextInt();
		x = keyboard.nextInt();
		y = keyboard.nextInt();
		int K = keyboard.nextInt();
		keyboard.nextLine();
		
		map = new int[N][M]; // 지도
		int[] command = new int[K]; // 커멘드
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] = keyboard.nextInt();
			}
			keyboard.nextLine();
		}
		
		for(int i=0;i<K;i++) {
			command[i] = keyboard.nextInt(); 
		}
		keyboard.close();
		
		int[] planes = new int[6];
		
		for(int i=0;i<K;i++) {
			int tmpx=x, tmpy=y;
			if(i==0) {
				planes = dice(command[i], null);
			}
			else {
				planes = dice(command[i], planes);
			}
			if((tmpx==x && tmpy==y) && (i!=K-1)) continue;
			if(i>1 && command[i-1]==command[i]) continue;
			System.out.println(planes[0]);
		}
	}
	
	public static int[] dice(int direction, int[] planes) {
		
		if (planes==null) planes = new int[] {0,0,0,0,0,0};
		int[] toplanes = planes.clone();

		switch (direction) {
		case 1: // East
			++x;
			if(x>M-1) {
				--x;
				break;
			}
			toplanes[0] = planes[4];
			toplanes[2] = planes[0];
			toplanes[4] = planes[5];
			if(map[y][x]!=0) {
				toplanes[5] = map[y][x];
				map[y][x] = 0;
			} else {
				toplanes[5] = planes[2];
				map[y][x] = toplanes[5];
			}
			break;
		case 2: // West
			--x;
			if (x<0) {
				++x;
				break;
			}
			toplanes[0] = planes[2];
			toplanes[2] = planes[5];
			toplanes[4] = planes[0];
			if(map[y][x]!=0) {
				toplanes[5] = map[y][x];
				map[y][x] = 0;
			} else {
				toplanes[5] = planes[4];
				map[y][x] = toplanes[5];
			}
			break;
		case 3: // North
			--y;
			if (y<0) {
				++y;
				break;
			}
			toplanes[0] = planes[3];
			toplanes[1] = planes[0];
			toplanes[3] = planes[5];
			if(map[y][x]!=0) {
				toplanes[5] = map[y][x];
				map[y][x] = 0;
			} else {
				toplanes[5] = planes[1];
				map[y][x] = toplanes[5];
			}
			break;
		case 4: // South
			++y;
			if (y>N-1) {
				--y;
				break;
			}
			toplanes[0] = planes[1];
			toplanes[1] = planes[5];
			toplanes[3] = planes[0];
			if(map[y][x]!=0) {
				toplanes[5] = map[y][x];
				map[y][x] = 0;
			} else {
				toplanes[5] = planes[3];
				map[y][x] = toplanes[5];
			}
			break;

		default:
			break;
		}
		return toplanes;
	}
	
}
