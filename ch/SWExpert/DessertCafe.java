package 디저트카페;

import java.util.Scanner;

public class DessertCafe {
	static int startX, startY, answer;// 최초의 위치
	static boolean[] 특정디저트를먹었는지;
	static boolean[][] 이미방문했는지;
	static int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 입력 영역
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();// 테스트 케이스 개수
		int tNum = 1;
		while (tNum <= T) {
			int N = scan.nextInt();// 디저트카페 구역의 한 변의 길이 N
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
				}

			// 알고리즘 영역 - 이동은 대각선만, 같은 디저트는 먹지 않는다(숫자 중복 X), 최대한의 디저트를 먹도록,
			// 한 방향으로만 회전하도록, 시작 위치로 돌아오면 끝나도록 , 다음으로 갈 방향을 선택할 때 2가지 사항을 검사
			// 같은 방향으로 갈지, 방향을 꺾어줄지
			// 방문 체크
			이미방문했는지 = new boolean[N][N];
			// 중복 숫자 체크(1~100)
			특정디저트를먹었는지 = new boolean[101];
			// result 초기화
			answer = 0;
			// 50개 케이스 3초 n=20개 최대
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					startX = j;
					startY = k;
					이미방문했는지[j][k] = true;
					특정디저트를먹었는지[map[j][k]] = true;
					// 왼쪽으로 도나 오른쪽으로 도나 똑같기 때문에 한 쪽 아래로만 돌면 된다.
					dfs(j, k, 1, 0, map, N);
					이미방문했는지[j][k] = false;
					특정디저트를먹었는지[map[j][k]] = false;
				}
			}
			if (answer < 4)
				answer = -1;
			// 출력 영역
			System.out.println("#" + tNum + " " + answer);
			tNum++;
		}
	}

	public static void dfs(int x, int y, int cnt, int d, int[][] map, int N) {
		if (d == 4)
			return;
		int tx = x + dir[d][0];
		int ty = y + dir[d][1];
		if (tx < 0 || ty < 0 || tx >= N || ty >= N)
			return;
		if (이미방문했는지[tx][ty] || 특정디저트를먹었는지[map[tx][ty]]) {
			// 이미 갔던 지점이 시작 점일 경우 개수 따지기
			if (tx == startX && ty == startY)
				answer = Math.max(answer, cnt);
			return;
		}
		// 숫자 사용 & 방문 표시
		특정디저트를먹었는지[map[tx][ty]] = true;
		이미방문했는지[tx][ty] = true;
		// 한 방향으로 쭉
		dfs(tx, ty, cnt + 1, d, map, N);
		// 그 때 그 때 다음 방향으로 틀기
		dfs(tx, ty, cnt + 1, d + 1, map, N);
		이미방문했는지[tx][ty] = false;
		특정디저트를먹었는지[map[tx][ty]] = false;
	}

}
