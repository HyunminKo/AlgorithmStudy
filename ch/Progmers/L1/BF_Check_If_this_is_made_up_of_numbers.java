public class BF_Check_If_this_is_made_up_of_numbers {
    public static void main(String[] args) {
        boolean answer = solution("01234b6789");
        System.out.println(answer);
    }

    public static boolean solution(String s) {
        boolean answer = true;
        char[] pieces = s.toCharArray();
        int l = pieces.length;
        if (l == 4 || l == 6)
            for (int i = 0; i < l; i++) {
                if (48 <= pieces[i] && pieces[i] <= 57)
                    continue;
                else
                    answer = false;
            }
        else
            answer = false;
        return answer;
    }
}
