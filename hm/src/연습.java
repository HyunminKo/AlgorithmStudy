public class 연습 {
    public static void main(String[] args) {
//        go(0,"");
//        String[] str = "12 2".split(" ");
//        System.out.println(str[0]);
//        System.out.println(str[1]);
        for(int k = 1; k <= 1000; k++){
            System.out.println(k + " "+(k*k + (k-1)*(k-1)));
        }
    }

    private static void go(int count, String str) {
        if(count >= 2){
            System.out.println(str);
            return;
        }
        for(int i = 0 ; i < 2; i++){
            String temp = str + i;
            go(count+1,temp);
        }
    }
}
