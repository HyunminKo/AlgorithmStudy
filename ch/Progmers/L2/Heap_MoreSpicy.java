import java.util.*;

public class Heap_MoreSpicy {

    public static void main(String[] args) {
        int a = solution(new int[]{12,10,9,3,2,1}, 7);
        System.out.println(a);
    }

    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i = 0; i < scoville.length; i++)
            q.add(scoville[i]);

        int count = 0;
        while(q.size() > 1 && q.peek() < K){
            int weakHot = q.poll();
            int secondWeakHot = q.poll();

            int mixHot = weakHot + (secondWeakHot * 2);
            q.add(mixHot);
            count++;
            if(q.peek() >= K)
                return count;
        }

        return -1;
    }
}
