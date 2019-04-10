import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 무선충전 {
	static int time, numOfBC;
	static int[][] path,map;
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			time = sc.nextInt(); numOfBC= sc.nextInt();
			path = new int[2][time];
			map = new int[10][10];
			int result = 0;
			for(int i = 0 ; i < 2; i++) {				
				for(int j = 0 ; j < time;j++) {
					path[i][j] = sc.nextInt();
				}
			}
			ArrayList<BC> stations = new ArrayList<>();
			for(int i = 0 ; i < numOfBC ; i++) {
				int x = sc.nextInt()-1; int y = sc.nextInt()-1;
				int c = sc.nextInt(); int p = sc.nextInt();
				stations.add(new BC(y,x,c,p));
			}
			startBC(stations);
			Point A = new Point(0,0);
			Point B = new Point(9,9);
			result+= selectBC(A,B,stations);
			for(int i = 0 ; i < time; i++){
				A.x += dx[path[0][i]];
				A.y += dy[path[0][i]];
				B.x += dx[path[1][i]];
				B.y += dy[path[1][i]];

				result += selectBC(A,B,stations);
			}
			System.out.println(String.format("#%d %d",test_case,result));
		}
	}

	private static int selectBC(Point A, Point B, ArrayList<BC> stations) {
		int count = 0;
		ArrayList<Integer> bcOfA;
		ArrayList<Integer> bcOfB;
		bcOfA = scanBC(A,stations);
		bcOfB = scanBC(B,stations);
		if(bcOfA.size() == 0){
			if(bcOfB.size() != 0){
				int max = 0;
				for(int i = 0 ; i < bcOfB.size(); i++){
					BC bc = stations.get(bcOfB.get(i));
					int temp = bc.performance / (bc.numOfUser + 1);
					if(temp > max) {
						max = temp;
					}
				}
				count += max;
			}
		}
		else if(bcOfA.size() == 1){
			BC bcA = stations.get(bcOfA.get(0));
			bcA.numOfUser++;
			if(bcOfB.size() != 0){
				int max = 0;
				for(int i = 0 ; i < bcOfB.size(); i++){
					BC bc = stations.get(bcOfB.get(i));
					bc.numOfUser++;
					int temp = bc.performance / bc.numOfUser;
					temp += bcA.performance / bcA.numOfUser;
					bc.numOfUser--;
					if(temp > max) {
						max = temp;
					}
				}
				count += max;
			} else {
				count += bcA.performance / bcA.numOfUser;
			}
			bcA.numOfUser--; //초기화
		}else {
			int max = 0;
			for(int i = 0 ; i <bcOfA.size(); i++){
				BC bcA = stations.get(bcOfA.get(i));
				bcA.numOfUser++;
				int temp = 0;
				if(bcOfB.size() != 0){
					int index = getMaxIndex(stations, bcOfB);
					BC bcB = stations.get(index);
					bcB.numOfUser++;
					temp += bcB.performance / bcB.numOfUser;
					temp += bcA.performance / bcA.numOfUser;
					bcB.numOfUser--;
				}else {
					temp += bcA.performance / bcA.numOfUser;
				}
				if(max < temp){
					max = temp;
				}
				bcA.numOfUser--; //초기화
			}
			count += max;
		}
		return count;
	}

	private static int getMaxIndex(ArrayList<BC> stations, ArrayList<Integer> bcOfB) {
		int max = 0;
		int index = 0;
		for (int i = 0; i < bcOfB.size(); i++) {
			BC bcB = stations.get(bcOfB.get(i));
			int temp = bcB.performance / (bcB.numOfUser + 1);
			if (max < temp) {
				index = i;
				max = temp;
			}
		}
		return bcOfB.get(index);
	}

	private static ArrayList<Integer> scanBC(Point p, ArrayList<BC> stations) {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0 ; i < stations.size(); i++){
			BC station = stations.get(i);
			if(station.range.contains(p.x + " " + p.y)){
				result.add(i);
			}
		}
		return result;
	}

	private static void startBC(ArrayList<BC> stations) {
		for(BC bc : stations) {
			boolean[][] visited = new boolean[10][10];
			int[][] distance = new int[10][10];
			Queue<Point> q = new LinkedList<>();
			visited[bc.x][bc.y] = true;
			distance[bc.x][bc.y] = 0;
			q.offer(new Point(bc.x,bc.y));
			bc.range.add(bc.x + " " + bc.y);
			while(!q.isEmpty()) {
				Point p = q.poll();
				for(int i = 1; i <= 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if(nx<0 || ny<0 || nx>=10 || ny>=10 || visited[nx][ny]) continue;
					if(distance[p.x][p.y] + 1 > bc.coverage) continue;
					visited[nx][ny] = true;
					distance[nx][ny] = distance[p.x][p.y] + 1;
					bc.range.add(nx + " " + ny);
					q.offer(new Point(nx,ny));
				}
			}
			
		}
	}
	private static class BC{
		int x,y;
		int coverage;
		int performance;
		int numOfUser = 0;
		HashSet<String> range = new HashSet<>();
		
		public BC(int x, int y, int coverage, int performance) {
			this.x = x;
			this.y = y;
			this.coverage = coverage;
			this.performance = performance;
		}
		
	}
	private static class Point {
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}
//1
//60 4
//0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4
//1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1
//6 9 1 180
//9 3 4 260
//1 4 1 500
//1 3 1 230
