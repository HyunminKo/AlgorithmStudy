import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 적록색약_bfs_10026 {
    static int N, numOfNormalArea,numOfAbnormalArea;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.nextLine();
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N ; i++){
            String line = sc.nextLine();
            for(int j = 0 ; j < N ;j++){
                if(line.charAt(j) == 'R'){ // 1
                    map[i][j] = 1;
                }else if(line.charAt(j) == 'G'){ //2
                    map[i][j] = 2;
                }else { //B , 3
                    map[i][j] = 3;
                }
            }
        }
        Queue<Point> q = new LinkedList<>();
        bfs(q, 0);
        visited = new boolean[N][N];
        bfs(q,1);
        System.out.println(numOfNormalArea+" "+numOfAbnormalArea);
    }

    private static void bfs(Queue<Point> q, int normal) { // 0: normal, 1: abnormal
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(!visited[i][j]){
                    q.offer(new Point(i,j));
                    visited[i][j]= true;
                    while(!q.isEmpty()){
                        Point p = q.poll();
                        for(int k = 0 ; k < 4; k++){
                            int nx = p.x + dx[k];
                            int ny = p.y + dy[k];
                            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                            if(normal == 0){ // 정상
                                if(map[nx][ny] != map[p.x][p.y]) continue;
                            }else { // 비정상
                                if(map[p.x][p.y] == 3){
                                    if(map[nx][ny] != 3) continue;
                                } else if(map[p.x][p.y] == 2 ||map[p.x][p.y] == 1){
                                    if(map[nx][ny] == 3) continue;
                                }
                            }
                            q.offer(new Point(nx,ny));
                            visited[nx][ny] = true;
                        }
                    }
                    if(normal == 0){
                        numOfNormalArea++;
                    }else {
                        numOfAbnormalArea++;
                    }
                }
            }
        }
    }

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
