import java.util.Scanner;

public class ASetOfScales {
	static int N; // 추의 개수
	static int[] Wi; // 추의 무게
	static int G; // 구슬의 개수
	static int[][] D; // 다이나믹 배열
	static int[] S; // 추의 무게합을 저장해둔 배열

	static int abs(int a) {
		return (a > 0) ? a : -a;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Wi = new int[N + 1];
		S = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			Wi[i] = sc.nextInt();
			S[i] = S[i - 1] + Wi[i];
		}

		D = new int[N + 1][40000 + 1];

		// 다이나믹 표 작성
		for (int i = 1; i <= N; i++) {
			// 내추 가능
			D[i][Wi[i]] = 1;
			for (int j = 1; j <= S[i - 1]; j++) {
				// 이전추가 측정 가능했을때 기준으로 다이나믹표 작성
				if (D[i - 1][j] == 1) {
					// 이전추가 측정가능했으므로 현재도 측정가능함
					D[i][j] = 1;

					// 이전추의 무게 + 현재추 무게 해도 측정가능함
					D[i][j + Wi[i]] = 1;

					// 이전추무게 - 현재추도 측정가능함
					D[i][abs(j - Wi[i])] = 1;

				}
			}
		}

		G = sc.nextInt();

		for (int i = 1; i <= G; i++) {
			int g = sc.nextInt();
			if (D[N][g] == 1)
				System.out.print("Y ");
			else
				System.out.print("N ");
		}

		sc.close();
	}

}