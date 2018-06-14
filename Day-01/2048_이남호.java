import java.util.Scanner;
import java.io.FileInputStream;

/*
   ����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
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
		   �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   �������� �ۼ��� �ڵ带 �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��,
		   �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ �� ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�.
		   ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.
		   ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.
		 */
		System.setIn(new FileInputStream("input.txt"));

		/*
		   ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.
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