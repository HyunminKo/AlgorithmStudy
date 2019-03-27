package BFSDFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class N_2583 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static int rec_num = 0;
	static int count = 0;
	static int[][] arr;
	static List<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 행 수
		int M = sc.nextInt(); // 열 수
		int num = sc.nextInt();
		arr = new int[N][M];
		int k = 0;
		while(k < num) { // 영역 구분
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			for(int i=sy;i<ey;i++) {
				for(int j=sx;j<ex;j++) {
					arr[i][j] = -1; // 색칠된 부분은 -1로
				}
			}
			k++;
		}
		
		for(int i=0;i<N;i++) {
			for(int j =0;j<M;j++) {
				if(arr[i][j] == 0) {
					count = 1;
					arr[i][j] = ++rec_num;
					dfs(i,j);
					list.add(count);
				}
			}
		}
		
		System.out.println(rec_num);
		Collections.sort(list);
		for(int i : list) {
			System.out.print(i+" ");
		}
	}
	
	public static void dfs(int x,int y) {
		for(int i=0;i<4;i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length) continue;
			
			if(arr[dx][dy] == 0) {
				arr[dx][dy] = rec_num;
				count++;
				dfs(dx,dy);
			}
		}
	}

}
