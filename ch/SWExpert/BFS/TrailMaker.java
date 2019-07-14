import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TrailMaker {
	static int T;
	static int N;
	static int K;
	static int[][] map;

	static Queue<Pair> 계산대상봉우리들;
	static ArrayList<Pair> 가장높은봉우리;
	static int 만들_수_있는_가장_긴_등산로의_길이 = 0;
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		// 입력 영역
		Scanner scan = new Scanner(System.in);
		T = scan.nextInt();// 테스트 케이스 개수
		int tNum = 1;
		while (tNum <= T) {
			N = scan.nextInt();
			K = scan.nextInt();
			map = new int[N][N];

			int maxNum = 0;// 최고 높이의 봉우리가 얼마인지 찾아내기 위한 방법
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
					if (maxNum < map[i][j])
						maxNum = map[i][j];
				}
			// 알고리즘 영역
			for (int k = 1; k <= K; k++) {
				for (int ii = 0; ii < N; ii++)
					for (int jj = 0; jj < N; jj++) {
						map[ii][jj] = map[ii][jj] - k;

						가장높은봉우리 = new ArrayList<Pair>();
						계산대상봉우리들 = new LinkedList<Pair>();
						for (int i = 0; i < N; i++)
							for (int j = 0; j < N; j++)
								if (maxNum == map[i][j]) {
									가장높은봉우리.add(new Pair(i, j, 1));
								}

						while (!가장높은봉우리.isEmpty()) {
							Pair peak = 가장높은봉우리.remove(0);
							int 봉우리로부터의길이 = peak.l;
							계산대상봉우리들.add(peak);

							while (!계산대상봉우리들.isEmpty()) {
								Pair p = 계산대상봉우리들.poll();
								int x = p.i;
								int y = p.j;
								int l = p.l;
								만들_수_있는_가장_긴_등산로의_길이 = Math.max(만들_수_있는_가장_긴_등산로의_길이, l);

								for (int q = 0; q < 4; q++) {
									int nx = x + dx[q];
									int ny = y + dy[q];
									if (nx < 0 || nx >= N || ny < 0 || ny >= N)
										continue;
									if (map[nx][ny] < map[x][y]) {
										int temp_l = l + 1;
										계산대상봉우리들.offer(new Pair(nx, ny, temp_l));
									}
								}
							}
						}
						map[ii][jj] = map[ii][jj] + k;
					}
			}

			// 출력 영역
			System.out.println("#" + tNum + " " + 만들_수_있는_가장_긴_등산로의_길이);
			만들_수_있는_가장_긴_등산로의_길이 = 0;
			tNum++;
		}
	}

	public static class Pair {
		int i;
		int j;
		int l;

		public Pair(int i, int j, int l) {
			this.i = i;
			this.j = j;
			this.l = l;
		}
	}

}
