package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_5653 {
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // 상하좌우
	static int dist[][];
	static int[][] arr;
	static int[][] temp;
	static int K;
	static Queue<DT> queue;
	static boolean[][] now;
	static int[][] check; // 0 = 비활성, 1 = 활성, 2 = 죽음
	static boolean[][] live; // 활성화 된적 있으면 true
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			queue = new LinkedList<DT>();
			int N = sc.nextInt();
			int M = sc.nextInt();
			K = sc.nextInt();
			arr = new int[2*K][2*K];
			temp = new int[arr.length][arr[0].length];
			check = new int[arr.length][arr[0].length];
			for (int i = 0; i < N; i++) { // 배열 생성및 구분
				for (int j = 0; j < M; j++) {
					int ix = arr.length/2+i;
					int iy = arr[0].length/2+j;
					arr[ix][iy] = sc.nextInt();
					if(arr[ix][iy] != 0) {
						queue.add(new DT(ix,iy));						
					}
				}
			}
			bfs();
			System.out.println("#"+test_case+" "+getCount());
		}
	}
	
	public static int getCount() { // 활성화, 비활성화 개수 세기
		int count = 0;
		for (int i = 0; i < arr.length; i++) { // 배열 생성및 구분
			for (int j = 0; j < arr[0].length; j++) {
				if(check[i][j] < 2 && arr[i][j] > 0) count++;
			}
		}
		return count;
	}
	
	public static boolean isDup(Queue<DT> q,int dx,int dy) {
		for(DT d : q) {
			if(d.x == dx && d.y == dy) {
				return true;
			}
		}
		return false;
	}
	
	public static void bfs() {
		Queue<DT> q;
		int time = 0;
		while(time < K) {
			q = new LinkedList<DT>();
			now = new boolean[arr.length][arr[0].length];
			while (!queue.isEmpty()) {
				DT d = queue.poll();
				if(temp[d.x][d.y] == arr[d.x][d.y]-1 && check[d.x][d.y] == 0) { // 비활성이면 활성으로 켜줌
					check[d.x][d.y] = 1;			
					q.add(d);
				}else if(check[d.x][d.y] == 1) { // 활성일 경우
						for (int i = 0; i < dot.length; i++) {
							int dx = d.x + dot[i][0];
							int dy = d.y + dot[i][1];

							if(arr[dx][dy] == 0 || (now[dx][dy] && arr[d.x][d.y] > arr[dx][dy])) {
								now[dx][dy] = true;
								arr[dx][dy] = arr[d.x][d.y];
								if(!isDup(q,dx,dy)) q.add(new DT(dx,dy));
							}
						}
						if(temp[d.x][d.y] == 0) {
							check[d.x][d.y] = 2; // 죽음
						}else {
							temp[d.x][d.y] -= 1;
							q.add(d);
						}
				}else if(check[d.x][d.y] == 1 && temp[d.x][d.y] == 0) {
					check[d.x][d.y] = 2; // 죽음
				}else if(temp[d.x][d.y] < arr[d.x][d.y] && check[d.x][d.y] == 0){ // 활성되길 기다리는 중
					q.add(d);
					temp[d.x][d.y] += 1;
				}
			}
			queue = q;
			time++;
		}

}
}

class DT {
	int x,y;
	
	public DT(int x,int y) {
		this.x=x;
		this.y=y;
	}
}

