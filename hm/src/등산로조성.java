import java.util.*;

public class 등산로조성 {
    static int N,K,result=0,maxHeight=0;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<Peak> peaks;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++){
            N = sc.nextInt(); K = sc.nextInt();
            map = new int[N][N];
            peaks = new ArrayList<>();
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N ;j++){
                    map[i][j] = sc.nextInt();
                    if(maxHeight<map[i][j]){
                        maxHeight = map[i][j];
                    }
                }
            }

            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N ;j++){
                    if(map[i][j] == maxHeight){
                        peaks.add(new Peak(i,j));
                    }
                }
            }
            for(int k = 1; k <= K; k++){
                go(k);
            }
            System.out.println(String.format("#%d %d",test_case,result));
            result = 0;
            maxHeight = 0;

        }
    }

    private static void go(int k) {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j] - k >= 0){
                    map[i][j] -= k;
                    bfs();
                    map[i][j] += k;
                }
            }
        }
    }

    private static void bfs() {
        for(Peak peak : peaks){
            int[][] score = new int[N][N];
            Queue<Peak> q = new LinkedList<>();
            score[peak.x][peak.y] = 1;
            q.offer(peak);
            while(!q.isEmpty()){
                Peak p = q.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
                    if(score[nx][ny] != 0 && score[nx][ny] >= score[p.x][p.y] + 1) continue;
                    if(map[nx][ny] >= map[p.x][p.y]) continue;

                    score[nx][ny] = score[p.x][p.y] + 1;
                    result = Math.max(result,score[nx][ny]);
                    q.offer(new Peak(nx,ny));
                }
            }
        }
    }

    private static class Peak {
        int x,y;

        public Peak(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Peak{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}