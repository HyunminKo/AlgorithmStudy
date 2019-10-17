import java.util.*;

public class Hash_unfinished_player {

    public static void main(String[] args) {
        String a = solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});
        System.out.println(a);
    }

    public static String solution(String[] participant, String[] completion) {

        // 나의 최초 코드...
//        String answer = "";
//        HashMap<String, List<Boolean>> isCompleted_h = new HashMap<>();
//        for (int i = 0; i < participant.length; i++) {
//            if (isCompleted_h.containsKey(participant[i])) {
//                List ll = isCompleted_h.get(participant[i]);
//                ll.add(false);
//                isCompleted_h.put(participant[i], ll);
//            } else {
//                List<Boolean> l = new ArrayList<>();
//                l.add(false);
//                isCompleted_h.put(participant[i], l);
//            }
//        }
//
//        for (int i = 0; i < completion.length; i++) {
//            List<Boolean> l = isCompleted_h.get(completion[i]);
//            for (int j = 0; j < l.size(); j++) {
//                if (l.get(j) == false) {
//                    l.set(j,true);
//                    break;
//                }
//            }
//        }
//
//        for (int i = 0; i < participant.length; i++) {
//            List<Boolean> l = isCompleted_h.get(participant[i]);
//            for (int j = 0; j < l.size(); j++) {
//                if(l.get(j)==false) {
//                    answer = participant[i];
//                    break;
//                }
//            }
//        }
//        return answer;

        // 개선된 코드.
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);// 중복을 고려해서 getOrDefault 를 사용.
        for (String player : completion) hm.put(player, hm.get(player) - 1);// 완주한 선수는 0을 갖도록 설계

        for (Map.Entry<String, Integer> es : hm.entrySet()) {
            // 해쉬맵의 entrySet 을 사용한 이유는 hm에 저장된 key-value 쌍을 엔트리(키와 값의 결합)의 형태로 Set 에 저장하여 반환하기에
            // 굳이 hm.keySet() 통하여 현재 맵에 있는 모든 키를 가져오고 그것들을 이용해서 또 한번 get()을 통해 hm에 접근하여 해당 value 를
            // 가져오는 일은 너무 비효율적이라서 사용했다. get할 때마다 계속 HashMap을 search하니까...비효율적!
          if (es.getValue()!=0) {
                answer = es.getKey();
            }
        }
        return answer;

    }
}
