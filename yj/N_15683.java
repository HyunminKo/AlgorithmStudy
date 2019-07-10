package BackJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_15683 {
	static int[][] dot = {{1,0},{0,1},{-1,0},{0,-1}};
	// 0:상, 1:우, 2:하, 3:좌
	static int[][] arr;
	static List<DOT> list; // cctv list
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N][M];
		result = Integer.MAX_VALUE;
		list = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] > 0 && arr[i][j] < 6) list.add(new DOT(i,j)); // cctv담기
			}
		}
		int[][] temp = new int[arr.length][arr[0].length];
		setArr(temp,arr);
		dfs(temp,0);
		System.out.println(result);
	}
	
	public static int getResult(int[][] temp) {
		int count = 0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(temp[i][j] == 0) count++;
			}
		}
		return count;
	}
	
	public static void dfs(int[][] temp,int count) {
		int[][] visit = new int[arr.length][arr[0].length];
		if(count >= list.size()) {
			result = Math.min(result, getResult(temp));
			return;
		}
		
		
		DOT d = list.get(count);
		if(arr[d.x][d.y] == 1) {
			for(int i=0;i<4;i++) {
				setArr(visit,temp);
				move(d.x,d.y,i,visit);
				dfs(visit,count+1);
			}
		}else if(arr[d.x][d.y] == 2) {
			for(int i=0;i<2;i++) {
				setArr(visit,temp);
				move(d.x,d.y,i,visit);
				move(d.x,d.y,i+2,visit);
				dfs(visit,count+1);
			}
		}else if(arr[d.x][d.y] == 3) {
			for(int i=0;i<4;i++) {
				setArr(visit,temp);
				move(d.x,d.y,i,visit);
				if(i == 3) {
					move(d.x,d.y,0,visit);
				}else {
					move(d.x,d.y,i+1,visit);					
				}
				dfs(visit,count+1);
			}
		}else if(arr[d.x][d.y] == 4) {
			for(int i=0;i<4;i++) {
				setArr(visit,temp);
				move(d.x,d.y,i,visit);
				if(i == 2) {
					move(d.x,d.y,i+1,visit);
					move(d.x,d.y,0,visit);
				}else if(i == 3) {
					move(d.x,d.y,0,visit);
					move(d.x,d.y,1,visit);
				}else {
					move(d.x,d.y,i+1,visit);
					move(d.x,d.y,i+2,visit);
				}
				dfs(visit,count+1);
			}
		}else if(arr[d.x][d.y] == 5) {
			setArr(visit,temp);
			move(d.x,d.y,0,visit);
			move(d.x,d.y,1,visit);
			move(d.x,d.y,2,visit);
			move(d.x,d.y,3,visit);
			dfs(visit,count+1);
		}
	}
	
	public static void setArr(int[][] visit,int[][] temp) {
		for(int i=0;i<temp.length;i++) {
			System.arraycopy(temp[i], 0, visit[i], 0, temp[0].length);
		}
	}
	
	public static void move(int x,int y,int index,int[][] visit) {
		int dx = x + dot[index][0];
		int dy = y + dot[index][1];
		
		if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || arr[dx][dy] == 6) return;
		
		visit[dx][dy] = -1; // 방문 표시
		move(dx,dy,index,visit); // 막힐때까지 계속 방문
	}
	
	public static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}
