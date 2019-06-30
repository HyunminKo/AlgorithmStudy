import java.util.*;

public class 연구소3_17142 {
    static int N,M;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int result=0x3f3f3f3f;
    static List<Point> locationOfVirus = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N ; j++){
                map[i][j]=sc.nextInt();
                if(map[i][j] == 2){
                    locationOfVirus.add(new Point(i,j));
                }
            }
        }
        boolean[] visited = new boolean[locationOfVirus.size()];
        go(0,0,visited);
        if(result == 0x3f3f3f3f){
            System.out.println(-1);
        }else {
            System.out.println(result);
        }
    }

    private static void go(int n,int count, boolean[] visited) {
        if(count == M){
            List<Integer> pick = new ArrayList<>();
            for(int i = 0 ; i < visited.length ; i++){
                if(visited[i]){
                    pick.add(i);
                }
            }
            activeVirus(pick);
            return;
        }
        for(int i = n ; i < locationOfVirus.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                go(i,count+1,visited);
                visited[i] = false;
            }
        }
    }

    private static void activeVirus(List<Integer> pick) {
        int[][] timeMap = new int[N][N];
        for(int index : pick){
            boolean[][] visited = new boolean[N][N];
            Point l = locationOfVirus.get(index);
            Queue<Point> q = new LinkedList<>();
            q.offer(l);
            timeMap[l.x][l.y] = 0;
            while(!q.isEmpty()){
                Point p = q.poll();
                visited[p.x][p.y] = true;
                for(int i = 0 ; i < 4 ; i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if(nx < 0 || ny < 0 || nx >= N || ny>= N || visited[nx][ny] || map[nx][ny] == 1) continue;
                    if(timeMap[nx][ny] != 0 && timeMap[nx][ny] <= timeMap[p.x][p.y] + 1) continue;
                    visited[nx][ny] = true;
                    timeMap[nx][ny] = timeMap[p.x][p.y] + 1;
                    q.offer(new Point(nx,ny));
                }
            }
        }
        int count = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N ; j++){
                if(timeMap[i][j] == 0 && map[i][j] == 0){
                    count = 0x3f3f3f3f;
                    break;
                }
                if(timeMap[i][j] == 0) continue;
                if(map[i][j] == 2) continue;
                count = Math.max(timeMap[i][j] , count);
            }
        }
        result = Math.min(count,result);
    }

    private static void print(int[][] m) {
        System.out.println();
        for(int i = 0 ; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
