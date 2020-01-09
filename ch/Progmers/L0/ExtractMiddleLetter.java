
public class ExtractMiddleLetter {
	public static void main(String[] args) {
		String a = solution("abwwcd");
		System.out.println(a);
	}

	public static String solution(String s) {
		String answer = "";
		char[] a = s.toCharArray();
		int b = a.length;
		int middleIdx = b / 2;
		if (b % 2 == 0) {// 짝수
			answer = String.valueOf(a[middleIdx - 1]) + String.valueOf(a[middleIdx]);
		} else {// 홀수
			answer = String.valueOf(a[middleIdx]);
		}
		return answer;
	}
}
