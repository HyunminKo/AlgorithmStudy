package SWExpertAcademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_2105 {
	static int[][] arr;
	static int[][] dot = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
	// 순서는 우하 -> 좌하 -> 좌상 -> 우상 (0,1,2,3)
	static int result;
	static boolean[][] visit;
	static List<Integer> desert;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			result = -1;
			int N = sc.nextInt();
			arr = new int[N][N];
			visit = new boolean[N][N];
			desert = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit[i][j] = true;
					desert.add(arr[i][j]);
					dfs(i,j, i, j,0, false);
					visit[i][j] = false;
					desert.remove(desert.get(desert.size()-1));
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
	
	public static void dfs(int a,int b, int x, int y,int dir, boolean start) { // x,y 는 시작 위치
		if(dir > 3) return;
			
		int dx = a + dot[dir][0];
		int dy = b + dot[dir][1];
			
		if(start && dx ==x && dy == y) {
			if(desert.size() > result) result = desert.size();
			return;
		}
		
		if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length || desert.contains(arr[dx][dy]) || visit[dx][dy]) return;
				
		visit[dx][dy] = true;
		desert.add(arr[dx][dy]);
			
		dfs(dx,dy,x,y,dir,true); // 똑같은 방향
		dfs(dx,dy,x,y,dir+1,true); // 다른 방향
		
		visit[dx][dy] = false;
		desert.remove(desert.get(desert.size()-1));
			
	}
}
