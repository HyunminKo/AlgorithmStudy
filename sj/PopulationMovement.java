package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PopulationMovement {

    static int N;
    static int R;
    static int L;

    static int[][] ground;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int moveCnt = 0;
    static boolean moveChecked = true;
    static boolean[][] _visited;

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]);
            L = Integer.parseInt(str[1]);
            R = Integer.parseInt(str[2]);

            ground = new int[N][N];
            for (int i = 0; i < N; i++) {// 땅 초기화
                str = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    ground[i][j] = Integer.parseInt(str[j]);
                }
            }

            while(moveChecked) {
                _visited = new boolean[N][N];
                moveChecked = false;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(_visited[i][j] == false)bfs(i, j);
                    }
                }
                if(moveChecked){
                    moveCnt++;
                }
            }
            System.out.println(moveCnt);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static void bfs(int x, int y){
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();
        int sum = 0;

        q.add(new Point(x, y , ground[x][y]));
        list.add(new Point(x, y , ground[x][y]));
        _visited[x][y] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            sum += p.cnt;
            for(int i = 0; i < 4; i++){

                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >=N) continue;
                if(_visited[nx][ny] == true) continue;
                int sub = Math.abs(ground[nx][ny] - p.cnt);
                if(sub >= L && sub <=R){
                    q.add(new Point(nx,ny,ground[nx][ny]));
                    list.add(new Point(nx,ny,ground[nx][ny]));
                    _visited[nx][ny] = true;
                }
            }
        }
        if(list.size() > 1){
            moveChecked = true;
        }
        for (int j = 0; j < list.size(); j++) {
            int move = sum / list.size();
            Point temp = list.get(j);
            ground[temp.x][temp.y] = move;
        }
    }
    static class Point{
        int x,y;
        int cnt;

        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
