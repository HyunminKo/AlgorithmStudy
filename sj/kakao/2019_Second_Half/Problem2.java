package kakao.Ha_2019;

import java.util.Stack;

public class Problem2 {
    public static void main(String[] args) {
        String p1 = "(()())()";
        String p2 = ")(";
        String p3 = "()))((()";

        String result = solution(p2);

        System.out.println(result);
    }

    public static String solution(String p) {
        String answer = "";
        if(check(p)){
            answer = p;
            return answer;
        }
        else{
            int endIdx= separate(p);
            String u = p.substring(0, endIdx+1);
            String v;
            if(endIdx == p.length()-1) v = "";
            else{
                v = p.substring(endIdx+1, p.length());
            }

            if(check(u)){
                answer += u;
                answer += solution(v);
            }
            else{
                String tmp = "(";
                tmp += solution(v);
                tmp += ")";

                String t = "";
                u = u.substring(1,u.length()-1);
                for (int i = 0; i < u.length(); i++) {
                    if(u.charAt(i) == '('){
                        t += ")";
                    }
                    else{
                        t += "(";
                    }
                }
                answer += tmp+t;
            }
        }
        return answer;
    }

    private static int separate(String s){
        int open = 0;
        int close = 0;
//        int[] bracketIdxArray = new int[2];
        int endIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                open++;
            }
            else{
                close++;
            }

            if((open != 0 || close != 0) && open == close){
                endIdx = i;
                break;
            }

        }
        return endIdx;
    }

    private static boolean check(String p) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < p.length(); i++){
            char c = p.charAt(i);
            if(stack.isEmpty()){
                if(c == ')') return false;
            }
            if(c == '('){
                stack.push(Character.valueOf(c));
            }
            if(c == ')'){
                stack.pop();
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}
