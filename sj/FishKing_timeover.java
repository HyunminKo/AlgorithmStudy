package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;

public class FishKing {

    static int _R;
    static int _C;
    static int _M;
    static int result = 0;
    static PriorityQueue<Shark>[] sharks;
    static PriorityQueue<Shark>[] sharks2;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        _R = input.nextInt();
        _C = input.nextInt();
        _M = input.nextInt();

        sharks = new PriorityQueue[_C];
        sharks2 = new PriorityQueue[_C];
        for(int i = 0; i <_C;i++){
            sharks[i] = new PriorityQueue<Shark>();
            sharks2[i] = new PriorityQueue<Shark>();
        }
        int r,c,s,d,z;
        for(int i = 0; i<_M; i++){
            r =input.nextInt();
            c =input.nextInt();
            s =input.nextInt();
            d =input.nextInt();
            z =input.nextInt();
            Shark shark = new Shark(r,c,s,d,z);
            sharks[c-1].add(shark);

        }

        fishing();
        System.out.println(result);
    }

    static void fishing(){

        //step 1: 사람을 옮긴다
        for(int i = 0; i<_C;i++){
            //step 2: 열에서 가장 가까운거 잡는다.
            if(!sharks[i].isEmpty()){//열에 물고기가 있으면 잡는다.
                result += sharks[i].poll().size;
            }
            //step 3: 상어를 옮긴다.
            for(int j = 0; j<sharks.length;j++){
                PriorityQueue<Shark> sharksQ = sharks[j];
                for(Shark shark : sharksQ){
                    shark.move();
                    //같은게 있으면 큰게 먹는다.
                    shark.eat();
                }
            }

            init();
        }

    }

    static void init(){
        sharks = sharks2;
        sharks2 = new PriorityQueue[_C];
        for(int i = 0; i <_C;i++){
            sharks2[i] = new PriorityQueue<Shark>();
        }
    }

    static class Shark implements Comparable<Shark>{

        int r,c;
        int speed;
        int direction;
        // 1: up, 2: down, 3: right, 4:left
        int size;

        public Shark(int r, int c, int speed, int direction, int size){
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public void eat(){
            PriorityQueue<Shark> sharksQ = sharks2[this.c-1];
            PriorityQueue<Shark> newSharksQ = new PriorityQueue<>();

            int size = sharksQ.size();
            if(size == 0) newSharksQ.add(this);
            else {
                for (int i = 0; i < size; i++) {
                    Shark shark = sharksQ.poll();
                    if (shark.r != this.r) {
                        newSharksQ.add(shark);
                    } else {//같은 위치면
                        if (shark.size > this.size) {
                            newSharksQ.add(shark);
                        }

                        else{
                            newSharksQ.add(this);
                        }
                    }
                }
                if(newSharksQ.size() == size) newSharksQ.add(this);
            }
            sharks2[this.c-1] = newSharksQ;
        }

        public void move(){

            for(int i = 0; i < this.speed;i++){
                if((this.r <=1 && this.direction == 1)|| (this.r >=_R && this.direction == 2)||
                        (this.c <=1 && this.direction==4)|| (this.c >=_C && this.direction == 3)){//끝에 다다르면,
                    this.turn();
                }
                this.go();
            }

        }

        public void turn(){
            switch(this.direction){
                case 1:
                    this.direction = 2;
                    break;
                case 2:
                    this.direction = 1;
                    break;
                case 3:
                    this.direction = 4;
                    break;
                case 4:
                    this.direction = 3;
                    break;
            }
        }
        public void go(){
            switch(this.direction){
                case 1:
                    this.r = this.r-1;
                    break;
                case 2:
                    this.r = this.r+1;
                    break;
                case 3:
                    this.c = this.c+1;
                    break;
                case 4:
                    this.c = this.c-1;
                    break;
            }
        }

        @Override
        public int compareTo(Shark o) {
            if(this.r < o.r) return -1;
            else return 1;
        }
    }
}
/*


4 6 8
4 1 3 3 8
1 3 5 2 9
2 4 8 4 1
4 5 0 1 4
3 3 1 2 7
1 5 8 4 3
3 6 2 1 2
2 2 2 3 5

 */