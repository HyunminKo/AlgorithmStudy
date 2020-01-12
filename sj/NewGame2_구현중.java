package samsung;

import java.util.*;

public class NewGame2 {
    static int n;
    static int k;
    static int overlapCnt = 0;

    static int[][] board;
    static Horse[][] horseBoard;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static Map<String, List<Horse>> horseMap = new HashMap<>();
    static Queue<Horse> q = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        board = new int[n][n];
        horseBoard = new Horse[n][n];

        //색 구분 체스판 초기화
        for(int i = 0; i < n; i++){
            for(int j = 0; j< n; j++){
                board[i][j] = sc.nextInt();
            }
        }

        //말 위치 초기화
        for(int i = 1; i<= k;i++){
            int row = sc.nextInt();
            int col = sc.nextInt();
            int dir = sc.nextInt();

            row = row-1;
            col = col-1;

            Horse h = new Horse(row, col, dir, i);
            String position = row+","+col;

            if(horseMap.containsKey(position)){
                horseMap.get(position).add(h);
            }
            else{
                List<Horse> list = new ArrayList<>();
                list.add(h);
                horseMap.put(position, list);
            }
            q.add(h);
        }

        int result = solution();
        System.out.println(result);
    }

    private static int solution() {
        int result = 0;

        while(true){
            //모든 말을 한 번씩 움직임.
            for (int i = 0; i < k; i++) {
                Horse h = q.poll();
                int nr = makeNr(h.row, h.dir);
                int nc = makeNc(h.col, h.dir);

                if(nr < 0 || nc < 0 || nr >=n || nc >= n){
                    goBlue(h);
                    continue;
                }
                int nextPosition = board[nr][nc];

                if(nextPosition == 0){
                    goWhite(h, nr, nc);
                }
                else if(nextPosition == 1){
                    goRed(h);
                }
                else{
                    goBlue(h);
                }


                q.add(h);
            }

            result++;

            //종료 조건
            if(result > 1000) {
                result = -1;
                break;
            }
            if(overlapCnt == 4){
                break;
            }
        }
        return result;
    }

    private static void goWhite(Horse h, int nr, int nc) {
        String np = nr+","+nc;
        if(horseMap.containsKey(np)){//다음 자리에 있음

        }
        else{// 다음 자리에 없음.

            h.row = nr;
            h.col = nc;
        }
    }

    private static void goRed(Horse h) {

    }

    private static void goBlue(Horse h) {

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
                nr = row + dr[4];
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
                nc = col + dc[4];
                break;
        }
        return nc;
    }

    static class Horse{
        int row;
        int col;
        int dir;
        int horseNum;
        String horses;

        public Horse(int row, int col, int dir, int number){
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.horseNum = number;
            horses = String.valueOf(number);
        }
    }
}

/*


4 4
0 0 2 0
0 0 1 0
0 0 1 2
0 2 0 0
2 1 1
3 2 3
2 2 1
4 1 2

-1

4 4
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0
1 1 1
1 2 1
1 3 1
1 4 1

1

n x n
말의 개수 k
하나의 말 위에 다른 말 올리기 가능
체스판 흰,빨,파
k개를 놓고 시작
1~k 번호 매겨져 있고, 이동 방향 정해져 있어(위,아래,왼,오)
턴 한번 : 1~k까지 순서대로 이동, 올려져 있는말도 같이 이동
턴이 진행되던 중에 말이 4개 쌓이는 순간 게임 종료

a가 움직일 때 이동 칸이
- 흰색인 경우 그 칸으로 이동, 이미 있는 경우 가장위에 올려 놓는다.
 -> a번 위에 다른말이 있으면 함께 이동
 -> a,b,c 가 이동 하려는데 d,e 가 있으면 d,e,a,b,c

- 빨간색인 경우 이동 후 a와 위에 모든 말의 쌓인 순서를 바꾼다.
 -> a,b,c가 이동하고, 이동하려는 칸에 없으면 c,b,a
 -> a,d,f,g 이동, 이동 칸에 e,c,b 있으면, e,c,b,g,f,d,a

- 파란색인 경우 a의 이동방향을 반대로 하고 한칸 이동, 이방향 바꾼 후 이동 칸이 파란색이면 이동 X, 방향만 바꿈.

- 체스판을 벗어나는 경우는 파란색과 같은 경우

* 게임이 종료되는 턴의 번호를 구하라.

입력
첫 줄 n, 말의 개수 k
둘 째 부터 n개의 줄에 대한 정보, 칸의 수는 색
0은 흰색, 1은 빨강, 2는 파랑
다음 k개의 줄에 말의 정보가 1번 말부터 주어짐
세 개의 정수 : 행, 열, 이동방향
1: 오른
2: 왼
3: 위
4: 아래


말이 가지고 있는 것
그 자리에 쌓인 것.
 */