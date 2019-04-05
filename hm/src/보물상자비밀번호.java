import java.util.*;

public class 보물상자비밀번호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt(); int K = sc.nextInt();
            sc.nextLine();
            String str = sc.nextLine();
            Set<String> set = new HashSet<>();
            int lenOfSide = N / 4;
            for(int i = 0 ; i < lenOfSide; i++){
                if(i != 0) {
                    String endChar = String.valueOf(str.charAt(str.length()-1));
                    str = endChar + str.substring(0,str.length()-1);
                }
                for(int j = 1 ; j <= N; j++){
                    if(j % lenOfSide == 0){
                        set.add(str.substring(j-lenOfSide,j));
                    }
                }
            }
            ArrayList<String> passwords = new ArrayList<>(set);
            Collections.sort(passwords,Collections.reverseOrder());
            System.out.println(String.format("#%d %d",test_case,Integer.parseInt(passwords.get(K-1),16)));
        }
    }
}
