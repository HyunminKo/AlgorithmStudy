package samsung;

import java.util.Scanner;

public class Circle {

    static int n;//원의 수
    static int m;//원 위의 숫자
    static int t;//회저 수

    static int[][] map;
    static int[] command = new int[3];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 3; i++) {
            command[i] = sc.nextInt();
        }

        while(t > 0){

            //1. 배수 확인
            for (int i = 1; i <= n; i++) {
                if(i < command[0]){
                    continue;
                }
                else if(i%command[0] == 0){
                    turn();
                }
            }
            //2. 돌림
            //3. 평균 계산
           t--;
        }
    }

    private static void turn() {
    }


    private int getAverage(){
        int avg = 0;
        int sum;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

            }
        }

        return avg;
    }


}


/*

 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
 인접하면서 수가 같은 것을 모두 찾는다.
 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
 */