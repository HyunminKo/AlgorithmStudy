public class TargetNumber {

    private int answer = 0;
    public int getAnswer() {
        return answer;
    }
    public void solution(int[] numbers, int target) {
        dfs(numbers, numbers[0], target, 0);
        dfs(numbers, -numbers[0], target, 0);
    }

    public void dfs(int[] numbers, int number, int target, int i) {

        if (i == numbers.length - 1 && number == target) {
            answer++;
        }
        if (i != numbers.length - 1)
            dfs(numbers, number + numbers[i + 1], target, i + 1);
        if (i != numbers.length - 1)
            dfs(numbers, number - numbers[i + 1], target, i + 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        int target = 3;
        TargetNumber s = new TargetNumber();
        s.solution(arr,target);
        System.out.println(s.getAnswer());
    }
}


//    public static void main(String[] args) {
//        int a = solution(new int[]{1, 1, 1, 1, 1}, 3);
//        System.out.println(a);
//    }
//
//    public static int solution(int[] numbers, int target) {
//        return DFS(numbers, target, 0, 0);
//    }
//
//    public static int DFS(int[] numbers, int target, int index, int num) {
//        if(index == numbers.length) {
//            return num == target ? 1 : 0;
//        } else {
//            return DFS(numbers, target, index + 1, num + numbers[index])
//                    + DFS(numbers, target, index + 1, num - numbers[index]);
//        }
//    }

//
//
//
//class Solution {
//    int answer = 0;
//
//    public int solution(int[] numbers, int target) {
//        dfs(numbers, numbers[0], target, 0);
//        dfs(numbers, -numbers[0], target, 0);
//        return answer;
//    }
//
//    public void dfs(int[] numbers, int number, int target, int i) {
//
//        if (i == numbers.length - 1 && number == target) {
//            answer++;
//        }
//        if (i != numbers.length - 1)
//            dfs(numbers, number + numbers[i + 1], target, i + 1);
//        if (i != numbers.length - 1)
//            dfs(numbers, number - numbers[i + 1], target, i + 1);
//    }
//}