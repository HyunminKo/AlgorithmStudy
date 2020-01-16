package SWExpert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyAndSell {

    static int tc;
    static long result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        tc = sc.nextInt();
        int tcNum = 1;

        while(tc > 0){
            int num = sc.nextInt();
            int[] ary;
            ary = new int[num];
            int maxValue = 0;

            for (int i = 0; i < num; i++) {
                int value = sc.nextInt();
                ary[i] = value;
                maxValue = Math.max(maxValue, value);
            }

            solution(ary, maxValue);

            System.out.println("#"+tcNum + " " + result);
            tc--;
            tcNum++;
            result = 0;
        }
    }

    private static void solution(int[] ary, int maxValue) {

        int startIdx = 0;
        while(startIdx < ary.length){
            List<Integer> buyList = new ArrayList<>();
            int max;
            //max 값 설정
            if(startIdx == 0 ){
                max = maxValue;
            }
            else{
                max = findMaxValue(ary, startIdx);
            }

            //
            for (int i = startIdx; i < ary.length; i++) {
                int value = ary[i];

                //buy
                if(value < max){
                    buyList.add(value);
                }
                //sell
                else{
                    for(int v : buyList){
                        result+= max-v;
                    }
                    startIdx = i+1;
                    break;
                }

            }
        }
    }
    private static int findMaxValue(int[] ary, int sIdx){
        int maxValue = -1;
        for (int i = sIdx; i < ary.length; i++) {
            if(ary[i] > maxValue) maxValue = ary[i];
        }
        return maxValue;
    }
}


/*
3
3
10 7 6
3
3 5 9
5
1 1 3 1 2

1
3
10 7 6


방법1
1. 배열에서 가장 큰 숫자를 찾는다.
2. 그 전까지를 다 사 들인다.
3. 큰 숫자를 만나면 팔아
4. 다음 꺼부터 진행

인덱스 설정
찾는다, 산다, 판다
인덱스 재설정

1
2
522 4575
 */