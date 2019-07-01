import java.util.*;

public class 이차원배열과연산_17140 {
    static int r,c,k,time=0;
    static int[][] map = new int[101][101];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt() ; c = sc.nextInt(); k = sc.nextInt();
        for(int i = 0 ; i < 3; i++){
            for(int j = 0 ; j < 3; j++){
                map[i][j] = sc.nextInt();
            }
        }
        go();
        System.out.println(time);
    }

    private static void go() {
        while(time <= 100){
            if(map[r-1][c-1]==k){
                break;
            }
            int rowCount = calcRow(); //행의 개수, 밑으로
            int colCount = calcCol(); //열의 개수, 옆으로

            if(rowCount>=colCount){ //R 연산, 옆으로
                operationOfRow(rowCount,colCount);
            }else { // C연산, 아래로
                operationOfCol(rowCount,colCount);
            }
            time++;
        }
        if(time == 101){
            time = -1;
        }
    }

    private static void operationOfCol(int rowCount, int colCount) {
        for(int j = 0 ; j < colCount; j++){
            Map<Integer,Integer> counter = new HashMap<>();
            for(int i = 0 ; i < rowCount; i++){
                if(map[i][j] == 0) continue;
                int key = map[i][j];
                if(counter.containsKey(key)){
                    counter.put(key,counter.get(key)+1);
                }else {
                    counter.put(key,1);
                }
            }
            List<Item> basket = new ArrayList<>();
            counter.forEach((k,v) -> basket.add(new Item(k,v)));
            basket.sort(
                    Comparator.comparingInt(Item::getCount)
                            .thenComparingInt(Item::getNum));
            int index = 0;
            for(int i = 0 ; i < basket.size() && index <101 ; i++){
                Item item = basket.get(i);
                if(index + 1 < 101){
                    map[index++][j] = item.getNum();
                }else {
                    index++;
                    continue;
                }
                if(index + 1 < 101){
                    map[index++][j] = item.getCount();
                }else {
                    index++;
                    continue;
                }
            }
            for(int i = index ; i < rowCount; i++){
                map[i][j] = 0;
            }
        }
    }

    private static void operationOfRow(int rowCount, int colCount) {
        for(int i = 0 ; i < rowCount; i++){
            Map<Integer,Integer> counter = new HashMap<>();
            for(int j = 0 ; j< colCount; j++){
                if(map[i][j] == 0) continue;
                int key = map[i][j];
                if(counter.containsKey(key)){
                    counter.put(key,counter.get(key)+1);
                }else {
                    counter.put(key,1);
                }
            }
            List<Item> basket = new ArrayList<>();
            counter.forEach((k,v) -> basket.add(new Item(k,v)));
            basket.sort(
                    Comparator.comparingInt(Item::getCount)
                              .thenComparingInt(Item::getNum));
            int index = 0;
            for(int j = 0 ; j < basket.size() && index <101 ; j++){
                Item item = basket.get(j);
                if(index + 1 < 101){
                    map[i][index++] = item.getNum();
                }else {
                    index++;
                    continue;
                }
                if(index + 1 < 101){
                    map[i][index++] = item.getCount();
                }else {
                    index++;
                    continue;
                }
            }
            for(int j = index ; j < colCount; j++){
                map[i][j] = 0;
            }
        }
    }

    private static int calcCol() {
        int result = 0;
        for(int i = 0 ; i < 101; i++){
            int count = 0;
            for(int j = 0 ; j< 101; j++){
                if(map[i][j]!=0){
                    count++;
                    continue;
                }else if(map[i][j] == 0){
                    break;
                }
            }
            result = Math.max(result,count);
        }
        return result;
    }

    private static int calcRow() {
        int result = 0;
        for(int j = 0 ; j < 101; j++){
            int count = 0;
            for(int i = 0 ; i <101; i++){
                if(map[i][j]!=0){
                    count++;
                    continue;
                }else if(map[i][j] == 0){
                    break;
                }
            }
            result = Math.max(result, count);
        }
        return result;
    }
    static class Item {
        int num,count;
        public Item(int num, int count) {
            this.num = num;
            this.count = count;
        }
        public int getNum() {
            return num;
        }
        public int getCount() {
            return count;
        }
    }
}
