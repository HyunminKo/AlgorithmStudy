package baekjoon;


import java.util.*;

public class ByeDust {

    static int r;
    static int c;
    static int t;
    static int result = 0;
    static AirCleaner airCleaner;

    static Map<String, Dust> dustMap = new HashMap<>();
    static List<String> dustList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        r = input.nextInt();
        c = input.nextInt();
        t = input.nextInt();

        int flag = 0;
        int ux = 0;
        int uy = 0;
        int dx = 0;
        int dy = 0;

        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                int tmp = input.nextInt();
                if(tmp == -1){
                    if(flag == 0){
                        ux = i;
                        uy = j;
                        flag = 1;
                    }else{
                        dx = i;
                        dy = j;
                    }
                }else if(tmp > 0){
                    dustMap.put(i+","+j,new Dust(i,j,tmp));
                    dustList.add(i+","+j);
                }
//                home[i][j] = tmp;
            }
        }
        airCleaner = new AirCleaner(ux,uy,dx,dy);
        for(int i =0;i<t;i++) {
            circulate();
            dustList.clear();
            for(String str : dustMap.keySet()){
                dustList.add(str);
            }
        }
        for(Dust d : dustMap.values()){
            result+= d.size;
        }
        System.out.println(result);
    }

    static void circulate(){

        for(int i = 0; i<dustList.size();i++){
            Dust dust = dustMap.get(dustList.get(i));
            dust.diffusion(r,c, dustMap, airCleaner);
        }
        for(Dust dust : dustMap.values()){//확산된 값 더해주기.
            if(dust.diff > 0){
                dust.size = dust.size + dust.diff;
                dust.diff = 0;
                dustMap.put(dust.x+","+dust.y, dust);
            }
        }
//        print();
        //청정기 가동
        airCleaner.upClean(dustMap, r, c);
        airCleaner.downClean(dustMap, r, c);
//        print();

    }
/*
    static void print(){
        int[][] map = new int[r][c];

        for(Dust d : dustMap.values()){
            map[d.x][d.y] = d.size;
        }

        for(int i = 0; i<r;i++){
            for(int j = 0; j <c; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
    */
}

class Dust{

    int x,y,size,diff;
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};

    public Dust(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.diff = 0;
    }
    public void diffusion(int r, int c, Map<String,Dust> m, AirCleaner ac){

        int difCnt = 0;
        int newDust = this.size/5;

        if(newDust == 0) return;

        for(int i = 0; i< 4; i++){
            int nx = this.x + dx[i];
            int ny = this.y + dy[i];

            if(nx < 0 || ny < 0 || nx >= r || ny >= c ||
                    (nx == ac.upX&&ny==ac.upY)||(nx == ac.downX&&ny == ac.downY)) continue;

            if(m.containsKey(nx+","+ny)){//다음자리에 먼지가 있으면
                Dust tmp = m.get(nx+","+ny);
                tmp.diff = tmp.diff + newDust;
            }
            else{
                m.put(nx+","+ny,new Dust(nx,ny,newDust));
            }
            difCnt++;
        }
        this.size = this.size - (this.size/5)*difCnt;
        m.put(this.x+","+this.y,this);
    }
}
class AirCleaner{

    int upX, upY;
    int downX, downY;

    public AirCleaner(int upX, int upY, int downX, int downY){
        this.upX = upX;
        this.upY = upY;
        this.downX = downX;
        this.downY = downY;
    }

    public void upClean(Map<String,Dust> m,int r,int c){

        int x = this.upX;
        int y = this.upY+1;
        int direction = 1;// 1 : 오른쪽, 2: 위, 3:왼, 4: 아래

        String tmpStr = x+","+y;
        Dust tmpDust = null;
        Dust nextDust;

        while(!tmpStr.equals(this.upX+","+this.upY)){
            if((y == c-1&&x==this.upX) || (y == 0 && x== 0 )|| (x == 0 && y==c-1)){
                direction = ChangeD(direction);
            }
            if(m.containsKey(x+","+y)){
                if(tmpDust != null){
                    nextDust = m.get(x+","+y);
                    tmpDust.x = x;
                    tmpDust.y = y;
                    m.put(x+","+y, tmpDust);
                    tmpDust = nextDust;
                }
                else {//null이면
                    tmpDust = m.get(x + "," + y);
                    m.remove(x + "," +y);
                }

            }
            else{
                if(tmpDust != null){
                    tmpDust.x = x;
                    tmpDust.y = y;
                    m.put(x+","+y, tmpDust);
                    tmpDust = null;
                }
            }
            switch(direction){
                case 1:
                    y++;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    y--;
                    break;
                case 4:
                    x++;
                    break;
            }
            tmpStr = x+","+y;
        }

    }

    public void downClean(Map<String,Dust> m,int r,int c){
        int x = this.downX;
        int y = this.downY+1;
        int direction = 1;// 1 : 오른쪽, 2: 아래, 3:왼, 4: 위

        String tmpStr = x+","+y;
        Dust tmpDust = null;
        Dust nextDust;

        while(!tmpStr.equals(this.downX+","+this.downY)){
            if((y == c-1&&x==this.downX) || (y == 0 && x== r-1 )|| (x == r-1 && y==c-1)){
                direction = ChangeD(direction);
            }
            if(m.containsKey(x+","+y)){
                if(tmpDust != null){
                    nextDust = m.get(x+","+y);
                    tmpDust.x = x;
                    tmpDust.y = y;
                    m.put(x+","+y, tmpDust);
                    tmpDust = nextDust;
                }
                else {//null이면
                    tmpDust = m.get(x + "," + y);
                    m.remove(x + "," +y);
                }

            }
            else{
                if(tmpDust != null){
                    tmpDust.x = x;
                    tmpDust.y = y;
                    m.put(x+","+y, tmpDust);
                    tmpDust = null;
                }
            }
            switch(direction){
                case 1:
                    y++;
                    break;
                case 2:
                    x++;
                    break;
                case 3:
                    y--;
                    break;
                case 4:
                    x--;
                    break;
            }
            tmpStr = x+","+y;
        }

    }

    public int ChangeD(int direction){
        int d = 0;
        switch(direction){
            case 1:
                d = 2;
                break;
            case 2:
                d = 3;
                break;
            case 3:
                d = 4;
                break;
        }
        return d;
    }
}
/*
미세먼지
1. 초마다 먼지가 확산. 먼지는 네방향으로 확산
2. 확산할때 공기청정기 있거나 칸 없으면 노
3. 확산 될 때 5로 나누고 소수점 버려
4. 남은 양은 (남은양 - 남은양/5 * 확산 횟수)


공기 청정기
1. 위쪽 바람 반시계 순환, 아래쪽 공기 시계방향 순환
2. 바람이 불면 미세먼지가 바람의 방향대로 한칸씩 이동
3. 공기청정기로 들어간 미세먼지는 모두 정화


7 8 1
0 0 0 0 0 0 0 9
0 0 0 0 3 0 0 8
-1 0 5 0 0 0 22 0
-1 8 0 0 0 0 0 0
0 0 0 0 0 10 43 0
0 0 5 0 15 0 0 0
0 0 40 0 0 0 20 0
 */