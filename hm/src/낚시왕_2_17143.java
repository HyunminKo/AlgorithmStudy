import java.util.*;

public class 낚시왕_2_17143 {
    static int N,M,C,result=0;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static Map<Integer,Shark> sharks = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); C = sc.nextInt();
        map = new int[N][M];

        for(int i = 0 ; i < C; i++){
            int id  = sharks.size()+1;
            int x = sc.nextInt()-1; int y = sc.nextInt()-1;
            map[x][y] = id;
            sharks.put(id,new Shark(id,x,y,sc.nextInt(),sc.nextInt()-1,sc.nextInt()));
        }
        for(int col = 0 ; col < M ; col++){
            catchShark(col);
            moveShark();
        }
        System.out.println(result);
    }

    private static void moveShark() {
        map = new int[N][M];
        List<Integer> removeSharkID = new ArrayList<>();
        for(Integer id : sharks.keySet()) {
            Shark shark = sharks.get(id);
            int returnedVal;
            if(shark.dir == 0 || shark.dir == 1){
                returnedVal = (N - 1) * 2;
            }else {
                returnedVal = (M - 1) * 2;
            }
            int repeat = shark.speed % returnedVal;
            for(int i = 0 ; i < repeat; i++){
                int dir = shark.dir;
                if(dir == 0 || dir == 1) { // 상,하
                    if(dir == 0){ // 상
                        if(shark.x + dx[dir] < 0) shark.dir = 1;
                    }else if(dir == 1){ // 하
                        if(shark.x + dx[dir] >= N) shark.dir = 0;
                    }
                    shark.x += dx[shark.dir];
                }else if(dir == 2 || dir == 3){ //우, 좌
                    if(dir == 2){ //우
                        if(shark.y + dy[dir] >= M) shark.dir = 3;
                    }else if(dir == 3){ //좌
                        if(shark.y + dy[dir] < 0) shark.dir = 2;
                    }
                    shark.y += dy[shark.dir];
                }
            }
            if(map[shark.x][shark.y] != 0){
                Shark existShark = sharks.get(map[shark.x][shark.y]);
                if(existShark.size > shark.size){
                    removeSharkID.add(shark.id);
                }else {
                    removeSharkID.add(existShark.id);
                    map[shark.x][shark.y] = shark.id;
                }
            }else {
                map[shark.x][shark.y] = shark.id;
            }
        }
        for(int key : removeSharkID){
            sharks.remove(key);
        }
    }

    private static void catchShark(int col) {
        for (int i = 0; i < N; i++) {
            if(map[i][col] != 0){
                Shark shark = sharks.get(map[i][col]);
                result += shark.size;
                sharks.remove(map[i][col]);
                break;
            }
        }
    }
    private static class Shark {
        int id,x,y,size,speed,dir;

        public Shark(int id, int x, int y, int speed, int dir, int size) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
            this.dir = dir;
        }
    }
}
