import java.io.FileInputStream;
import java.util.Scanner;

//https://www.acmicpc.net/problem/14890 - 복습

public class Solution {

    static int g[][];
    static int N;
    static int L;
    static int result = 0;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int T;
        T = sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();
            L = sc.nextInt();
            g = new int[N][N];
            result = 0;
            // Input
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    g[i][j] = sc.nextInt();
                }
            }

            // N*2 개의 길을 확인
            for (int t = 0; t < N * 2; t++) {
                int[] nowG = new int[N];
                int x = t;

                // 확인할 길을 만듬 : 가로 확인 후(N 이후) 세로 확인
                for (int idx = 0; idx < N; idx++) {
                    int y = idx;

                    if (t / N > 0) { // x y 교환
                        y = t % N;
                        x = idx;
                    }
                    nowG[idx] = g[x][y];
                }

                cal(nowG);
            }

            System.out.printf("#%d %d\n", test_case, result);
        }
    }

    private static void cal(int[] arr) {
        int dif = 0; // 경사로단계를 나타냄 0 : 이전길과 같음 -1 : 오르막 길 1 : 내리막길
        boolean[] visit = new boolean[N]; // 경사로를 놨던 길인지 확인

        boolean canGo = true;
        for (int i = 1; i < arr.length; i++) {
            dif = arr[i - 1] - arr[i];

            // 01. 다음 길의 경사가 1보다 차이가 나서 지나갈 수 없는 경우
            if (dif > 1 || dif < -1) {
                canGo = false;
                break;
            }

            // 02 경사로 만들기 : 다음 중 하나라도 해당되는 경우 경사로 만들 수 없음
            // (길의 범위 벗어남 || 이미 경사로를 만든 길 || 평평하지 않은길 )

            // _1. 오르막길인경우 L 만큼 이전길 확인
            if (dif == -1) {
                for (int j = i - 1; j >= i - L; j--) {
                    if (j < 0 || visit[j] || arr[j] != arr[i - 1]) {
                        canGo = false;
                        break;
                    }
                    visit[j] = true;
                }
                // _2. 내리막길인경우 L 만큼 다음길 확인
            } else if (dif == 1) {
                for (int j = i; j < i + L; j++) {
                    if (j >= arr.length || visit[j] || arr[j] != arr[i]) {
                        canGo = false;
                        break;
                    }
                    visit[j] = true;
                }
            }
        }

        if (canGo)
            result++;
    }
}