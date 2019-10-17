import java.util.HashMap;
import java.util.Scanner;

public class PinballGame {

	static int T;
	static int N;
	static int[][] map;
	static int answer = Integer.MIN_VALUE;
	// 상 하 좌 우
	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static HashMap<Integer, Wormhole[]> whHash = new HashMap<Integer, Wormhole[]>();

	public static void main(String[] args) {
		// 입력 영역

		Scanner scan = new Scanner(System.in);
		T = scan.nextInt();
		int tNum = 1;
		while (tNum <= T) {
			N = scan.nextInt();
			map = new int[N][N];

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
					if (6 <= map[i][j] && map[i][j] <= 10) {
						if (whHash.containsKey(map[i][j])) {
							Wormhole[] temp = whHash.get(map[i][j]);
							temp[1] = new Wormhole(i, j);
							whHash.remove(map[i][j]);
							whHash.put(map[i][j], temp);
						} else {
							Wormhole[] w = new Wormhole[2];
							w[0] = new Wormhole(i, j);
							whHash.put(map[i][j], w);
						}

					}
				}

			// 알고리즘 영역
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (map[i][j] == 0)
						for (int k = 0; k < 4; k++)// 시작 위치로 부터 4가지 방향에 따른 경우의 수를 모두 계산
							answer = Math.max(answer, collisionNumberCounter(new Pinball(i, j), k));

			// 출력 영역
			System.out.println("#" + tNum + " " + answer);
			answer = Integer.MIN_VALUE;
			whHash.clear();
			tNum++;
		}
	}

	public static int collisionNumberCounter(Pinball pb, int dir) {
		int count = 0;
		int initX = pb.x;
		int initY = pb.y;

		while (true) {
			// 핀볼이 정해진 방향에 의해 이동된 후의 좌표를 계산해보자.
			// 실제로 이동한 것은 아니고 다음 위치를 예측하고
			// 그 위치에 있을 장애물이 있을 때 핀볼의 진행 방향과 위치를
			// 상황 별로 대처하는 코드를 짜야한다.
			int nx = pb.x + d[dir][0];
			int ny = pb.y + d[dir][1];

			if (nx == initX && ny == initY)
				break;
			// 진행방향에 따라 핀볼의 다음 위치가 게임판 밖을 나갈 경우다.
			// 원래 진행 방향의 반대 방향으로 dir을 바꿔주자.
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				if (dir == 0)
					dir = 1;
				else if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 3;
				else
					dir = 2;
				count++;
			} else if (map[nx][ny] == -1) {
				break;
			} else if (map[nx][ny] == 0) {
				pb.x = nx;
				pb.y = ny;

				continue;
			} else if (map[nx][ny] == 1) {// 왼쪽 아래 삼각형
				if (dir == 0)
					dir = 1;
				else if (dir == 1)
					dir = 3;
				else if (dir == 2)
					dir = 0;
				else
					dir = 2;

				count++;

			} else if (map[nx][ny] == 2) {// 왼쪽 위 삼각형
				if (dir == 0)
					dir = 3;
				else if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 1;
				else
					dir = 2;

				count++;

			} else if (map[nx][ny] == 3) {// 오른쪽 위 삼각형
				if (dir == 0)
					dir = 2;
				else if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 3;
				else
					dir = 1;

				count++;

			} else if (map[nx][ny] == 4) {// 오른쪽 아래 삼각형
				if (dir == 0)
					dir = 1;
				else if (dir == 1)
					dir = 2;
				else if (dir == 2)
					dir = 3;
				else
					dir = 0;

				count++;

			} else if (map[nx][ny] == 5) {// 사각형
				if (dir == 0)
					dir = 1;
				else if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 3;
				else
					dir = 2;

				count++;
			} else if (6 <= map[nx][ny] && map[nx][ny] <= 10) {
				Wormhole[] whArr = whHash.get(map[nx][ny]);
				for (int i = 0; i < whArr.length; i++) {
					if (nx != whArr[i].x && ny != whArr[i].y) {
						nx = whArr[i].x;
						ny = whArr[i].y;
						break;
					}
				}
			}
			pb.x = nx;
			pb.y = ny;

		}
		return count;
	}

	public static class Pinball {
		int x, y;// 현재 위치

		public Pinball(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static class Wormhole {
		int x, y;// 현재 위치

		public Wormhole(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
