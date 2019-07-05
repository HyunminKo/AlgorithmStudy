package SWExpertAcademy;

import java.util.HashMap;
import java.util.LinkedList;
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
		Queue<Thing> temp_queue = new LinkedList<Thing>();
		while(!queue.isEmpty()) {
			Thing t = queue.poll();

			if(t.dir == 1) { // ��
				t.x = t.x - 1;
			}else if(t.dir == 2) { // ��
				t.x = t.x + 1;
			}else if(t.dir == 3) { // ��
				t.y = t.y - 1;
			}else { // ��
				t.y = t.y + 1;
			}
			
			if(t.x == 0 || t.x == N-1 || t.y == 0 || t.y == N-1) { // ��ǰ ĥ���� ��
				// �̻��� ���̱�
				t.num = t.num / 2;
				// ���� �ٲٱ�
				if(t.dir == 1) t.dir = 2;
				else if(t.dir == 2) t.dir = 1;
				else if(t.dir == 3) t.dir = 4;
				else t.dir = 3;
			}
			
			if(t.num != 0) { // ���� 0�̸� ���� ����
				temp_queue.add(t); // ������ ���� �ֱ�
			}
		}
		queue = grouping(temp_queue);
	}
	
	// ��ĥ �� ���� ū�� �������� ���ִ� �κ��� �������־�� ��
	public static LinkedList<Thing> grouping(Queue<Thing> temp_queue){
		HashMap<String,Thing> hm = new HashMap<>();
		HashMap<Integer,Thing> temp_hm = new HashMap<>();
		
		for(Thing t1: temp_queue) {
			String key1 = t1.x+","+t1.y;
			hm.put(key1, t1);
			for(Thing t2:temp_queue) {
				String key2 = t2.x+","+t2.y;
				if(hm.containsKey(key2)) {
					Thing choose = hm.get(key2);
					temp_hm.put(choose.num, choose);
				}
			}
			
			
		}
		
		for(Thing t : temp_queue) {
			
			String key = t.x+","+t.y;
			if(hm.containsKey(key)) { // �̹� ������
				Thing temp = hm.get(key); // ����
				if(temp.num > t.num) { // temp�� ���� ������ dir�� temp����
					t.dir = temp.dir;
				}
				t.num = temp.num + t.num;
			}
			hm.put(key,t); // �ʿ� �ֱ�
		}
		return new LinkedList<>(hm.values());
	}
	
	static public class Thing{ // (��: 1, ��: 2, ��: 3, ��: 4)
		int x,y,num,dir;
		
		public Thing(int x,int y,int num,int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
}
