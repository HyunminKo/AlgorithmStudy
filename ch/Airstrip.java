import java.util.Scanner;

public class Airstrip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();// 테스트 케이스 개수
		int tNum = 1;
		while (tNum <= T) {
			int tAnswer = 0;
			int N = scan.nextInt();// 지도 한 변의 크기
			int X = scan.nextInt();// 경사로 길이
			int[][] map = new int[N][N];
			boolean flag = true;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
				}
			// 가로 탐색
			for (int i = 0; i < N; i++) {
				boolean[] check = new boolean[N];
				int x = map[i][0];
				for (int j = 1; j < N; j++) {
					if (Math.abs(x - map[i][j]) >= 2) {
						flag = false;
						continue;
					}
					if (x - 1 == map[i][j]) {
						x = map[i][j];
						if (N - j >= X) {
							for (int k = 0; k < X; k++) {
								if (check[j + k] == false) {
									check[j + k] = true;
								} else if (check[j + k] == true) {
									flag = false;
									continue;
								}
							}
						} else {
							flag = false;
							continue;
						}
					} else if (x + 1 == map[i][j]) {
						x = map[i][j];
						if (j >= X) {
							for (int k = 1; k <= X; k++) {
								if (check[j - k] == false) {
									check[j - k] = true;
								} else if (check[j - k] == true) {
									flag = false;
									continue;
								}
							}
						} else {
							flag = false;
							continue;
						}
					}

				}
				if (flag == false) {
					flag = true;
				} else {
					tAnswer++;
				}
			}

			// 새로 탐색
			for (int j = 0; j < N; j++) {
				boolean[] check = new boolean[N];
				int x = map[0][j];
				for (int i = 1; i < N; i++) {
					if (Math.abs(x - map[i][j]) >= 2) {
						flag = false;
						continue;
					}
					if (x - 1 == map[i][j]) {
						x = map[i][j];
						if (N - i >= X) {
							for (int k = 0; k < X; k++) {
								if (check[i + k] == false) {
									check[i + k] = true;
								} else if (check[i + k] == true) {
									flag = false;
									continue;
								}
							}
						} else {
							flag = false;
							continue;
						}
					} else if (x + 1 == map[i][j]) {
						x = map[i][j];
						if (i >= X) {
							for (int k = 1; k <= X; k++) {
								if (check[i - k] == false) {
									check[i - k] = true;
								} else if (check[i - k] == true) {
									flag = false;
									continue;
								}
							}
						} else {
							flag = false;
							continue;
						}
					}

				}
				if (flag == false) {
					flag = true;
				} else {
					tAnswer++;
				}
			}

			System.out.println("#" + tNum + " " + tAnswer);
			tNum++;
		}
	}

}
