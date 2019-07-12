package SWExpertAcademy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class N_2383 {
	static List<DOT> list; // 사람 저장
	static Stair[] stair; // 계단 저장
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			list = new ArrayList<>();
			stair = new Stair[2];
			result = Integer.MAX_VALUE;
			int stair_index = 0;
			for (int i = 0; i < N; i++) {
				for(int j=0;j<N;j++) {
					int number = sc.nextInt();
					if(number == 1) list.add(new DOT(i,j,0,0)); // 사람 모으기
					if(number > 1) stair[stair_index++] = new Stair(i,j,number); // 계단 모으기
				}
			}
			
			ArrayList<DOT> stair1 = new ArrayList<>();
			ArrayList<DOT> stair2 = new ArrayList<>();
			dfs(0,stair1,stair2);
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void dfs(int index,ArrayList<DOT> stair1,ArrayList<DOT> stair2) {
		if(index >= list.size()) {
			int time1 = 0;
			int time2 = 0;
			if(!stair1.isEmpty()) time1 = downStair(stair1,0); // 계단 1번 내려가기
			if(!stair2.isEmpty()) time2 = downStair(stair2,1); // 계단 2번 내려가기
			result = Math.min(Math.max(time1,time2), result);
			return;
		}
		
		DOT d = list.get(index); // 사람 꺼내기
		for(int i=0;i<2;i++) { // 계단 2개
			int time = Math.abs(d.x - stair[i].x) + Math.abs(d.y - stair[i].y); // 이동시간
			d.dist = time;
			if(i == 0) {
				stair1.add(d);
			}else {
				stair2.add(d);
			}
			dfs(index+1,stair1,stair2);
			if(stair1.contains(d)) stair1.remove(d);
			if(stair2.contains(d)) stair2.remove(d);
		}
	}
	
	public static int downStair(ArrayList<DOT> arraylist,int num) {
		PriorityQueue<DOT> pq = new PriorityQueue<DOT>(arraylist);
		Queue<DOT> queue = new LinkedList<>();
		int stair_num = stair[num].value;
		int first = pq.peek().dist -1; // 맨 처음 셋팅
		
		while(!pq.isEmpty() || !queue.isEmpty()) {
			Queue<DOT> temp_queue = new LinkedList<>();
			for(DOT d : queue) { // 계단 내려가기
				d.down += 1;
				if(d.down != stair_num) { // 계단 남았으면 다시 넣기
					temp_queue.add(d);
				}else { // 다 내려 갓으면 0으로 변경
					d.down = 0;
				}
			}
			queue = temp_queue;
			
			while(!pq.isEmpty() && queue.size() < 3) { // 한 계단 위에는 최대 3명만 가능
				DOT d = pq.poll(); // 계단 위로 가기
				if(d.dist + d.down <= first) {
					queue.add(d);
				}else {
					pq.add(d); // 아직 시간 안됏으니까 큐에 다시 넣어주고
					break; // 빠져나가기
				}
			}
			first++;
		}
		
		return first;
	}
	
	public static class DOT implements Comparable<DOT>{
		int x,y,dist,down;
		
		public DOT(int x,int y,int dist,int down) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.down = down;
		}

		@Override
		public int compareTo(DOT d) {
			// TODO Auto-generated method stub
			if(this.dist < d.dist) {
				return -1;
			}
			return 1;
		}
	}
	
	public static class Stair{
		int x,y,value;
		
		public Stair(int x,int y,int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
