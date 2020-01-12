package SWExpert;

import java.util.Scanner;

//1205. view
public class View {

    static int[] ary;
    static int result = 0;

    public static void main(String[] args) {

        int tc = 1;
        int tcLength = 0;

        Scanner sc = new Scanner(System.in);

        while(tc <= 10){

            tcLength = sc.nextInt();
            ary = new int[tcLength];

            for(int i = 0; i < tcLength; i++){
                ary[i] = sc.nextInt();
            }

            solution(tcLength);
            System.out.println("#"+tc+" " + result);
            tc++;
            result = 0;
        }
    }

    private static void solution(int tcLength) {
        for (int i = 0; i < tcLength; i++) {
            int pres = ary[i];
            if(pres == 0) continue;

            int maxLeft = 0;
            int maxRight = 0;
            int resultLeft = 0;
            int resultRight = 0;
            boolean leftCheck = true;
            boolean rightCheck = true;

            //왼쪽 체크
            for (int j = i-1; j >= 0; j--) {
                int left = ary[j];

                if(left >= pres){//왼쪽 건물이 더 커
                    if(i-j < 3){
                        leftCheck = false;
                    }
                    break;
                }
                else{
                    maxLeft = Math.max(maxLeft, left);
                }
            }

            if(leftCheck == false) continue;
            if(maxLeft != 0) resultLeft = pres - maxLeft;
            else resultLeft = pres;

            //오른쪽 체크
            for (int j = i+1; j < tcLength; j++) {
                int right = ary[j];

                if(right >= pres){//현재보다 큰 건물이 나타남
                    if(j-i < 3){//2 이상 확보가 안됨.
                        rightCheck = false;
                    }
                    break;
                }
                else{
                    maxRight = Math.max(maxRight, right);
                }
            }

            if(rightCheck == false) continue;
            if(maxRight != 0) resultRight = pres - maxRight;
            else resultRight = pres;

            //양쪽 다 확보된 후 계산
            result += Math.min(resultLeft, resultRight);

        }
    }

}
/*

14
0 0 3 5 2 4 9 0 6 4 0 6 0 0
 */
