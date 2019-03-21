package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_10026 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static boolean check[][];
	static boolean abnormal = false;
	static Queue<Integer> xqueue;
	static Queue<Integer> yqueue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		xqueue = new LinkedList<Integer>();
		yqueue = new LinkedList<Integer>();
		int normal_count = 0;
		int abnormal_count = 0;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[][] normal_arr = new String[N][N];
		String[][] abnormal_arr = new String[N][N];
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < temp.length(); j++) {
				String x = String.valueOf(temp.charAt(j));
				normal_arr[i][j] = x;
				if (x.equals("R") || x.equals("G")) {
					abnormal_arr[i][j] = "R";
				} else {
					abnormal_arr[i][j] = x;
				}

			}
		} // 정상, 비정상 행렬채우기

		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check.length; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					xqueue.add(i);
					yqueue.add(j);
					normal_bfs(normal_arr);
					normal_count++;
				}
			}
		}
		check = new boolean[N][N];
		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check.length; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					xqueue.add(i);
					yqueue.add(j);
					abnormal_bfs(abnormal_arr);
					abnormal_count++;
				}
			}
		}
		
		System.out.println(normal_count + " "+ abnormal_count);
	}

	public static void normal_bfs(String[][] normal_arr) {
		while (!xqueue.isEmpty() && !yqueue.isEmpty()) {
			int xfront = xqueue.poll();
			int yfront = yqueue.poll();
			for (int a = 0; a < dot.length; a++) {
					int xdot = dot[a][0] + xfront;
					int ydot = dot[a][1] + yfront;

					if (xdot < 0 || ydot < 0 || xdot >= normal_arr.length || ydot >= normal_arr.length)
						continue;

					if (!check[xdot][ydot] && normal_arr[xdot][ydot].equals(normal_arr[xfront][yfront])) {
						xqueue.add(xdot);
						yqueue.add(ydot);
						check[xdot][ydot] = true; // 방문햇으니 체크
					}
				}
		}
	}
	
	public static void abnormal_bfs(String[][] abnormal_arr) {
		while (!xqueue.isEmpty() && !yqueue.isEmpty()) {
			int xfront = xqueue.poll();
			int yfront = yqueue.poll();
			for (int a = 0; a < dot.length; a++) {
					int xdot = dot[a][0] + xfront;
					int ydot = dot[a][1] + yfront;

					if (xdot < 0 || ydot < 0 || xdot >= abnormal_arr.length || ydot >= abnormal_arr.length)
						continue;
					if (!check[xdot][ydot] && abnormal_arr[xdot][ydot].equals(abnormal_arr[xfront][yfront])) {
						xqueue.add(xdot);
						yqueue.add(ydot);
						check[xdot][ydot] = true; // 방문햇으니 체크
					}
				}
		}
	}
}
