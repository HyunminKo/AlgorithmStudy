import java.util.*;

public class 줄기세포배양 {
    static int N,M,K,fixedSize;
    static int[][] map, statusMap, time;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static final int EMPTY = 0;
    static final int INACTIVE = 1;
    static final int ACTIVE = 2;
    static final int DEAD = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1 ; test_case <= T ; test_case++){
            N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
            fixedSize = 2 * Math.max(N,M) + K;
            map = new int[fixedSize][fixedSize];
            statusMap = new int[fixedSize][fixedSize];
            time = new int[fixedSize][fixedSize];
            int startX = (fixedSize)/2 - 1;
            int startY = (fixedSize)/2 - 1;
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < M; j++){
                    time[startX + i][startY + j] = map[startX + i][startY + j] = sc.nextInt();
                    if(map[startX + i][startY + j] == 0){
                        statusMap[startX + i][startY + j] = EMPTY;
                    }else {
                        statusMap[startX + i][startY + j] = INACTIVE;
                    }
                }
            }
            System.out.println(String.format("#%d %d",test_case,go()));
        }
    }

    private static int go() {
        Queue<Point> q = new LinkedList<>();
        for(int k  = 0 ; k < K; k++){
            changeState(q);
            bfs(q);
        }
        return calc();
    }

    private static int calc() {
        int result = 0;
        for(int i = 0 ; i < fixedSize ; i++){
            for(int j = 0 ; j < fixedSize ; j++){
                if(statusMap[i][j] == INACTIVE || statusMap[i][j] == ACTIVE){
                    result++;
                }
            }
        }
        return result;
    }

    private static void bfs(Queue<Point> q) {
        //퍼트리기
        Set<Point> hashSet = new HashSet<>();
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= fixedSize || ny >= fixedSize) continue;
                if(statusMap[nx][ny] == EMPTY){
                    if(map[nx][ny] < map[p.x][p.y]){
                        time[nx][ny] = map[nx][ny] = map[p.x][p.y];
                        hashSet.add(new Point(nx,ny));
                    }
                }
            }
        }
        hashSet.stream().forEach(p -> statusMap[p.x][p.y] = INACTIVE);
    }

    private static void changeState(Queue<Point> q) {
        //상태 변경
        for(int i = 0; i < fixedSize; i++){
            for(int j = 0 ; j < fixedSize; j++){
                if(statusMap[i][j] == EMPTY || statusMap[i][j] == DEAD) continue;
                if(statusMap[i][j] == INACTIVE) {
                    if(time[i][j] > 1){
                        time[i][j]--;
                    }else {
                        statusMap[i][j] = ACTIVE;
                        time[i][j] = map[i][j];
                    }
                } else if(statusMap[i][j] == ACTIVE){
                    q.offer(new Point(i,j));
                    time[i][j]--;
                    if(time[i][j] == 0){
                        statusMap[i][j] = DEAD;
                    }
                }
            }
        }
    }
    //test용
    private static void print() {
        StringBuilder sb = new StringBuilder();
        System.out.println();
        for(int i = 0 ; i < fixedSize; i++){
            for(int j = 0 ; j < fixedSize; j++){
                if(statusMap[i][j] == 0){
                    sb.append("____ ");
                    continue;
                }else if (statusMap[i][j] == 1){
                    sb.append('I');
                }else if (statusMap[i][j] == 2){
                    sb.append('A');
                }else {
                    sb.append('D');
                }
                sb.append(map[i][j]+"_" + time[i][j] + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
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
