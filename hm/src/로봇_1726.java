import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 로봇_1726 {
    static int N, M, result = 0x3f3f3f3f;
    static int[][] map;
    static boolean[][] visited;
    static int[][][] distance;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    enum DIR {
        UP, RIGHT, DOWN, LEFT;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];
        distance = new int[N][M][1];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                map[i][j] = sc.nextInt();
                distance[i][j][0] = Integer.MAX_VALUE;
            }
        }
        Robot pointer = new Robot(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
        Robot target = new Robot(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
        bfs(pointer,target);
        printMap();
        System.out.println(result);
    }

    private static void bfs(Robot s, Robot t) {
        Queue<Robot> q = new LinkedList<>();
        distance[s.x][s.y][0] = 0;
        q.offer(new Robot(s.x,s.y,s.d,s.numOfOperation, s.cost));
        while(!q.isEmpty()){
            Robot p = q.poll();
            if(p.x == t.x && p.y == t.y){
                p.numOfOperation += findNextDirection(p.d,t.d.ordinal());
                result = Math.min(result,p.numOfOperation);
                distance[p.x][p.y][0] = result;
                //printMap();
            }
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M  || map[nx][ny] == 1) continue;
                int count = findNextDirection(p.d,i); //방향 찾기 명령 수
                Robot newRobot = new Robot(nx,ny,DIR.values()[i],p.numOfOperation,p.cost);
                if(count == 0){ //방향이 같을 때
                    if(newRobot.cost == 0){ //3번 전진했을 때
                        newRobot.cost = 3;
                        newRobot.numOfOperation += 1;
                    }else {
                        if(newRobot.cost == 3){ //방향이 같고 처음 전진할 때
                            newRobot.numOfOperation += 1;
                        }
                        newRobot.cost--;
                    }
                }else { //방향이 다를 때
                    newRobot.numOfOperation += count;
                    newRobot.numOfOperation += 1; // 움직임 명령
                    newRobot.cost = 2;
                }
                if(distance[nx][ny][0] < newRobot.numOfOperation) continue;
                distance[nx][ny][0] = newRobot.numOfOperation;
                q.offer(newRobot);
                ////printMap();
            }
        }
    }

    private static void printMap() {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++){
                if(distance[i][j][0] == Integer.MAX_VALUE){
                    System.out.print("INF\t");
                }
                else {
                    System.out.print(distance[i][j][0] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int findNextDirection(DIR d, int next) {
        if(d.ordinal() == next) return 0;
        int c1 = 4, c2 = 4;
        for(int i = 1 ; i < 4; i++){
            if((d.ordinal()+i) % 4 == next){
                c1 = i;
                break;
            }
        }
        for(int i = 1 ; i < 4; i++){
            if(d.ordinal()-i < 0){
                if(4 + (d.ordinal()- i) == next){
                    c2 = i;
                    break;
                }
            }else {
                if(d.ordinal()-i == next){
                    c2 = i;
                    break;
                }
            }
        }
        return Math.min(c1,c2);
    }

    private static class Robot {
        int x,y;
        DIR d;
        int numOfOperation = 0;
        int cost = 3;
        public Robot(int x, int y, int d) {
            this.x = x;
            this.y = y;
            if(d == 1)
                this.d = DIR.RIGHT;
            else if(d == 2)
                this.d = DIR.LEFT;
            else if(d == 3)
                this.d = DIR.DOWN;
            else if(d == 4)
                this.d = DIR.UP;
        }

        public Robot(int x , int y, DIR d, int numOfOperation, int cost) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.numOfOperation = numOfOperation;
            this.cost = cost;
        }
    }
}
//T1
//5 2
//0 0
//0 1
//0 1
//0 1
//0 0
//5 2 2
//1 2 1

//6

//T2
//3 3
//0 0 0
//0 0 0
//0 0 0
//1 1 3
//3 2 3

//4

//T3
//3 3
//0 0 0
//0 0 0
//0 0 0
//1 1 3
//3 2 4

//4

//T4
//3 3
//0 0 0
//0 0 0
//0 0 0
//1 1 3
//3 2 2

//5

//T5
//3 3
//0 0 0
//0 0 0
//0 0 0
//1 1 3
//3 2 1

//3

//T6
//3 3
//0 0 0
//0 0 0
//0 0 0
//1 1 3
//3 3 1

//3

//T7
//5 6
//0 0 0 0 0 0
//0 1 1 1 1 1
//0 1 0 1 0 0
//0 0 0 1 1 0
//0 1 0 0 0 0
//1 1 1
//3 5 3

//13