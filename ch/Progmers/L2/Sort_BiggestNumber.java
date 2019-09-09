import java.util.*;

public class Sort_BiggestNumber {
    public static void main(String[] args) {
        String a = solution(new int[]{3, 30, 34, 5, 9});
        System.out.println(a);
    }

    public static String solution(int[] numbers) {

        String[] nums = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) { // simple case
                    return o2.compareTo(o1); // order by num desc
                }

                String sum = o1 + o2;
                String reverseSum = o2 + o1;

                return reverseSum.compareTo(sum);
            }
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(nums[i]);
        }

        return sb.toString();
    }


}

