package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_2146 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static Queue<DOT> xyqueue = new LinkedList<DOT>();
	static Queue<DOT> shore = new LinkedList<DOT>();
	static Queue<DOT> queue;
	static int[][] arr;
	static int[][] dist;
	static boolean check[][];
	static int island_Index = 0;
	static int min = Integer.MAX_VALUE;
	static int division;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
				if (arr[i][j] == 1) {
					xyqueue.add(new DOT(i, j));
					shore.add(new DOT(i, j));
				}
			}
		}

		check = new boolean[N][N];
		while (!xyqueue.isEmpty()) { // 각각 섬의 번호 지정
			DOT d = xyqueue.poll();
			int i = d.getX();
			int j = d.getY();

			if (!check[i][j]) {
				island_Index++;
				arr[i][j] = island_Index;
				check[i][j] = true;
				change(i, j);
			}
		}
		
		while(!shore.isEmpty()) { // 섬의 해당하는 곳만 담은 큐가 빌 때까지
			queue = new LinkedList<DOT>();
			DOT d = shore.poll();
			int i = d.getX();
			int j = d.getY();
			division = arr[i][j];
			queue.add(d);
			bfs();				
		}
		
		System.out.println(min);
	}

	public static void bfs() {
		check = new boolean[arr.length][arr.length];
		dist = new int[arr.length][arr.length];
		while (!queue.isEmpty()) {
			DOT d = queue.poll();
			int i = d.getX();
			int j = d.getY();
			check[i][j] = true;
			for (int k = 0; k < dot.length; k++) {
				int dx = dot[k][0] + i;
				int dy = dot[k][1] + j;

				if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length)
					continue; // 벗어나는거 필터링
				
				if(arr[dx][dy] != 0 && arr[dx][dy] != division) {
					if(min > dist[i][j]) min = dist[i][j];
				}
				
				if (!check[dx][dy] && arr[dx][dy] == 0) {
					check[dx][dy] = true;
					queue.add(new DOT(dx, dy));
					dist[dx][dy] = dist[i][j] + 1;
				}
			}
			
		}
	}

	public static void change(int i, int j) {
		for (int k = 0; k < dot.length; k++) {
			int dx = dot[k][0] + i;
			int dy = dot[k][1] + j;
			
			if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length)
				continue; // 벗어나는거 필터링

			if (!check[dx][dy] && arr[dx][dy] == 1) {
				check[dx][dy] = true;
				arr[dx][dy] = island_Index;
				change(dx, dy);
			}
		}
	}

}

class DOT {
	int x, y;

	public DOT(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}