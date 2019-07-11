package SWExpert;

import java.util.*;

public class LunchTime {

    static int T, N;
    static int[][] map;
    static int stair1_Length;
    static int stair2_Length;
    static int personCnt=0;
    static Stair stair1;
    static Stair stair2;
    static List<Person> list1 = new ArrayList<>();
    static List<Person> list2 = new ArrayList<>();
    static List<Person> personList = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        T = input.nextInt();

        while(T>0){
            N = input.nextInt();
            map = new int[N][N];

            int cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int num = input.nextInt();
                    if(num == 1){
                        personList.add(new Person(i,j));
                    }
                    else if(num > 1){
                        if(cnt == 0){
                            stair1_Length = num;
                            stair1 = new Stair(i,j,num,0);
                            cnt++;
                        }
                        else{
                            stair2_Length = num;
                            stair2 = new Stair(i,j,num,0);
                        }
                    }
                    else{
                        personCnt++;
                    }
                    map[i][j] = num;
                }
            }
            dfs();
            T--;
        }

    }
    private static void dfs(){
        if(list1.size() + list2.size() == personCnt){
            simulation();
            return ;
        }
        for(int i = 0; i<personList.size();i++){
            if(set.contains(i)) continue;
            Person p = personList.get(i);
            list1.add(p);
            set.add(i);
            dfs();
            list1.remove(p);
            set.remove(i);
            list2.add(p);
            set.add(i);
            list2.remove(p);
            set.remove(i);
            dfs();
        }
        simulation();
    }

    private static void simulation(){
        PriorityQueue<Person> pq = new PriorityQueue<>();
        int stair1Result = 0;
        int stair2Result = 0;
        for(int i = 0; i< list1.size();i++) {
            if (i < list1.size()) {
                Person p = list1.get(i);
                p.dist_stair = Math.abs(p.row - stair1.row) + Math.abs(p.col - stair1.col);
                pq.add(p);
            }
        }
//        while(!pq.isEmpty()){
//            System.out.print(pq.poll().dist_stair + " ");
//        }
        while(!pq.isEmpty()) {
            Person p = pq.poll();
        }

    }
    static class Person implements Comparable<Person>{
        int row, col, dist_stair;

        Person(int row, int col){
            this.row =row;
            this.col =col;
        }

        @Override
        public int compareTo(Person o) {
            if(this.dist_stair < o.dist_stair) return -1;
            else{
                return 1;
            }
        }
    }

    static class Stair{
        int row, col, length, cnt;

        Stair(int row, int col, int length, int cnt){
            this.row =row;
            this.col =col;
            this.length = length;
            this.cnt = cnt;
        }
    }
}
