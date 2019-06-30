package baekjoon;

import java.util.*;

public class Lab3 {

    static int N,M;

    static List<Virus> virusList = new ArrayList<>();
    static List<Virus> selectVirusList = new ArrayList<>();
    static int result = Integer.MAX_VALUE;

    static boolean check = false;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        M = input.nextInt();

        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int emptyPlace = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int num = input.nextInt();
                if(num == 1){//벽은 -1로 대체해서 넣음.
                    map[i][j] = -1;
                }
                else if(num == 2){
                    virusList.add(new Virus(i,j));
                    map[i][j] = num;
                }
                else{
                    map[i][j] = num;
                    emptyPlace++;
                }

            }
        }//setting 0 : 빈칸, 1 : 벽, 2 : 바이러스

        dfs(0, 0, map, emptyPlace, visited);
        if(check == false){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }

    }

    static public void dfs(int cnt, int idx, int[][] map, int emptyPlace, boolean[][] visited){
        if(cnt == M){
            int[][] _map = new int[N][N];
            int _emptyPlace = emptyPlace;
            boolean[][] _visited = new boolean[N][N];
            init(_map,_visited,map,visited);
            bfs(_map,_emptyPlace,_visited);
        }
        else{
            for(int i = idx; i< virusList.size();i++){
                Virus v = virusList.get(i);
                selectVirusList.add(v);
                visited[v.x][v.y] = true;
                dfs(cnt+1, i+1, map, emptyPlace, visited);
                selectVirusList.remove(v);
                visited[v.x][v.y] = false;
            }
        }
    }

    private static void init(int[][] map, boolean[][] visited, int[][] map1, boolean[][] visited1) {

        for(int i = 0;i < N; i++){
            for(int j = 0; j< N; j++){
                map[i][j] = map1[i][j];
                visited[i][j] = visited1[i][j];
            }
        }

    }

    static public void bfs(int[][] map, int emptyPlace, boolean[][] visited){

        Queue<Virus> q = new LinkedList<>();
        for(Virus v : selectVirusList){
            q.add(v);
        }

        int cycle = q.size();

        int time = 1;
        int max = 0;

        while(!q.isEmpty()){
            Virus virus = q.poll();

            for(int i = 0; i< 4; i++){
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1|| visited[nx][ny]) continue;

                if(map[nx][ny] == 2){
                    q.add(new Virus(nx,ny));
                }
                else{// 0, 1
                    if(map[nx][ny] == 0){
                        emptyPlace = emptyPlace-1;
                    }
                    map[nx][ny] = map[nx][ny]+1;
                    q.add(new Virus(nx,ny));
                    map[nx][ny] = time;
                    max = Math.max(max,time);
                }
                visited[nx][ny] = true;

            }
            cycle--;
            if(cycle == 0){
                time++;
                cycle = q.size();
            }
        }

//        print(map);
//        System.out.println(max);
//        System.out.println();

        if(emptyPlace == 0){
            result = Math.min(result, max);
            check = true;
        }

    }


    static public void print(int[][] map){
        for(int i = 0; i< N;i++){
            for(int j = 0; j< N;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Virus{
        int x,y;

        Virus(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
