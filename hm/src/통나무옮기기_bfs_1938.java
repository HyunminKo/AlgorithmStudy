import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 통나무옮기기_bfs_1938 {
    static int N;
    static int[][] map;
    static boolean[][][] visited;
    static final int HORIZON = 0;
    static final int VERTICAL = 1;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] dt = {-1,0,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        map = new int[N][N];
        visited = new boolean[N][N][2];
        int start = 0, destination = 0, prevS=-1, prevD=-1;
        Point pointer=null, target=null;
        for(int i = 0 ; i < N; i++){
            String line = sc.nextLine();
            for(int j = 0 ; j < N; j++){
                if(line.charAt(j) == 'B'){
                    start++;
                    if(start == 1){
                        prevS = i;
                    } else if(start==2){
                        if(prevS == i){
                            pointer = new Point(i,j,HORIZON);
                        }else {
                            pointer = new Point(i,j,VERTICAL);
                        }
                    }
                }else if(line.charAt(j) == 'E'){
                    destination++;
                    if(destination == 1){
                        prevD = i;
                    }else if(destination == 2){
                        map[i][j] = 2;
                        if(prevD == i){
                            target = new Point(i,j,HORIZON);
                        }else {
                            target = new Point(i,j,VERTICAL);
                        }
                    }
                }else if(line.charAt(j) == '1'){
                    map[i][j] = 1;
                }
            }

        }
        int result = 0;
        boolean flag = false;
        Queue<Point> q = new LinkedList<>();
        q.add(pointer);
        visited[pointer.x][pointer.y][pointer.dir] = true;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size; i++){
                Point p = q.poll();
                if(map[p.x][p.y] == 2){
                    if(p.x == target.x && p.y == target.y && p.dir == target.dir){
                        flag = true;
                        break;
                    }
                }
                move(p,q);
                turn(p,q);
            }
            if(flag){
                break;
            }
            result++;
        }
        if(flag){
            System.out.println(result);
        }else {
            System.out.println(0);
        }
    }

    private static void turn(Point p, Queue<Point> q) {
        boolean flag = true;
        if(p.dir == HORIZON){
            int p1x = p.x - 1;
            int p2x = p.x + 1;
            if(p1x >= 0 && p1x < N && p2x >= 0 && p2x < N ){
                for(int i = 0 ; i < 3; i++){
                    int py = p.y + dt[i];
                    if(map[p1x][py] == 1 || map[p2x][py] == 1){
                        flag = false;
                        break;
                    }
                }
            }else {
                flag = false;
            }
            if(flag) {
                if(!visited[p.x][p.y][VERTICAL]){
                    visited[p.x][p.y][VERTICAL] = true;
                    q.add(new Point(p.x,p.y,VERTICAL));
                }
            }
        }
        if(p.dir == VERTICAL){
            int p1y = p.y - 1;
            int p2y = p.y + 1;
            if(p1y >= 0 && p2y >= 0 && p1y < N && p2y < N){
                for(int i = 0 ; i < 3; i++){
                    int px = p.x  + dt[i];
                    if(map[px][p1y] == 1 || map[px][p2y] == 1) {
                        flag = false;
                        break;
                    }
                }
            }else {
                flag = false;
            }
            if(flag) {
                if(!visited[p.x][p.y][HORIZON])
                    visited[p.x][p.y][HORIZON] = true;
                    q.add(new Point(p.x,p.y,HORIZON));
            }
        }
    }

    private static void move(Point p, Queue<Point> q) {
        for(int i = 0 ; i < 4; i++){
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if(nx < 0 || ny < 0 || nx >=N || ny >=N || map[nx][ny] == 1) continue;
            if(p.dir == HORIZON){
                if( ny - 1 < 0 || ny + 1 >= N || map[nx][ny-1] == 1 || map[nx][ny+1] == 1) continue;
            }
            if(p.dir == VERTICAL){
                if( nx - 1 < 0 || nx + 1 >= N || map[nx-1][ny] == 1 || map[nx+1][ny] == 1) continue;
            }
            if(visited[nx][ny][p.dir]) continue;
            visited[nx][ny][p.dir] = true;
            q.add(new Point(nx,ny,p.dir));
        }
    }

    static class Point {
        int x,y,dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }
}