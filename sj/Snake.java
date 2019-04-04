package samsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {

    static int n,m,l;
    static int[][] map;
    static MoveInfo[] moveInfos;
    static Queue<Position> q = new LinkedList<>();
    static Queue<Position> snakeQ = new LinkedList<>();
    static boolean[][] visited;
    static int overtime = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];
        m = sc.nextInt();

        for(int i = 0; i<m;i++){
            map[sc.nextInt()-1][sc.nextInt()-1] = 1;
        }

        l = sc.nextInt();
        moveInfos = new MoveInfo[l];

        for(int i =0; i< l;i++){
            moveInfos[i] = new MoveInfo(sc.nextInt(),sc.next());
        }

        q.add(new Position(0,0, 1));
        visited[0][0] = true;
        snakeQ.add(new Position(0, 0));
        bfs();
        System.out.println(overtime);
    }
    static void bfs(){

        int presentTime = 0;
        int idx = 0;
        MoveInfo moveInfo = moveInfos[0];
        int turnTime = moveInfos[0].time;
        String turn = moveInfos[0].d;
        while(!q.isEmpty()){
            Position p = q.poll();

            if(turnTime == presentTime){
                if(turn.equals("L")){
                    p = turnLeft(p);
                }
                else{
                    p = turnRight(p);
                }
                idx++;
                if(idx < moveInfos.length) {
                    turnTime = moveInfos[idx].time;
                    turn = moveInfos[idx].d;
                }
            }

            int nx = p.x + dx[p.d-1];
            int ny = p.y + dy[p.d-1];

            if(nx<0 || ny <0|| nx>= n||ny>=n || visited[nx][ny]){//다음갈 곳이 벽, 꼬리, 몸통 부분이면
                overtime++;
                break;
            }

            if(map[nx][ny] == 1){// 몸 늘려.
                snakeQ.add(new Position(nx, ny));
                map[nx][ny] = 0;
            }
            else{
                snakeQ.add(new Position(nx, ny));
                Position temp = snakeQ.poll();
                visited[temp.x][temp.y] = false;
            }
            visited[nx][ny] = true;
            q.add(new Position(nx, ny, p.d));
            presentTime++;
            overtime++;
        }
    }

    static Position turnRight(Position p){
        switch (p.d){
            case 1:
                p.d =2;
                break;
            case 2:
                p.d =3;
                break;
            case 3:
                p.d =4;
                break;
            case 4:
                p.d =1;
                break;
        }
        return p;
    }

    static Position turnLeft(Position p){
        switch (p.d){
            case 1:
                p.d =4;
                break;
            case 2:
                p.d =1;
                break;
            case 3:
                p.d =2;
                break;
            case 4:
                p.d =3;
                break;
        }
        return p;
    }

    static class Position{
        int x,y, d;

        Position(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class MoveInfo{
        int time;
        String d;

        MoveInfo(int time, String d){
            this.time = time;
            this.d = d;
        }
    }
}
