package SWExpert;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pinball {

    static int T, N;
    static int[][] map;
    static int result = 0;
    static Ball ball;
    static int startRow;
    static int startCol;
    static int point = 0;
    static int warmholeCnt = 0;
    static Map<String, String> warmholes = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        T = input.nextInt();
        int problemNum = 1;
        while(T > 0){
            N = input.nextInt();
            map = new int[N][N];
            for(int i = 0; i< N; i++){
                for(int j = 0; j<N;j++){
                    int num = input.nextInt();
                    map[i][j] = num;

                    if(num > 5) warmholeCnt++;
                }
            }
            setWarmholes();

            for(int i = 0; i< N; i++){
                for(int j = 0; j<N;j++){
                    if(map[i][j] == 0){
                        ball = new Ball(i,j);
                        startRow = i;
                        startCol = j;
                        setDir(ball, i, j);
                    }
                }
            }
            System.out.println("#"+problemNum + " " +result);
            result = 0;
            problemNum++;
            T--;
        }
    }

    private static void setDir(Ball ball, int row, int col) {
        for(int i = 1; i<5; i ++){// 네 방향으로 출발.
            ball.row = row;
            ball.col = col;
            ball.dir = i;
            go(ball);
//            System.out.println("point : " + point);
            result = Math.max(point, result);
            point = 0;
        }

    }

    private static void go(Ball ball) {

        while(true) {

            int nr = 0, nc = 0;
            switch (ball.dir) {
                case 1:
                    nr = ball.row;
                    nc = ball.col - 1;
                    break;
                case 2:
                    nr = ball.row;
                    nc = ball.col + 1;
                    break;
                case 3:
                    nr = ball.row - 1;
                    nc = ball.col;
                    break;
                case 4:
                    nr = ball.row + 1;
                    nc = ball.col;
                    break;
            }

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {//벽이면
//                System.out.println(nr+"," +nc);
                changeDir_block5(ball.dir);//틩겨서 방향 바꾸는 코드
                point++;//포인트 증가.
                ball.row = nr;
                ball.col = nc;
//                if(ball.row == startRow && ball.col == startCol) break;
                continue;
            }

            if ((point != 0 && (nr == startRow && nc == startCol)) || map[nr][nc] == -1) {
//                System.out.println("도착 : " + nr+"," +nc);
                break;
            }

            if (map[nr][nc] >= 6) {//웜홀
                String next = warmholes.get(nr+","+nc);
                String[] tmp = next.split(",");
                ball.row = Integer.parseInt(tmp[0]);
                ball.col = Integer.parseInt(tmp[1]);


            } else if (map[nr][nc] > 0 && map[nr][nc] < 6) {//블록을 만나면
//                System.out.println("start : " + startRow + " " + startCol);
//                System.out.println("블록 : " + nr+"," +nc);
                changDir(ball.dir, map[nr][nc]);
                ball.row = nr;
                ball.col = nc;
                point++;
            }
            else{
                ball.row = nr;
                ball.col = nc;
            }
        }
    }

    private static void changDir(int dir, int blockNum){

        switch(blockNum){
            case 1:
                changeDir_block1(dir);
                break;
            case 2:
                changeDir_block2(dir);
                break;
            case 3:
                changeDir_block3(dir);
                break;
            case 4:
                changeDir_block4(dir);
                break;
            case 5:
                changeDir_block5(dir);
                break;
        }
    }


    private static void changeDir_block1(int dir){
        switch(dir){
            case 1:
                ball.dir = 3;
                break;
            case 2:
                ball.dir = 1;
                break;
            case 3:
                ball.dir = 4;
                break;
            case 4:
                ball.dir = 2;
                break;
        }
    }
    private static void changeDir_block2(int dir){
        switch(dir){
            case 1:
                ball.dir = 4;
                break;
            case 2:
                ball.dir = 1;
                break;
            case 3:
                ball.dir = 2;
                break;
            case 4:
                ball.dir = 3;
                break;
        }
    }
    private static void changeDir_block3(int dir){
        switch(dir){
            case 1:
                ball.dir = 2;
                break;
            case 2:
                ball.dir = 4;
                break;
            case 3:
                ball.dir = 1;
                break;
            case 4:
                ball.dir = 3;
                break;
        }
    }
    private static void changeDir_block4(int dir){
        switch(dir){
            case 1:
                ball.dir = 2;
                break;
            case 2:
                ball.dir = 3;
                break;
            case 3:
                ball.dir = 4;
                break;
            case 4:
                ball.dir = 1;
                break;
        }
    }
    private static void changeDir_block5(int dir){
        switch(dir){
            case 1:
                ball.dir = 2;
                break;
            case 2:
                ball.dir = 1;
                break;
            case 3:
                ball.dir = 4;
                break;
            case 4:
                ball.dir = 3;
                break;
        }
    }

    private static void setWarmholes(){
        boolean[][] visited = new boolean[N][N];
        int cnt = 0;
        while(cnt < warmholeCnt/2){

            int flag = 0;
            int warm = 0;
            String warmString= "";
            for(int i = 0; i<N; i++){
                for(int j =0; j<N;j++){
                    if(visited[i][j] || map[i][j] == 0) continue;
                    if(map[i][j] > 5){
                        visited[i][j] = true;
                        warm = map[i][j];
                        warmString = i+","+j;
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1) break;
            }
            flag = 0;
            for(int i = 0; i<N; i++){
                for(int j =0; j<N;j++){
                    if(visited[i][j] || map[i][j] == 0) continue;
                    if(map[i][j] == warm){
                        visited[i][j]= true;
                        flag = 1;
                        warmholes.put(warmString, i+","+j);
                        warmholes.put(i+","+j, warmString);
                        break;
                    }
                }
                if(flag == 1) break;
            }

            cnt++;
        }

    }

    static class Ball{
        int row, col;
        int dir;//1 : <- , 2: -> , 3: 위쪽으로, 4: 아래쪽으로 (진행방향)

        Ball(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
