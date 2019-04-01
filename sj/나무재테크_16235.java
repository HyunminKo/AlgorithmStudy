package samsung;

import java.util.*;

public class 나무재테크_16235 {
    static int N,M,K;
    static int[][] farm = new int[11][11];
    static int[][] nutriment = new int[11][11];
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt(); K = sc.nextInt();
        for(int[] row: farm){
            Arrays.fill(row,5);
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                nutriment[i][j]=sc.nextInt();
            }
        }
        List<Tree> trees = new ArrayList<>();
        List<Tree> deadTrees = new ArrayList<>();
        for(int i = 0 ; i < M; i++){
            int x = sc.nextInt(); int y = sc.nextInt(); int age = sc.nextInt();
            trees.add(new Tree(new Point(x-1,y-1),age));
        }
        for(int i = 0 ; i < K; i++){
            Collections.sort(trees);
            spring(trees,deadTrees);
            summer(deadTrees);
            deadTrees.clear();
            fall(trees);
            winter();
        }
        System.out.println(trees.size());
    }
    private static void winter() {
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                farm[i][j] += nutriment[i][j];
            }
        }
    }

    private static void fall(List<Tree> trees) {
        List<Tree> temp = new LinkedList<>();
        for(Tree tree: trees){
            if(tree.age % 5 == 0){
                for(int i = 0 ; i < 8; i++){
                    int nx = tree.p.x + dx[i];
                    int ny = tree.p.y + dy[i];
                    if(nx < 0 || ny < 0 || nx>=N || ny>=N ) continue;
                    temp.add(new Tree(new Point(nx,ny),1));
                }
            }
        }
        trees.addAll(temp);
    }

    private static void summer(List<Tree> deadTrees) {
        for(Tree deadTree : deadTrees){
            int x = deadTree.p.x; int y = deadTree.p.y;
            int nutriment = deadTree.age / 2;
            farm[x][y] += nutriment;
        }
    }

    private static void spring(List<Tree> trees, List<Tree> deadTrees) {
        Iterator<Tree> it = trees.iterator();
        while(it.hasNext()){
            Tree tree = it.next();
            int x = tree.p.x; int y = tree.p.y;
            if(farm[x][y] - tree.age>= 0){
                farm[x][y] -= tree.age;
                tree.age++;
            }else {
                deadTrees.add(tree);
                it.remove();
            }
        }
    }

    public static class Point{
        public int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static class Tree implements Comparable<Tree>{
        public Point p;
        public int age;

        public Tree(Point p, int age) {
            this.p = p;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "p=" + p +
                    ", age=" + age +
                    '}';
        }
        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}