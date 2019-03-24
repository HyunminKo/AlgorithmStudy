package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectedComponent {

    static int N, M;
    static List[] vertex;
    static boolean[] visited;
    static boolean[] inQueue;
    static Queue<Integer> q = new LinkedList<>();
    static int result = 0;


    public static void main(String[] args) {
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;
            line = br.readLine();
            String[] tokens = line.split(" ");
            N = Integer.parseInt(tokens[0]);
            M = Integer.parseInt(tokens[1]);

            vertex = new List[N];
            visited = new boolean[N];
            inQueue = new boolean[N];
            for(int i = 0; i<N; i++){
                LinkedList<Integer> list = new LinkedList<>();
                vertex[i] = list;
            }

            for(int i = 0; i< M; i++){
                line = br.readLine();
                tokens = line.split(" ");
                int v1 = Integer.parseInt(tokens[0]);
                int v2 = Integer.parseInt(tokens[1]);

                vertex[v1-1].add(v2-1);
                vertex[v2-1].add(v1-1);
            }

            solution();
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void solution(){

        for(int i = 0; i< N; i++){
            if(!visited[i] && !inQueue[i]) {
                visited[i] = true;
                inQueue[i] = true;
                List<Integer> list = vertex[i];
                for (int j = 0; j < list.size(); j++) {
                    q.add(list.get(j));
                    inQueue[j] = true;
                }
                while (!q.isEmpty()) {
                    int v = q.poll();
                    visited[v] = true;
                    List<Integer> tempList = vertex[v];
                    for (int j = 0; j < tempList.size(); j++) {
                        int tempV = tempList.get(j);
                        if (visited[tempV] || inQueue[tempV]) {
                            continue;
                        }else{
                            q.add(tempV);
                            inQueue[tempV] = true;
                        }
                    }
                }
                result++;
            }
        }
    }
}
