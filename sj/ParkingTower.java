package SWExpert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ParkingTower {

    static int TC;
    static int n, m;
    static int[] prices;
    static int[] weight;
    static int[] sequence;
    static PriorityQueue<Integer> pq;
    static Map<Integer, Integer> priceMap;
    static Map<Integer, Integer> parkingMap;

    public static void main(String[] args) {
        int revenue = 0;
        int testNum = 1;
        Scanner sc = new Scanner(System.in);
        TC = sc.nextInt();

        //test case 만큼 반복
        while(TC > 0){
            //입력 받은 후 초기화
            n = sc.nextInt();
            m = sc.nextInt();

            prices = new int[n];
            weight = new int[m];
            sequence = new int[2*m];
            pq = new PriorityQueue<>();
            priceMap = new HashMap<>();
            parkingMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int price = sc.nextInt();
                prices[i] = price;
                pq.add(i);
                priceMap.put(i, price);
            }

            for (int i = 0; i < m; i++) {
                weight[i] = sc.nextInt();
            }

            for (int i = 0; i < 2*m; i++) {
                sequence[i] = sc.nextInt();
            }
            //시뮬레이션 시작
            revenue = simulate();

            //결과 출력
            System.out.println("#"+testNum + " " + revenue);

            testNum++;
            TC--;
            revenue = 0;
        }
    }

    private static int simulate(){

        int revenue = 0;
        int checkingStartIndex = -1;
        boolean[] checkedCar = new boolean[m];//이미 한번 왔다간 차 확인.

        for (int i = 0; i < sequence.length; i++) {
            //차 번호를 받는다.
            int car = sequence[i];
            if(checkedCar[Math.abs(car)-1]) continue;

            //차 번호가 음수면 차를 뺀다.
            if(car < 0){
                int parkingNum = parkingMap.remove(Math.abs(car));
                pq.add(parkingNum);
                checkedCar[Math.abs(car)-1] = true;
                if(checkingStartIndex != -1){//다시 검사해야 할 부분 설정
                    i = checkingStartIndex-1;
                    checkingStartIndex = -1;
                }
            }
            //차 번호가 양수면
            else{
                if(possibleParking()){//주차공간이 있으면
                    int parkingNum = pq.poll();
                    int pay = weight[car-1] * priceMap.get(parkingNum);
                    revenue += pay;
                    parkingMap.put(car, parkingNum);
                }
                else{//주차 공간이 없을 시
                    if(checkingStartIndex == -1){//이미 검사해야할 인덱스가 있으면,
                        checkingStartIndex = i;
                    }
                }
            }
        }

        return revenue;
    }

    private static boolean possibleParking(){
        if(pq.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }


}
/*
    1~n 까지 번호가 매겨진 n개의 추자 공간

운영 방법
1. 차가 도착하면 비어있는 주차 공간을 검사
2. 비어있는 공간이 없다면 공간이 생길 때까지 차량을 대기
3. 빈 주차 공간이 있으면 주차, 주차 가능 공간 중 번호가 가장 작은 주차 공간에 주차하도록 한다.
4. 대기차량이 여러 대면, 입구의 대기장소에서 대기, 새치기 없음

주차요금은 차량의 무게와 주차공간 마다 따로 책정된 단위 무게당 금액을 곱한 가격, 종일권만 판매( 시간 X )

m대의 차량들이 들어오고 나가는 순서를 알고 있다.
총 수입을 계산하는 프로그램을 작성하라



입력
첫 째 줄에는 TC
다음 줄 부터
n m
n개의 줄에  i 번째 주차 공간의 단위 무게당 요금
m 개의 줄에 차량 i의 무게
이 후 2m 개의 줄에 차량들의 주차장 출입 순서가 정수로 주어짐
주어진 정수 x 가 양수면 x 번 차가 들어옴
음수면 나가는 것.


1. 차 번호를 받는다
2. 양수 음수 확인
3. 음수라면 차를 빼고 밀려있던 차부터 다시 확인하도록 index 설정.
4. 양수면 비어 있는지 확인한다.(메서드 따로 빼)
5. 비어 있다면 계산 후 주차 한다.
6. 비어 있지 않다면 자리가 비엇을 경우 확인해야 할 인덱스를 저장 후 다음 차를 가져 옴.

 */