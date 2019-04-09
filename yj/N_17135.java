package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_17135 {
	static int[][] arr;
	static int[][] temp;
	static int D;
	static List<DOT> list;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		D = sc.nextInt();
		arr = new int[N][M];
		temp = new int[N][M];
		list = new ArrayList<DOT>();
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
				temp[i][j] = arr[i][j];
				if (arr[i][j] == 1)
					list.add(new DOT(i, j));
			}
		}
		
		int[] archor = new int[3];
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					archor[0] = i;
					archor[1] = j;
					archor[2] = k;
					int tmp = kill(archor);
					if (tmp > result)
						result = tmp;
					initArr();
				}
			}
		}
		System.out.println(result);
	}

	public static int kill(int[] archor) {
		int killnum = 0;
		while (!list.isEmpty()) {
			DOT[] minD = new DOT[3];
			for (int i = 0; i < archor.length; i++) {
				int dIndex = archor[i];
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < list.size(); j++) {
					DOT d = list.get(j);
					int dist = Math.abs(d.x - arr.length) + Math.abs(d.y - dIndex);
					if (dist <= D) {
						if (min > dist || (min == dist && minD[i].y > d.y)) {
							minD[i] = d;
							min = dist;
						}
					}
				}

			}

			for (int i = 0; i < 3; i++) {
				if (minD[i] != null && arr[minD[i].x][minD[i].y] != 0) {
					arr[minD[i].x][minD[i].y] = 0;
					killnum++;
				}
			}

			fitArr();
		}
		return killnum;
	}

	public static void fitArr() {
		list = new ArrayList<DOT>();
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = arr[i - 1][j];
				if (arr[i][j] == 1)
					list.add(new DOT(i, j));
			}
		}
		for (int i = 0; i < arr[0].length; i++) {
			arr[0][i] = 0;
		}
	}

	public static void initArr() {
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				arr[i][j] = temp[i][j];
				if (arr[i][j] == 1)
					list.add(new DOT(i, j));
			}
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
