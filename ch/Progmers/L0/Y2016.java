public class Y2016
{
    public static void main(String[] args) {
        String a = solution(5,24);
        System.out.println(a);
    }

    public static String solution(int a, int b) {
        String answer = "";
        int[][] c = new int[13][32];
        int d = 1; // 1:금 , 2:토 , 3:일 , 4:월 , 5:화 , 6:수 , 7:목

        for(int i = 1 ; i < 13; i++) {
            if(i==1||i==3||i==5||i==7||i==8||i==10||i==12){
                for(int j = 1 ; j < 32; j++) {
                    c[i][j] = d++;
                    if(d == 8)
                        d = 1;
                }
            }else if(i==4||i==6||i==9||i==11){
                for(int j = 1 ; j < 31; j++) {
                    c[i][j] = d++;
                    if(d == 8)
                        d = 1;
                }
            }else{
                for(int j = 1 ; j < 30; j++) {
                    c[i][j] = d++;
                    if(d == 8)
                        d = 1;
                }
            }
        }
        if(c[a][b]==1){
            answer = "FRI";
        } else if(c[a][b]==2){
            answer = "SAT";
        } else if(c[a][b]==3){
            answer = "SUN";
        } else if(c[a][b]==4){
            answer = "MON";
        } else if(c[a][b]==5){
            answer = "TUE";
        } else if(c[a][b]==6){
            answer = "WED";
        } else {
            answer = "THU";
        }
        return answer;

        /*
        String answer = " ";
        int[] monthDay={31,29,31,30,31,30,31,31,30,31,30,31};
        for (int i = 1; i < a; i++) {
            b+=monthDay[i-1];
        }
        switch(b%7){
        case 3:answer="SUN";break;
        case 4:answer="MON";break;
        case 5:answer="TUE";break;
        case 6:answer="WED";break;
        case 0:answer="THU";break;
        case 1:answer="FRI";break;
        case 2:answer="SAT";break;
        }
        return answer;
         */
    }
}
