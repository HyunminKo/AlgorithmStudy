import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역_2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        int minHeight = 101;
        int maxHeight = 0;
        int result = 1; // 안전영역 최소 개수(비 안올때)
        for(int i = 0  ; i < N ; i++){
            for(int j = 0 ; j < N; j++){
                map[i][j] = sc.nextInt();
                if(minHeight > map[i][j]) minHeight = map[i][j];
                if(maxHeight < map[i][j]) maxHeight = map[i][j];
            }
        }
        for(int height = minHeight ; height <= maxHeight; height++){
            visited = new boolean[N][N];
            int count = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    if(map[i][j] > height && !visited[i][j]){
                        count++;
                        Queue<Point> q = new LinkedList<>();
                        visited[i][j] = true;
                        q.offer(new Point(i,j));
                        while(!q.isEmpty()){
                            Point p = q.poll();
                            for(int k = 0 ; k < 4; k++){
                                int nx = p.x + dx[k];
                                int ny = p.y + dy[k];
                                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] <= height || visited[nx][ny]) continue;
                                visited[nx][ny] = true;
                                q.offer(new Point(nx,ny));
                            }
                        }
                    }
                }
            }
            result = Math.max(result,count);
        }
        System.out.println(result);
    }
    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
