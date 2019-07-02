package SWExpert;

import java.util.Scanner;

public class Airstrip {

    static int[][] map;
    static int T, N, X;
    static int result = 0;
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        T = input.nextInt();
        int problem = 1;

        while(T != 0) {
            N = input.nextInt();
            X = input.nextInt();

            map = new int[N][N];
            for(int i = 0; i< N;i++){
                for(int j = 0; j< N; j++){
                    map[i][j] = input.nextInt();
                }
            }
            for(int i = 0; i < N; i++){//가로 탐색
                int[] temp = map[i];
                if(solution(temp)){
                    result++;
                }
            }
            for(int i = 0; i < N; i++){//세로 탐색
                int[] temp = new int[N];
                for(int j = 0; j< N; j++){
                    temp[j] = map[j][i];
                }
                if(solution(temp)){
                    result++;
                }
            }
            System.out.println("#"+ problem + " " + result);
            T--;
            problem++;
            result = 0;
        }
    }

    private static boolean solution(int[] temp) {
        boolean possible = true;
        boolean[] visited = new boolean[N];
        for(int i = 0; i< temp.length-1;i++){
            if(!possible) return false;
            if(temp[i] == temp[i+1]) continue;
            else{
                if(temp[i] < temp[i+1]){//다음 값이 클 때,
                    if(temp[i]+1 == temp[i+1]){//다음 값이 1 차이 날 때,
                        possible = checkFoundLeft(temp, i, visited);
                        if(!possible) return false;
                    }else{//2이상 차이 날 때,
                        return false;
                    }
                }
                else{// 다음 값이 작을 때,
                    if(temp[i]-1 == temp[i+1]) {//다음 값이 1 차이 날 때,
                        possible = checkFoundRight(temp, i+1, visited);
                        if(possible) i = i+(X-1);
                        else return false;
                    }
                    else{
                        return false;
                    }
                }
            }
        }

        return possible;
    }

    private static boolean checkFoundRight(int[] temp, int idx, boolean[] visited) {
        boolean result = true;

//        if(visited[idx])

        if(idx+(X-1) > temp.length-1) return false;
        else{
            int std = temp[idx];
            for(int i = idx+1;i < idx+X; i++){
                if(std != temp[i] || visited[i]) return false;
                else{
                    result = true;
                }
            }
            if(result){
                for(int i = idx; i < idx+X;i++){
                    visited[i] = true;
                }
            }
        }
        return result;
    }


    private static boolean checkFoundLeft(int[] temp, int idx, boolean[] visited){
        boolean result = true;

        if(visited[idx]) return false;

        if(idx-(X-1) <0) return false;
        else{
            int std = temp[idx];
            for(int i = idx-1; i > idx-X;i--){
                if(std != temp[i] || visited[i]) return false;
                else{
                    result = true;
                }
            }
            if(result) {
                for (int i = idx; i > idx - X; i--) {
                    visited[i] = true;
                }
            }

        }
        return result;
    }
}
