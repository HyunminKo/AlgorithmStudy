public class AdderNum {

    public static void main(String[] args) {
        Long a = solution(5 ,3);
        System.out.println(a);
    }

    public static long solution(int a, int b) {
        return sumAtoB(Math.min(a, b), Math.max(b, a));
    }

    private static long sumAtoB(long a, long b) {
        return (b - a + 1) * (a + b) / 2;
    }
}
