import java.util.ArrayList;
		import java.util.HashSet;
		import java.util.LinkedList;
		import java.util.Queue;
		import java.util.Scanner;
		import java.util.Set;

public class 캐슬디펜스_17135 {
	static int N,M,D,result = 0;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt(); D = sc.nextInt();
		map = new int[N][M];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int set = (1 << M) - 1;
		for(int i = 0 ; i <= set; i++) {
			if(Integer.bitCount(i) == 3) {
				ArrayList<Point> hunter = new ArrayList<>();
				for(int j = 0 ; j < M ; j++) {
					if(((i >> j) & 1) == 1) {
						hunter.add(new Point(N-1,j));
					}
				}
				go(hunter);
			}
		}
		System.out.println(result);
	}
	private static void go(ArrayList<Point> hunter) {
		int[][] backup = new int[N][M];
		copyAToB(map,backup);
		int count = 0;
		int size = N;
		while(size-- > 0) {
			Set<String> hashSet = new HashSet<>();
			for(Point h : hunter) {
				if(map[h.x][h.y]==1) {
					hashSet.add(h.x+" "+h.y);
					continue;
				}
				boolean[][] visited = new boolean[N][M];
				int[][] distance = new int[N][M];
				Queue<Point> q = new LinkedList<>();
				visited[h.x][h.y] = true;
				distance[h.x][h.y] = 1;
				q.offer(new Point(h.x,h.y));
				ArrayList<Point> candidate = new ArrayList<>();
				while(!q.isEmpty()) {
					Point p = q.poll();
					for(int i = 0 ; i < 4; i++) {
						int nx = p.x + dx[i];
						int ny = p.y + dy[i];
						if(nx < 0 || ny < 0 || nx >= N || ny >= M || distance[p.x][p.y] + 1 > D || visited[nx][ny]) continue;
						visited[nx][ny] = true;
						distance[nx][ny] = distance[p.x][p.y] + 1;
						if(map[nx][ny] == 1) {
							candidate.add(new Point(nx,ny));
						}
						q.offer(new Point(nx,ny));
					}
				}
				int tempX=0x3f3f3f3f, tempY=0x3f3f3f3f, tempDis = 0x3f3f3f3f;
				for(int i = 0 ; i < candidate.size(); i++){
					Point p = candidate.get(i);
					int dis = Math.abs(h.x - p.x) + Math.abs(h.y - p.y);
					if(dis < tempDis){
						tempDis = dis;
						tempX = p.x;
						tempY = p.y;
					}else if(dis == tempDis){
						if(p.y < tempY){
							tempX = p.x;
							tempY = p.y;
						}
					}
				}
				if(candidate.size() != 0){
					hashSet.add(tempX+" "+tempY);
				}
			}
			count += hashSet.size();
			hashSet.forEach(key -> {
				String[] position = key.split(" ");
				map[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = 0;
			});
			down(map);
		}
		copyAToB(backup,map);
		result = Math.max(result, count);
	}
	private static void down(int[][] map) {
		for(int i = N - 1; i >=0 ; i--) {
			for(int j = 0 ; j < M; j++) {
				if(i == 0) {
					map[i][j] = 0;
				}else {
					map[i][j] = map[i-1][j];
				}
			}
		}
	}
	private static void printMap(int[][] map) {
		System.out.println();
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	private static void copyAToB(int[][] A, int[][] B) {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				B[i][j] = A[i][j];
			}
		}
	}
	private static class Point {
		int x,y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}
}