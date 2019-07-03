import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 벌꿀채취 {
    static int N,M,C,result = 0;
    static int[][] map;
    static List<List<Integer>> honeyBoxes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <=T ;test_case++){
            N = sc.nextInt(); M = sc.nextInt(); C = sc.nextInt();
            map = new int[N][N];
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            go();
            System.out.println(String.format("#%d %d",test_case,result));
            result = 0;
        }
    }

    private static void go() {
        boolean[][] visited = new boolean[N][N];
        dfs(0,0,0,visited);
    }

    private static void dfs(int count, int x, int y, boolean[][] visited) {
        if(count == 2){
            int profit = 0;
            for(List<Integer> values : honeyBoxes){
                profit += calcProfit(values);
            }
            result = Math.max(result,profit);
            return;
        }
        for(int i = x ; i < N; i++){
            for(int j = y ; j < N; j++){
                if(j + M-1 >= N) continue;
                boolean flag = true;
                for(int k = j ; k <= j + M-1; k++){
                    if(visited[i][k]) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) continue;
                List<Integer> valueOfHoeny = new ArrayList<>();
                for(int k = j ; k <= j + M-1; k++){
                    valueOfHoeny.add(map[i][k]);
                    visited[i][k] = true;
                }
                honeyBoxes.add(valueOfHoeny);
                dfs(count+1,i,j+M,visited);
                for(int k = j ; k <= j + M-1; k++){
                    visited[i][k] = false;
                }
                honeyBoxes.remove(valueOfHoeny);
            }
            y=0;
        }
    }

    private static int calcProfit(List<Integer> values) {
        int result = 0;
        int size = (1 << M) -1;
        for(int i = 1; i <= size; i++){
            List<Integer> selectedIndex = new ArrayList<>();
            int temp = 0;
            for(int j = 0 ; j < M; j++){
                if(((i >> j) & 1) == 1){
                    selectedIndex.add(j);
                    temp += values.get(j);
                }
            }
            if(temp <= C){
                int sumValue = 0;
                for(int index : selectedIndex){
                    int value = values.get(index);
                    sumValue = sumValue + (value*value);
                }
                result = Math.max(result,sumValue);
            }
        }
        return result;
    }

}