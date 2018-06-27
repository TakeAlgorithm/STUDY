import java.io.FileInputStream;
import java.util.Scanner;

public class 감시_이남호 {
	static final boolean isForSubmission = false;
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] map = new int[N][M];
		Camera[] cameras = new Camera[8];
		int camcount = 0;
		int wallcount = 0;
		
		for(int i = 0; i < N ; i++) {
			for(int j =0 ; j < M ; j++) {
				int x = sc.nextInt();
				map[i][j] = x;
				if(x==6) {
					wallcount++;
				}else if(x>0&&x<6) {
					Camera c;
					cameras[camcount++] = c= new Camera();
					c.posX = j;
					c.posY = i;
					c.type = x;
				}
				
				
			}
		}
		
		
		int a = 1;
		for(int i = 0 ; i < camcount ; i++) {
			a*=4;
		}
		

		int ccc = 0;
		int curMin = N*M;
		for(int i = 0 ; i < a ; i++) {
			int[] dirs = new int[camcount];
			int b = i;
			for(int j = 0; j < camcount; j++) {
				dirs[j] = b%4;
				b = b/4;
			}
			
			int[][] newMap = new int[N][M];
			for(int j = 0 ; j < camcount; j++) {
				
				Camera c = cameras[j];
				int x = c.posX;
				int y = c.posY;
				int type = c.type;
				int dir = dirs[j];
				
				
				for(int k = 0; k < 4; k++) {
					int dir1 = (dir+k)%4;
					if(type==1&&(k==1||k==2||k==3)) {
						continue;
					}
					if(type==2&&(k==1||k==3)) {
						continue;
					}
					if(type==3&&(k==2||k==3)) {
						continue;
					}
					if(type==4&&(k==3)) {
						continue;
					}
					fillup(x,y,dir1,newMap,map,N,M);
				}
			}
			
			int count = -wallcount;
			for(int j = 0 ; j < N ; j++) {
				for(int k = 0; k< M; k++) {
					if(newMap[j][k]==0) {
						count++;
					}
				}
			}
			
			if(count<curMin) {
				curMin = count;
			}
			
		}
		
		
		
		System.out.println(curMin);
		
	}
	
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static void fillup(int startX,int startY,int dir, int[][] map, int[][] theMap, int N , int M) {
		int x = startX;
		int y = startY;
		int addX = dir==RIGHT?1:((dir==LEFT)?-1:0);
		int addY = dir==DOWN?1:((dir==UP)?-1:0);
		while(true) {
			if(x>M-1||x<0||y>N-1||y<0) {
				break;
			}
			if(theMap[y][x]==6) {
				break;
			}
			map[y][x] = 7;
			y+=addY;
			x+=addX;
		}
	}
	
	static class Camera{
		int posX;
		int posY;
		int type;
		
	}
}