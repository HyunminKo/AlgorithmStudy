package samsung;

import java.util.*;

public class NewGame2 {

    static int[][] board;
    static int n;
    static int k;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static Horse[] horses;

    static Map<String, List<Integer>> positionMap = new HashMap<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        board = new int[n][n];
        horses = new Horse[k+1];

        //board input
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = input.nextInt();
            }
        }

        for (int i = 1; i <= k; i++) {
            int row = input.nextInt();
            int col = input.nextInt();
            int dir = input.nextInt();

            row = row - 1;
            col = col - 1;

            String position = row+","+col;

            Horse h = new Horse(row,col,dir,i);
            List<Integer> list = new ArrayList<>();
            list.add(i);
            positionMap.put(position, list);

            horses[i] = h;
        }
        int result = solution();
        System.out.println(result);
    }

    private static int solution() {
        int result = 0;
        boolean check = false;
        while(true) {
            //반복 수 증가.
            result++;

            for (int i = 1; i <= k; i++) {

                //말을 꺼낸다.
                Horse h = horses[i];

                int nr = makeNr(h.row, h.dir);
                int nc = makeNc(h.col, h.dir);

                if(nr < 0 || nc < 0 || nr >= n || nc >= n){
                    if(goBlue(h)){
                        check = true;
                        break;
                    }
                    else{
                        continue;
                    }
                }
                else if(board[nr][nc] == 0){//white
                    goWhite(nr,nc,h);
                }
                else if(board[nr][nc] == 1){//red
                    goRed(nr,nc,h);
                }
                else if(board[nr][nc] == 2){//blue
                    if(goBlue(h)){
                        check = true;
                        break;
                    }
                    else{
                        continue;
                    }
                }
                if(positionMap.get(nr+","+nc).size() >= 4){
                    check = true;
                    break;
                }
            }

            if(check) break;

            if(result > 1000){
                result = -1;
                break;
            }

        }

        return result;
    }

    private static boolean goBlue(Horse h) {
        changeDir(h);

        int nr = makeNr(h.row, h.dir);
        int nc = makeNc(h.col, h.dir);

        if(nr < 0 || nc < 0 || nr >= n || nc >= n){
            return false;
        }
        else if(board[nr][nc] == 0){//white
            goWhite(nr,nc,h);
        }
        else if(board[nr][nc] == 1){//red
            goRed(nr,nc,h);
        }
        else if(board[nr][nc] == 2){//blue
            return false;
        }
        if(positionMap.get(nr+","+nc).size() >= 4){
            return true;
        }

        return false;
    }

    private static void goRed(int nr, int nc, Horse h) {
        String nextPosition = nr+","+nc;
        String nowPosition = h.row+","+h.col;
        List<Integer> nowList = positionMap.get(h.row+","+h.col);

        if(positionMap.containsKey(nextPosition)){
            List<Integer> nextList = positionMap.get(nextPosition);

            moveHorse(nowList, nextList, h, nr, nc, 1);
        }
        else{
            List<Integer> newList = new ArrayList<>();

            //말들을 옮김.
            moveHorse(nowList, newList, h, nr, nc,1);

            //맵에 넣어줌
            positionMap.put(nextPosition, newList);
        }

        /*옮기는 작업이 끝난 후*/
        if(nowList.size() == 0){
            positionMap.remove(nowPosition);
        }
    }

    private static void goWhite(int nr, int nc, Horse h) {
        String nextPosition = nr+","+nc;
        String nowPosition = h.row+","+h.col;
        List<Integer> nowList = positionMap.get(h.row+","+h.col);

        if(positionMap.containsKey(nextPosition)){//exist nextPosition
            List<Integer> nextList = positionMap.get(nextPosition);

            moveHorse(nowList, nextList, h, nr, nc, 0);

        }
        else{// no exist nextPosition
            List<Integer> newList = new ArrayList<>();

            //말들을 옮김.
            moveHorse(nowList, newList, h, nr, nc,0);

            //맵에 넣어줌
            positionMap.put(nextPosition, newList);
        }

        /*옮기는 작업이 끝난 후*/
        if(nowList.size() == 0){
            positionMap.remove(nowPosition);
        }
    }

    private static void moveHorse(List<Integer> nowList, List<Integer> nextList, Horse h, int nr, int nc, int color){
        int idx = -1;
        if(color == 0){//white
            for (int i = 0; i < nowList.size(); i++) {
                if(nowList.get(i) == h.num){//처음 움직이려는 말 찾음.
                    idx = i;
                    nextList.add(nowList.remove(i));
                    h.row = nr;
                    h.col = nc;
                    i--;
                }
                else if(i == idx){//위에 있는 말들 옮김.
                    int horseNum = nowList.remove(i);
                    horses[horseNum].row = nr;
                    horses[horseNum].col = nc;
                    nextList.add(horseNum);
                    i--;
                }
            }
        }
        else if(color == 1){//red
            for (int i = nowList.size()-1; i >= 0; i--) {
                if(nowList.get(i) == h.num){
                    nextList.add(nowList.remove(i));
                    h.row = nr;
                    h.col = nc;
                    break;
                }
                int horseNum = nowList.remove(i);
                horses[horseNum].row =nr;
                horses[horseNum].col =nc;
                nextList.add(horseNum);
            }
        }
    }

    private static void changeDir(Horse h){
        switch(h.dir){
            case 1:
                h.dir = 2;
                break;
            case 2:
                h.dir = 1;
                break;
            case 3:
                h.dir = 4;
                break;
            case 4:
                h.dir = 3;
        }
    }

    private static int makeNr(int row, int dir){
        int nr = 0;
        switch (dir){
            case 1:
                nr = row + dr[0];
                break;
            case 2:
                nr = row + dr[1];
                break;
            case 3:
                nr = row + dr[2];
                break;
            case 4:
                nr = row + dr[3];
                break;
        }
        return nr;
    }

    private static int makeNc(int col, int dir){
        int nc = 0;
        switch (dir){
            case 1:
                nc = col + dc[0];
                break;
            case 2:
                nc = col + dc[1];
                break;
            case 3:
                nc = col + dc[2];
                break;
            case 4:
                nc = col + dc[3];
                break;
        }
        return nc;
    }

    private boolean goBlue2(Horse h){
        changeDir(h);

        int nr = makeNr(h.row, h.dir);
        int nc = makeNc(h.col, h.dir);

        if(nr < 0 || nc < 0 || nr >= n || nc >= n){
            return false;
        }
        else if(board[nr][nc] == 0){//white
            goWhite(nr,nc,h);
        }
        else if(board[nr][nc] == 1){//red
            goRed(nr,nc,h);
        }
        else if(board[nr][nc] == 2){//blue
            return false;
        }
        if(positionMap.get(nr+","+nc).size() >= 4){
            return true;
        }

        return false;
    }

    static class Horse{
        int row;
        int col;
        int dir;
        int num;

        public Horse(int row, int col, int dir, int num){
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.num = num;
        }
    }
}

/*

4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
1 4 1



4 4
1 1 1 1
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 2
1 4 1



5 4
0 0 0 0 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1
1 2 1
1 3 2
1 4 1




4 4
0 0 0 1
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
2 4 3


4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 4
2 1 3
3 1 4
4 1 4


4 4
0 0 2 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
1 4 1
 */