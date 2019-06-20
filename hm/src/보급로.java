import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 보급로 {
    static int N;
    static int[][] map,score;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 1; i <= T; i++){
            N = sc.nextInt();
            sc.nextLine();
            map = new int[N][N];
            score = new int[N][N];
            visited = new boolean[N][N];
            for(int j = 0 ; j < N; j++){
                String line = sc.nextLine();
                for(int k = 0 ; k < line.length(); k++){
                    map[j][k] = line.charAt(k) - '0';
                }
            }
            go(0,0);
            System.out.println(String.format("#%d %d",i,score[N-1][N-1]));
        }
    }

    private static void go(int x , int y) {
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >=N) continue;
                if(!visited[nx][ny] || score[p.x][p.y] + map[nx][ny] < score[nx][ny]){
                    score[nx][ny] = score[p.x][p.y] + map[nx][ny];
                    visited[nx][ny] = true;
                    q.offer(new Point(nx,ny));
                }
            }
        }
    }

    public static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}