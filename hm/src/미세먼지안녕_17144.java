import java.util.Scanner;

public class 미세먼지안녕_17144 {
    static int N,M,T;
    static int[][] map;
    static Point airCleaner;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); T = sc.nextInt();

        map = new int[N][M];

        boolean flag = false;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                map[i][j] = sc.nextInt();
                if(!flag && map[i][j] == -1){
                    airCleaner = new Point(i,j);
                    flag = true;
                }
            }
        }

        go();
    }

    private static void go() {
        for(int i = 0 ; i < T ; i++){
            spreadOut();
            cleaning();
        }
        System.out.println(calc());
    }

    private static int calc() {
        int result = 0;
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] != -1){
                    result += map[i][j];
                }
            }
        }
        return result;
    }

    private static void cleaning() {
        //공기청정기 top
        // down
        for(int x = airCleaner.x-1; x > 0 ; x--){
            map[x][airCleaner.y] = map[x-1][airCleaner.y];
        }
        // <<<<<
        for(int y = 0 ; y < M-1; y++){
            map[0][y] = map[0][y+1];
        }
        // up
        for(int x = 0; x < airCleaner.x ; x++){
            map[x][M-1] = map[x+1][M-1];
        }
        // >>>>>
        for(int y = M-1 ; y > 1; y--){
            map[airCleaner.x][y] = map[airCleaner.x][y-1];
        }


        //공기청정기 bottom
        //up
        for(int x = airCleaner.x; x < N-1 ; x++){
            map[x][0] = map[x+1][0];
        }

        // <<<<<
        for(int y = 0 ; y < M-1; y++){
            map[N-1][y] = map[N-1][y+1];
        }
        // down
        for(int x = N-1; x > airCleaner.x+1 ; x--){
            map[x][M-1] = map[x-1][M-1];
        }
        // >>>>>
        for(int y = M-1 ; y > 0; y--){
            map[airCleaner.x+1][y] = map[airCleaner.x+1][y-1];
        }
        map[airCleaner.x][0] = -1;
        map[airCleaner.x][1] = 0;
        map[airCleaner.x+1][0] = -1;
        map[airCleaner.x+1][1] = 0;
    }

    private static void spreadOut() {
        int[][] tempMap = new int[N][M];

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                if(map[i][j] != 0 && map[i][j] != -1){
                    int count = 0;
                    int amount = map[i][j] / 5;
                    if(amount == 0){
                        tempMap[i][j] += map[i][j];
                        continue;
                    }
                    for(int k = 0 ; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if( nx < 0 || ny < 0 || nx >= N  || ny >= M || map[nx][ny] == -1) continue;
                        tempMap[nx][ny] += amount;
                        count++;
                    }
                    tempMap[i][j] = map[i][j] - amount*count + tempMap[i][j];
                }
            }
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                map[i][j] = tempMap[i][j];
            }
        }
    }

    private static void print(int[][] map) {
        System.out.println();
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
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
