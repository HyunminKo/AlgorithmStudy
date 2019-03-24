import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class 통나무옮기기_dfs_fail_1938 {
    static int N,result=0x3f3f3f3f;
    static int[][] map;
    static boolean[][] visited;
    static HashMap<String,Integer> desMap = new HashMap<>();
    static int[] dx = {-1,-1,-1,1,1,1,0,0};
    static int[] dy = {-1,0,1,-1,0,1,-1,1};
    enum DRT {
        UPLEFT,UP,UPRIGHT,DOWNLEFT,DOWN,DOWNRIGHT,LEFT,RIGHT,TURN
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];
        sc.nextLine();
        ArrayList<Point> current = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            String line = sc.nextLine();
            for(int j = 0 ; j < N; j++){
                if(line.charAt(j) == 'B'){
                    map[i][j] = 2;
                    current.add(new Point(i,j));
                }else if(line.charAt(j) == 'E'){
                    map[i][j] = 3;
                    desMap.put(i+""+j,1);
                }else if(line.charAt(j) == '1'){
                    map[i][j] = 1;
                }
            }
        }
        dfs(0,current,visited);
        if(result == 0x3f3f3f3f){
            System.out.println(0);
        }else {
            System.out.println(result);
        }
    }

    private static void dfs(int count, ArrayList<Point> current, boolean[][] visited) {
        boolean flag = true;
        for(int i = 0 ; i < current.size(); i++){
            Point p = current.get(i);
            visited[p.x][p.y] = true;
            if(!desMap.containsKey(p.x+""+p.y)) flag = false;
        }
        if(count > result) return;
        if(flag){
            result = Math.min(result,count);
            return;
        }
        if(move(DRT.UP,current)){
            dfs(count+1,current, visited);
            unMove(DRT.UP,current);
        }
        if(move(DRT.DOWN,current)){
            dfs(count+1,current, visited);
            unMove(DRT.DOWN,current);
        }
        if(move(DRT.LEFT,current)){
            dfs(count+1,current, visited);
            unMove(DRT.LEFT,current);
        }
        if(move(DRT.RIGHT,current)){
            dfs(count+1,current, visited);
            unMove(DRT.RIGHT,current);
        }
        if(move(DRT.TURN,current)){
            dfs(count+1,current, visited);
            unMove(DRT.TURN,current);
        }


    }

    private static void unMove(DRT drt, ArrayList<Point> current) {
        Point p1 = current.get(0);
        Point p2 = current.get(1);
        Point p3 = current.get(2);
        visited[p1.x][p1.y] = false;
        visited[p2.x][p2.y] = false;
        visited[p3.x][p3.y] = false;
        if(drt == DRT.UP){
            drt = DRT.DOWN;
        } else if( drt == DRT.DOWN){
            drt = DRT.UP;
        }
        if(drt == DRT.LEFT) {
            drt = DRT.RIGHT;
        } else if(drt == DRT.RIGHT) {
            drt = DRT.LEFT;
        }

        if(drt == DRT.UP || drt == DRT.DOWN){
            p1.x += dx[drt.ordinal()];
            p2.x += dx[drt.ordinal()];
            p3.x += dx[drt.ordinal()];
        }else if(drt == DRT.LEFT || drt == DRT.RIGHT){
            p1.y += dy[drt.ordinal()];
            p2.y += dy[drt.ordinal()];
            p3.y += dy[drt.ordinal()];
        }else if(drt == DRT.TURN){
            if(p2.x - 1 == p1.x || p2.x + 1 == p1.x){//세로 모양
                if(p2.x + 1 == p1.x){
                    p1.x -= 1;
                    p1.y += 1;
                    p3.x += 1;
                    p3.y -= 1;
                }else {
                    p1.x += 1;
                    p1.y -= 1;
                    p3.x -= 1;
                    p3.y += 1;
                }
            }else {//가로 모양
                if(p2.y + 1 == p1.y){
                    p1.x -= 1;
                    p1.y -= 1;
                    p3.x += 1;
                    p3.y += 1;
                }else {
                    p1.x += 1;
                    p1.y += 1;
                    p3.x -= 1;
                    p3.y -= 1;
                }
            }
        }

    }

    private static boolean move(DRT drt, ArrayList<Point> current) {
        ArrayList<Point> temp = new ArrayList<>();
        for(Point p : current){
            temp.add(new Point(p.x,p.y));
        }
        Point p1 = temp.get(0);
        Point p2 = temp.get(1);
        Point p3 = temp.get(2);

        if(drt == DRT.UP || drt == DRT.DOWN){
            p1.x += dx[drt.ordinal()];
            p2.x += dx[drt.ordinal()];
            p3.x += dx[drt.ordinal()];
        }else if(drt == DRT.LEFT || drt == DRT.RIGHT){
            p1.y += dy[drt.ordinal()];
            p2.y += dy[drt.ordinal()];
            p3.y += dy[drt.ordinal()];
        }else if(drt == DRT.TURN){
            if(p2.x - 1 == p1.x || p2.x + 1 == p1.x){
                if(canTurn(p2.x,p2.y,0)){ //세로 모양
                    if(p2.x - 1 == p1.x){
                        p1.x += 1;
                        p1.y += 1;
                        p3.x -= 1;
                        p3.y -= 1;
                    }else {
                        p1.x -= 1;
                        p1.y -= 1;
                        p3.x += 1;
                        p3.y += 1;
                    }
                }else {
                    return false;
                }
            }else {
                if(canTurn(p2.x,p2.y,1)) { //가로 모양
                    if(p2.y + 1 == p1.y){
                        p1.x += 1;
                        p1.y -= 1;
                        p3.x -= 1;
                        p3.y += 1;
                    }else {
                        p1.x -= 1;
                        p1.y += 1;
                        p3.x += 1;
                        p3.y -= 1;
                    }
                }else {
                    return false;
                }
            }
        }
        if(!canRange(p1) || !canRange(p2) || !canRange(p3)){
            return false;
        }
        if(isVisited(p1) && isVisited(p2) && isVisited(p3)){
            return false;
        }
        current.clear();
        for(Point p : temp){
            current.add(new Point(p.x,p.y));
        }
        return true;
    }

    private static boolean isVisited(Point p) {
        if(visited[p.x][p.y]) return true;
        return false;
    }

    private static boolean canRange(Point p) {
        boolean flag = true;
        if (p.x < 0 || p.y < 0 || p.x >= N || p.y >= N || map[p.x][p.y] == 1) {
            flag = false;
        }
        return flag;
    }

    private static boolean canTurn(int x, int y, int drt) { // drt:0 세로, drt:1 가로
        boolean flag = true;
        for(int i = 0 ; i < 8; i++){
            if(drt == 0){
                if(i == DRT.UP.ordinal() || i == DRT.DOWN.ordinal()) continue;
            }else if(drt == 1){
                if(i == DRT.LEFT.ordinal() || i == DRT.RIGHT.ordinal()) continue;
            }
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1){
                flag = false;
                break;
            }
        }
        return flag;
    }

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }
}
