import java.util.*;

public class 디저트카페 {
    static int N,result=-1;
    static int startX, startY;
    static int[][] map;
    static boolean[][] visited;
    static Set<Integer> set = new HashSet<>();
    static int[] dx = {1,1,-1,-1}; //우하:0, 좌하:1, 좌상:2, 우상:3
    static int[] dy = {1,-1,-1,1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1 ; test_case <= T; test_case++){
            N =sc.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            go();
            System.out.println(String.format("#%d %d",test_case,result));
            result = -1;
        }
    }

    private static void go() {
        for(int i = 0 ; i < N - 2 ; i++){
            for(int j = 1 ; j < N - 1 ; j++){
                startX = i; startY = j;
                set.add(map[i][j]);
                drawRect(i,j,0);
                set.remove(map[i][j]);
            }
        }
    }

    private static void drawRect(int i, int j, int dir) {
        if(dir > 3) return;

        int nx = i + dx[dir];
        int ny = j + dy[dir];
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) return;

        if(nx == startX && ny == startY){
            result = Math.max(result, set.size());
            return;
        }

        if(visited[nx][ny] || set.contains(map[nx][ny])) return;

        visited[nx][ny] = true;
        set.add(map[nx][ny]);
        drawRect(nx,ny,dir);
        drawRect(nx,ny,dir+1);
        visited[nx][ny] = false;
        set.remove(map[nx][ny]);

    }
    public static class Point {
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


//1
//4
//9 8 9 8
//4 6 9 4
//8 7 7 8
//4 5 3 5
