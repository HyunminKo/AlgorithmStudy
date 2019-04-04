package SWExpertAcademy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_1953 {
	static Tunnel[][] arr;
	static int[][] dist;
	static int dot[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } }; // »óÇÏÁÂ¿ì
	static Queue<Tunnel> queue;
	static int hour;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			result = 0;
			int N= sc.nextInt(); // ¼¼·Î
			int M =sc.nextInt(); // °¡·Î
			int MHx = sc.nextInt(); // ¸ÇÈ¦ ¼¼·Î ÁÂÇ¥
			int MHy = sc.nextInt(); // ¸ÇÈ¦ °¡·Î ÁÂÇ¥
			hour = sc.nextInt(); // °É¸° ½Ã°£
			arr = new Tunnel[N][M];
			dist = new int[N][M];
			queue = new LinkedList<Tunnel>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					int temp = sc.nextInt();
					arr[i][j] = setTunnel(i,j,temp);
					if(i == MHx && j == MHy) {
						queue.add(arr[i][j]); // ¸ÇÈ¦¶Ñ²± Áý¾î³Ö±â
						dist[i][j] = 1;
					}
				}
			}
			
			bfs();
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			Tunnel t = queue.poll();
			result++;
			
			if(dist[t.x][t.y] == hour) continue;
			
			for(int i=0;i<4;i++) {
				int tx = t.x + dot[i][0];
				int ty = t.y + dot[i][1];
				
				if(tx < 0 || ty < 0 || tx >= arr.length || ty >= arr[0].length) continue;


				if(dist[tx][ty] < 1 && dist[tx][ty] < hour && !isTunnelEmpty(arr[tx][ty]) && isGoTunnel(i, arr[t.x][t.y]) && isCombination(i, arr[tx][ty])) {
					dist[tx][ty] = dist[t.x][t.y]+ 1; 
					queue.add(arr[tx][ty]);
				}
			}
		}
	}
	
	public static boolean isTunnelEmpty(Tunnel t) { // ºñ¾îÀÖÀ¸¸é true
		return t.up == 0 && t.down == 0 && t.left == 0 && t.right == 0;
	}
	
	public static boolean isGoTunnel(int i,Tunnel t) { // ¶Õ¸°Áö ¸·ÈùÁö ÆÇ´Ü
		return (i == 0 && t.up != 0) || (i == 1 && t.left != 0) || (i == 2 && t.down != 0) || (i == 3 && t.right != 0);
	}
	
	public static boolean isCombination(int i,Tunnel t) { // ¶Õ¸°Áö ¸·ÈùÁö ÆÇ´Ü
		return (i == 0 && t.down != 0) || (i == 1 && t.right != 0) || (i == 2 && t.up != 0) || (i == 3 && t.left != 0);
	}
	
	public static Tunnel setTunnel(int i,int j,int n) {
		switch(n) {
			case 1 :
				return new Tunnel(i,j,1,1,1,1);
			case 2 :
				return new Tunnel(i,j,1,1,0,0);
			case 3 :
				return new Tunnel(i,j,0,0,1,1);
			case 4 :
				return new Tunnel(i,j,1,0,0,1);
			case 5 :
				return new Tunnel(i,j,0,1,0,1);
			case 6 :
				return new Tunnel(i,j,0,1,1,0);
			case 7 :
				return new Tunnel(i,j,1,0,1,0);
		}
		return new Tunnel(i,j,0,0,0,0);
	}
	
	static class Tunnel{
		int x,y,up,down,left,right;
		
		public Tunnel(int x,int y,int up,int down,int left,int right) {
			this.x = x;
			this.y = y;
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
	}
}
