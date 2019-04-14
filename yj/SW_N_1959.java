package SWExpertAcademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_1959 {
	static int[][] arr;
	static boolean[][] visit;
	static int[][] dot = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 상하좌우
	static int K;
	static int result;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int max = Integer.MIN_VALUE;
			K = sc.nextInt();
			arr = new int[N][N];
			List<DOT> list = new ArrayList<DOT>();
			visit = new boolean[N][N];
			result = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
					max = Math.max(max, arr[i][j]);
				}
			}
			
			list = getMax(max);
			for(DOT d : list) {
				visit[d.x][d.y] = true;
				dfs(d.x, d.y, 1,false);	
			}
			
			if(list.size() == 1) { // 가장 큰 봉우리 하나인 경우
				DOT d = list.get(0);
				for(int i=1;i<=K;i++) {
					arr[d.x][d.y]= max - i;
//					list = getMax(max-i); 봉우리는 고정되어있다!!!!!
					visit[d.x][d.y] = true;
					dfs(d.x, d.y, 1,true);					
				}
			}

			System.out.println("#" + test_case + " "+result);
		}
	}
	
	public static List<DOT> getMax(int max) {
		List<DOT> list = new ArrayList<DOT>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][j] == max) {
					list.add(new DOT(i,j));
				}
			}
		}
		return list;
	}
	
	public static void dfs(int i, int j,int count,boolean isCut) {
		for (int a = 0; a < 4; a++) {
			int dx = i + dot[a][0];
			int dy = j + dot[a][1];

			if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length || visit[dx][dy])
				continue;

			if (arr[i][j] > arr[dx][dy]) {
				visit[dx][dy] = true;
				dfs(dx,dy,count+1,isCut);
			}else {
				for(int b =1;b<=K;b++) {
					int cut = arr[dx][dy]-b;
					if(!isCut && arr[i][j] > cut) {
						int temp = arr[dx][dy];
						visit[dx][dy] = true;
						arr[dx][dy] = cut;
						dfs(dx,dy,count+1,true);
						arr[dx][dy] = temp;
					}					
				}
			}
		}
		visit[i][j] = false;
		if(count > result) {
			result = count;
		}
	}
	
	static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}
