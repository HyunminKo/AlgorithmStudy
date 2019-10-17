package SWExpert;

import java.util.Scanner;

public class ProtectFilm {

    static int D, W;//두께 row, 넓이 col.
    static int K;//합격기준.
    static int T;
    static int[][] map;
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        T = input.nextInt();

        int problem = 1;
        while (T > 0) {

            D = input.nextInt();
            W = input.nextInt();
            K = input.nextInt();

            map = new int[D][W];
            visited = new boolean[D];
            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    int num = input.nextInt();
                    map[i][j] = num;
                }
            }

            dfs(0);
            System.out.println("#"+problem+ " " +result);
            result = Integer.MAX_VALUE;
            problem++;
            T--;
        }
    }

    private static void dfs(int injectCnt) {

        if(result <= injectCnt) return;
        if(passCheck()){
            result = Math.min(result, injectCnt);
            return;
        }
        for (int i = 0; i < D; i++) {
            if(visited[i]) continue;
            int[] recoveryArray = makeRecoveryArray(i);

            visited[i] = true;
            inject(i, 0);
            dfs(injectCnt+1);

            inject(i, 1);

            dfs(injectCnt+1);
            visited[i] = false;

            recovery(i, recoveryArray);
        }
    }

    private static void print() {

        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(map[i][j] + " " );
            }
            System.out.println();
        }
    }

    private static void inject(int row, int drug){
        visited[row] = true;
        for(int i = 0; i < W;i++){
            if(drug == 0) map[row][i] = 0;
            else map[row][i] = 1;
        }
    }

    private static void recovery(int row, int[] recoveryArray){
        for(int i = 0; i< W; i++){
            map[row][i] = recoveryArray[i];
        }
    }

    private static int[] makeRecoveryArray(int row){
        int[] recoveryArray = new int[W];

        for(int i = 0; i < W; i++){
            recoveryArray[i] = map[row][i];
        }
        return recoveryArray;
    }

    private static boolean passCheck(){
        boolean pass = false;
        int sameCnt = 1;
        for (int i = 0; i < W; i++) {
            pass = false;
            sameCnt = 1;
            int std = map[0][i];
            for (int j = 1; j < D; j++) {
                if(map[j][i] == std){
                    sameCnt++;
                }
                else{
                    std = map[j][i];
                    sameCnt = 1;
                }
                if(sameCnt == K){
                    pass = true;
                    break;
                }
            }
            if(!pass){
                break;
            }
        }

        return pass;
    }

}
//약품의 최소투입 수를 출력.
//A:0 , B:1