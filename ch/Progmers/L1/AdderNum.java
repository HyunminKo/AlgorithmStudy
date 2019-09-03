public class AdderNum {
    public static void main(String[] args) {
        Long a = solution(5 ,3);
        System.out.println(a);
    }
    public static long solution(int a, int b) {

        // 필요 로컬 변수
        long answer = 0;
        long small;
        long big;

        // 뭐가 큰지 비교
        if(a > b) {
            small = b;
            big = a;
        }else if(a < b){
            small = a;
            big = b;
        }else
            return a;

        // 차례대로 덧셈
        for(long i = small ; i <= big ; i++){
            answer += i;
        }

        // 결과 반환
        return answer;
    }
}
