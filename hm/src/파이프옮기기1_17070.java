import java.util.Scanner;

public class 파이프옮기기1_17070 {
    static int N,result = 0;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                map[i][j] = sc.nextInt();
            }
        }
        dfs(0,1, 0);
        System.out.println(result);
    }

    private static void dfs(int x, int y, int dir) { //0 오른 1 아래 2 대각

        if( x == N -1 && y == N-1){
            result++;
            return;
        }
        if(dir == 0){
            if(y + 1 < N){
                if(map[x][y+1] != 1){
                    dfs(x,y+1, 0);
                }
            }
        }else if (dir == 1){
            if(x + 1 < N) {
                if (map[x + 1][y] != 1) {
                    dfs(x + 1, y, 1);
                }
            }
        }else if (dir == 2){
            if(y + 1 < N){
                if(map[x][y+1] != 1){
                    dfs(x,y+1, 0);
                }
            }
            if(x + 1 < N) {
                if (map[x + 1][y] != 1) {
                    dfs(x + 1, y, 1);
                }
            }
        }
        if(x + 1 < N && y + 1 < N){
            if(map[x][y+1] != 1 && map[x+1][y] != 1 && map[x+1][y+1] != 1)
                dfs(x+1,y+1,2);
        }
    }
}
