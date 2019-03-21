package DFS;

import java.util.Scanner;

public class N_11724 {
	static boolean[] check;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int result = 0;
		check = new boolean[N];
		int[][] arr = new int[N][N];
		int count = 0;
		while (count < M) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[y - 1][x - 1] = 1;
			arr[x - 1][y - 1] = 1;
			count++;
		} // 인접행렬 완성

		for (int i = 0; i < arr.length; i++) {
			if (!check[i]) {
				result++; // 개수 1만큼 증가
				dfs(i, arr);
			}
		}
		System.out.println(result);
	}

	public static void dfs(int i,int[][] arr) {
		check[i] = true;
		for (int j = 0; j < arr.length; j++) {
			if (!check[j] && arr[i][j] == 1) {
				dfs(j, arr);
			}
		}

	}
}
