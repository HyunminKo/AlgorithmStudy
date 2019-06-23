import java.util.*;

public class 낚시왕_17143 {
    static int N,M,S,result=0;
    static int[] dx = {0,-1,1,0,0}; //원점, 위, 아래, 오른쪽, 왼쪽
    static int[] dy = {0,0,0,1,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); S = sc.nextInt();
        List<Shark> sharks = new ArrayList<>();
        for(int i = 0 ; i < S; i++){
            sharks.add(new Shark(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt(),sc.nextInt(),sc.nextInt()));
        }
        go(sharks);
        System.out.println(result);
    }

    private static void go(List<Shark> sharks) {
        for(int i = 0 ; i < M; i++){
            catchShark(sharks,i);
            moveAndEatSharks(sharks);
        }
    }
    private static void catchShark(List<Shark> sharks, int col) {
        Optional<Shark> foundShark = sharks.stream()
                .filter(s -> s.y == col)
                .sorted(Comparator.comparingInt(s -> s.x))
                .findFirst();
        foundShark.ifPresent(shark -> {
            result+=shark.level;
            sharks.remove(shark);
        });
    }

    private static void moveAndEatSharks(List<Shark> sharks) {
        Map<String, Shark> map = new HashMap<>();
        Iterator<Shark> iter = sharks.iterator();
        List<Shark> removeList = new ArrayList<>();
        while(iter.hasNext()){
            Shark shark = iter.next();
            int numOfMove = shark.speed;
            for(int i = 0 ; i < numOfMove; i++){
                moving(shark);
            }
            String key = shark.x + "," + shark.y;
            if(map.containsKey(key)){
                Shark tempShark = map.get(key);
                if(tempShark.level > shark.level){
                    iter.remove();
                }else {
                    map.put(key,shark);
                    removeList.add(tempShark);
                }
            } else {
                map.put(key,shark);
            }
        }
        sharks.removeAll(removeList);
    }

    private static void moving(Shark shark) {
        int nx = shark.x + dx[shark.dir];
        int ny = shark.y + dy[shark.dir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= M){
            shark.dir = changeDir(shark.dir);
            nx = shark.x + dx[shark.dir];
            ny = shark.y + dy[shark.dir];
        }
        shark.x = nx;
        shark.y = ny;
    }

    private static int changeDir(int dir) {
        int result = 0;
        switch (dir){
            case 1:
                result = 2;
                break;
            case 2:
                result = 1;
                break;
            case 3:
                result = 4;
                break;
            case 4:
                result = 3;
                break;
        }
        return result;
    }




    public static class Shark {
        int x,y,speed,dir,level;

        public Shark(int x, int y, int speed, int dir, int level) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", speed=" + speed +
                    ", dir=" + dir +
                    ", level=" + level +
                    '}';
        }
    }
}
