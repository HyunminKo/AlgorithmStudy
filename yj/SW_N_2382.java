package SWExpertAcademy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class N_2382 {
	static int N;
	static Queue<Thing> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			
			queue = new LinkedList<>();
			
			for(int i=0;i<K;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int num = sc.nextInt();
				int dir = sc.nextInt();
				queue.add(new Thing(x,y,num,dir));
			}
			
			int time = 0;
			while(time < M) {
				moveThing();
				time++;
			}
			
			int sum = 0;
			for(Thing t : queue) {
				sum += t.num;
			}

			System.out.println("#" + test_case + " " + sum);
		}
	}
	
	public static void moveThing() {
		PriorityQueue<Thing> temp_queue = new PriorityQueue<>();
		while(!queue.isEmpty()) {
			Thing t = queue.poll();

			if(t.dir == 1) { // 상
				t.x = t.x - 1;
			}else if(t.dir == 2) { // 하
				t.x = t.x + 1;
			}else if(t.dir == 3) { // 좌
				t.y = t.y - 1;
			}else { // 우
				t.y = t.y + 1;
			}
			
			if(t.x == 0 || t.x == N-1 || t.y == 0 || t.y == N-1) { // 약품 칠해진 셀
				// 미생물 죽이기
				t.num = t.num / 2;
				// 방향 바꾸기
				if(t.dir == 1) t.dir = 2;
				else if(t.dir == 2) t.dir = 1;
				else if(t.dir == 3) t.dir = 4;
				else t.dir = 3;
			}
			
			if(t.num > 0) { // 수가 0이면 군집 제거
				temp_queue.add(t); // 움직인 생물 넣기
			}
		}
		queue = grouping(temp_queue);
	}
	
	public static LinkedList<Thing> grouping(Queue<Thing> temp_queue){
		HashMap<String,Thing> hm = new HashMap<>();
		
		while(!temp_queue.isEmpty()) { // 같은 위치에 있는거 합치기
			Thing t1 = temp_queue.poll();
			String key1 = t1.x+","+t1.y;
			if(hm.containsKey(key1)) {
				Thing th = hm.get(key1);
				th.num = th.num + t1.num;
				hm.put(key1,th);
			}else{
				hm.put(key1,t1);				
			}
		}
		return new LinkedList<>(hm.values());
	}
	
	static public class Thing implements Comparable<Thing>{ // (상: 1, 하: 2, 좌: 3, 우: 4)
		int x,y,num,dir;
		
		public Thing(int x,int y,int num,int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Thing t) {
			// TODO Auto-generated method stub
			if(this.num > t.num) {
				return -1;
			}else if(this.num < t.num) {
				return 1;
			}
			return 0;
		}
	}
}
