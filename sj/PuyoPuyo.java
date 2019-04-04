package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PuyoPuyo {

    static String[][] map;
    static boolean[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int flag = 0;
    static int flag2 = 0;
    static int result = 0;
    static Queue<Position> q = new LinkedList<>();
    static ArrayList<Position> deleteList = new ArrayList<>();
    public static void main(String[] args) {

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            map = new String[12][6];
            visited = new boolean[12][6];
            String line;

            for(int i = 0; i< 12;i++){
                line = br.readLine();
                for(int j = 0; j< 6; j++){
                    map[i][j] = Character.toString(line.charAt(j));
                }
            }

            while(flag2 == 0) {
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 6; j++) {
                        if (!map[i][j].equals(".") && !visited[i][j]){
                            q.add(new Position(i, j));
                            visited[i][j] = true;
                            bfs();
                        }
                    }
                }
                if(flag == 1){
                    reArrange();
                    result++;
                    flag = 0;
                    visited = new boolean[12][6];
                    deleteList.clear();
                }
                else{
                    flag2 = 1;
                }

            }
            System.out.print(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static void bfs(){
        ArrayList<Position> sameList = new ArrayList<>();
        while(!q.isEmpty()) {
            Position p = q.poll();
            sameList.add(p);
            String color = map[p.x][p.y];
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0|| ny < 0|| nx >= 12|| ny>= 6) continue;
                if(map[nx][ny].equals(".")) continue;
                if(visited[nx][ny]) continue;

                if(map[nx][ny].equals(color)){
                    q.add(new Position(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }
        if(sameList.size() >= 4){
            flag = 1;
            Position p;
            for(int i = 0; i< sameList.size();i++){
                p = sameList.get(i);
                map[p.x][p.y] = "d";
                deleteList.add(p);
            }
            sameList.clear();
        }
    }

    static void print(){
        for(int i = 0; i< 12;i++){
            for(int j =0; j< 6;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void reArrange(){
        Collections.sort(deleteList);
        for(Position p : deleteList){
            for(int i = p.x; i >= 0; i--){
                if(map[i][p.y].equals(".")) break;
                if( i == 0) map[i][p.y] = ".";
                else{
                    map[i][p.y] = map[i-1][p.y];
                }
            }
        }
    }
    static class Position implements Comparable<Position> {
        int x, y;

        @Override
        public int compareTo(Position o) {
            if(this.x < o.x){
                 return -1;
            }
            else if(this.x > o.x){
                return 1;
            }
            else{
                return 0;
            }
        }

        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG.

......
......
......
......
......
......
......
......
.Y....
.YGG..
RRYG..
RRYGG.

......
......
......
......
......
......
......
GGGG..
GBBG..
GGGG..
GYYG..
GGGG..



......
......
......
......
..BY..
..BY..
..YB..
GGGY..
GBBG..
GGGG..
GYYG..
GGGG..



......
......
......
......
..BY..
..BB..
..YY..
GGGY..
GBBG..
GGGG..
GYYG..
GGGG..


.B
.BY
.YY
BBB
YYY

*/