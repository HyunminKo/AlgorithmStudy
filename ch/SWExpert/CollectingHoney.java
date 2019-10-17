import java.util.Scanner;

public class CollectingHoney {
	static int ProfitMax_A = 0;
	static int ProfitMax_B = 0;
	static int ProfitMax_sum = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 입력 영역
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();// 테스트 케이스 개수
		int tNum = 1;
		while (tNum <= T) {

			int N = scan.nextInt();// 벌통 모임의 가로 새로 길이(크기)
			int[][] map = new int[N][N];// N의 최대 크기를 정해줬음.
			int M = scan.nextInt();// 선택할 수 있는 벌통 개수
			int C = scan.nextInt();// 꿀 채취 최대 양

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					map[i][j] = scan.nextInt();
				}
			// 알고리즘 영역

			int[] combArr = new int[M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int start_A = j;
					int end_A = start_A + M;
					if ((N - start_A) < M)
						continue;
					// 첫 번째 일꾼 채취
					int insertIdxA = 0;
					int[] arrA = new int[M];
					for (int k = start_A; k < end_A; k++) {
						arrA[insertIdxA++] = map[i][k];
					}
					for (int k = 1; k <= M; k++) {
						doCombination('A', combArr, M, k, 0, 0, arrA, C);
					}
					int B_start_line = i;
					int B_zeroOrNext_idx = end_A;
					if (end_A + M > N) {
						B_start_line = i + 1;
						B_zeroOrNext_idx = 0;
					}
					for (int a = B_start_line; a < N; a++) {
						for (int b = B_zeroOrNext_idx; b < N; b++) {
							int start_B = b;
							int end_B = start_B + M;
							if ((N - start_B) < M)
								continue;
							// 두 번째 일꾼 채취
							// A일꾼의 선택할 수 있는 벌통 중 최대 이윤을 계산했다. 그다음 나머지 선택되지 않은
							// 벌통 중 B일꾼의 최대 이윤을 다 계산하여 나중에 두 개의 계산 값을 더하여 최종 최대 이윤을 뽑자.
							int insertIdxB = 0;
							int[] arrB = new int[M];
							for (int c = start_B; c < end_B; c++) {
								arrB[insertIdxB++] = map[a][c];
							}
							for (int c = 1; c <= M; c++) {
								doCombination('B', combArr, M, c, 0, 0, arrB, C);
							}
							ProfitMax_sum = Math.max(ProfitMax_sum, ProfitMax_A + ProfitMax_B);
							ProfitMax_B = 0;
						}
					}
				}
			}

			// 출력 영역
			System.out.println("#" + tNum + " " + ProfitMax_sum);
			ProfitMax_sum = 0;
			ProfitMax_A = 0;
			tNum++;
		}
	}

	public static void doCombination(char a, int[] combArr, int n, int r, int index, int target, int[] arr, int C) {

		// r ==0 이란 것은 뽑을 원소를 다 뽑았다는 뜻.
		if (r == 0) {
			int sum = 0;
			for (int i = 0; i < index; i++)
				sum += arr[combArr[i]];
			if (sum <= C) {
				int powSum = 0;
				for (int i = 0; i < index; i++) {
					powSum += (int) Math.pow(arr[combArr[i]], 2);
				}
				if (a == 'A')
					ProfitMax_A = Math.max(ProfitMax_A, powSum);
				else
					ProfitMax_B = Math.max(ProfitMax_B, powSum);
			} else
				return;
			// 끝까지 다 검사한 경우..1개를 뽑은 상태여도 실패한 경우임 무조건 return 으로 종료
		} else if (target == n) {

			return;

		} else {
			combArr[index] = target;
			// (i) 뽑는 경우
			// 뽑으니까, r-1
			// 뽑았으니 다음 index + 1 해줘야 함
			doCombination(a, combArr, n, r - 1, index + 1, target + 1, arr, C);

			// (ii) 안 뽑는경우
			// 안뽑으니까 그대로 r
			// 안뽑았으니, index는 그대로!
			// index를 그대로하면, 예를 들어 현재 1번 구슬을 comArr에 넣엇어도 다음 재귀에 다시 엎어쳐서 결국 1구슬을 뽑지 않게 된다.
			doCombination(a, combArr, n, r, index, target + 1, arr, C);
		}
	}

}
