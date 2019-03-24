package samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PipeLine {

    static int N;
    static int result = 0;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 1, 0};

    static Queue<Pipe> q = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int[][] map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i <N; i++){
            for(int j = 0; j < N; j++){
               map[i][j] = sc.nextInt();
            }
        }
        Pipe p = new Pipe(0, 0, 0, 1);

        visited[0][0] = true;
        visited[0][1] = true;

        dfs(p, map);
        System.out.println(result);
    }

    static void dfs(Pipe p, int[][] map){

        for(int i = 0; i<3; i++){
            int nx = p.ex + dx[i];
            int ny = p.ey + dy[i];

            if(p.ex == N-1 && p.ey == N-1){//지점 도착.
                result++;
                return;
            }
            if(nx >= N || ny >=N) continue;//범위를 넘거나 벽일 경우
            if(map[nx][ny] == 1){
                continue;
            }

            if(i == 0){//가로로 가려고 해
                if(p.sy == p.ey) continue;
            }
            else if(i == 1){//대각으로 가려고 해
                int cnt = 0;
                for(int j = 0; j< 3; j++){
                    int tempNX = p.ex + dx[j];
                    int tempNY = p.ey + dy[j];
                    if(map[tempNX][tempNY] == 1){
                        cnt++;
                        break;
                    }
                }
                if(cnt >= 1){
                    continue;
                }
            }
            else{
                if(p.sx == p.ex) continue;
            }

            dfs(new Pipe(p.ex,p.ey, nx, ny), map);
        }

    }

    static class Pipe{
        int sx, sy;
        int ex, ey;

        Pipe(int sx, int sy, int ex, int ey){
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }
}
