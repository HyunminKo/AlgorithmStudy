import java.util.Scanner;

public class 연결요소의개수_dfs_11724 {
    static int N, M;
    static int[][] adjMatrix;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        adjMatrix = new int[N][N];
        visited = new boolean[N];
        for(int i = 0 ; i < M ; i ++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
        int result = 0;
        for(int i = 0 ; i < N; i++){
            if(!visited[i]){
                dfs(i);
                result ++;
            }
        }
        System.out.println(result);
    }

    private static void dfs(int u) {
        visited[u] = true;
        for(int i = 0 ; i < N; i++){
            if(!visited[i] && adjMatrix[u][i] == 1)
                dfs(i);
        }
    }
}
