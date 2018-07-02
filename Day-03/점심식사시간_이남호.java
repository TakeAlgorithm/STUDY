import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final int MAX = Integer.MAX_VALUE;
		
	
	static int cnt = 0;
	public static void log(String s) {
		if(true && cnt++ < 200)System.out.println(s);
	}
	
	
	public static int go(int N , int numPeople,int index, Stair[] stairs, Person[] people) {
		if( index >= numPeople-1) {
			int a = 0;
			for(int i = 0 ; i < 2 ; i++) {
				
				Stair s = stairs[i];
				Person[][] que = stairs[i].que;
				int[] endTime = {-1,-1,-1};
				int mod3 = 0;
			
				for(int j = 0 ; j < 2*N ; j++) {
					int arvtime = j+1;
					Person[] people1 = que[j]; 
					for(int k = 0 ; k < numPeople ; k++) {
						if(people1[k] != null) {
							if(people1[k].stair == i) {
								if(arvtime>= endTime[mod3]) {
									endTime[mod3] = arvtime+s.length;
								}else {
									endTime[mod3] += s.length;
								}	
								mod3 = (mod3+1)%3;			
							}				
						}else {
							break;
						}
					}
				}
			
				int last = (mod3+3-1)%3;
				int end = endTime[last];
				if(end>a) a= end;
			}
			return a;
		}else {
			int a = MAX;
			int b = MAX;
			index = index+1;
			people[index].stair = 0;
			b = go(N,numPeople, index , stairs , people);
			if(b<a)a =b;
			people[index].stair = 1;
			b = go(N,numPeople, index , stairs , people);
			if(b<a)a =b;
			
			return a;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		
		for(int T = 0 ;T<numCases;T++) {
			
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			Person[] people = new Person[10];
			Stair[] stairs = new Stair[2];
			
			int numPeople = 0;
			int stairIndex = 0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					int a = sc.nextInt();
					switch(a) {
					case(0):
						break;
					case(1):
						people[numPeople] = new Person();
						people[numPeople].xPos = j;
						people[numPeople].yPos = i; 
						numPeople++;
						break;
					default:
						Stair s = new Stair();
						stairs[stairIndex] = s;
						s.length = a;
						s.xPos = j;
						s.yPos = i;
						stairIndex++;	
						break;
					}
				}				
			}
			
			for(int j = 0; j < 2 ; j++) {
				stairs[j].que = new Person[2*N][numPeople];
			}
			
			for(int i = 0 ; i < numPeople ; i++ ) {
				Person p = people[i];
				for(int j = 0 ; j < 2 ; j ++) {
					int a = p.xPos - stairs[j].xPos;
					int b = p.yPos - stairs[j].yPos;
					a = a<0?-a:a;
					b = b<0?-b:b;
					Person[] que = stairs[j].que[a+b];
					for(int k = 0 ; k < numPeople ; k ++) {
						if(que[k]==null) {
							que[k] = p;
							break;
						}
					}
				}
			}
		
			System.out.println("#"+(T+1)+" "+go(N ,numPeople,-1, stairs, people));
		}
		
		
		
	}
	
	static class Person{
		int xPos;
		int yPos;
		int stair;
	}
	
	static class Stair{
		int xPos;
		int yPos;
		int length;
		Person[][] que;
	}
	
}