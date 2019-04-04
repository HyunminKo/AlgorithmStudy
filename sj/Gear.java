package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gear {

    static String[] gears;
    static int K;
    static int score = 0;
    static int[] scores = {1,2,4,8};
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            gears = new String[4];
            String str;
            for(int i = 0; i < 4; i++){
                str = br.readLine();
                gears[i] = str;
            }
            K = Integer.parseInt(br.readLine());

            for(int i = 0; i < K; i++){
                str = br.readLine();
                String[] tokens = str.split(" ");
                rotate(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), 0);
            }

            for(int j = 0; j < gears.length;j++){
                int temp = Character.getNumericValue(gears[j].charAt(0));
                if(temp == 1){
                    score += scores[j];
                }
            }
            System.out.println(score);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //prev = 0 : 처음 도는 것.
    //prev = 1 : 오른쪽 톱니가 바뀌어서 온 것.
    //prev = 2 : 왼쪽 톱니가 바뀌어서 온 것.
    private static void rotate(int gear, int direction, int prev) {
        String thisGear;
        String prevGear;
        switch (prev) {
            case 0:
                thisGear = gears[gear - 1];
                if (direction == 1) {
                    gears[gear - 1] = rotateRight(thisGear);
                } else if (direction == -1) {
                    gears[gear - 1] = rotateLeft(thisGear);
                }
                rotate(gear - 1, direction, 1);
                rotate(gear + 1, direction, 2);
                break;
            case 1:
                if (gear == 0) {//종료조건
                    return;
                }
                thisGear = gears[gear - 1];
                prevGear = gears[gear];
                if (direction == -1) {//이전에 반시계로 돌았으면
                    if (thisGear.charAt(2) != prevGear.charAt(5)) {//극이 다르면
                        gears[gear - 1] = rotateRight(thisGear);
                        rotate(gear - 1, 1, 1);
                    }
                } else {//이전에 시계로 돌았으면
                    if (thisGear.charAt(2) != prevGear.charAt(7)) {//극이 다르면
                        gears[gear - 1] = rotateLeft(thisGear);
                        rotate(gear - 1, -1, 1);
                    }
                }
                break;
            case 2:
                if (gear == 5) {
                    return;
                }
                thisGear = gears[gear - 1];
                prevGear = gears[gear - 2];
                if (direction == -1) {
                    if (thisGear.charAt(6) != prevGear.charAt(1)) {//극이 다르면
                        gears[gear - 1] = rotateRight(thisGear);
                        rotate(gear+1, 1, 2);
                    }
                }
                else{
                    if (thisGear.charAt(6) != prevGear.charAt(3)) {//극이 다르면
                        gears[gear - 1] = rotateLeft(thisGear);
                        rotate(gear+1, -1, 2);
                    }
                }
                break;
        }
    }

    private static String rotateLeft(String gear){//반시계
        char temp = gear.charAt(0);
        String result;
        result = gear.substring(1,8)+temp;
        return result;
    }
    private static String rotateRight(String gear){//시계
        char temp = gear.charAt(7);
        String result;
        result = temp + gear.substring(0,7);
        return result;
    }
}
