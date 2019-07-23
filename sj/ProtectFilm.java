package SWExpert;

import java.util.Scanner;

public class ProtectFilm {

    static int D, W;//두께 row, 넓이 col.
    static int K;//합격기준.
    static int T;
    static int[][] map;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        T = input.nextInt();

        while(T > 0){

            D = input.nextInt();
            W = input.nextInt();
            K = input.nextInt();
            map = new int[D][W];
            for(int i = 0; i < D;i++ ){
                for(int j = 0; j<W;j++){
                    int num = input.nextInt();
                    map[i][j] = num;
                }
            }

            solution();
            System.out.println(result);
            result = Integer.MAX_VALUE;
            T--;
        }
    }

    private static void solution() {

        boolean[] visited = new boolean[D];

        if(check()) return ;
        else {
            inject_drug_dfs(visited, 0);
        }
    }

    private static void inject_drug_dfs(boolean[] visited, int injectCnt) {

        for(int i = 0; i< D; i++){
            if(visited[i]) continue;
            visited[i] = true;
            int[] tempArray = new int[W];
            for(int j = 0; j< W; j++){
                tempArray[j] = map[i][j];
            }
            inject(i, 0);//약 투여.
            injectCnt++;
            inject_drug_dfs(visited, injectCnt);
            if(check()){
                result = Math.min(result, injectCnt);
            }
            recovery(i, tempArray);

            inject(i, 1);
            inject_drug_dfs(visited, injectCnt);
            if(check()){
                result = Math.min(result, injectCnt);
            }
            recovery(i, tempArray);
            injectCnt--;
            visited[i] =false;
        }
    }

    private static void recovery(int row, int[] tempArray){
        for(int i = 0; i<W; i++){
            map[row][i]= tempArray[i];
        }
    }

    private static void inject(int row, int drug){
        for(int i = 0; i< W;i++){
            map[row][i] = drug;
        }
    }


    private static boolean check(int col){
        int cnt = 1;
        int std = map[0][col];
        for(int i = 1; i<D; i++){
           if(cnt == 3) return true;
           if(std == map[i][col]) cnt++;
           else{
               std = map[i][col];
               cnt = 1;
           }
        }
        return false;
    }

    private static boolean check(){

        int passCnt = 0;
        for(int i = 0; i < W; i++){

            int cnt = 1;
            int std = map[0][i];
            for(int j = 1; j<D; j++){
                if(cnt == 3) {
                    passCnt++;
                    cnt = 1;
                    break;
                }
                if(std == map[j][i]) cnt++;
                else{
                    std = map[j][i];
                    cnt = 1;
                }
            }
            if(cnt == 3){
                passCnt++;
                cnt=1;
            }

        }
        if(passCnt == W) return true;
        else{
            return false;
        }
    }
}
//약품의 최소투입 수를 출력.
//A:0 , B:1