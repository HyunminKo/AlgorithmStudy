package samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cheese {

    static int M,N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static boolean[][] melted;
    static int cntCheese = 0;
    static int time = 0;
    static int prevCnt = 0;
    static Queue<Position> q = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];
        melted = new boolean[N][M];

        for(int i = 0; i< N; i++){
            for(int j = 0; j<M;j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1){
                    cntCheese++;
                }
            }
        }

        while(cntCheese != 0){
            prevCnt = cntCheese;
            q.add(new Position(0, 0));
            visited[0][0] = true;
            melt();
            visited = new boolean[N][M];
            melted = new boolean[N][M];
            time++;
        }
        System.out.println(time);
        System.out.println(prevCnt);
    }

    static void melt(){

        while(!q.isEmpty()) {
            Position p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(visited[nx][ny]) continue;

                if (map[nx][ny] == 1){//치즈를 만나면
                    map[nx][ny] = 0;
                    cntCheese--;
                    melted[nx][ny] = true;
                }
                else{
                    if(!melted[nx][ny]) {
                        q.add(new Position(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    static class Position{
        int x, y;

        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}