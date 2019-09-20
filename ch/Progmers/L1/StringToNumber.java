public class StringToNumber {

    public static void main(String[] args) {
        int  a = solution("1234");
        System.out.println(a);
    }

    public static int solution(String s) {
        int answer = 0;
        answer = Integer.parseInt(s);
        return answer;
    }
}
