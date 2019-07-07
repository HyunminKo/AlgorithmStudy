package SWExpert;

import java.util.*;

public class Microbial_Isolation {

    static int T, N, M, K;
    static int result = 0;
    static List<Microbe> microbeList = new ArrayList<>();
    static Map<String, PriorityQueue<Microbe>> microbeMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        T = input.nextInt();

        int problemNum = 1;
        while(T > 0){

            N = input.nextInt();
            M = input.nextInt();
            K = input.nextInt();

            for(int i = 0; i< K; i++){
                Microbe microbe = new Microbe();
                for(int j = 0; j < 4; j++){
                    int inValue = input.nextInt();
                    if(j == 0){
                        microbe.row = inValue;
                    }
                    else if(j == 1){
                        microbe.col = inValue;
                    }
                    else if(j == 2){
                        microbe.cnt = inValue;
                    }
                    else{
                        microbe.dir = inValue;
                    }
                }
                microbeList.add(microbe);
            }


            solution();
            microbeList.clear();
            System.out.println("#"+problemNum + " " + result);
            problemNum++;
            T--;
            result = 0;
        }

    }

    private static void solution() {

        for(int i = 0; i< M;i ++){// M만큼 반복
            result = 0;
            for(Microbe m : microbeList){
                m = move(m);//움직여
                String key = m.row + "," + m.col;
                if(microbeMap.containsKey(key)){//키값이 존재 한다면
                    microbeMap.get(key).add(m);
                }
                else{
                    PriorityQueue<Microbe> pq = new PriorityQueue<>();
                    pq.add(m);
                    microbeMap.put(key,pq);
                }
            }
            microbeList.clear();
            for(PriorityQueue<Microbe> pq : microbeMap.values()){

                Microbe m = pq.poll();
                int finalDir = m.dir;
                int finalCnt = m.cnt;

                while(!pq.isEmpty()){
                    finalCnt += pq.poll().cnt;
                }
                result += finalCnt;
                microbeList.add(new Microbe(m.row, m.col, finalCnt, finalDir));
            }
            microbeMap.clear();
        }
    }

    private static Microbe move(Microbe m){

        switch (m.dir){
            case 1:
                m.row = m.row-1;
                if(m.row == 0) {
                    m.dir = 2;
                    m.cnt = (int)Math.floor(m.cnt/2);
                }
                break;
            case 2:
                m.row = m.row+1;
                if(m.row == N-1){
                    m.dir = 1;
                    m.cnt = (int)Math.floor(m.cnt/2);
                }
                break;
            case 3:
                m.col = m.col-1;
                if(m.col == 0){
                    m.dir = 4;
                    m.cnt = (int)Math.floor(m.cnt/2);
                }
                break;
            case 4:
                m.col = m.col+1;
                if(m.col == N-1){
                    m.dir = 3;
                    m.cnt = (int)Math.floor(m.cnt/2);
                }
                break;
        }
        return m;

    }

    static class Microbe implements Comparable<Microbe>{
        int row;
        int col;
        int cnt;
        int dir;// 1: 상, 2:하, 3: 왼, 4:오

        Microbe(){

        }
        Microbe(int row, int col, int cnt, int dir){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.dir = dir;
        }
        @Override
        public int compareTo(Microbe o) {
            if(this.cnt > o.cnt) return -1;
            else{
                return 1;
            }
        }
    }
}
