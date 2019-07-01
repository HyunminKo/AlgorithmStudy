public class 연습 {
    static int[] arr = {1,2,3,4,5};
    public static void main(String[] args) {
//        go(0,"");
//        String[] str = "12 2".split(" ");
//        System.out.println(str[0]);
//        System.out.println(str[1]);
          boolean[] visited = new boolean[5];
          go(0,0,visited);
    }

    private static void go(int index, int count, boolean[] visited) {
        if(count == 3){
            for(int i = 0 ; i < visited.length; i++){
                if(visited[i]){
                    System.out.print(i+ " ");
                }
            }
            System.out.println();
            return;
        }
        for(int i = index ; i < arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                go(i+1,count+1,visited);
                visited[i] = false;
            }
        }
    }
}
