package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_11724 {
	static boolean[] check;
	static Queue<Integer> queue;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		queue = new LinkedList<Integer>(); 
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int result = 0;
		check = new boolean[N];
		int[][] arr = new int[N][N];
		int count = 0;
		while (count < M) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[y - 1][x - 1] = 1;
			arr[x - 1][y - 1] = 1;
			count++;
		} // ������� �ϼ�, x�� y�� ť�� ���� ��ǥ �ֱ�
		
		for(int i=0;i<arr.length;i++) {
			if(!check[i]) {
				check[i] = true; // �湮
				queue.add(i);
				bfs(i,arr);
				result++;
			}
		}
		System.out.println(result);
	}
	
	public static void bfs(int i,int[][] arr) {
		while(!queue.isEmpty()) {
			int front = queue.poll();
			for(int j=0;j<arr.length;j++) {
				if(!check[j] && arr[front][j]==1) {
					check[j] = true; // �湮
					queue.add(j);
				}
			}
			
		}
	}
}
