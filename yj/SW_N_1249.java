package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_1249 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static int dist[][];
	static int[][] arr;
	static boolean[][] check;
	static Queue<DT> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		for (int test_case = 1; test_case <= C; test_case++) {
			int N = sc.nextInt();
			arr = new int[N][N];
			queue = new LinkedList<DT>();
			
			for (int i = 0; i < N; i++) { // 배열 생성및 구분
				String temp = sc.next();
				String[] str_arr = temp.split("");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(str_arr[j]);
				}
			}
			
			check = new boolean[N][N];
			queue.add(new DT(0,0));
			dist = new int[N][N];
			check[0][0] = true;
			bfs();
			System.out.println("#"+test_case+" "+dist[arr.length-1][arr.length-1]);
		}
	}
	
	public static void bfs() {
		while (!queue.isEmpty()) {
			DT d = queue.poll();
			for (int i = 0; i < dot.length; i++) {
				int dx = d.x + dot[i][0];
				int dy = d.y + dot[i][1];

				if (dx < 0 || dy < 0 || dx >= arr.length || dy >= arr.length)
					continue;

				if(!check[dx][dy] || (dist[d.x][d.y] + arr[dx][dy] < dist[dx][dy])) {
					check[dx][dy] = true;
					dist[dx][dy] = dist[d.x][d.y] + arr[dx][dy];
					queue.add(new DT(dx,dy));
				}
			}
		}
	}
	
}

class DT {
	int x,y;
	
	public DT(int x,int y) {
		this.x=x;
		this.y=y;
	}
}