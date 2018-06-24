import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final boolean isForSubmission = true;
	
	final static int UP = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;
	final static int RIGHT =4;
	
	final static int DEAD = -1;
	
	public static void log(String s) {
		if(!isForSubmission)System.out.println(s);
	}
	
	public static void main(String[] args) throws Exception {
		
		if(!isForSubmission) System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		
		for(int T = 0 ;T<numCases;T++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			
			Cluster[][] map = new Cluster[N][N];
			Cluster[] clusters = new Cluster[K];
			
			for(int i = 0 ; i < K ;i++) {
				Cluster c = new Cluster();
				int y = sc.nextInt();
				int x = sc.nextInt();
				map[y][x] = c;
				c.y = y;
				c.x = x;
				c.number = sc.nextInt();
				c.dir = sc.nextInt();
				clusters[i] = c;
			}
			
			int time = M;
			while(time>0) {
				log("b");
				Cluster[][] newmap = new Cluster[N][N];
				for(int i = 0; i < K ; i++) {
					Cluster c = clusters[i];
					map[c.y][c.x] = null;
					if(c.dir != DEAD) {
						switch(c.dir) {
						case(UP):
							c.y--;
							break;
						case(DOWN):
							c.y++;
							break;
						case(LEFT):
							c.x--;
							break;
						case(RIGHT):
							c.x++;
							break;
						}
						
						if(c.x==0) {
							c.dir = RIGHT;
							c.number = c.number/2;
						}else if(c.x == N-1) {
							c.dir = LEFT;
							c.number = c.number/2;
						}else if(c.y == 0) {
							c.dir = DOWN;
							c.number = c.number/2;
						}else if(c.y == N-1) {
							c.dir = UP;
							c.number = c.number/2;
						}
						
						if(newmap[c.y][c.x] != null) {
							Cluster c2 = newmap[c.y][c.x];
							while(c2.next!=null) {
								log("a");
								c2 = c2.next;
							}
							c2.next = c;
						}else {
							newmap[c.y][c.x] = c;
						}
						
					}
				}

				map = newmap;
				

				//merge	
				for(int i = 0;i<N;i++) {
					for(int j = 0 ; j <N ; j++) {
						if(map[i][j]!=null && map[i][j].next!=null) {
							
							Cluster c = map[i][j];
							
							Cluster maxNumCluster = null;
							int maxNum = 0; 
							int sumNum = 0;
							int maxNumDir = 0;
							
							while(c!=null) {
								if(c.number > maxNum) {
									maxNum = c.number;
									maxNumCluster = c;
									maxNumDir = c.dir;
								}
								c.dir = DEAD;
								sumNum +=c.number;
								c = c.next;
							}
							
							maxNumCluster.number = sumNum;
							maxNumCluster.next = null;
							maxNumCluster.dir = maxNumDir;
							map[i][j] = maxNumCluster;
						}
					}
				}
			
				
				
				
				time--;
			}
			
			int sum = 0;
			for(int i = 0;i<N;i++) {
				for(int j = 0 ; j <N ; j++) {
					if(map[i][j]!=null) {
						sum+=map[i][j].number;
					}
				}
			}
		
			
			System.out.println("#"+(T+1)+" "+sum);
			
		
		}
		
	}
	
	static class Cluster{
		int dir;
		int number;
		int x;
		int y;
		Cluster next;
	}
	
	
	
	


}
