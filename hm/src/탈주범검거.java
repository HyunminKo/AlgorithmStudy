import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 탈주범검거 {
    static int N,M;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1 ; test_case <= T; test_case++){
            N = sc.nextInt(); M = sc.nextInt();
            int x = sc.nextInt(); int y = sc.nextInt();
            int time = sc.nextInt()-1;
            map = new int[N][M];
            boolean[][] visited = new boolean[N][M];
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            Queue<Point> q = new LinkedList<>();
            visited[x][y] = true;
            q.offer(new Point(x,y));
            int result = 1;
            while(true){
                if(time == 0) break;
                int size = q.size();
                for(int i = 0 ; i < size; i++){
                    Point p = q.poll();
                    result += addNextPath(p.x,p.y,map[p.x][p.y],q,visited);
                }
                time--;
            }
            System.out.println(String.format("#%d %d",test_case,result));
        }
    }

    private static int addNextPath(int x, int y, int type, Queue<Point> q, boolean[][] visited) {
        int count = 0;
        if(type == 1){
            for(int i = 0 ; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                count += addPosition(q, visited, nx, ny, i);
            }
        }else if(type == 2){
            int nx = x + dx[0];
            int ny = y + dy[0];
            count += addPosition(q, visited, nx, ny, 0);
            nx = x + dx[2];
            ny = y + dy[2];
            count += addPosition(q, visited, nx, ny, 2);
        }else if(type == 3){
            int nx = x + dx[1];
            int ny = y + dy[1];
            count += addPosition(q, visited, nx, ny, 1);
            nx = x + dx[3];
            ny = y + dy[3];
            count += addPosition(q, visited, nx, ny, 3);
        }else if(type == 4){
            int nx = x + dx[0];
            int ny = y + dy[0];
            count += addPosition(q, visited, nx, ny, 0);
            nx = x + dx[1];
            ny = y + dy[1];
            count += addPosition(q, visited, nx, ny, 1);
        }else if(type == 5){
            int nx = x + dx[1];
            int ny = y + dy[1];
            count += addPosition(q, visited, nx, ny, 1);
            nx = x + dx[2];
            ny = y + dy[2];
            count += addPosition(q, visited, nx, ny, 2);
        }else if(type == 6){
            int nx = x + dx[2];
            int ny = y + dy[2];
            count += addPosition(q, visited, nx, ny, 2);
            nx = x + dx[3];
            ny = y + dy[3];
            count += addPosition(q, visited, nx, ny, 3);
        }else if(type == 7){
            int nx = x + dx[0];
            int ny = y + dy[0];
            count += addPosition(q, visited, nx, ny, 0);
            nx = x + dx[3];
            ny = y + dy[3];
            count += addPosition(q, visited, nx, ny, 3);
        }
        return count;
    }

    private static int addPosition(Queue<Point> q, boolean[][] visited, int nx, int ny, int dir) {
        if(nx < 0 || ny < 0 || nx>= N || ny >=M) return 0;
        if(visited[nx][ny]) return 0;
        if(map[nx][ny] == 0) return 0;
        if(dir == 0){
            if(map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) return 0;
        }else if(dir==1){
            if(map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) return 0;
        }else if(dir==2){
            if(map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) return 0;
        }else if(dir==3){
            if(map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) return 0;
        }
        visited[nx][ny] = true;
        q.offer(new Point(nx,ny));
        return 1;
    }

    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
//1
//5 6 2 2 6
//3 0 0 0 0 3
//2 0 0 0 0 6
//1 3 1 1 3 1
//2 0 2 0 0 2
//0 0 4 3 1 1
