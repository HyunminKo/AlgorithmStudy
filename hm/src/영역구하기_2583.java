import java.util.*;

public class 영역구하기_2583 {
    static int N,M,K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt(); N = sc.nextInt(); K = sc.nextInt();

        map= new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0 ; i < K; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt()-1;
            int y2 = sc.nextInt()-1;
            fill(x1,y1,x2,y2);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] == 0 && !visited[i][j]){
                    int count = 0;
                    Queue<Point> q = new LinkedList<>();
                    q.offer(new Point(i,j));
                    visited[i][j] = true;
                    while(!q.isEmpty()){
                        Point p = q.poll();
                        count++;
                        for(int k = 0 ; k < 4; k++){
                            int nx = p.x + dx[k];
                            int ny = p.y + dy[k];
                            if(nx <0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 1) continue;
                            visited[nx][ny] = true;
                            q.offer(new Point(nx,ny));
                        }
                    }
                    result.add(count);
                }
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for(int i = 0 ; i < result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }

    private static void fill(int x1, int y1, int x2, int y2) {
        for(int i = x1; i <= x2; i++){
            for(int j = y1; j <= y2; j++){
                map[i][j] = 1;
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
