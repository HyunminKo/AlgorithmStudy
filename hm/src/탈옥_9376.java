import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 탈옥_9376 {
    static int T,N,M;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] map;
    static int[][] copyMap;
    static int[][] distFromExternalDoor;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        while(T-- > 0){
            N = sc.nextInt(); M = sc.nextInt();
            map=new int[N][M];
            sc.nextLine();
            ArrayList<Point> prisonerLocation = new ArrayList<>();
            for(int i = 0 ; i < N; i++){
                String line = sc.nextLine();
                for(int j = 0 ; j < M; j++){
                    if(line.charAt(j) == '$'){
                        map[i][j] = 2;
                        prisonerLocation.add(new Point(i,j));
                    } else if (line.charAt(j) == '#'){
                        map[i][j] = 1;
                    } else if (line.charAt(j) == '.'){
                        map[i][j] = 0;
                    } else {
                        map[i][j] = -1;
                    }
                }
            }
            int minNumOfOpendDoor = 0x3f3f3f3f;
            Point AP = prisonerLocation.get(0);
            ArrayList<ArrayList<Point>> Apaths = new ArrayList<>();
            int[][] AdistFromExternalDoor = new int[N][M];
            copyMap = new int[N][M];
            copyMapAtoB(map, copyMap);
            int Acount = bfs(AP.x,AP.y,Apaths,AdistFromExternalDoor,0);
            if(Acount == 0){
                Point BP = prisonerLocation.get(1);
                ArrayList<ArrayList<Point>> Bpaths = new ArrayList<>();
                int[][] BdistFromExternalDoor = new int[N][M];
                int Bcount = bfs(BP.x,BP.y,Bpaths,BdistFromExternalDoor, 0);
                if(Bcount == 0){
                    minNumOfOpendDoor = 0;
                }else {
                    Bpaths = new ArrayList<>();
                    BdistFromExternalDoor = new int[N][M];
                    bfs(BP.x,BP.y,Bpaths,BdistFromExternalDoor, 1);
                    for(int j = 0; j < Bpaths.size(); j++){
                        minNumOfOpendDoor = Math.min(minNumOfOpendDoor,Bpaths.get(j).size());
                    }
                }
            }else {
                Apaths = new ArrayList<>();
                AdistFromExternalDoor = new int[N][M];
                bfs(AP.x,AP.y,Apaths,AdistFromExternalDoor,1);
                for(int i = 0 ; i < Apaths.size(); i++){
                    copyMap = new int[N][M];
                    copyMapAtoB(map, copyMap);
                    for(int j = 0 ; j <Apaths.get(i).size(); j++){
                        Point p = Apaths.get(i).get(j);
                        copyMap[p.x][p.y] = 0;
                    }
                    ArrayList<ArrayList<Point>> Bpaths = new ArrayList<>();
                    int[][] BdistFromExternalDoor = new int[N][M];
                    Point BP = prisonerLocation.get(1);
                    int Bcount = bfs(BP.x,BP.y,Bpaths,BdistFromExternalDoor, 0);
                    if(Bcount == 0){
                        minNumOfOpendDoor = Math.min(minNumOfOpendDoor,Apaths.get(i).size());
                    }else {
                        Bpaths = new ArrayList<>();
                        BdistFromExternalDoor = new int[N][M];
                        bfs(BP.x,BP.y,Bpaths,BdistFromExternalDoor, 1);
                        for(int j = 0; j < Bpaths.size(); j++){
                            minNumOfOpendDoor = Math.min(minNumOfOpendDoor,Apaths.get(i).size() + Bpaths.get(j).size());
                        }
                    }
                }
            }
            System.out.println(minNumOfOpendDoor);
        }
    }

    private static int bfs(int x, int y, ArrayList<ArrayList<Point>> paths, int[][] distFromExternalDoor, int sharp) {
        int minCount = 0x3f3f3f3f;
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        ArrayList<Point> escapeLocation = new ArrayList<>();
        visited[x][y] = true;
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.x == 0 || p.x == N-1 || p.y == 0 || p.y == M-1){
                escapeLocation.add(new Point(p.x,p.y));
            }
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0|| nx>=N || ny>=M ||visited[nx][ny]) continue;
                if(sharp == 0){
                    if(copyMap[nx][ny] == 1) continue;
                }
                if(copyMap[nx][ny] == -1) continue;
                distFromExternalDoor[nx][ny] = getDirection(i);
                visited[nx][ny] = true;
                q.offer(new Point(nx,ny));
            }
        }
        for(int i = 0 ; i < escapeLocation.size(); i++){
            int count = 0;
            Point p = escapeLocation.get(i);
            paths.add(new ArrayList<>());
            ArrayList<Point> path = paths.get(i);
            if(copyMap[p.x][p.y] == 1) {
                count++;
                path.add(new Point(p.x,p.y));
            }
            int nx = p.x;
            int ny = p.y;
            while(nx != x || ny != y){
                int dir = distFromExternalDoor[nx][ny];
                nx = nx + dx[dir];
                ny = ny + dy[dir];
                if(copyMap[nx][ny] == 1){
                    count++;
                    path.add(new Point(nx,ny));
                }
            }
            minCount = Math.min(count,minCount);
        }
        return minCount;
    }

    private static int getDirection(int dir) {
        if(dir == 0){
            return 2;
        }else if(dir == 1){
            return 3;
        }else if(dir == 2){
            return 0;
        }else if(dir == 3){
            return 1;
        }
        return -1;
    }

    private static void copyMapAtoB(int[][] map, int[][] copyMap) {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                copyMap[i][j] = map[i][j];
            }
        }
    }

    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

//1
//5 9
//****#****
//*..#.#..*
//****.****
//*$#.#.#$*
//*********

//1
//9 9
//*#**#**#*
//*#**#**#*
//*#**#**#*
//*#**.**#*
//*#*#.#*#*
//*$##*##$*
//*#*****#*
//*.#.#.#.*
//*********

//5 4
//####
//...#
//##.#
//$$.#
//####

//1
//8 4
//####
//...#
//##.#
//...#
//.###
//...#
//$$.#
//####

//1
//9 5
//#####
//....#
//###.#
//#...#
//#.###
//#...#
//###.#
//#$$.#
//#####
