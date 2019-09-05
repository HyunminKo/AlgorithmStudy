import java.util.Arrays;
import java.util.Collections;

public class FindPrimeNumber {
    public static void main(String[] args) {
        int a = solution("17");
        System.out.println(a);
    }

    public static int solution(String numbers) {
        int answer = 0;

        char[] char_pieces = numbers.toCharArray();

        Integer l = char_pieces.length;

        Integer[] int_pieces = new Integer[l];

        int[] count = new int[10];

        for (int i = 0; i < l; i++) {
            int_pieces[i] = char_pieces[i] - 48;
            count[int_pieces[i]]++;
        }

        Arrays.sort(int_pieces, Collections.reverseOrder());// 가장 큰 수를 찾기 위한 과정
        String str_max = Arrays.toString(int_pieces).replaceAll("[^0-9]", "");// int[] to String
        int int_max = Integer.parseInt(str_max);// 가장 큰 수

        int[] fromTwoToMax = new int[int_max + 1];// 2부터 종이조각으로 만들 수 있는 최대 크기의 수(int_max) 사이에 존재하는 모든 소수를 찾기 위한 배열.

        for (int i = 2; i <= int_max; i++) { // 배열 값 초기화
            fromTwoToMax[i] = i;
        }

        // 소수를 제외한 모든 수들은 0을 가지게됨
        for (int i = 2; i <= int_max; i++) {
            if (fromTwoToMax[i] == 0) continue; // 전 과정에서 이미 0으로 바뀔 수가 있음. 그래서 그냥 뛰어 넘자.
            for (int j = 2 * i; j <= int_max; j += i) {
                fromTwoToMax[j] = 0;
            }
        }

        //배열에서 0이 아닌 것들을 찾는다.
        for (int i = 0; i < fromTwoToMax.length; i++) {
            if (fromTwoToMax[i] != 0) {
                if (isPossible(fromTwoToMax[i], count)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static boolean isPossible(int primeNum, int[] count) {
        int[] numCount = Arrays.copyOf(count, count.length);

        while (primeNum != 0) {
            if (numCount[primeNum % 10] <= 0) return false;
            numCount[primeNum % 10]--;
            primeNum /= 10;
        }

        return true;
    }

}
