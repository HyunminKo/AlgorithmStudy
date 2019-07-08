package baekjoon;

import java.util.Scanner;

public class StoneBridge {

    static String[] target;
    static String targetString;
    static int N;
    static int result = 0;

    public static void main(String[] args) {

        String[] devil;
        String[] engel;

        Scanner input = new Scanner(System.in);

        String line;

        line = input.nextLine();
        target = line.split("");
        targetString = line;
        line = input.nextLine();
        devil = line.split("");
        line = input.nextLine();
        engel = line.split("");

        N = devil.length;

        solution(devil, engel, 0,0, 0, "");
        solution(devil, engel, 0,1, 0, "");
        System.out.println(result);
    }

    private static void solution(String[] devil, String[] engel, int idx, int select, int targetIdx, String resultString){

        for(int i = idx; i < N;i++){
            if(resultString.equals(targetString)){
                result++;
                return ;
            }
            if(select == 0){
                if(devil[i].equals(target[targetIdx])){
                    resultString += devil[i];
                    if(i == N-1){
                        solution(devil, engel, i, 1, targetIdx+1, resultString);
                    }
                    else{
                        solution(devil, engel, i+1, 1, targetIdx+1, resultString);
                    }

                    resultString = resultString.substring(0,resultString.length()-1);
                }
            }
            else{
                if(engel[i].equals(target[targetIdx])){
                    resultString += engel[i];
                    if(i == N-1){
                        solution(devil, engel, i, 0, targetIdx+1,resultString);
                    }
                    else{
                        solution(devil, engel, i+1, 0, targetIdx+1,resultString);
                    }
                    resultString = resultString.substring(0,resultString.length()-1);
                }
            }
        }

    }
}
