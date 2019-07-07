import java.util.ArrayList;
import java.util.Scanner;

public class CrossingAStoneBridge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int answer = 0;
		char[] 두루마리문자열 = scan.nextLine().toCharArray();
		char[] 악마의돌다리 = scan.nextLine().toCharArray();
		char[] 천사의돌다리 = scan.nextLine().toCharArray();

		answer = Cross(두루마리문자열, 악마의돌다리, 천사의돌다리);

		System.out.println(answer);

	}

	public static int Cross(char[] 두루마리문자열, char[] 악마의돌다리, char[] 천사의돌다리) {
		int[][] D = new int[21][101];
		int[][] A = new int[21][101];

		int range = 악마의돌다리.length - 두루마리문자열.length + 1;

		for (int i = 1; i <= range; i++) {
			if (두루마리문자열[0] == 악마의돌다리[i - 1]) {
				D[1][i] = D[1][i - 1] + 1;
			} else {
				D[1][i] = D[1][i - 1];
			}

			if (두루마리문자열[0] == 천사의돌다리[i - 1]) {
				A[1][i] = A[1][i - 1] + 1;
			} else {
				A[1][i] = A[1][i - 1];
			}
		}

		for (int i = 2; i <= 두루마리문자열.length; i++) {
			range = 악마의돌다리.length - 두루마리문자열.length + i;
			for (int j = i; j <= range; j++) {
				if (i % 2 == 1) {
					if (악마의돌다리[j - 1] != 두루마리문자열[i - 1]) {
						D[i][j] = D[i][j - 1];
					} else {
						D[i][j] = D[i][j - 1] + D[i - 1][j - 1];
					}
				} else {
					if (천사의돌다리[j - 1] != 두루마리문자열[i - 1]) {
						D[i][j] = D[i][j - 1];
					} else {
						D[i][j] = D[i][j - 1] + D[i - 1][j - 1];
					}
				}
			}
		}

		for (int i = 2; i <= 두루마리문자열.length; i++) {
			range = 천사의돌다리.length - 두루마리문자열.length + i;
			for (int j = i; j <= range; j++) {
				if (i % 2 == 1) {
					if (천사의돌다리[j - 1] != 두루마리문자열[i - 1]) {
						A[i][j] = A[i][j - 1];
					} else {
						A[i][j] = A[i][j - 1] + A[i - 1][j - 1];
					}
				} else {
					if (악마의돌다리[j - 1] != 두루마리문자열[i - 1]) {
						A[i][j] = A[i][j - 1];
					} else {
						A[i][j] = A[i][j - 1] + A[i - 1][j - 1];
					}
				}
			}
		}

		return D[두루마리문자열.length][악마의돌다리.length] + A[두루마리문자열.length][천사의돌다리.length];
	}

}
