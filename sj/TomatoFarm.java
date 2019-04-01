package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class TomatoFarm {

    static int N;
    static int M;
    static int[][] farm;
    static boolean[][] visited;
    static Queue<Tomato> queue  = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int maxValue = 0;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]);
            M = Integer.parseInt(str[1]);
            farm = new int[M][N];
//            visited = new boolean[N][M];
            int cntOfzero = 0;

            for (int i = 0; i < M; i++) {
                str = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int tempTomato = Integer.parseInt(str[j]);
                    farm[i][j] = tempTomato;
                    if(tempTomato == 0){
                        cntOfzero++;
                    }
                    if(tempTomato == 1){// 토마토는 큐에 담는다.
                        queue.add(new Tomato(i,j, 0));
                    }
                }
            }

            if(cntOfzero == 0){
                maxValue = 0;
            }
            else{
                affect();
            }
            System.out.println(maxValue);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void affect(){

        while(!queue.isEmpty()){
            Tomato tomato = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextX = tomato.x + dx[i];
                int nextY = tomato.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N){
                    continue;
                }

                if(farm[nextX][nextY] == -1){//토마토가 들어있지 않은 칸.
                    continue;
                }
                if(farm[nextX][nextY] != 0) {
                    if (farm[nextX][nextY] > tomato.day + 1) {
                        queue.add(new Tomato(nextX, nextY,tomato.day + 1));
                        farm[nextX][nextY] = farm[tomato.x][tomato.y] + 1;
                    }
                }
                else{
                    queue.add(new Tomato(nextX, nextY, tomato.day + 1));
                    farm[nextX][nextY] = tomato.day + 1;
                }
            }
        }

        for(int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (farm[i][j] == 0) {
                    maxValue = -1;
                    return;
                } else {
                    maxValue = Math.max(farm[i][j], maxValue);
                }
            }
        }
    }

    static class Tomato{
        int x;
        int y;
        int day;

        public Tomato(int x, int y, int day){
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}
