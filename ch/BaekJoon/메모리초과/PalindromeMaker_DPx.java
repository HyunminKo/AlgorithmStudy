import java.util.ArrayList;
import java.util.Scanner;

public class PalindromeMaker_DPx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);

		char[] in = scan.nextLine().toCharArray();

		ArrayList<Character> S = new ArrayList<Character>();

		for (int i = 0; i < in.length; i++)
			S.add(in[i]);

		int answer = maker(S);
		System.out.println(answer);

	}

	public static int maker(ArrayList<Character> S) {
		int init_end_index = S.size() - 1;// 최초의 기준
		while (S.get(init_end_index) == S.get(init_end_index - 1)) {
			init_end_index--;
		}
		for (int i = 0; i < init_end_index;) {
			char x = S.get(i);
			S.add(x);
			while (x == S.get(i + 1)) {
				S.add(S.get(i + 1));
				i++;
			}
			if (itIsADecalcomaniOrNot(S)) {
				for (int j = 0; j < S.size(); j++)
					System.out.print(S.get(j));
				System.out.println();
				return S.size();
			}
			for (int j = 1; j <= i; j++)
				S.remove(S.size() - j);
		}
		while (!itIsADecalcomaniOrNot(S)) {

			S.add(S.get(--init_end_index));

			if (init_end_index < 0)
				break;
		}
		for (int i = 0; i < S.size(); i++)
			System.out.print(S.get(i));
		System.out.println();
		return S.size();
	}

	public static boolean itIsADecalcomaniOrNot(ArrayList<Character> S) {

		int start = 0;
		int end = S.size() - 1;
		while (start < end) {
			if (S.get(start) == S.get(end)) {
				start++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}
}
