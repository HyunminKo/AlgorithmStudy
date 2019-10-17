import java.util.*;

public class 아기상어_16236 {
    static int N;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int time = 0;
    static Fish shark;
    static Map<Integer,Fish> fishes = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = sc.nextInt();
                if(temp == 0) continue;
                if(temp == 9) {
                    shark = new Fish(i,j,2);
                    shark.count = 0;
                    map[i][j] = -1;
                }else {
                    int id = fishes.size()+1;
                    fishes.put(id,new Fish(i,j,temp));
                    map[i][j] = id;
                }
            }
        }
//        print(map);
        Boolean cantEat;
        while(true) {
            if(fishes.size() == 0) break;
            cantEat = bfs();
            // 먹을 수 있는 고기가 없을때
            if(cantEat) break;
        }
        System.out.println(time);
    }

    private static boolean bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] cost = new int[N][N];
        visited[shark.x][shark.y] = true;
        cost[shark.x][shark.y] = 1;
        q.add(new Point(shark.x,shark.y));
        int minDist = Integer.MAX_VALUE;
        int minFishId = -1;
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i = 0 ; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(cost[p.x][p.y] + 1 > minDist) continue;
                if(map[nx][ny] != 0 && map[nx][ny] != -1) {
                    Fish fish = fishes.get(map[nx][ny]);
                    if(fish.size > shark.size) continue;
                    if(fish.size < shark.size){
                        if(minDist > cost[p.x][p.y] + 1){
                            minDist = cost[p.x][p.y] + 1;
                            minFishId = map[nx][ny];
                        }else if(minDist == cost[p.x][p.y] + 1){
                            Fish minFish = fishes.get(minFishId);
                            if(fish.x < minFish.x){
                                minFishId = map[nx][ny];
                            }else if(fish.x == minFish.x){
                                if(fish.y < minFish.y){
                                    minFishId = map[nx][ny];
                                }
                            }
                        }
                    }
                }
                if(cost[nx][ny] >= cost[p.x][p.y] + 1) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                cost[nx][ny] = cost[p.x][p.y] + 1;
                q.add(new Point(nx,ny));
            }
        }
        if(minFishId == -1) return true;
        Fish minFish = fishes.get(minFishId);
        map[shark.x][shark.y] = 0;
        shark.x = minFish.x;
        shark.y = minFish.y;
        shark.count++;
        if(shark.count == shark.size){
            shark.size++;
            shark.count = 0;
        }
        fishes.remove(minFishId);
        map[shark.x][shark.y] = -1;
        time += minDist-1;
//        print(map);
        return false;
    }

    private static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0 || map[i][j] == -1){
                    System.out.print(map[i][j] + " ");
                }else {
                    System.out.print(fishes.get(map[i][j]).size + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Fish {
        int x,y,size,count;

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    '}';
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
