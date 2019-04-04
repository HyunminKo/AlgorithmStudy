package samsung;

import java.util.Scanner;

public class Tetromino {
    static int N;
    static int M;
    static int resultValue;
    static int[][] matrix;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();
        matrix = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0;j < M;j++){
                matrix[i][j] = input.nextInt();
            }
        }


        for(int i = 0; i < N; i++){
            for(int j = 0; j < M;j++){
                cover(i, j, 0, 0);
                exceptionShape(i,j);
            }
        }

        System.out.println(resultValue);
    }

    public static void cover(int x, int y , int maxValue, int cnt){

        if(cnt == 4){
            resultValue = Math.max(resultValue, maxValue);
            return;
        }

        for(int i = 0; i<4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                continue;
            }
            if(visited[nextX][nextY] == true){
                continue;
            }

            visited[nextX][nextY] = true;
            cover(nextX, nextY, maxValue+matrix[nextX][nextY], cnt+1);
            visited[nextX][nextY] = false;

        }

    }

    public static void exceptionShape(int x, int y) {
        int cnt = 4;
        int sum = matrix[x][y];
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (cnt <= 2) {
                return;
            }
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                cnt--;
                continue;
            }
            minValue = Math.min(minValue, matrix[nextX][nextY]);
            sum += matrix[nextX][nextY];
        }
        if (cnt == 4) {
            sum = sum - minValue;
        }
        resultValue = Math.max(sum, resultValue);
    }


}
