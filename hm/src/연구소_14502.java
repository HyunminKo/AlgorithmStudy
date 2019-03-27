import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연구소_14502 {
    static int N,M,result=0;
    static int[][] map, copyMap;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static ArrayList<Point> virus;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        virus = new ArrayList<>();
        map = new int[N][M];
        copyMap = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                copyMap[i][j] = map[i][j] = sc.nextInt();
                if(map[i][j] == 2) virus.add(new Point(i,j));
            }
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                visited = new boolean[N][M];
                if(copyMap[i][j] != 1 && copyMap[i][j] == 0 && !visited[i][j]){
                    copyMap[i][j] = 1;
                    visited[i][j] = true;
                    dfs(1,visited,copyMap);
                    visited[i][j] = false;
                    copyMap[i][j] = 0;
                }
                copyAToB(map,copyMap);
            }
        }
        System.out.println(result);
    }

    private static void copyAToB(int[][] map, int[][] copyMap) {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                copyMap[i][j] = map[i][j];
            }
        }
    }

    private static void dfs(int count, boolean[][] visited, int[][] map) {
        if(count == 3){
            int[][] tempMap = new int[N][M];
            copyAToB(map,tempMap);
            for(Point p: virus){
                bfs(p.x,p.y,tempMap);
            }
            int safe = 0;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    if(tempMap[i][j] == 0) safe++;
                }
            }
            result = Math.max(result, safe);
            return;
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] != 1 && map[i][j] == 0 && !visited[i][j]){
                    map[i][j] = 1;
                    visited[i][j] = true;
                    dfs(count+1,visited, map);
                    visited[i][j] = false;
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void printMap(int[][] map) {
        System.out.println();
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int x, int y, int[][] map) {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 1) continue;
                map[nx][ny] = 2;
                visited[nx][ny] = true;
                q.offer(new Point(nx,ny));
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
