import java.util.Scanner;

public class 활주로건설 {
    static int N,X,result=0;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <=T; test_case++){
            N = sc.nextInt(); X=sc.nextInt();
            map = new int[N][N];
            for(int i = 0 ; i< N; i++){
                for(int j = 0 ; j < N; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            go();
            System.out.println(String.format("#%d %d",test_case,result));
            result = 0;
        }
    }

    private static void go() {
        calRow();
        calCol();
    }

    private static void calCol() {
        int[] arr = new int[N];
        for(int j = 0 ; j < N; j++){
            for(int i = 0 ; i < N; i++){
                arr[i] = map[i][j];
            }
            building(arr);
        }
    }

    private static void calRow() {
        for(int i = 0 ; i < N; i++){
            building(map[i]);
        }
    }

    private static void building(int[] arr) {
        if(canBuildSlope(arr)) result++;
    }

    private static boolean canBuildSlope(int[] arr) {
        boolean result = false;
        boolean flag = false;
        boolean[] slope = new boolean[N];
        for(int i = 0 ; i < N-1;){
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff == 1){
                if(arr[i] > arr[i+1]){// shape: [\]
                    if(i + X >= N) {
                        flag = true;
                        break;
                    }
                    int height = arr[i+1];

                    for(int j = 1; j <= X-1; j++){ //경사로 x 길이만큼 같은 높이인지 체크
                        if(arr[i+1+j] != height) {
                            flag = true;
                            break;
                        }
                    }

                    for(int j = i+1; j <= i+X; j++){ //경사로 세우기
                        slope[j] = true;
                    }

                    if(flag) break;
                    i = i + X;
                }else { // shape: [/]
                    if(i - X + 1 < 0){
                        flag = true;
                        break;
                    }
                    int height = arr[i];

                    for(int j = 1; j <= X-1; j++){
                        if(arr[i-j] != height){
                            flag = true;
                            break;
                        }
                    }
                    for(int j = i; j > i-X; j--){ //경사로 세우기
                        if(slope[j]){
                            flag = true;
                            break;
                        }
                        slope[j] = true;
                    }
                    if(flag) break;
                    i = i + 1;
                }
            }else if(diff > 1){
                flag = true;
                break;
            }else {
                i = i + 1;
            }
        }
        if(!flag){
            result = true;
        }
        return result;
    }
}
