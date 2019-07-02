package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

public class ArrayAndOper {

    static int r, c, k;
    static int R;
    static int C;
    static int[][] map = new int[3][3];
    static int maxInMap = 0;
    static int result = 0;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        r = input.nextInt();
        c = input.nextInt();
        k = input.nextInt();

        R = 3;
        C = 3;
        for(int i = 0; i< 3; i++){
            for(int j = 0; j< 3; j++){
                int in = input.nextInt();
                map[i][j] = in;
                maxInMap = Math.max(maxInMap,in);
            }
        }


        while(true){

            if(result > 100){
                result = -1;
                break;
            }

            if( r > R || c > C){
                if(R >= C){
                    operR();
                }
                else{
                    operC();
                }
            }
            else{
                if(map[r-1][c-1] == k) break;

                if(R >= C){
                    operR();
                }
                else{
                    operC();
                }
            }
            result++;
        }
        System.out.println(result);
    }

    static void optimize(int r, int c){

        int max = 0;
        int[][] newMap = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j< c; j++){
                newMap[i][j] = map[i][j];
                max = Math.max(newMap[i][j],max);
            }
        }
        maxInMap = max;
    }

    static void operR(){

        int maxCol = 0;

        for(int i = 0; i< R;i++){
            int[] cnt = new int[maxInMap+1];
            for(int j = 0; j< C;j++){// 한 행에 있는 숫자들을 세어.
                cnt[map[i][j]]++;
            }
            PriorityQueue<CountOfNumber> pq = new PriorityQueue<>();
            for(int j = 1; j< cnt.length;j++){
                if(cnt[j] > 0) {// 0보다 큰 것만 q에 넣음.
                    maxInMap = Math.max(maxInMap, cnt[j]);
                    pq.add(new CountOfNumber(j, cnt[j]));
                }
            }

            maxCol = Math.max(maxCol, pq.size()*2);
            resizeCol(pq, i);
        }

        if(maxCol> 100) maxCol = 100;
        if(C > maxCol){
            C = maxCol;
            optimize(R,C);
        }
    }

    static void resizeCol(PriorityQueue<CountOfNumber> pq, int row){
        C = Math.max(pq.size()*2, C);
        if(C > 100) C=100;
        int[][] newMap = new int[R][C];

        for(int i = 0; i< R; i++){
            for(int j = 0; j< C; j++){
                if(i==row){
                    if(!pq.isEmpty()) {
                        CountOfNumber cn = pq.poll();
                        newMap[i][j] = cn.number;
                        j++;
                        if (j < C) newMap[i][j] = cn.count;
                        else{
                            pq.clear();
                            break;
                        }
                    }
                }
                else{
                    if(j >= map[0].length) break;
                    else{
                        newMap[i][j] = map[i][j];
                    }
                }
            }
        }
        map = newMap;
    }

    static void operC(){

        int maxRow = 0;

        for(int i = 0; i< C;i++){
            int[] cnt = new int[maxInMap+1];
            for(int j = 0; j< R;j++){// 한 행에 있는 숫자들을 세어.
                cnt[map[j][i]]++;
            }
            PriorityQueue<CountOfNumber> pq = new PriorityQueue<>();
            for(int j = 1; j< cnt.length;j++){
                if(cnt[j] > 0) {// 0보다 큰 것만 q에 넣음.
                    maxInMap = Math.max(maxInMap, cnt[j]);
                    pq.add(new CountOfNumber(j, cnt[j]));
                }
            }

            maxRow = Math.max(maxRow, pq.size()*2);
            resizeRow(pq, i);
        }

        if(maxRow> 100) maxRow = 100;
        if(R > maxRow){
            R = maxRow;
            optimize(R,C);
        }
    }

    static void resizeRow(PriorityQueue<CountOfNumber> pq, int col){
        R = Math.max(pq.size()*2, R);
        if(R > 100) R=100;
        int[][] newMap = new int[R][C];

        for(int i = 0; i< C; i++){
            for(int j = 0; j< R; j++){
                if(i==col){
                    if(!pq.isEmpty()) {
                        CountOfNumber cn = pq.poll();
                        newMap[j][i] = cn.number;
                        j++;
                        if (j < R) newMap[j][i] = cn.count;
                        else{
                            pq.clear();
                            break;
                        }
                    }
                }
                else{
                    if(j >= map.length) break;
                    else{
                        newMap[j][i] = map[j][i];
                    }
                }
            }
        }
        map = newMap;
    }

    static class CountOfNumber implements Comparable<CountOfNumber>{
        int number;
        int count;

        CountOfNumber(int number, int count){
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(CountOfNumber o) {
            if (this.count == o.count){
                return this.number-o.number;
            }
            else{
                return this.count-o.count;
            }
        }
    }
}
