package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N_17144 {
	static int[][] arr;
	static int dot[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 상하좌우
	static List<DOT> air_machine;
	static int result;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int T = sc.nextInt();
		arr = new int[R][C];
		air_machine = new ArrayList<>();
		for(int i = 0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == -1) air_machine.add(new DOT(i,j));
			}
		}
		for(int i=0;i<T;i++) {
			result = 0;
			diffusionDust();
			turnOnMachine();
		}
		System.out.println(result);
		
	}
	
	public static void diffusionDust() {
		int[][] temp = new int[arr.length][arr[0].length];
		for(int a=0;a<arr.length;a++) {
			for(int b=0;b<arr[0].length;b++) {
				if(arr[a][b] > 0) {
					int count = 0;
					int diffusion_amount = arr[a][b] / 5; 
					for(int i=0;i<dot.length;i++) {
						int dx = a + dot[i][0];
						int dy = b + dot[i][1];
						
						if(dx < 0 || dy < 0 || dx >= arr.length || dy >= arr[0].length || arr[dx][dy] == -1) continue;
						
						temp[dx][dy] += diffusion_amount; // 확산
						count++;
					}
					temp[a][b] += arr[a][b] - (diffusion_amount * count);					
				}
			}
		}
		for(int i=0;i<temp.length;i++) { // 배열 복사
			System.arraycopy(temp[i], 0, arr[i], 0, temp[i].length);
		}
	}
	
	public static void turnOnMachine() {
		int[][] temp = new int[arr.length][arr[0].length];
		for(int i=0;i<arr.length;i++) { // 배열 복사
			System.arraycopy(arr[i], 0, temp[i], 0, arr[i].length);
		}
		
		for(int i = 0;i<air_machine.size();i++) {
			int air_x = air_machine.get(i).x;
			int air_y = air_machine.get(i).y;
			int dir = 3;
			int dx = air_x;
			int dy = air_y;
			
			do {
				switch(dir) { // 0:상, 1:하, 2:좌, 3:우
					case 0:
						temp[dx-1][dy] = arr[dx][dy];
						dx -= 1;
						break;
					case 1:
						temp[dx+1][dy] = arr[dx][dy];
						dx += 1;
						break;
					case 2:
						temp[dx][dy-1] = arr[dx][dy];
						dy -= 1;
						break;
					case 3:
						temp[dx][dy+1] = arr[dx][dy];
						dy += 1;
						break;
				}
				
				// 예외처리
				if((dir == 0 && dx <= 0) || (dir == 1 && dx>= arr.length-1)) {
					dir = 2; // 좌
				}else if(dir == 2 && dy <= 0) {
					if(i == 0) {
						dir = 1; // 상
					}else {
						dir = 0; // 하
					}
				}else if(dir == 3 && dy >= arr[0].length-1) { 
					if(i == 0) {
						dir = 0; // 상
					}else {
						dir = 1; // 하
					}
				}
			}while(!(dx == air_x && dy == air_y));
			temp[air_x][air_y] = -1;
		}
		
		for(int i=0;i<temp.length;i++) { // 배열 복사
			for(int j=0;j<temp[0].length;j++) {
				if(temp[i][j] != -1) result += temp[i][j];
				arr[i][j] = temp[i][j];
			}
		}
	}
	
	public static class DOT{
		int x,y;
		
		public DOT(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}
