import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final boolean isForSubmission = false;
	
	static final int NONE = 2;
	static final int A = 0;
	static final int B = 1;
	
	static boolean gogo(int numChem,int neededHeight,int height,int width,int count,int index,int[] layers, int[][] map) {
		if(index == height-1 && count==numChem) {
			//try			
			A:for(int i = 0 ; i < width;i++) {
				
				int currentHeight=0;
				int currentType = NONE;
				
				B:for(int j = 0; j<height ; j++) {
					int type = NONE;
					if(layers[j]==NONE) {
						type = map[j][i];
					}else {
						type = layers[j];
					}
					
					if(type == currentType) {
						currentHeight++;
					}else {
						currentHeight = 1;
						currentType = type;
					}

					if(currentHeight>=neededHeight) {
						continue A;
					}
				}
				
				return false;
			}
			return true;
			
		}else {
			if(count>numChem) { //도달 불가
				return false;
			}else if( ((height-1)-index)<(numChem-count)){
				return false;
			}else
				index = index+1;
				layers[index] = NONE;
				if(gogo(numChem,neededHeight,height,width,count,index,layers,map))return true;
				layers[index] = A;
				if(gogo(numChem,neededHeight,height,width,count+1,index,layers,map))return true;
				layers[index] = B;
				if(gogo(numChem,neededHeight,height,width,count+1,index,layers,map))return true;
				return false;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		//if(!isForSubmission) System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		for(int T = 0 ;T<numCases;T++) {
			
			int D = sc.nextInt();
			int W = sc.nextInt();
			int K = sc.nextInt();
			
			int [][] map = new int[D][W];
				
			
			for(int i = 0 ; i < D ;i++) {
				for(int j = 0; j< W ; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int minChem = K;
			int[] layers = new int[D];
			for(int i = 0 ; i <K ; i++) {
				if(gogo(i,K,D,W,0,-1,layers,map)) {
					minChem = i;
					break;
				}
			}
			
			System.out.println("#"+(T+1)+" "+minChem);
		}
	}
}