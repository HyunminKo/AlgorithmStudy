package baekjoon;

import java.util.*;

public class CCTV {

    static int N,M;
    static int[][] map;
    static int zeroCnt = 0;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Queue<Position> q = new LinkedList<>();
        Stack<Position> s = new Stack<>();
        N = input.nextInt();
        M = input.nextInt();
        map = new int[N][M];

        for(int i = 0; i <N; i++){
            for(int j = 0; j < M; j++){
                int num = input.nextInt();
                if(num != 0 && num != 6){
//                    q.add(new Position(i,j,num));
                    s.add(new Position(i,j,num));
                }
                if(num == 0){
                    zeroCnt++;
                }
                map[i][j] = num;
            }
        }
        System.out.println("Zero : " + zeroCnt);
        solution(s);
        System.out.println("result : " + result);
        System.out.println("check : "+ check);
    }

    /*
    private static void solution(Queue<Position> q) {

        while(!q.isEmpty()){
            Position p = q.poll();
            for(int i = 0; i < 4; i++){
                if(p.cctvNum == 1){
                    cctv1(p, i, true);
                    if(q.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }else{
                        solution(q);
                        cctv1(p, i, false);
                    }

                }
                else if(p.cctvNum == 2){
                    if(i > 1) break;
                    cctv2(p,i, true);
                    if(q.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    solution(q);
                    cctv2(p,i, false);
                }
                else if(p.cctvNum == 3){
                    cctv3(p,i, true);
                    if(q.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    solution(q);
                    cctv3(p,i, false);
                }
                else if(p.cctvNum == 4){
                    cctv4(p,i, true);
                    if(q.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    solution(q);
                    cctv4(p,i, false);
                }
                else{
                    cctv5(p, true);
                    if(q.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    solution(q);
                    cctv5(p, false);
                    break;
                }

            }
        }
    }
*/

    /*
    private static void solution(Stack<Position> s) {

        while(!s.isEmpty()){
            Position p = s.pop();
            for(int i = 0; i < 4; i++){
                if(p.cctvNum == 1){
                    cctv1(p, i, true);
                    if(s.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }else{
                        solution(s);
                        s.add(p);
                    }
                    cctv1(p, i, false);

                }
                else if(p.cctvNum == 2){
                    if(i > 1) break;
                    cctv2(p,i, true);
                    if(s.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }else{
                        solution(s);
                        s.add(p);
                    }
                    cctv2(p,i, false);
                }
                else if(p.cctvNum == 3){
                    cctv3(p,i, true);
                    if(s.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    else{
                        solution(s);
                        s.add(p);
                    }
                    cctv3(p,i, false);

                }
                else if(p.cctvNum == 4){
                    cctv4(p,i, true);
                    if(s.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }else{
                        solution(s);
                        s.add(p);
                    }
                    cctv4(p,i, false);
                }
                else{
                    cctv5(p, true);
                    if(s.isEmpty()){
                        System.out.println("result zeroCnt : " + zeroCnt);
                        result = Math.min(result, zeroCnt);
                    }
                    else{
                        solution(s);
                        s.add(p);
                    }
                    cctv5(p, false);
                    break;
                }

            }
        }
    }
*/
    static int check = 0;
    private static void solution(Stack<Position> s) {

        if(s.isEmpty()){
            check++;
            System.out.println("result zeroCnt : " + zeroCnt);
            print();
            result = Math.min(result, zeroCnt);
            return ;
        }
        else{
            Position p = s.pop();
            if(p.cctvNum == 1){
                for(int i = 0; i<4; i++){
                    cctv1(p, i, true);
                    solution(s);
                    cctv1(p, i, false);
                }
                s.add(p);
            }
            else if(p.cctvNum == 2){
                for(int i = 0; i< 2; i++){
                    cctv2(p,i, true);
                    solution(s);
                    cctv2(p,i,false);
                }
                s.add(p);
            }
            else if(p.cctvNum == 3){
                for(int i = 0; i<4; i++){
                    cctv3(p, i, true);
                    solution(s);
                    cctv3(p, i, false);
                }
                s.add(p);
            }
            else if(p.cctvNum == 4){
                for(int i = 0; i<4; i++){
                    cctv4(p,i,true);
                    solution(s);
                    cctv4(p, i, false);
                }
                s.add(p);
            }
            else{
                cctv5(p,true);
                solution(s);
                cctv5(p,false);
            }
        }

    }

    private static void cctv1(Position p, int idx, boolean flag){
        switch (idx){
            case 0 :// 오른
                if(flag) right(p);
                else recoveryRight(p);
                break;
            case 1:// 아래
                if(flag) down(p);
                else recoveryDown(p);
                break;
            case 2:// 왼
                if(flag) left(p);
                else recoveryLeft(p);
                break;
            case 3:// 위
                if(flag) up(p);
                else recoveryUp(p);
                break;
        }
    }
    private static void cctv2(Position p, int idx, boolean flag){
        switch (idx){
            case 0 :// 좌우
                if(flag) {
                    left(p);
                    right(p);
                } else{
                    recoveryLeft(p);
                    recoveryRight(p);
                }
                break;
            case 1:// 위아래
                if(flag) {
                    up(p);
                    down(p);
                } else{
                    recoveryUp(p);
                    recoveryDown(p);
                }
                break;
        }
    }
    private static void cctv3(Position p, int idx, boolean flag){
        switch (idx){
            case 0 :// 위, 오른
                if(flag) {
                    up(p);
                    right(p);
                }else{
                    recoveryUp(p);
                    recoveryRight(p);
                }
                break;
            case 1://오른, 아래
                if(flag) {
                    right(p);
                    down(p);
                }else{
                    recoveryRight(p);
                    recoveryDown(p);
                }
                break;
            case 2://아래, 왼
                if(flag) {
                    down(p);
                    left(p);
                }else{
                    recoveryDown(p);
                    recoveryLeft(p);
                }
                break;
            case 3://왼, 위
                if(flag) {
                    left(p);
                    up(p);
                }else{
                    recoveryLeft(p);
                    recoveryUp(p);
                }
                break;
        }
    }
    private static void cctv4(Position p, int idx, boolean flag){
        switch (idx){
            case 0 :// 왼 위 오
                if(flag) {
                    left(p);
                    up(p);
                    right(p);
                }else{
                    recoveryLeft(p);
                    recoveryUp(p);
                    recoveryRight(p);
                }
                break;
            case 1:// 위 오 아
                if(flag) {
                    up(p);
                    right(p);
                    down(p);
                }else{
                    recoveryUp(p);
                    recoveryRight(p);
                    recoveryDown(p);
                }
                break;
            case 2:// 오 아 왼
                if(flag) {
                    right(p);
                    down(p);
                    left(p);
                }else{
                    recoveryRight(p);
                    recoveryDown(p);
                    recoveryLeft(p);
                }
                break;
            case 3:// 아 왼 위
                if(flag) {
                    down(p);
                    left(p);
                    up(p);
                }else{
                    recoveryDown(p);
                    recoveryLeft(p);
                    recoveryUp(p);
                }
                break;
        }
    }
    private static void cctv5(Position p, boolean flag){
        if(flag) {
            right(p);
            left(p);
            up(p);
            down(p);
        }else{
            recoveryRight(p);
            recoveryLeft(p);
            recoveryUp(p);
            recoveryDown(p);
        }
    }

    private static void right(Position p){
        int tmp;
        for(int i = p.col+1; i<N;i++){
            tmp = map[p.row][i];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0) || tmp < 0) continue;
            map[p.row][i] = -1;
            zeroCnt--;
        }
    }
    private static void left(Position p){
        int tmp;
        for(int i = p.col-1; i >=0; i--){
            tmp = map[p.row][i];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0)|| tmp < 0) continue;
            map[p.row][i] = -1;
            zeroCnt--;
        }
    }
    private static void up(Position p){
        int tmp;
        for(int i = p.row-1; i>=0; i--){
            tmp = map[i][p.col];
            if(tmp == 6) break;
            if((tmp > 0 && tmp < 6)|| tmp < 0) continue;
            map[i][p.col] = -1;
            zeroCnt--;
        }
    }
    private static void down(Position p){
        int tmp;
        for(int i = p.row+1; i< N; i++){
            tmp = map[i][p.col];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0) || tmp < 0) continue;
            map[i][p.col] = -1;
            zeroCnt--;
        }
    }

    private static void recoveryRight(Position p){
        int tmp;
        for(int i = p.col+1; i<N;i++){
            tmp = map[p.row][i];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0)||tmp == 0) continue;
            map[p.row][i] = 0;
            zeroCnt++;
        }
    }
    private static void recoveryLeft(Position p){
        int tmp;
        for(int i = p.col-1; i >=0; i--){
            tmp = map[p.row][i];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0)|| tmp == 0) continue;
            map[p.row][i] = 0;
            zeroCnt++;
        }
    }
    private static void recoveryUp(Position p){
        int tmp;
        for(int i = p.row-1; i>=0; i--){
            tmp = map[i][p.col];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0)|| tmp == 0) continue;
            map[i][p.col] = 0;
            zeroCnt++;
        }
    }
    private static void recoveryDown(Position p){
        int tmp;
        for(int i = p.row+1; i< N; i++){
            tmp = map[i][p.col];
            if(tmp == 6) break;
            if((tmp < 6 && tmp > 0)|| tmp == 0) continue;
            map[i][p.col] = 0;
            zeroCnt++;
        }
    }

    private static void print(){
        for(int i = 0; i< N; i++){
            for(int j = 0; j <M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static class Position{
        int row, col, cctvNum;

        Position(int row, int col, int cctvNum){
            this.row = row;
            this.col = col;
            this.cctvNum = cctvNum;
        }
    }
}
