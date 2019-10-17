import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BF_MockTest {

    public static void main(String[] args) {
        int[] input = {1,3,2,4,2}; // 답안지
        int[] a = solution(input);
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static int[] solution(int[] answers) {

        //Rules
        int[] rule1 = {1, 2, 3, 4, 5};// 5
        int[] rule2 = {2, 1, 2, 3, 2, 4, 2, 5};// 8
        int[] rule3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};// 10

        int r1_l = 5;
        int r2_l = 8;
        int r3_l = 10;

        int r1_correct = 0;
        int r2_correct = 0;
        int r3_correct = 0;

        // 배열 to 리스트
        List<Integer> l = new ArrayList<Integer>();
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 0; i < answers.length; i++) {
            if (a == r1_l) {
                a = 0;
            }
            if (b == r2_l) {
                b = 0;
            }
            if (c == r3_l) {
                c = 0;
            }
            if (answers[i] == rule1[a])
                r1_correct++;
            if (answers[i] == rule2[b])
                r2_correct++;
            if (answers[i] == rule3[c])
                r3_correct++;
            a++;
            b++;
            c++;
        }

        Student[] student = {new Student(1, r1_correct), new Student(2, r2_correct), new Student(3, r3_correct)};
        Arrays.sort(student);

        int highestScoreId = student[2].getId();
        int highestScore = student[2].getScore();
        l.add(highestScoreId);

        for (int i = 1; i >= 0; i--) {
            if(student[i].getScore() == highestScore)
                l.add(student[i].getId());
            else
                break;
        }

        // 리스트 to 배열
        int[] answer = new int[l.size()];
        int size = 0;
        for (int temp : l) {
            answer[size++] = temp;
        }
        Arrays.sort(answer);
        return answer;
    }

    static class Student implements Comparable<Student> {
        Integer id;
        Integer score;

        public Student(Integer id, Integer score) {
            this.id = id;
            this.score = score;
        }

        public Integer getId() {
            return id;
        }

        public Integer getScore() {
            return score;
        }

        public int compareTo(Student comparePerson) { // override
            // ascending order
            return this.score - comparePerson.score;
            // descending order
            // return comparePerson.score - this.score;

        }

    }
}
