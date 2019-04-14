import java.util.*;

public class 원자소멸시뮬레이션 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int[][] map= new int[4004][4004];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1; test_case<=T; test_case++){
            int N = sc.nextInt();
            ArrayList<Atom> atoms = new ArrayList<>();
            map = new int[4004][4004];
            for(int i = 0 ; i < N; i++){
                int x = (sc.nextInt()+1000)*2; int y = (sc.nextInt()+1000)*2;
                int dir = sc.nextInt(); int energy = sc.nextInt();
                atoms.add(new Atom(x,y,dir,energy));
                map[x][y] += 1;
            }
            int result = go(atoms);
            System.out.println(String.format("#%d %d",test_case,result));
        }
    }

    private static int go(ArrayList<Atom> atoms) {
        int result = 0;
        while(atoms.size()!=0){
            ArrayList<Integer> outOfRange = new ArrayList<>();
            for(int i = 0 ; i < atoms.size(); i++){
                Atom atom = atoms.get(i);
                int prevX = atom.x; int prevY = atom.y;
                map[prevX][prevY] -= 1;
                atom.x += dx[atom.dir];
                atom.y += dy[atom.dir];
                if(atom.x < 0 || atom.y < 0 || atom.x > 4000 || atom.y > 4000){
                    outOfRange.add(i);
                    map[prevX][prevY] += 1;
                    continue;
                }
                map[atom.x][atom.y] += 1;
                if(map[atom.x][atom.y] > 1){
                    if(map[atom.x][atom.y] > 1000){
                        map[atom.x][atom.y] += 1;
                    }else {
                        map[atom.x][atom.y] += 1000;
                    }
                }
            }
            removeAtom(atoms, outOfRange);
            outOfRange = new ArrayList<>();
            for(int i = 0 ; i < atoms.size(); i++){
                Atom atom = atoms.get(i);
                if(map[atom.x][atom.y] > 1000){
                    if(map[atom.x][atom.y] == 1001){
                        map[atom.x][atom.y] = 1;
                    }
                    result+=atom.energy;
                    map[atom.x][atom.y] -=1;
                    outOfRange.add(i);
                }
            }
            removeAtom(atoms, outOfRange);
        }

        return result;
    }

    private static void removeAtom(ArrayList<Atom> atoms, ArrayList<Integer> outOfRange) {
        if (outOfRange.size() != 0) {
            Collections.sort(outOfRange, Collections.reverseOrder());
            for (int i = 0; i < outOfRange.size(); i++) {
                int index = outOfRange.get(i);
                atoms.remove(index);
            }
        }
    }

    private static class Atom {
        int x,y;
        int dir,energy;

        public Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
}