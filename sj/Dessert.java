package SWExpert;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dessert {

    static int T, N;
    static int[][] map;
    static int[] dx = {1,1,-1,-1};
    static int[] dy = {1,-1,-1,1};
    static boolean[][] visited;
    static Set<Integer> set = new HashSet<>();
    static int startIdx_i;
    static int startIdx_j;
    static int result = 0;
    static int tCase = 1;
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        T = input.nextInt();

        while(T > 0){
            N = input.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i = 0 ; i < N; i++){
                for(int j = 0; j < N; j++){
                    if((i == 0 && j == 0) || (i ==0 && j==(N-1)) || (i== N-1 && j== 0) || ( i == N-1 && j == N-1)){
                        visited[i][j] = true;
                    }
                    map[i][j] = input.nextInt();
                }
            }

            solution();
            if(result == 0){
                System.out.println("#"+tCase + " " + -1);
            }
            else {
                System.out.println("#" + tCase + " " + result);
            }
            T--;
            tCase++;
            result = 0;

        }
    }
    static void solution(){

        for(int i = 0; i< N; i++){
            for(int j = 0; j < N; j++){
                if(!(i == 0 && j == 0) || (i ==0 && j==(N-1)) || (i== N-1 && j== 0) || ( i == N-1 && j == N-1)){
                    startIdx_i = i;
                    startIdx_j = j;
                    visited[i][j] = true;
                    set.add(map[i][j]);
                    dfs(i, j, 0, 0);
                    visited[i][j] = false;
                    set.remove(map[i][j]);
                }
            }
        }
    }

    static void dfs(int idx_i, int idx_j, int cafe, int direction){



        for(int i = 0; i< 4; i++){
            if(i < direction) continue;
            int nx = idx_i + dx[i];
            int ny = idx_j + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;//인덱스 넘어가면 걸러
            if(visited[nx][ny] && (cafe == 1 || (nx != startIdx_i || ny != startIdx_j))) continue;
            if(set.contains(map[nx][ny])){
                if((nx != startIdx_i || ny != startIdx_j))continue;// 같은 수가 있으면 걸러
            }

            if(nx == startIdx_i && ny == startIdx_j) {//다음 갈 곳이 출발지면
                result = Math.max(result, cafe+1);
//                for(int t : set){
//                    System.out.print(t + " ");
//                }
//                System.out.println();
                return;
            }

            if(i == 2){
                if(nx == startIdx_i && ny < startIdx_j) continue;
            }

            visited[nx][ny] = true;
            cafe++;
            set.add(map[nx][ny]);
            dfs(nx, ny, cafe, i);
            set.remove(map[nx][ny]);
            cafe--;
            visited[nx][ny] = false;

        }

    }
}
