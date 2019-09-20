package kakao.Ha_2019;

public class Problem1 {
    public static void main(String[] args) {

        String s = "xababcdcdababcdcd";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = s.length();

        for (int w = 1; w <= (s.length()/2); w++) {
            StringBuilder sb = new StringBuilder();

            int i;
            int cnt = 1;
            String preString;
            String nextString;
            for (i = w-1; i < s.length(); i = i+w) {
                int preEndIdx = i;
                int nextEndIdx = i+w;

                preString = s.substring(preEndIdx-w+1,preEndIdx+1);

                if(preEndIdx == s.length()-1){// 지금 딱 마지막이야. 그러면 sb에 추가해.
                    if(cnt == 1) sb.append(preString);
                    else sb.append(cnt+preString);
                    break;
                }
                if(nextEndIdx > s.length()-1){//다음 인덱스 넘어 갈때
                    nextString = s.substring(preEndIdx+1);//다음 꺼는 마지막까지만 잘라
                }
                else{// 다음 인덱스도 포함, 다음 인덱스 딱 마지막일 때,
                    nextString = s.substring(preEndIdx+1, nextEndIdx+1);
                }

                if(preString.equals(nextString)){//다음이랑 같으면 카운트 증가
                    cnt++;
                }
                else{// 다음이랑 다르면 sb에 추가.
                    if(cnt == 1){
                        sb.append(preString);
                    }
                    else{
                        sb.append(cnt+preString);
                    }

                    cnt = 1;
                }
                if(nextEndIdx > s.length()-1) sb.append(nextString);// 다음 인덱스가 넘어가 버림릴  나때머지 추가.
            }
            answer = Math.min(sb.toString().length(), answer);
//            System.out.println(sb.toString());
        }
        return answer;
    }
}



/*
preStr nextStr

aabbccaac
1. w를 1개씩 늘려가면서 답을 찾는다.

w = 1 : a,a,b,b,c,c,a,a,c
w = 2 : aa,bb,cc,aa,c
w = 3 : aab,bcc,aac
w = 4 : aabb,ccaa,c
---------------------------
w = 5 : aabbc, caac 앞이 과반수 이상, 끝!


StringBuilder - single thread
StringBuffer - multi thread

멀티쓰레드에서 StringBuilder 를 쓰면 깨지게 된다.

"aabbaccc" 7
"ababcdcdababcdcd" 9
"abcabcdede" 8
"abcabcabcabcdededededede" 14
"xababcdcdababcdcd" 17

*/