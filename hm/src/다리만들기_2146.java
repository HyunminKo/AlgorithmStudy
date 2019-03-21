import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 다리만들기_2146 {
    static int N;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map, colorMap;
    static boolean[][] visited;
    static int result = 0x3f3f3f3f;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        colorMap = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                map[i][j] = sc.nextInt();
            }
        }
        int color = 1;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(!visited[i][j] && map[i][j] != 0){
                    dfs(i,j,color);
                    color++;
                }
            }
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                visited = new boolean[N][N];
                if(colorMap[i][j] != 0){
                    bfs(i,j,colorMap[i][j]);
                }
            }
        }
        System.out.println(result-1);
    }

    private static void bfs(int x, int y, int color) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        visited[x][y] = true;
        boolean flag = false;
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || colorMap[nx][ny] == color) continue;
                    if(colorMap[nx][ny] != 0 && colorMap[nx][ny] != color){
                    result = Math.min(result, map[p.x][p.y]);
                    flag = true;
                }else {
                    if(flag){
                        if(result < map[x][y] + 1) continue;
                    }
                    map[nx][ny] = map[p.x][p.y] + 1;
                    q.offer(new Point(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void dfs(int x, int y, int color) {
        visited[x][y] = true;
        colorMap[x][y] = color;
        for(int i = 0 ; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 0) continue;
            visited[nx][ny] = true;
            colorMap[nx][ny] = color;
            dfs(nx,ny,color);
        }
    }
    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
