import java.util.Scanner;

public class 적록색약_dfs_10026 {
    static int N,numOfNormal,numOfAbnormal;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            String line = sc.nextLine();
            for(int j = 0 ; j < N; j++){
                if(line.charAt(j) == 'R'){ // 1
                    map[i][j] = 1;
                }else if(line.charAt(j) == 'G'){ // 2
                    map[i][j] = 2;
                }else { // B 3
                    map[i][j] = 3;
                }
            }
        }
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N;j ++){
                if(!visited[i][j]){
                    dfs(i,j);
                    numOfNormal++;
                }
            }
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j] == 2){
                    map[i][j] = 1;
                }
            }
        }
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N;j ++){
                if(!visited[i][j]) {
                    dfs(i, j);
                    numOfAbnormal++;
                }
            }
        }
        System.out.println(numOfNormal + " " + numOfAbnormal);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for(int i = 0 ; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if( nx < 0 || ny < 0 || nx >=N || ny >= N || map[nx][ny] != map[x][y] || visited[nx][ny]) continue;
            dfs(nx,ny);
        }
    }
}
