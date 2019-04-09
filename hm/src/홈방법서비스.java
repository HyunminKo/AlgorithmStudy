import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 홈방법서비스 {
    static int N,M,result;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++){
            N = sc.nextInt(); M= sc.nextInt();
            map= new int[N][N];
            int numOfHouse = 0;
            for(int i  = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                    if(map[i][j] == 1) numOfHouse++;
                }
            }
            int totalProfit = numOfHouse * M;
            int maxK = 0;
            for(int k = 1; k <= 46; k++){
                int temp = k*k + (k-1)*(k-1);
                if(temp > totalProfit){
                    maxK = k-1;
                    break;
                }
            }
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    go(i,j,maxK);
                }
            }
            System.out.println(String.format("#%d %d",test_case,result));
            result=0;
        }
    }

    private static void go(int x, int y, int maxK) {
        for(int k = 1; k <= maxK; k++){
            int cost = k*k + (k-1)*(k-1);
            int money = bfs(x,y,k);
            if(cost <= money){
                result = Math.max(result,money/M);
            }
        }
    }

    private static int bfs(int x, int y, int k) {
        int money = 0;
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];
        visited[x][y] = true;
        distance[x][y] = 1;
        Queue<Point> q = new LinkedList<>();
        if(map[x][y] == 1) money += M;
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny] || distance[p.x][p.y] + 1 > k) continue;
                if(map[nx][ny] == 1) money += M;
                visited[nx][ny] = true;
                distance[nx][ny] = distance[p.x][p.y] + 1;
                q.offer(new Point(nx,ny));
            }
        }
        return money;
    }

    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
