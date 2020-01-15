package SWExpert;

import java.util.Scanner;

//1205. view
public class View_2 {

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
            int building = ary[i];
            if(building == 0) continue;

            boolean check = false;
            int maxBuilding = -1;
            for (int j = i-2; j < i+3; j++) {
                if(j == i) continue;
                if(ary[j] >= building) {
                    check = true;
                    break;
                }
                else{
                    maxBuilding = Math.max(ary[j], maxBuilding);
                }
            }
            if(check) continue;
            int temp = building - maxBuilding;
            result += temp;
        }
    }

}
/*

14
0 0 3 5 2 4 9 0 6 4 0 6 0 0


  2 3 5 4 1 -> 4 5-4 = 1

  5 3 5 4 1

1. 현재 건물을 체크한다.
2. 현재 건물이 0이면 패스한다.
3. 현재 건물을 기준으로 왼쪽을 확인한다.
4. 왼쪽 끝까지 반복
4-1. 왼쪽 건물 선택
4-2. 왼쪽 건물이 크면 거리를 측정.
4-2. 거리가 2 이상이면 maxLeft 와 선택된 건물을 비교해서 maxLeft 를 업데이트 후 break.
4-3. 거리가 2 이하면 check 를 false 로 바꾸고 break.
5. check 가 false 면 continue;
6. 현재 높이 - maxLeft 하여 왼쪽에서 조망권 저장


 */
