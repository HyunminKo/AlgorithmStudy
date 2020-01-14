package samsung;

import java.util.*;

public class NewGame_3 {

    static int[][] board;
    static int n;
    static int k;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static Horse[] horses;

//    static Queue<Horse> q = new LinkedList<>();
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

//            q.add(h);
            horses[i] = h;
        }
        int result = solution();
        System.out.println(result);
    }

    private static int solution() {
        int result = 0;

        while(true){
            result++;
            boolean check = false;
            for (int i = 1; i <= k; i++) {

//                Horse h = q.poll();
                Horse h = horses[i];
                int nr = makeNr(h.row, h.dir);
                int nc = makeNc(h.col, h.dir);

                if(nr < 0 || nc < 0 || nr >= n || nc >= n){
                    goBlue(h);
                    nr = h.row;
                    nc = h.col;
                }
                else if(board[nr][nc] == 0){
                    goWhite(nr, nc, h);
                }
                else if(board[nr][nc] == 1){
                    goRed(nr, nc, h);
                }
                else{
                    goBlue(h);
                    nr = h.row;
                    nc = h.col;
                }
                if(positionMap.get(nr+","+nc).size() >= 4){
                    check = true;
                    break;
                }
                h.row = nr;
                h.col = nc;
//                q.add(h);

            }
            if(check == true) break;
            //네개가 겹칠 때, 또는 1000이 넘을 때
            if(result > 1000){
                result = -1;
                break;
            }

        }

        return result;
    }

    private static void goBlue(Horse h) {

        changeDir(h);
        int nr = makeNr(h.row, h.dir);
        int nc = makeNc(h.col, h.dir);
        if(nr < 0 || nc < 0 || nr >= n || nc >= n){
            return ;
        }
        int nextColor = board[nr][nc];

        if(nextColor == 0){
            goWhite(nr, nc, h);
        }
        else if(nextColor == 1){
            goRed(nr, nc, h);
        }
        else{
            return ;
        }
    }

    private static void goRed(int nr, int nc, Horse h) {
        String nextPosition = nr+","+nc;
        String nowPosition = h.row + "," + h.col;
        List<Integer> nowList = positionMap.get(nowPosition);

        if(positionMap.containsKey(nextPosition)){//있으면
            List<Integer> nextList = positionMap.get(nextPosition);

            while(true){
                int horseNum = nowList.remove(nowList.size()-1);
                nextList.add(horseNum);
                horses[horseNum].row = nr;
                horses[horseNum].col = nc;

                if(horseNum == h.num) break;
            }
        }
        else{//없으면
            List<Integer> list = new ArrayList<>();

            while(true){
                int horseNum = nowList.remove(nowList.size()-1);
                list.add(horseNum);
                horses[horseNum].row = nr;
                horses[horseNum].col = nc;

                if(horseNum == h.num) break;
            }
            positionMap.put(nr+","+nc, list);
        }

        if(nowList.size() == 0){
            positionMap.remove(nowPosition);
        }
    }

    private static void goWhite(int nr, int nc, Horse h) {
        String nextPosition = nr+","+nc;
        String nowPosition = h.row + "," + h.col;
        List<Integer> nowList = positionMap.get(nowPosition);
        if(positionMap.containsKey(nextPosition)){//있으면
            List<Integer> nextList = positionMap.get(nextPosition);

            int idx = -1;
            for (int i = 0; i < nowList.size(); i++) {
                if(nowList.get(i) == h.num) idx = i;

                if(i == idx) {
                    int horseNum = nowList.remove(i);
                    horses[horseNum].row = nr;
                    horses[horseNum].col = nc;
                    nextList.add(horseNum);
                    i--;
                }
            }

        }
        else{//없으면
            List<Integer> list = new ArrayList<>();
            while(nowList.size() > 0){
                int horseNum = nowList.remove(0);
                list.add(horseNum);
                horses[horseNum].row = nr;
                horses[horseNum].col = nc;
            }
            positionMap.put(nr+","+nc, list);
        }

        if(nowList.size() == 0){
            positionMap.remove(nowPosition);
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
0 0 1 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
1 4 1



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