package kakao.Ha_2019;

import java.util.HashSet;
import java.util.Set;

public class Problem4 {
    public static void main(String[] args) {

        String[] s = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] q = {"fro??","????o", "fr???", "fro???", "pro?"};
        solution(s,q);
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        Set<String> wordSet = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            wordSet.add(words[i]);
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            String subQuery = "";
            int questionStartIdx = -1;
            int questionEndIdx = -1;
            int matching = 0;


            /*
            for (int j = 0; j < query.length(); j++) {
                if(query.charAt(j) != '?'){
                    subQuery+= query.charAt(j);
                    if(!frontQ){
                        questionStartIdx = j+1;
                    }
                    else{
                        if(flag == 0){
                            questionEndIdx = j-1;
                            flag = 1;
                        }

                    }
                }
                else{
                    if(j == 0){
                        questionStartIdx = 0;
                        frontQ = true;
                    }
                }
            }
*/

            if(query.charAt(0) == '?'){//?가 앞에 있음.
                questionStartIdx = 0;
                for (int j = 1; j < query.length(); j++) {
                    if(query.charAt(j) != '?'){
                        questionEndIdx = j-1;
                        break;
                    }
                }
            }else{
                questionEndIdx = query.length()-1;
                for (int j = 0; j < query.length(); j++) {
                    if(query.charAt(j) == '?'){
                        questionStartIdx = j;
                        break;
                    }
                }
            }

            System.out.println("start : " + questionStartIdx);
            System.out.println("end : " + questionEndIdx);

            System.out.println();


//            if(questionStartIdx != 0){
//                questionEndIdx = query.length()-1;
//            }

//            System.out.println("subQuery " + subQuery);
//            System.out.println("questionStartIdx " + questionStartIdx);
//            System.out.println("questionEndIdx " + questionEndIdx);

            for(String word : wordSet){
                if(word.length() != query.length()) continue;

                String s;
                if(questionStartIdx == 0){//? 가 앞에
                    s = word.substring(questionEndIdx+1, word.length());
                    subQuery =query.substring(questionEndIdx+1);
//                    System.out.println("test " + s);
                }
                else{// ?가 뒤에
                    s = word.substring(0,questionStartIdx);
                    subQuery = query.substring(0, questionStartIdx);
//                    System.out.println("test2 " + s);
                }
                if(s.equals(subQuery)) matching++;
            }
//            System.out.println("matching " + matching);
            answer[i] = matching;
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }

        return answer;
    }
}
