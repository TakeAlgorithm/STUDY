import java.io.FileInputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	final static boolean display = true;
		
	final static int NONE = 0;
	final static int APPLE = 1;
	final static int RIGHT = 5;
	final static int UP = 6;
	final static int LEFT = 7;
	final static int DOWN = 8;
	
	static int[][] map;
	static int currentDir;
	static int currentTime;
	
	
	static void switchDir(char LorD) {
		switch(currentDir) {
		case(RIGHT):
			if(LorD == 'L') {
				currentDir = UP;
			}else {
				currentDir = DOWN;
			}
			break;
		case(DOWN):
			if(LorD == 'L') {
				currentDir = RIGHT;
			}else {
				currentDir = LEFT;
			}
			break;
		case(LEFT):
			if(LorD == 'L') {
				currentDir = DOWN;
			}else {
				currentDir = UP;
			}
			break;
		case(UP):
			if(LorD == 'L') {
				currentDir = LEFT;
			}else {
				currentDir = RIGHT;
			}
			break;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
	
		if(display)System.setIn(new FileInputStream("input.txt"));

		
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		map = new int[size][size];
		
		
		int numApple = sc.nextInt();
		Pos[] apples = new Pos[numApple];
		for(int i = 0 ; i < numApple ; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = APPLE;
		}
		int numMove = sc.nextInt();
		Move[] moves = new Move[numMove+1];
		sc.nextLine();
		for(int i = 0 ; i < numMove;i ++) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			moves[i] = new Move(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0)); 
			if(display)System.out.println("got Move :"+i+" " + moves[i]);
		}
		moves[numMove] = new Move(100000,'D');
		
		currentDir = RIGHT;
		currentTime = 0;
		int currentPendingMove = 0;
		
		int headX = 0;
		int headY = 0;
		
		int tailX =0;
		int tailY =0;
		
		while(true) {
			currentTime++;
			
			// CHANGE DIR IF NEEDED
			if(moves[currentPendingMove].time <= currentTime) {
				switchDir(moves[currentPendingMove].dir);
				currentPendingMove ++;
			}
			
			
			//colorMap;
			map[headY][headX] = currentDir;
			boolean ateApple =false;
			
			//moveHead
			switch(currentDir) {
			case(RIGHT):
				headX++;
				break;
			case(DOWN):
				headY++;
				break;
			case(LEFT):
				headX--;
				break;
			case(UP):
				headY--;
				break;
			}
			
			//if touch
			
			if(headX<0||headX>=size||headY<0||headY>=size) {
				break;
			}else if(map[headY][headX]==APPLE) {
				ateApple = true;
			}else if(map[headY][headX]!=NONE) {
				break;
			}
			
			
			//moveTail
			if(!ateApple) {
				int curTail = map[tailY][tailX];
				map[tailY][tailX] = NONE;
				switch(curTail) {
				case(RIGHT):
					tailX++;
					break;
				case(DOWN):
					tailY++;
					break;
				case(LEFT):
					tailX--;
					break;
				case(UP):
					tailY--;
					break;
				}
			}
			
			if(display) {
				for(int i = 0 ; i < size ;i++) {
					for(int j=0;j<size;j++) {
						char c = '.';
						switch(map[i][j]) {
						case(APPLE): c = 'O';break;
						case(NONE): break;
						default: c='X'; break;
						}
						System.out.print(c);
					}
					System.out.println();
				}

				System.out.println();
				Thread.sleep(500);
			}
			
		}

		System.out.println(currentTime);
	}
	
	static class Pos{
		public Pos(int x , int y) {
			this.x= x ;
			this.y = y;
		}
		int x;
		int y;
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static class Move{
		int time;
		char dir;
		public Move(int time, char dir) {
			// TODO Auto-generated constructor stub
			this.time = time;
			this.dir = dir;
			
		}
		@Override
		public String toString() {
			return "Move [time=" + time + ", dir=" + dir + "]";
		}
	}
	
	
}
