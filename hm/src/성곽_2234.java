import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 성곽_2234 {
    static int N,M,result;
    static int[][][] map;
    static int[][] rooms;
    static boolean[][] visited;
    static int[] dx = {0,-1,0,1}; // 서 0, 북 1, 동 2, 남 3
    static int[] dy = {-1,0,1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt(); N = sc.nextInt();
        map = new int[N][M][4];
        rooms = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                int temp = sc.nextInt();
                for(int k = 0 ; k < 4 ; k++){
                    if((1 & (temp >> k)) == 1){
                        map[i][j][k] = 1;
                    }
                }
            }

        }
        visited = new boolean[N][M];
        int numOfRoom = 0;
        int maxAreaOfRoom = 0;
        int rmWallMaxAreaOfRoom = 0;
        ArrayList<Integer> roomAry = new ArrayList<>();
        for(int i = 0 ; i < N ;i++){
            for(int j = 0 ; j < M; j++){
                if(!visited[i][j]){
                    numOfRoom++;
                    Queue<Point> q = new LinkedList<>();
                    q.offer(new Point(i,j));
                    rooms[i][j] = numOfRoom;
                    visited[i][j] = true;
                    int temp = bfs(q,numOfRoom);
                    roomAry.add(temp);
                    if(maxAreaOfRoom < temp){
                        maxAreaOfRoom = temp;
                    }
                }
            }
        }
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++){
                if(j != M-1){
                    if(rooms[i][j] != rooms[i][j+1]){
                        int temp = roomAry.get(rooms[i][j]-1) + roomAry.get(rooms[i][j+1]-1);
                        if(rmWallMaxAreaOfRoom < temp){
                            rmWallMaxAreaOfRoom = temp;
                        }
                    }
                }
                if(i != N - 1){
                    if(rooms[i][j] != rooms[i+1][j]){
                        int temp = roomAry.get(rooms[i][j]-1) + roomAry.get(rooms[i+1][j]-1);
                        if(rmWallMaxAreaOfRoom < temp){
                            rmWallMaxAreaOfRoom = temp;
                        }
                    }
                }
            }
        }
        System.out.println(numOfRoom);
        System.out.println(maxAreaOfRoom);
        System.out.println(rmWallMaxAreaOfRoom);
    }

    private static int bfs(Queue<Point> q, int numOfRoom) {
        int count = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
            count++;
            for(int k = 0 ; k < 4; k++){
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(map[p.x][p.y][k] == 1) continue;
                visited[nx][ny] = true;
                rooms[nx][ny] = numOfRoom;
                q.offer(new Point(nx,ny));
            }
        }
        return count;
    }
    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
