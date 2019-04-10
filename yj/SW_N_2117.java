package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_2117 {
	static int[][] arr;
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static Queue<DOT> queue;
	static int M;
	static int result;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // 도시 크기
			M = sc.nextInt(); // 집마다의 비용
			queue = new LinkedList<DOT>();
			arr = new int[N][N];
			result = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			for(int K=1;K<=arr.length+1;K++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						bfs(i, j, K);
					}
				}				
			}

			System.out.println("#" + test_case + " " + result);
		}
	}

	public static void bfs(int i, int j, int K) {
		int cost = K * K + (K-1) * (K-1);
		int home = 0;
		int[][] dist = new int[arr.length][arr[0].length];
		boolean[][] visit = new boolean[arr.length][arr[0].length];
		
		if(arr[i][j] == 1) home++; // 자기 자신이 집이면
		dist[i][j] = 0;
		visit[i][j] = true;
		queue.add(new DOT(i, j));

		while (!queue.isEmpty()) {
			DOT d = queue.poll();
			for (int a = 0; a < 4; a++) {
				int dx = d.x + dot[a][0];
				int dy = d.y + dot[a][1];

				if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length || visit[dx][dy])
					continue;

				visit[dx][dy] = true;
				dist[dx][dy] = dist[d.x][d.y] + 1;
				if (dist[dx][dy] < K) {
					if(arr[dx][dy] == 1) home++;
					queue.add(new DOT(dx, dy));
				}
			}

		}
		int company = home * M - cost;
		if (company >= 0) {
			result = Integer.max(result, home);
		}
	}

	static class DOT {
		int x, y;

		public DOT(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
