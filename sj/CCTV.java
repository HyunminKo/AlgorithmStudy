package baekjoon;

import java.util.*;

public class CCTV {

    static int N, M;
    static int[][] map;
    static int zeroCnt = 0;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Stack<Position> s = new Stack<>();
        N = input.nextInt();
        M = input.nextInt();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = input.nextInt();
                if (num != 0 && num != 6) {
                    s.add(new Position(i, j, num));
                }
                if (num == 0) {
                    zeroCnt++;
                }
                map[i][j] = num;
            }
        }
        solution(s);
        System.out.println(result);
    }

    static int check = 0;

    private static void solution(Stack<Position> s) {

        if (s.isEmpty()) {
            result = Math.min(result, zeroCnt);
            return;
        } else {
            Position p = s.pop();
            if (p.cctvNum == 1) {
                Set<String> tmpSet = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    cctv1(p, i, tmpSet);
                    solution(s);
                    recovery(tmpSet);
                    tmpSet.clear();
                }
                s.add(p);
            } else if (p.cctvNum == 2) {
                Set<String> tmpSet = new HashSet<>();
                for (int i = 0; i < 2; i++) {
                    cctv2(p, i, tmpSet);
                    solution(s);
                    recovery(tmpSet);
                    tmpSet.clear();
                }
                s.add(p);
            } else if (p.cctvNum == 3) {
                Set<String> tmpSet = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    cctv3(p, i, tmpSet);
                    solution(s);
                    recovery(tmpSet);
                    tmpSet.clear();
                }
                s.add(p);
            } else if (p.cctvNum == 4) {
                Set<String> tmpSet = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    cctv4(p, i, tmpSet);
                    solution(s);
                    recovery(tmpSet);
                    tmpSet.clear();
                }
                s.add(p);
            } else {
                Set<String> tmpSet = new HashSet<>();
                cctv5(p, tmpSet);
                solution(s);
                recovery(tmpSet);
                tmpSet.clear();

                s.add(p);
            }
        }

    }

    private static void cctv1(Position p, int idx, Set<String> tmpSet) {
        switch (idx) {
            case 0:// 오른
                right(p, tmpSet);
                break;
            case 1:// 아래
                down(p, tmpSet);
                break;
            case 2:// 왼
                left(p, tmpSet);
                break;
            case 3:// 위
                up(p, tmpSet);
                break;
        }
    }

    private static void cctv2(Position p, int idx, Set<String> tmpSet) {
        switch (idx) {
            case 0:// 좌우

                left(p, tmpSet);
                right(p, tmpSet);

                break;
            case 1:// 위아래

                up(p, tmpSet);
                down(p, tmpSet);

                break;
        }
    }

    private static void cctv3(Position p, int idx, Set<String> tmpSet) {
        switch (idx) {
            case 0:// 위, 오른

                up(p, tmpSet);
                right(p, tmpSet);

                break;
            case 1://오른, 아래

                right(p, tmpSet);
                down(p, tmpSet);

                break;
            case 2://아래, 왼

                down(p, tmpSet);
                left(p, tmpSet);

                break;
            case 3://왼, 위

                left(p, tmpSet);
                up(p, tmpSet);

                break;
        }
    }

    private static void cctv4(Position p, int idx, Set<String> tmpSet) {
        switch (idx) {
            case 0:// 왼 위 오
                left(p, tmpSet);
                up(p, tmpSet);
                right(p, tmpSet);
                break;
            case 1:// 위 오 아
                up(p, tmpSet);
                right(p, tmpSet);
                down(p, tmpSet);
                break;
            case 2:// 오 아 왼
                right(p, tmpSet);
                down(p, tmpSet);
                left(p, tmpSet);
                break;
            case 3:// 아 왼 위
                down(p, tmpSet);
                left(p, tmpSet);
                up(p, tmpSet);
                break;
        }
    }
    private static void cctv5(Position p, Set<String> tmpSet) {
        right(p, tmpSet);
        left(p, tmpSet);
        up(p, tmpSet);
        down(p, tmpSet);
    }

    private static void right(Position p, Set<String> tmpSet) {
        int tmp;
        for (int i = p.col + 1; i < M; i++) {
            tmp = map[p.row][i];
            if (tmp == 6) break;
            if ((tmp < 6 && tmp > 0) || tmp < 0) continue;
            map[p.row][i] = -1;
            tmpSet.add(p.row + "," + i);
            zeroCnt--;
        }
    }

    private static void left(Position p, Set<String> tmpSet) {
        int tmp;
        for (int i = p.col - 1; i >= 0; i--) {
            tmp = map[p.row][i];
            if (tmp == 6) break;
            if ((tmp < 6 && tmp > 0) || tmp < 0) continue;
            map[p.row][i] = -1;
            tmpSet.add(p.row + "," + i);
            zeroCnt--;
        }
    }

    private static void up(Position p, Set<String> tmpSet) {
        int tmp;
        for (int i = p.row - 1; i >= 0; i--) {
            tmp = map[i][p.col];
            if (tmp == 6) break;
            if ((tmp > 0 && tmp < 6) || tmp < 0) continue;
            map[i][p.col] = -1;
            tmpSet.add(i + "," + p.col);
            zeroCnt--;
        }
    }

    private static void down(Position p, Set<String> tmpSet) {
        int tmp;
        for (int i = p.row + 1; i < N; i++) {
            tmp = map[i][p.col];
            if (tmp == 6) break;
            if ((tmp < 6 && tmp > 0) || tmp < 0) continue;
            map[i][p.col] = -1;
            tmpSet.add(i + "," + p.col);
            zeroCnt--;
        }
    }

    private static void recovery(Set<String> tmpSet) {
        for (String str : tmpSet) {
            int row = Integer.parseInt(String.valueOf(str.charAt(0)));
            int col = Integer.parseInt(String.valueOf(str.charAt(2)));
            map[row][col] = 0;
            zeroCnt++;
        }
    }

    private static class Position {
        int row, col, cctvNum;

        Position(int row, int col, int cctvNum) {
            this.row = row;
            this.col = col;
            this.cctvNum = cctvNum;
        }
    }
}
