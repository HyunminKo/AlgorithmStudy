package SWExpert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Supply {

    static int[][] _matrix;
    static int _testCase;
    static int _n;
    static int _result = 0;
    static int[][] _minDist;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static Queue<Position> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = "";

        _testCase = Integer.parseInt(input.nextLine());

        for(int i = 0; i<_testCase; i++){
            _n = Integer.parseInt(input.nextLine());
            _matrix = new int[_n][_n];
            _minDist = new int[_n][_n];

            for(int j=0; j<_n;j++){
                line = input.nextLine();
                for(int k=0; k<_n; k++){
                    _matrix[j][k] = Character.getNumericValue(line.charAt(k));
                    _minDist[j][k] = Integer.MAX_VALUE;
                }
            }
            _minDist[0][0] = 0;//도착 지점 가장 큰 값으로.

            q.add(new Position(0,0));
            operation();
            _result = _minDist[_n-1][_n-1];

            System.out.println("#"+ (i+1) + " " + _result);

        }
    }

    static void operation(){
        while(!q.isEmpty()){
            Position p = q.poll();
            for(int i = 0; i <4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= _n || ny >= _n) continue;

                if(nx == _n-1 && ny == _n-1){
                    if(_minDist[nx][ny] > _minDist[p.x][p.y]) _minDist[nx][ny] = _minDist[p.x][p.y];
                }
                else{
                    int temp = _minDist[p.x][p.y]+_matrix[nx][ny];
                    if(temp < _minDist[nx][ny]){
                        _minDist[nx][ny] = temp;
                        q.add(new Position(nx, ny));
                    }
                }

            }
        }
    }

    static class Position{
        int x;
        int y;

        Position(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
}
/*
2
4
0100
1110
1011
1010
6
011001
010100
010011
101001
010101
111010


1
6
011001
010100
010011
101001
010101
111010
 */