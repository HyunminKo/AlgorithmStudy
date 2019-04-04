import java.util.ArrayList;
import java.util.Scanner;

public class 점심식사시간 {
    static int T,result=0x3f3f3f3f;
    static int[][] map;
    static ArrayList<Stair> stairs;
    static ArrayList<Person> people;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        int count = 1;
        while(T-- > 0){
            int N = sc.nextInt();
            map = new int[N][N];
            stairs = new ArrayList<>();
            people = new ArrayList<>();
            for(int i = 0 ; i < N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                    if(map[i][j] == 1) {
                        people.add(new Person(i,j,-1,-1,-1));
                    }else if(map[i][j] >= 2){
                        stairs.add(new Stair(i,j,map[i][j]));
                    }
                }
            }
            for(int i = 0 ; i <= (1 << people.size()) - 1; i++){
                ArrayList<Person> copyPeople = new ArrayList<>();
                ArrayList<Stair> copyStairs = new ArrayList<>();
                for(int j = 0 ; j < people.size(); j++){
                    Person p = people.get(j);
                    copyPeople.add(new Person(p.x,p.y,-1,-1,-1));
                }
                for(int j = 0 ; j < stairs.size(); j++){
                    Stair s = stairs.get(j);
                    copyStairs.add(new Stair(s.x,s.y,s.len));
                }
                go(i,copyPeople,copyStairs);
            }
            System.out.println(String.format("#%d %d",count++,result));
            result = 0x3f3f3f3f;
        }
    }

    private static void go(int set, ArrayList<Person> people, ArrayList<Stair> stairs) {
        for(int i = 0; i < people.size(); i++){
            int stair = ( set >> i ) & 1;
            Stair s = stairs.get(stair);
            Person p = people.get(i);
            p.distanceFromStair = Math.abs(p.x - s.x) + Math.abs(p.y - s.y);
            p.lenOfDown = s.len + 1;
            p.stair = stair;
        }
        int goal = people.size();
        int time = 0;
        while(goal > 0){
            for(int i = 0 ; i < people.size(); i++){
                Person p = people.get(i);
                if(p.status == 2) continue; //완료
                if(p.distanceFromStair > 0){
                    p.distanceFromStair--;
                    continue;
                }else if(p.distanceFromStair == 0){ //계단 입구 도착
                    if(p.status == 0){
                        Stair s = stairs.get(p.stair);
                        if(s.count > 0){
                            s.count--;
                            p.lenOfDown--;
                            p.status = 1;
                        }
                    }else if(p.status == 1){
                        Stair s = stairs.get(p.stair);
                        p.lenOfDown--;
                        if(p.lenOfDown == 0){
                            p.status = 2;
                            s.count++;
                            if(s.count == 1){
                                int index = findWaitingPerson(people,i);
                                if(index> 0){
                                    Person temp = people.get(index);
                                    temp.lenOfDown--;
                                    temp.status = 1;
                                    if(temp.lenOfDown == 0) {
                                        temp.status = 2;
                                        goal--;
                                    }else {
                                        s.count--;
                                    }
                                }
                            }
                            goal--;
                        }
                    }
                }
            }
            time++;
        }
        result = Math.min(result,time);
    }

    private static int findWaitingPerson(ArrayList<Person> people, int index) {
        for(int i = 0 ; i < index; i++){
            Person p  = people.get(i);
            if(p.status == 0){
                return i;
            }
        }
        return -1;
    }

    private static class Person {
        int x,y,distanceFromStair,lenOfDown,stair,status; //status: 0 계단 아님, 1 내려가는중, 2 완료

        public Person(int x, int y, int distanceFromStair, int lenOfDown, int stair) {
            this.x = x;
            this.y = y;
            this.distanceFromStair = distanceFromStair;
            this.lenOfDown = lenOfDown;
            this.stair = stair;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "distanceFromStair=" + distanceFromStair +
                    ", lenOfDown=" + lenOfDown +
                    ", stair=" + stair +
                    ", status=" + status +
                    '}';
        }
    }
    private static class Stair {
        int x,y,len,count=3;

        public Stair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
}



