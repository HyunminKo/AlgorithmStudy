package BFSDFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_2234 {
	static WALL[][] arr;
	static int dot[][] = { { -1, 0}, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	static boolean[][] check;
	static int[][] num;
	static int count = 0;
	static int room_num = 0;
	static List<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new WALL[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int temp = sc.nextInt();
				arr[i][j] = toBinary(temp);
			}
		}
		
		num = new int[M][N];
		int max = 0; // 가장 넓은 방의 넓이
		check = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				count = 1;
				if(!check[i][j]) {
					++room_num;
					dfs(i,j);
					list.add(count);
				}
				max = Math.max(max, count);
			}
		}
		
		int remove_max = 0; // 제거 후 가장 넓은 방의 넓이
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				count = 0;
				remove_room(i,j);
				remove_max = Math.max(remove_max, count);
			}
		}
		
		System.out.println(room_num);
		System.out.println(max);
		System.out.println(remove_max);
	}
	
	public static void dfs(int x, int y) {
		check[x][y] = true;
		num[x][y] = room_num;
		for(int i=0;i<4;i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length) continue;
			
			if(!check[dx][dy] && arr[x][y].getDirection(i) == 0) {
				count++;
				dfs(dx,dy);
			}
		}
	}
	
	public static void remove_room(int x, int y) {
		for(int i=0;i<4;i++) {
			int dx = x + dot[i][0];
			int dy = y + dot[i][1];
			
			if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length) continue;
			
			if(arr[x][y].getDirection(i) == 1 && num[x][y] != num[dx][dy]) {
				count = list.get(num[x][y]-1) + list.get(num[dx][dy]-1);
			}
		}
	}

	public static WALL toBinary(int num) {
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		
		String str = String.format("%04d", Integer.parseInt(Integer.toBinaryString(num).toString()));
		// 벽이 있으면 1로
		if (str.charAt(0) == '1') {
			down = 1;
		}
		if (str.charAt(1) == '1') {
			right = 1;
		}
		if (str.charAt(2) == '1') {
			up = 1;
		}
		if (str.charAt(3) == '1') {
			left = 1;
		}

		return new WALL(up, down, left, right);
	}

	static class WALL {
		int up, down, left, right;

		public WALL(int up, int down, int left, int right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
		
		public int getDirection(int i) {
			switch(i) {
				case 0 :
					return up;
				case 1 :
					return down;
				case 2 : 
					return left;
				case 3 : 
					return right;
			}
			return -1;
		}
	}
}
