package BaekJoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class N_17142 {
	static int[][] arr;
	static int dot[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 상하좌우
	static List<DOT> virus;
	static Queue<DOT> select_virus;
	static int[][] state;
	static int M;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		virus = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 2) virus.add(new DOT(i,j));
			}
		}
		go(0,"",0);
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
	}
	
	public static void go(int count,String str,int index) {
		if(count >= M){
			int check = bfs(str);
			if(check != -1) result = Integer.min(result, check);
            return;
        }
        for(int i = index ; i < virus.size(); i++){
            str += i + ",";
            go(count+1,str, i+1);
            String[] s = str.split(",");
            str = "";
            for(int a=0;a<s.length-1;a++) str += s[a] + ",";
        }
	}
	
	public static int bfs(String str) {
		select_virus = new LinkedList<>();
		state = new int[arr.length][arr[0].length];
		int count = 0;
		String[] temp = str.split(","); // state배열 설정
		// 벽은 -1 , 비활성 바이러스는 -2, 활성바이러스는 0
		for(int k=0;k<temp.length;k++) {
			DOT d = virus.get(Integer.parseInt(temp[k]));
			select_virus.add(d);
			state[d.x][d.y] = -5;
		}

		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(arr[i][j] == 2 && state[i][j] != -5) state[i][j] = -2;
				else if(arr[i][j] == 1) state[i][j] = -1;
			}
		}
		
		for(DOT d : select_virus) {
			state[d.x][d.y]= 0; 
		}
		
		while(true) {
			boolean check = checkEnd(temp);
			if(select_virus.isEmpty() && !check) {
				count = -1;
				break;
			}
			if(check) break;
			
			Queue<DOT> temp_virus = new LinkedList<>();
			while(!select_virus.isEmpty()) {
				DOT d = select_virus.poll();
				for(int i=0;i<4;i++) {
					int dx = d.x + dot[i][0];
					int dy = d.y + dot[i][1];
					
					if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length) continue;
					
					if(arr[dx][dy] == 2 && state[dx][dy] == -2) {
						state[dx][dy] = 0;
						temp_virus.add(new DOT(dx,dy));
					}
					if((arr[dx][dy] == 0 && state[dx][dy] == 0) || state[dx][dy] > state[d.x][d.y] + 1) {
						state[dx][dy] = state[d.x][d.y] + 1;
						temp_virus.add(new DOT(dx,dy));
					}
				}
			}
			count++;
			select_virus = temp_virus;
		}
		
		return count;
	}
	
	public static boolean checkEnd(String[] temp) {
		boolean check = true;

		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(arr[i][j] == 0 && state[i][j] == 0) {
					check = false;
					break;
				}
			}
		}

		return check;
	}

	public static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}
