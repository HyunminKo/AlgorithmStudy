import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 치즈_2636 {
    static int N,M,innerCount,exterCount;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M =sc.nextInt();
        map = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j< M ;j++){
                map[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        while(true){
            visited = new boolean[N][M];
            Queue<Point> q = new LinkedList<>();
            visited[0][0] = true;
            q.offer(new Point(0,0));
            while(!q.isEmpty()){
                Point p = q.poll();
                for(int i = 0 ; i < 4; i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                    if(map[nx][ny] == 1) {
                        map[nx][ny] = 2; // 공기하고 맞닿아 있는 부분 2로 변경.
                        visited[nx][ny] = true;
                        continue;
                    }
                    visited[nx][ny] = true;
                    q.offer(new Point(nx,ny));
                }
            }
            removeCheese();
            result++;
            if(innerCount == 0) break;
            innerCount = 0;
            exterCount = 0;
        }
        System.out.println(result);
        System.out.println(exterCount);
    }

    private static void removeCheese() {
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 2){
                    exterCount++;
                    map[i][j] = 0;
                }else if(map[i][j] == 1){
                    innerCount++;
                }
            }
        }
    }

    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
