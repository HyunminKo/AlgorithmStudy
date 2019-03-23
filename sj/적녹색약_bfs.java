package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RGB {

    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static Queue<Point> queue  = new LinkedList<>();
    static Point[][] map;
    static int result_0 = 0;
    static int result_1 = 0;
    public static void main(String[] args) {

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];
            map = new Point[N][N];
            int cnt = 0;
            String line;

            for( int i = 0; i< N; i++){
                line = br.readLine();
                for(int j = 0; j< line.length();j++){
                    map[i][j] = new Point(line.charAt(j), i, j);
                }
            }

            bfs(0);//적록색약
            visited = new boolean[N][N];
            bfs(1);//정상

            System.out.println(result_1 + " " + result_0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void bfs(int flag){

        for(int i = 0; i <N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    queue.add(map[i][j]);
                    visited[i][j] = true;

                    while(!queue.isEmpty()){
                        Point point = queue.poll();
                        char nowC = point.c;
                        for(int k = 0; k < 4; k++){
                            int nextX = point.x + dx[k];
                            int nextY = point.y + dy[k];

                            if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= N){
                                continue;
                            }
                            if(visited[nextX][nextY]){
                                continue;
                            }
                            if(flag == 0){//적록색약
                                if(nowC == 'R' || nowC == 'G'){
                                    if(map[nextX][nextY].c == 'B'){
                                        continue;
                                    }
                                }
                                else{
                                    if(map[nextX][nextY].c != nowC){
                                        continue;
                                    }
                                }
                            }
                            else{//정상
                                if(map[nextX][nextY].c != nowC){
                                    continue;
                                }
                            }
                            queue.add(map[nextX][nextY]);
                            visited[nextX][nextY] = true;
                        }
                    }

                    if(flag == 0){
                        result_0++;
                    }
                    else{
                        result_1++;
                    }
                }
            }
        }

    }

    static class Point{
        char c;
        int x;
        int y;

        Point(char c, int x, int y){
            this.c = c;
            this.x = x;
            this.y = y;
        }

    }
}
