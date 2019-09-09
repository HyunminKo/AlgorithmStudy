import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort_BiggestNumber implements Comparator<Num> {
    public static void main(String[] args) {
        String a = solution(new int[]{6, 10, 2});
    }

    public static String solution(int[] numbers) {

        List<Num> numList = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            numList.add(new Num(numbers[i]));
        }

        Sort_BiggestNumber sbn = new Sort_BiggestNumber();
        Collections.sort(numList, sbn);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < numbers.length; i++) {
            sb.append(numList.get(i).getNumber());
        }

        return sb.toString();
    }

    @Override
    public int compare(Num o1, Num o2) {
        int num1 = o1.getNumber();
        int num2 = o2.getNumber();
        while(num1 != 0){
            num1=num1%10;
            o1.setNumber(num1%10);
        }

        while(num2 != 0){
            num2=num2%10;
            o2.setNumber(num2%10);
        }

        return 0;
    }
}

class Num {

    int number;

    public Num(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
