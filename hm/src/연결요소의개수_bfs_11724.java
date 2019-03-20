import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연결요소의개수_bfs_11724 {
    static int N,M;
    static int[][] adjMatrix;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        adjMatrix = new int[N][N];
        visited= new boolean[N];
        for(int i = 0 ; i < M; i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
        int result = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < N; i++){
            if(!visited[i]){
                q.offer(i);
                visited[i] = true;
                while(!q.isEmpty()){
                    int p = q.poll();
                    for(int j = 0 ; j < N; j++){
                        if(p == j || visited[j] || adjMatrix[p][j] == 0) continue;
                        q.offer(j);
                        visited[j] = true;
                    }
                }
                result++;
            }
        }
        System.out.println(result);
    }
}
