import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Main
{
	
	static int size;
	static int[][] nums;
	static int countss = 0;
	
	public static void go(boolean jDirIsPositive,boolean jIsRight, int[][] input, int[][] output) {
		
		int jStart = jDirIsPositive?0:(size-1);
		int jDir= jDirIsPositive?1:-1;
		int jEnd = jStart+jDir*size;
		
		int i = 0;		
		while(i<size) {
			int j = jStart;
			
			int curWrittenPos = jStart-jDir;
			int justRead = -1;
			boolean justMerged = false;
			
			while(j!=jEnd) {
				int a = jIsRight?i:j;
				int b = jIsRight?j:i;
				if(input[a][b]==0) {
					j+=jDir;
					continue;
				}
				
				if(jIsRight) {
					
					if(input[a][b] == justRead && !justMerged) {	
						justMerged = true;
						output[a][curWrittenPos]*=2;
					}else {
						justMerged = false;
						justRead = input[a][b];
						curWrittenPos+=jDir;
						output[a][curWrittenPos] = justRead; 
					}
				}else {
					if(input[a][b] == justRead && !justMerged) {	
						justMerged = true;
						output[curWrittenPos][b]*=2;
					}else {
						justMerged = false;
						justRead = input[a][b];
						curWrittenPos+=jDir;
						output[curWrittenPos][b] = justRead; 
					}
				}
			
				j+=jDir;
			}
			i++;
		}
	}
	
	
	public static int getBiggestIn(int[][] nums) {
		int a = -1;
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < size ; j ++) {
				if(a<nums[i][j]) {
					a = nums[i][j];
				}
			}	
		}
		return a;
	}
	
	
	public static int gogo(int count,int curMove,int[][] input) {
		
		int[][] output = new int[size][size];
		
		if(curMove <0) {
			output = input;
		}else{
			switch(curMove) {
			case(0):
				go(true,true,input,output);
				break;
			case(1):
				go(true,false,input,output);
				break;
			case(2):
				go(false,true,input,output);
				break;
			case(3):
				go(false,false,input,output);
				break;
			}
			count++;
		}
		
		if(count>=5) {
			return getBiggestIn(output);
		}else {
			int a = -1;
			for(int i=0;i<4;i++) {
				int out = gogo(count,i,output);
				if(out>a)a = out;
			}
			return a;	
		}
		
	}
	
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		System.setIn(new FileInputStream("input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		
		size = sc.nextInt();
		nums = new int[size][size];
		
		for(int i = 0 ; i < size ;i ++) {
			for(int j = 0 ; j < size ;j ++) {
				nums[i][j] = sc.nextInt();
				//System.out.print(nums[i][j]+" ");
			}
			//System.out.println();
		}
		
		
		System.out.print(gogo(0,-1,nums));
		
				
		
	}
}