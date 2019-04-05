import java.util.ArrayList;
import java.util.Scanner;

public class 벽돌깨기 {
    static int size,N,M,result=0x3f3f3f3f;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++)
        {
            size = sc.nextInt(); M = sc.nextInt(); N = sc.nextInt();
            int[][] map = new int[N][M];
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            go(0,"",map);
            System.out.println(String.format("#%d %d",test_case,result));
            result = 0x3f3f3f3f;
        }
    }

    private static void go(int count, String index, int[][] map) {
        if(count >= size){
            calc(map);
            System.out.println(index);
            return;
        }
        int[][] backup = new int[N][M];
        for(int i = 0 ; i < M; i++){
            copyAToB(map,backup);
            shoot(i,map);
            go(count+1,index+i, map);
            copyAToB(backup,map);
        }
    }

    private static void printMap(int[][] map) {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void calc(int[][] map) {
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] != 0){
                    count++;
                }
            }
        }
        result = Math.min(result,count);
    }

    private static void copyAToB(int[][] A, int[][] B) {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                B[i][j] = A[i][j];
            }
        }
    }

    private static void shoot(int index, int[][] map) {
        for(int i = 0 ; i < N; i++){
            if(map[i][index] == 0) continue;
            boolean[][] visited = new boolean[N][M];
            visited[i][index] = true;
            if(map[i][index] > 1){
                bomb(i,index,map[i][index],map,visited);
            }else {
                map[i][index] = 0;
            }
            break;
        }
        down(map);
    }

    private static void down(int[][] map) {
        ArrayList<Integer> survivor = new ArrayList<>();
        for(int j = 0 ; j < M; j++){
            for(int i = N - 1 ; i >= 0; i--){
                if(map[i][j] != 0){
                    survivor.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int index = N - 1;
            for(int i = 0; i < survivor.size(); i++){
                map[index--][j] = survivor.get(i);
            }
            survivor = new ArrayList<>();
        }
    }

    private static void bomb(int x, int y, int lenOfBomb, int[][] map, boolean[][] visited) {
        map[x][y] = 0;
        for(int i = 0 ; i < 4; i++){
            for(int j = 1 ; j <= lenOfBomb-1; j++){
                int nx = x + dx[i] * j;
                int ny = y + dy[i] * j;
                if( nx < 0 || ny < 0 || nx >= N || ny>= M) break;
                if(map[nx][ny] == 0|| visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if( map[nx][ny] > 1){
                    bomb(nx,ny,map[nx][ny],map, visited);
                } else if(map[nx][ny] == 1){
                    map[nx][ny] = 0;
                }
            }
        }
    }
}

//1
//3 10 10
//0 0 0 0 0 0 0 0 0 0
//1 0 1 0 1 0 0 0 0 0
//1 0 3 0 1 1 0 0 0 1
//1 1 1 0 1 2 0 0 0 9
//1 1 4 0 1 1 0 0 1 1
//1 1 4 1 1 1 2 1 1 1
//1 1 5 1 1 1 1 2 1 1
//1 1 6 1 1 1 1 1 2 1
//1 1 1 1 1 1 1 1 1 5
//1 1 7 1 1 1 1 1 1 1
