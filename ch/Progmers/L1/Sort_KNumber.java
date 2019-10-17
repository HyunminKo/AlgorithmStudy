import java.util.Arrays;

public class Sort_KNumber {

    public static void main(String[] args) {
        int[] a = solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int command_num = 0;
        while (command_num < commands.length) {
            int start = commands[command_num][0] - 1;
            int end = commands[command_num][1] - 1;
            int[] newArr = new int[end - start + 1];
            int indexToInsert = 0;
            for (int i = start; i <= end; i++) {
                newArr[indexToInsert++] = array[i];
            }
            Arrays.sort(newArr);
            int K = commands[command_num][2] - 1;
            answer[command_num++] = newArr[K];
        }
        return answer;
    }
}
