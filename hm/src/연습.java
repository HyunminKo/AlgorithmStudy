public class 연습 {
    public static void main(String[] args) {
        go(0,"");
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
