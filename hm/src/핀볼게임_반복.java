import java.util.*;

public class 핀볼게임_반복 {

    static int N,result,startX,startY;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static Map<Integer, ArrayList<Point>> wormholeInfo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++){
            N = sc.nextInt();
            map = new int[N][N];
            wormholeInfo = new HashMap<>();
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                    if(map[i][j] > 5){ //웜홀일경우
                        if(!wormholeInfo.containsKey(map[i][j])){
                            wormholeInfo.put(map[i][j],new ArrayList<>());
                        }
                        wormholeInfo.get(map[i][j]).add(new Point(i,j));
                    }
                }
            }
            go();
            System.out.println(String.format("#%d %d",test_case,result));
            result = 0;
        }
    }

    private static void go() {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j] == 0){
                    startX = i;
                    startY = j;
                    for(int k = 0 ; k < 4; k++){
                        move(i,j,k,0);
                    }
                }
            }
        }
    }

    private static void move(int x, int y, int dir, int count) {
        while(true){
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(isOutOfRange(nx,ny)){
                count++;
                dir = getOppositeDir(dir);
                x = nx;
                y = ny;
            } else {
                int type = map[nx][ny];
                if( (nx == startX && ny == startY) || type == -1) {
                    result = Math.max(result,count);
                    break;
                }
                if(type == 0) {
                  x = nx;
                  y = ny;
                } else if(type <= 5){
                    count++;
                    dir = getNextDir(type,dir);
                    x = nx;
                    y = ny;
                }else {
                    List<Point> wormholeListByKey = wormholeInfo.get(type);
                    for(Point p : wormholeListByKey) {
                        if(nx == p.x && ny == p.y) continue;
                        x = p.x;
                        y = p.y;
                    }
                }
            }
        }

    }
    private static boolean isOutOfRange(int x ,int y){
        boolean result = false;
        if(x < 0 || y < 0 || x >= N || y >= N){
            result = true;
        }
        return result;
    }
    private static int getNextDir(int type, int dir) {
        int result;

        if(type == 1){
            if(dir == 2){
                result = 1;
            }else if(dir == 3){
                result = 0;
            }else {
                result = getOppositeDir(dir);
            }
        }else if(type == 2){
            if(dir == 0){
                result = 1;
            }else if(dir == 3){
                result = 2;
            }else {
                result = getOppositeDir(dir);
            }
        }else if(type == 3){
            if(dir == 1){
                result = 2;
            }else if(dir == 0){
                result = 3;
            }else {
                result = getOppositeDir(dir);
            }
        }else if(type == 4){
            if(dir == 2){
                result = 3;
            }else if(dir == 1){
                result = 0;
            }else {
                result = getOppositeDir(dir);
            }
        }else {
            result = getOppositeDir(dir);
        }

        return result;
    }

    private static int getOppositeDir(int dir) {
        int result = -1;
        switch (dir){
            case 0:
                result = 2;
                break;
            case 1:
                result = 3;
                break;
            case 2:
                result = 0;
                break;
            case 3:
                result = 1;
                break;
        }
        return result;
    }

    private static class Point {
        int x,y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


//1
//10
//0 1 0 3 0 0 0 0 7 0
//0 0 0 0 -1 0 5 0 0 0
//0 4 0 0 0 3 0 0 2 2
//1 0 0 0 1 0 0 3 0 0
//0 0 3 0 0 0 0 0 6 0
//3 0 0 0 2 0 0 1 0 0
//0 0 0 0 0 1 0 0 4 0
//0 5 0 4 1 0 7 0 0 5
//0 0 0 0 0 1 0 0 0 0
//2 0 6 0 0 4 0 0 0 4
