package BFSDFS;

import java.util.Scanner;

public class N_2468 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static int[][] arr;
	static boolean[][] check;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); 
		arr = new int[N][N];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j] = sc.nextInt();
				if(min > arr[i][j]) min = arr[i][j];
				if(max < arr[i][j]) max = arr[i][j];
			}
		}
		
		int max_count=1;
		while(min <= max) { // 높이의 경우의 수
			int result = 0;
			check = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!check[i][j] && arr[i][j] > min) {
						dfs(i,j,min);
						result++;
					}
				}
			}
			if(max_count < result) max_count = result;
			min++;
		}
		System.out.println(max_count);
	}
	
	public static void dfs(int x, int y, int min) {
		check[x][y] = true;
		for(int i=0;i<4;i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length) continue;
			
			if(!check[dx][dy] && arr[dx][dy] > min) {
				dfs(dx,dy,min);
			}
		}
	}

}
