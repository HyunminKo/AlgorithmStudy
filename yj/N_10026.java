package DFS;

import java.util.Scanner;

public class N_10026 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static boolean check[][];
	static boolean abnormal = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int normal_count = 0;
		int abnormal_count = 0;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[][] arr = new String[N][N];
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String temp = sc.next();
			for (int j = 0; j < temp.length(); j++) {
				arr[i][j] = String.valueOf(temp.charAt(j));
			}
		}

		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check.length; j++) {
				if (check[i][j] == false) {
					normal_count++;
					check[i][j] = true;
					dfs(i, j, arr);
				}
			}
		}

		check = new boolean[N][N];
		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check.length; j++) {
				if (check[i][j] == false) {
					if (arr[i][j].equals("R") || arr[i][j].equals("G")) {
						abnormal = true;
					}
					abnormal_count++;
					check[i][j] = true;
					abnormal_dfs(i, j, arr);
					abnormal = false;
				}
			}
		}

		System.out.println(normal_count + " " + abnormal_count);
	}

	public static void dfs(int i, int j, String[][] arr) { // 정상인 경우
		for (int a = 0; a < dot.length; a++) {
			int dotx = i + dot[a][0];
			int doty = j + dot[a][1];

			// 범위 벗어나는 것 제거
			if (dotx < 0 || dotx >= check.length || doty < 0 || doty >= check.length)
				continue;

			if (check[dotx][doty] == false && arr[dotx][doty].equals(arr[i][j])) {
				// 아직 방문하지 않았으면서 색깔이 같으면 계속 파고들기
				check[dotx][doty] = true;
				dfs(dotx, doty, arr);
			}

		}
	}

	public static void abnormal_dfs(int i, int j, String[][] arr) { // 비정상인 경우
		for (int a = 0; a < dot.length; a++) {
			int dotx = i + dot[a][0];
			int doty = j + dot[a][1];

			// 범위 벗어나는 것 제거
			if (dotx < 0 || dotx >= check.length || doty < 0 || doty >= check.length)
				continue;

			if (abnormal) {
				if (check[dotx][doty] == false && (arr[dotx][doty].equals("R") || arr[dotx][doty].equals("G"))) {
					// 아직 방문하지 않았으면서 색깔이 같으면 계속 파고들기
					check[dotx][doty] = true;
					abnormal_dfs(dotx, doty, arr);
				}
			} else {
				if (check[dotx][doty] == false && arr[dotx][doty].equals(arr[i][j])) {
					// 아직 방문하지 않았으면서 색깔이 같으면 계속 파고들기
					check[dotx][doty] = true;
					abnormal_dfs(dotx, doty, arr);
				}
			}
		}
	}
}
