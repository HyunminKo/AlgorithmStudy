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
            tc++;
            result = 0;
        }
    }

    private static void solution(int tcLength) {
        for (int i = 0; i < ary.length; i++) {
            //왼쪽
            int maxLeft = 0;
            int maxRight = 0;
            boolean check = true;

            //왼쪽 체크
            for (int j = i-1; j >= 0; j--) {
                if(j < 0) continue;
                int left = ary[j];
                if(left > ary[i]){//왼쪽 건물이 더 커

                    break;
                }

                if(ary[i])
            }

            //오른쪽 체크

        }
    }

}
/*
    1. 하나씩 체크 한다.

 */
