import java.util.ArrayList;
import java.util.Scanner;

public class PuyoPuyo_11559 {
    static int[][] map = new int[12][6];
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i < 12; i++){
            String line = sc.nextLine();
            for(int j = 0 ; j < 6;j++){
                if(line.charAt(j) == 'R'){//1
                    map[i][j] = 1;
                }else if(line.charAt(j) == 'G'){//2
                    map[i][j] = 2;
                }else if(line.charAt(j) == 'B'){//3
                    map[i][j] = 3;
                }else if(line.charAt(j) == 'P'){//4
                    map[i][j] = 4;
                }else if(line.charAt(j) == 'Y'){//5
                    map[i][j] = 5;
                }
            }
        }
        int result = 0;
        while(true){
            boolean[][] visited = new boolean[12][6];
            boolean flag = true;
            for(int i = 0 ; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(!visited[i][j] && map[i][j] != 0){
                        visited[i][j] = true;
                        ArrayList<Point> points = new ArrayList<>();
                        points.add(new Point(i,j));
                        int count = dfs(i,j,visited,points) + 1;
                        if(count >= 4){
                            for(Point p : points){
                                map[p.x][p.y] = 0;
                            }
                            flag = false;
                        }
                    }
                }
            }
            if(flag){
                break;
            }
            down();
            result++;
            printMap();
        }
        System.out.println(result);
    }

    private static void printMap() {
        for(int i = 0 ; i < 12; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < 6 ; j++){
                if(map[i][j]==0){
                    sb.append(".");
                }else if(map[i][j]==1){
                    sb.append("R");
                }else if(map[i][j]==2){
                    sb.append("G");
                }else if(map[i][j]==3){
                    sb.append("B");
                }else if(map[i][j]==4){
                    sb.append("P");
                }else if(map[i][j]==5){
                    sb.append("Y");
                }
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    private static void down() {
        for(int j = 0; j < 6; j++){
            ArrayList<Integer> color = new ArrayList<>();
            for(int i = 11; i >= 0; i--){
                if(map[i][j] != 0){
                    color.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            if(color.size() != 0){
                int index = 11;
                for(int k = 0 ; k < color.size(); k++){
                    map[index--][j] = color.get(k);
                }
            }
        }
    }

    private static int dfs(int x, int y, boolean[][] visited, ArrayList<Point> points) {
        int result = 0;
        for(int i = 0 ; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx>=12 || ny>=6 || map[nx][ny] == 0) continue;
            if(visited[nx][ny]) continue;
            if(map[x][y] != map[nx][ny]) continue;
            visited[nx][ny] = true;
            points.add(new Point(nx,ny));
            result += dfs(nx,ny,visited, points) + 1;
        }
        return result;
    }

    private static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}