package SWExpert;

import java.util.*;

public class HoneyPicking {

    static int T,N,M,C;
    static int[][] map;
    static boolean[][] visited;
    static int MaxOfHoney1;
    static int MaxOfHoney2;
    static Map<String, List<Integer>> honeyBottleMap = new HashMap<>();
    static Set<String> checkSet = new HashSet<>();
    static int result = 0;
    public static void main(String[] args) {

        int problem = 1;
        Scanner input = new Scanner(System.in);
        T = input.nextInt();

        while(T > 0){

            N = input.nextInt();
            M = input.nextInt();
            C = input.nextInt();
            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i< N; i++){
                for (int j =0 ; j< N; j++){
                    map[i][j] = input.nextInt();
                }
            }

            possibleHoneyBottle();
            MaxOfHoney1 = 0;
            MaxOfHoney2 = 0;
            solution();
            T--;
            System.out.println("#"+problem + " " + result);
            result = 0;
            honeyBottleMap.clear();
            checkSet.clear();
            problem++;
        }
    }

    private static void possibleHoneyBottle(){
        //가능한 꿀통 다 가져와.
        for(int i = 0; i< N; i++){
            for(int j = 0; j< N-(M-1); j++){
                String key = "";
                List<Integer> list = new ArrayList<>();
                for(int k = j; k<(j+M); k++){
                    if(k == j) key = Integer.toString(i)+Integer.toString(k);
                    else key += "-" + Integer.toString(i)+Integer.toString(k);
                    list.add(map[i][k]);
                }
                honeyBottleMap.put(key, list);
            }
        }
    }
    private static void solution(){
        for(String key : honeyBottleMap.keySet()){
            MaxOfHoney1 = 0;
            List<Integer> firstHoney = honeyBottleMap.get(key);
            boolean[] tmp = new boolean[M];

            calculate(firstHoney, 0, 0, tmp, 1);//honey1 계산

            for(String secondKey : honeyBottleMap.keySet()){
                MaxOfHoney2 = 0;
                if(key.equals(secondKey)) continue;

                if(checkSet.contains(key.substring(0,2) + secondKey.substring(0,2))) continue;

                String[] arry = secondKey.split("-");
                if(key.contains(arry[0]) || key.contains(arry[arry.length-1])) continue;
                List<Integer> secondHoney = honeyBottleMap.get(secondKey);
                tmp = new boolean[M];

                calculate(secondHoney, 0, 0, tmp, 2);

                result = Math.max(result, MaxOfHoney1 + MaxOfHoney2);

                checkSet.add(key.substring(0,2) + secondKey.substring(0,2));
                checkSet.add(secondKey.substring(0,2) + key.substring(0,2));

            }
        }
    }

    private static void calculate(List<Integer> honeyList, int maxValue, int sum, boolean[] tmp, int flag){
        for(int i = 0; i <honeyList.size(); i++){
            if(tmp[i]) continue;
            if(sum + honeyList.get(i) > C){
                continue;
            }
            else{
                int amount = honeyList.get(i);
                int price = amount*amount;
                sum += honeyList.get(i);
                tmp[i] = true;
                maxValue += price;
                if(flag == 1) MaxOfHoney1 = Math.max(MaxOfHoney1, maxValue);
                else MaxOfHoney2 = Math.max(MaxOfHoney2, maxValue);
                calculate(honeyList, maxValue, sum, tmp, flag);
                sum -= honeyList.get(i);
                maxValue -= price;
                tmp[i] = false;
            }
        }
    }

    private static void newCalculate(List<Integer> honeyList, int maxValue, int sum, boolean[] tmp, int flag){
        for(int i = 0; i< M; i++){
            if(tmp[i]) continue;
            if(sum+honeyList.get(i) > C){
                continue;
            }
            else{
                int amount = honeyList.get(i);
                int price = amount*amount;
                sum += amount;
                tmp[i] = true;
                maxValue += price;
                if(flag == 1) MaxOfHoney1 = Math.max(MaxOfHoney1, maxValue);
                else MaxOfHoney2 = Math.max(MaxOfHoney2, maxValue);

            }
        }
    }

}
