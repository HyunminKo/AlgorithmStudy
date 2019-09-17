public class Network {
    static boolean[][] link;

    public static void main(String[] args) {
        int a = solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println(a);
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        link = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (!link[i][i]) {
                dfs(computers, i, n);
                answer++;
            }
        }
        return answer;
    }

    public static void dfs(int[][] computers, int idx, int n) {
        for (int i = 0; i < n; i++) {
            if (computers[idx][i] == 1 && !link[idx][i]) {
                link[idx][i] = link[i][idx] = true;
                dfs(computers, i, n);
            }
        }
    }
}
